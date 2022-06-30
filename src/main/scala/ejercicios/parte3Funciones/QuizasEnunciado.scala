package ejercicios.parte3Funciones

import lecciones.parte3Funciones.MapFlatMapFilterFor

/**
 * Ejercicio de la parte teórica [[MapFlatMapFilterFor]].
 *
 * Una colección con una sólo elemento puede parecer que no tenga muchos usos.
 * Sin embargo, es lo que se utiliza para gestionar valores opcionales.
 * Tareas: 
 *   - Crear un trait Quizás[+T]
 *   - Crea un case class Hay que extienda Quizás
 *   - Crea un objeto NoHay para los valores que no existan
 *   - Implementa map, flatMap y filter
 *   - Experimenta en el main.
 *
 * Solución en [[QuizasSolucion]].
 */
object QuizasEnunciado {

  def main(args: Array[String]): Unit = {
    // Aquí tus pruebas
  }

  sealed abstract class Quizás[+T] {

    def map[B](transformador: T => B): Quizás[B]

    def flatMap[B](transformador: T => Quizás[B]): Quizás[B]

    def filter(evaluador: T => Boolean): Quizás[T]

  }

  final case class Hay[+T](valor: T) extends Quizás[T] {
    def map[B](transformador: T => B): Quizás[B] = ???

    def flatMap[B](transformador: T => Quizás[B]): Quizás[B] = ???

    def filter(evaluador: T => Boolean): Quizás[T] = ???
  }

  case object NoHay extends Quizás[Nothing] {
    def map[B](transformador: Nothing => B): Quizás[B] = ???

    def flatMap[B](transformador: Nothing => Quizás[B]): Quizás[B] = ???

    def filter(evaluador: Nothing => Boolean): Quizás[Nothing] = ???
  }

}

