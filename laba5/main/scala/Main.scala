object Main extends App {
  println ("Введите число")
  val x: Int = scala.io.StdIn.readInt()
  println ("Введите степень")
  val y: Int = scala.io.StdIn.readInt()
  val value: Int = 1
  val count: Int = 0

  println("(Хвостовая рекурсия) Ответ: " + sqr(x, y, value, count))
  println("(Простая рекурсия) Ответ: " + sqr1(x, y, value, count))
  def sqr(x:Int, y: Int, value: Int, count: Int): Int =
    if(count == y) {value}
    else{
      sqr(x, y, value * x ,count+1)
    }
  def sqr1(x:Int, y: Int, value: Int, count: Int): Int =
    if(count == y) {value}
    else{
      x * sqr1(x, y, value ,count+1)
    }
}

