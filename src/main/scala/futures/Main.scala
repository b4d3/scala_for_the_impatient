package futures

import java.util.concurrent.Executors

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

import scala.collection.concurrent.TrieMap

object Main extends App {

  /**
    * 1. Consider the expression
    *
    * for (n1 <- Future { Thread.sleep(1000) ; 2 }
    *
    * n2 <- Future { Thread.sleep(1000); 40 })
    *
    * println(n1 + n2)
    *
    * How is the expression translated to map and flatMap calls? Are the two futures executed
    * concurrently or one after the other? In which thread does the call to println occur?
    */
  for (n1 <- Future {
    Thread.sleep(1000)
    2
  };
       n2 <- Future {
         Thread.sleep(1000)
         40
       })
    println(n1 + n2)

  Future {
    Thread.sleep(1000)
    2
  } flatMap (n1 => Future {
    Thread.sleep(1000)
    40
  } map (n2 => println(n1 + n2)))


  /**
    * 2. Write a function doInOrder that, given two functions f: T => Future[U] and g: U =>
    * Future[V], produces a function T => Future[U] that, for a given t, eventually yields g(f(t)).
    */
  def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] = x => f(x) flatMap g

  /**
    * 3. Repeat the preceding exercise for any sequence of functions of type T => Future[T].
    */
  def doInOrderSeq[T, U, V](futures: Seq[T => Future[U]], g: U => Future[V]): T => Future[V] =
    (t: T) => {
      val futureSeq = for (f <- futures) yield f(t)
      val result: Future[U] = Future.firstCompletedOf(futureSeq)
      result flatMap g
    }

  //  val fg = doInOrderSeq(Array(f, f2), g)(2)

  /**
    * 4. Write a function doTogether that, given two functions f: T => Future[U] and g: U =>
    * Future[V], produces a function T => Future[(U, V)], running the two computations in
    * parallel and, for a given t, eventually yielding (f(t), g(t)).
    */
  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] = (t: T) =>
    for (first <- f(t);
         second <- g(t))
      yield (first, second)

  //  val res = doTogether(f, f2)(5)
  //  println(res)

  /**
    * 5. Write a function that receives a sequence of futures and returns a future that
    * eventually yields a sequence of all results.
    */
  def toResultSeq[T](seq: Seq[Future[T]]): Future[Seq[T]] = Future.sequence(seq)

  val f = (t: Int) => Future {
    t * 2.0
  }
  val f2 = (t: Int) => Future {
    t * 1.0
  }
  val g = (ft: Double) => Future {
    ft > 3
  }

  /**
    * 6. Write a method
    * Future[T] repeat(action: => T, until: T => Boolean)
    * *
    * that asynchronously repeats the action until it produces a value that is accepted by the until
    * predicate, which should also run asynchronously. Test with a function that reads a password
    * from the console, and a function that simulates a validity check by sleeping for a second
    * and then checking that the password is "secret". Hint: Use recursion.
    */
  def repeat[T](action: => T, until: T => Boolean): Future[T] = {

    val f = for (value <- Future {
      action
    };
                 cond <- Future {
                   until(value)
                 })
      yield (value, cond)

    val g = Await.result(f, 10.seconds)
    if (g._2) Future.successful(g._1) else repeat(action, until)
  }

  //  repeat(scala.io.StdIn.readLine(), (s: String) => {
  //    Thread.sleep(1000); s.equals("secret")
  //  })


  /**
    * 7. Write a program that counts the prime numbers between 1 and n, as reported by BigInt
    * .isProbablePrime. Divide the interval into p parts, where p is the number of available
    * processors. Count the primes in each part in concurrent futures and combine the results.
    */
  def countPrimes(range: Range): Int = {

    def countSubset(range: Range): Int = {
      val bits = for (i <- range if BigInt(i).isProbablePrime(1)) yield 1
      bits.sum
    }

    val p = if (Runtime.getRuntime.availableProcessors > range.end) range.end else Runtime.getRuntime.availableProcessors

    val futures = new collection.mutable.ListBuffer[Future[Int]]

    for (i <- 0 until p) {
      val part: Int = math.ceil(range.end.asInstanceOf[Double] / p).asInstanceOf[Int]
      val start = range.start + part * i
      val end = if (start + part > range.end) range.end else start + part
      futures += Future {
        countSubset(start until end)
      }
    }

    val result = Future.sequence(futures)
    val ss = Await.result(result, 10.seconds)
    ss.sum
  }

  //  println(countPrimes(1 to 20))


  def getWebContent(url: String): Future[String] = Future {
    io.Source.fromURL(url)("ISO-8859-1").mkString
  }

  def getUrlFromUser: Future[String] = Future {
    io.StdIn.readLine()
  }

  /**
    * 8. Write a program that asks the user for a URL, reads the web page at that URL, and
    * displays all the hyperlinks. Use a separate Future for each of these three steps.
    */
  def parseContentForLinks(content: String): Future[Seq[String]] = Future {
    val pattern = """href="(.*?)"""".r
    val parsed = for (href <- pattern findAllMatchIn content) yield href.group(1)
    parsed.toSeq.filter(_.startsWith("http"))
  }

  /**
    * 9. Write a program that asks the user for a URL, reads the web page at that URL, finds all
    * the hyperlinks, visits each of them concurrently, and locates the Server HTTP header for
    * each of them. Finally, print a table of which servers were found how often. The futures
    * that visit each page should return the header.
    */
  def getHeadersForLink(link: String): Future[String] = {
    val p = Promise[String]()

    Future {
      val headers = (new DefaultHttpClient).execute(new HttpGet(link)).getAllHeaders.toString
      p.success(headers)
    }

    p.future
  }


  /**
    * 10. Change the preceding exercise where the futures that visit each header update a shared
    * Java ConcurrentHashMap or Scala TrieMap. This isn’t as easy as it sounds. A threadsafe data
    * structure is safe in the sense that you cannot corrupt its implementation, but you have to
    * make sure that sequences of reads and updates are atomic.
    */
  def getHeaderForEachLink(links: Seq[String], table: TrieMap[String, Int]): Future[TrieMap[String, Int]] = Future {
    links.foreach { link =>
      Future {
        val headerNames = for (header <- (new DefaultHttpClient).execute(new HttpGet(link)).getAllHeaders)
          yield header.getName

        for ((name, names) <- headerNames.groupBy(a => a)) {
          if (table.contains(name))
            table(name) = table(name) + names.length
          else table(name) = names.length
        }

      }
    }
    table
  }

  //  val table = new TrieMap[String, Int]()
  //
  //  val parsedHrefs = for (s <- getUrlFromUser;
  //                         c <- getWebContent(s);
  //                         p <- parseContentForLinks(c);
  //                         h <- getHeaderForEachLink(p, table))
  //    yield h
  //
  //  println(Await.result(parsedHrefs, 100.seconds))

  /**
    * 11. Using futures, run four tasks that each sleep for ten seconds and then print the
    * current time. If you have a reasonably modern computer, it is very likely that it reports
    * four available processors to the JVM, and the futures should all complete at around the
    * same time. Now repeat with forty tasks. What happens? Why? Replace the execution context
    * with a cached thread pool. What happens now? (Be careful to define the futures after
    * replacing the implicit execution context.)
    */
  val pool = Executors.newCachedThreadPool()
  implicit val ec = ExecutionContext.fromExecutor(pool)

  val futures: Seq[Future[Unit]] = for (i <- 1 to 40) yield Future {
    Thread.sleep(10000);
    println(System.currentTimeMillis())
  }

  Await.result(Future.sequence(futures), 15.seconds)


  /**
    * 12. Write a method that, given a URL, locates all hyperlinks, makes a promise for each of
    * them, starts a task in which it will eventually fulfill all promises, and returns a
    * sequence of futures for the promises. Why would it not be a good idea to return a sequence
    * of promises?
    */
  private def promiseLink: Seq[Future[String]] = {

    val parsedHrefs = for (s <- getUrlFromUser;
                           c <- getWebContent(s);
                           p <- parseContentForLinks(c))
      yield p

    val links = Await.result(parsedHrefs, 100.seconds)

    for (link <- links) yield getHeadersForLink(link)

  }

  //  val promiseLinks = Await.result(Future.sequence(promiseLink), 100.seconds)
  //
  //  promiseLink foreach println


  /**
    * 13. Use a promise for implementing cancellation. Given a range of big integers, split the
    * range into subranges that you concurrently search for palindromic primes. When such a prime
    * is found, set it as the value of the future. All tasks should periodically check whether
    * the promise is completed, in which case they should terminate.
    */
  def findPalindromicPrime(range: Range): Future[BigInt] = {

    def findPalindromicPrimeInSubset(range: Range, promise: Promise[BigInt]): Unit = {
      while (!promise.isCompleted) {
        for (i <- range if BigInt(i).isProbablePrime(1)) {
          val bigIntString = BigInt(i).toString()
          if (bigIntString == bigIntString.reverse) promise.success(BigInt(i))
        }
      }
    }

    @volatile var promise = Promise[BigInt]()

    val p = if (Runtime.getRuntime.availableProcessors > range.end) range.end else Runtime.getRuntime.availableProcessors

    val futures = new collection.mutable.ListBuffer[Future[Int]]

    for (i <- 0 until p) {
      val part: Int = math.ceil(range.end.asInstanceOf[Double] / p).asInstanceOf[Int]
      val start = range.start + part * i
      val end = if (start + part > range.end) range.end else start + part
      Future {
        findPalindromicPrimeInSubset(start until end, promise)
      }
    }

    promise.future
  }

  val palindromicPrime = Await.result(findPalindromicPrime(51513515 to 124515159), 100.seconds)

  println(palindromicPrime)
}
