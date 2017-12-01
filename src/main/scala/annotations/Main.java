package annotations;

public class Main {

    public static void main(String args[]) {

        CallFromJava callFromJava = new CallFromJava();
        System.out.println(callFromJava.sum(1, 2, 3));

        try {
            System.out.println(callFromJava.allLines("myfil.txt"));

        } catch (Exception e) {
            System.out.println("Can't open/read file");
        }
    }
}
