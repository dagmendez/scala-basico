package ejercicios.parte3Funciones

/**
 * Solución a los ejercicios de [[HOFsYCurriesEnunciado]] .
 * */
object HOFsYCurriesSolucion {

  def main(args: Array[String]): Unit = { // Estos son algunos ejemplos para verificar que los métodos funcionan correctamente.

    println(restar(10, 5))
    println(restaCurrificada(10)(5))

    println(descurrificar(restaCurrificada)(10, 5))

    println(componer[String, String, String](_.toLowerCase(), _.substring(7, 10))("Hola, ¿qué tal?"))
    println(componer((x: String) => x.toLowerCase(), (y: String) => y.substring(7, 10))("Hola, ¿qué tal?"))
    println(yDespués[Int, Int, Int](_ * 2, _ + 1)(10))
    println(yDespués[Int, Int, Int](_ + 1, _ * 2)(10))
    println(yDespués((x: Int) => x + 1, (y: Int) => y * 2)(10))
  }

  def restaCurrificada: Int => Int => Int = currificar(restar)

  def currificar[A](f: (A, A) => A): A => A => A = x => y => f(x, y)

  def restar: (Int, Int) => Int = (x, y) => x - y

  def descurrificar[A](f: A => A => A): (A, A) => A = (x, y) => f(x)(y)

  def componer[A, B, T](f: A => B, g: T => A): T => B = x => f(g(x))

  def yDespués[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

}
