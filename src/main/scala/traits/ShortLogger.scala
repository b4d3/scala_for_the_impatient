package traits

trait ShortLogger extends ConsoleLogger {
  var maxLength: Int

  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg else s"${msg.substring(0, 12)}...")
  }
}
