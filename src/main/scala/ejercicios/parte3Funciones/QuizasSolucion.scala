package ejercicios.parte3Funciones

import lecciones.parte3Funciones.MapFlatMapFilterFor

/**
 * Solución al ejercicio [[QuizasEnunciado]].
 */
object QuizasSolucion {

  def main(args: Array[String]): Unit = {
    val algo = Hay('a')

    println(algo.filter((c: Char) => c == 'b'))
    println(algo.filter((c: Char) => c == 'a'))

    println(algo.map((c: Char) => c.toUpper))
    println(algo.flatMap((c: Char) => Hay(c.byteValue)))
  }

  sealed abstract class Quizás[+T] {

    def map[B](transformador: T => B): Quizás[B]

    def flatMap[B](transformador: T => Quizás[B]): Quizás[B]

    def filter(evaluador: T => Boolean): Quizás[T]

  }

  final case class Hay[+T](valor: T) extends Quizás[T] {
    def map[B](transformador: T => B): Quizás[B] = Hay(transformador(valor))

    def flatMap[B](transformador: T => Quizás[B]): Quizás[B] = transformador(valor)

    def filter(evaluador: T => Boolean): Quizás[T] =
      if (evaluador(valor)) this
      else NoHay
  }

  case object NoHay extends Quizás[Nothing] {
    def map[B](transformador: Nothing => B): Quizás[B] = this

    def flatMap[B](transformador: Nothing => Quizás[B]): Quizás[B] = this

    def filter(evaluador: Nothing => Boolean): Quizás[Nothing] = this
  }
  
}

