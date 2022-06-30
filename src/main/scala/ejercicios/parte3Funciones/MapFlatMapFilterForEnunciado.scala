package ejercicios.parte3Funciones

import ejercicios.parte3Funciones.ColeccionFuncionalExtendidaSolucion.ColecciónFuncionalExtendida
import lecciones.parte3Funciones.MapFlatMapFilterFor


/**
 * Ejercicio de la parte teórica [[MapFlatMapFilterFor]].
 * Tareas:
 *   - Comprobar que podemos utilizar for comprehension en instancias de [[ColecciónFuncionalExtendida]]
 *   - Implementar una colección con un solo elemento que pueda hacer for comprehension.
 *
 * Solución disponible en [[MapFlatMapFilterForSolucion]].
 *
 * @note El método filter no funciona en las for comprehension porque en realidad se utiliza withFilter.
 */
object MapFlatMapFilterForEnunciado {

  def main(args: Array[String]): Unit = {
    // Import necesario para la primera tarea
    import ejercicios.parte3Funciones.ColeccionFuncionalExtendidaSolucion.*

  }


  sealed abstract class ColecciónConUnSoloElemento[+T] {

    def fold[B](valorInicial: B)(function: (B, T) => B): B

    def zipWith[B, C](otra: ColecciónConUnSoloElemento[B], funciónZipper: (T, B) => C): ColecciónConUnSoloElemento[C]

    def foreach(función: T => Unit): Unit

    def cabeza: T

    def estáVacía: Boolean

    def map[B](transformador: T => B): ColecciónConUnSoloElemento[B]

    def filter(evaluador: T => Boolean): ColecciónConUnSoloElemento[T]

    def flatMap[B](transformador: T => ColecciónConUnSoloElemento[B]): ColecciónConUnSoloElemento[B]

    def elemento: String

    override def toString: String = s"[$elemento]"
  }


  case class ColecciónConUnSoloElementoLlena[T](cabeza: T) extends ColecciónConUnSoloElemento[T] {

    def estáVacía: Boolean = ???

    def map[B](transformador: T => B): ColecciónConUnSoloElementoLlena[B] = ???

    def filter(evaluador: T => Boolean): ColecciónConUnSoloElemento[T] = ???

    def flatMap[B](transformer: T => ColecciónConUnSoloElemento[B]): ColecciónConUnSoloElemento[B] = ???

    def elemento: String = ???

    def fold[B](valorInicial: B)(function: (B, T) => B): B = ???

    def foreach(función: T => Unit): Unit = ???

    def zipWith[B, C](otra: ColecciónConUnSoloElemento[B], funciónZipper: (T, B) => C): ColecciónConUnSoloElemento[C] = ???
  }

  object ColecciónConUnSoloElementoVacía extends ColecciónConUnSoloElemento[Nothing] {

    def cabeza: Nothing = ???

    def estáVacía: Boolean = ???

    def filter(evaluador: Nothing => Boolean): ColecciónConUnSoloElemento[Nothing] = ???

    def map[B](transformador: Nothing => B): ColecciónConUnSoloElemento[B] = ???

    def flatMap[B](transformer: Nothing => ColecciónConUnSoloElemento[B]): ColecciónConUnSoloElemento[B] = ???

    def elemento: String = ???

    def fold[B](valorInicial: B)(function: (B, Nothing) => B): B = ???

    def foreach(función: Nothing => Unit): Unit = ???

    def zipWith[B, C](otra: ColecciónConUnSoloElemento[B], funciónZipper: (Nothing, B) => C): ColecciónConUnSoloElemento[C] = ???

  }

}
