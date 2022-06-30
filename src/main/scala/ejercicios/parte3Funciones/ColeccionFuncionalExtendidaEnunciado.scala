package ejercicios.parte3Funciones

import lecciones.parte3Funciones.HOFsYCurries

import scala.annotation.targetName

/**
 * Ejercicios de la parte teórica [[HOFsYCurries]].
 *
 * Añadir nuevas funcionalidades:
 *    - foreach: A => Unit  
 *    - sort: ((A,A)=> Int) => MiColeccionFuncionalExtendida[A]
 *    - zipWith: (list, (A, A) => B) => MiColeccionFuncionalExtendida[B]
 *    - fold: (start)(function) => value
 *
 * Soluciones en [[ColeccionFuncionalExtendidaSolucion]]
 */
object ColeccionFuncionalExtendidaEnunciado {


  def main(args: Array[String]): Unit = { 
    // Ejecuta aquí tus pruebas
  }


  sealed abstract class ColecciónFuncionalExtendida[+A] {

    def fold[B](valorInicial: B)(función: (B, A) => B): B

    def zipWith[B, C](otraColección: ColecciónFuncionalExtendida[B], funciónCremallera: (A, B) => C): ColecciónFuncionalExtendida[C]

    def sort(funciónDeOrdenación: (A, A) => Int): ColecciónFuncionalExtendida[A]

    def foreach(función: A => Unit): Unit

    def cabeza: A

    def cola: ColecciónFuncionalExtendida[A]

    def estáVacía: Boolean

    def añadir[B >: A](elemento: B): ColecciónFuncionalExtendida[B]

    def map[B](funciónTransformadora: A => B): ColecciónFuncionalExtendida[B]

    def filter(funciónEvaluadora: A => Boolean): ColecciónFuncionalExtendida[A]

    def flatMap[B](funciónTransformadora: A => ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B]

    @targetName("concatenar") def ++[B >: A](otraColección: ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B]

    def elementos: String

    override def toString: String = s"[$elementos]"
  }

  final case class LlenaFuncionalExtendida[A](cabeza: A, cola: ColecciónFuncionalExtendida[A]) extends ColecciónFuncionalExtendida[A] {

    def estáVacía: Boolean = false

    def añadir[B >: A](elemento: B): LlenaFuncionalExtendida[B] = new LlenaFuncionalExtendida[B](elemento, this)


    def map[B](funciónTransformadora: A => B): ColecciónFuncionalExtendida[B] = {
      new LlenaFuncionalExtendida[B](funciónTransformadora(cabeza), cola.map(funciónTransformadora))
    }

    def filter(funciónEvaluadora: A => Boolean): ColecciónFuncionalExtendida[A] = {
      if (funciónEvaluadora(cabeza)) LlenaFuncionalExtendida[A](cabeza, cola.filter(funciónEvaluadora))
      else cola.filter(funciónEvaluadora)
    }

    def flatMap[B](funciónTransformadora: A => ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B] = {
      funciónTransformadora(cabeza) ++ cola.flatMap(funciónTransformadora)
    }

    @targetName("concatenar")
    def ++[B >: A](otraColección: ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B] = {
      LlenaFuncionalExtendida[B](cabeza, cola ++ otraColección)
    }

    def elementos: String = s"$cabeza, ${cola.elementos}"

    def fold[B](valorInicial: B)(función: (B, A) => B): B = ???

    def zipWith[B, C](otraColección: ColecciónFuncionalExtendida[B],
                      funciónCremallera: (A, B) => C): ColecciónFuncionalExtendida[C] = ???


    def sort(funciónDeOrdenación: (A, A) => Int): ColecciónFuncionalExtendida[A] = {
      // Pista: Utiliza recursión
      ???
    }

    def foreach(función: A => Unit): Unit = ???
  }

  case object VaciaFuncionalExtendia extends ColecciónFuncionalExtendida[Nothing] {
    def cabeza: Nothing = throw new NoSuchFieldError

    def cola: ColecciónFuncionalExtendida[Nothing] = throw new NoSuchFieldError

    def añadir[B >: Nothing](elemento: B): LlenaFuncionalExtendida[B] = LlenaFuncionalExtendida[B](elemento, this)

    def estáVacía: Boolean = true

    def filter(funciónEvaluadora: Nothing => Boolean): ColecciónFuncionalExtendida[Nothing] = this

    def map[B](funciónTransformadora: Nothing => B): ColecciónFuncionalExtendida[B] = this

    def flatMap[B](funciónTransformadora: Nothing => ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B] = this

    @targetName("concatenar")
    def ++[B >: Nothing](otraColección: ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B] = otraColección

    def elementos: String = ""

    def fold[B](valorInicial: B)(función: (B, Nothing) => B): B = ???

    def zipWith[B, C](otraColección: ColecciónFuncionalExtendida[B],
                      funciónCremallera: (Nothing, B) => C): ColecciónFuncionalExtendida[C] = ???

    def sort(funciónDeOrdenación: (Nothing, Nothing) => Int): ColecciónFuncionalExtendida[Nothing] = ???

    def foreach(función: Nothing => Unit): Unit = ???

  }


}
