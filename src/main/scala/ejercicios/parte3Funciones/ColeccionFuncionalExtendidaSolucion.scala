package ejercicios.parte3Funciones

import scala.annotation.targetName

/**
 * Solución a los ejercicios de [[ColeccionFuncionalExtendidaEnunciado]].
 */
object ColeccionFuncionalExtendidaSolucion {


  def main(args: Array[String]): Unit = {

    // Esto son unas pequeñas computaciones para demostrar que los métodos funcionan bien.

    val unaLista = LlenaFuncionalExtendida[Short](1, LlenaFuncionalExtendida[Short](2, LlenaFuncionalExtendida[Short](3, VaciaFuncionalExtendia)))

    val otraLista = LlenaFuncionalExtendida[Short](100, VaciaFuncionalExtendia)
    println(unaLista.elementos)
    println(otraLista.elementos)

    val dosListas = unaLista ++ otraLista

    println("----------- métodos funcionales ------------")
    dosListas.foreach(println)
    unaLista.sort((x, y) => x - y).foreach(println)

    println(dosListas.sort((x, y) => y - x).toString)

    val unListaPorDos = unaLista.map(_ * 2)
    println(unaLista.toString)
    println(unListaPorDos.toString)

    println(unaLista.zipWith(unListaPorDos, (x: Short, y: Int) => (x, y)).toString)
    println(unaLista.zipWith(unListaPorDos, (x: Short, y: Int) => x * y).toString)

    println(unaLista.fold(0)(_ + _))
  }

  sealed abstract class ColecciónFuncionalExtendida[+A] {

    def fold[B](valorInicial: B)(function: (B, A) => B): B

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

    @targetName("concatenar")
    def ++[B >: A](otraColección: ColecciónFuncionalExtendida[B]): ColecciónFuncionalExtendida[B]

    def elementos: String

    override def toString: String = s"[$elementos]"
  }

  final case class LlenaFuncionalExtendida[A](cabeza: A, cola: ColecciónFuncionalExtendida[A]) extends ColecciónFuncionalExtendida[A] {

    def estáVacía: Boolean = false

    def añadir[B >: A](elemento: B): LlenaFuncionalExtendida[B] = LlenaFuncionalExtendida[B](elemento, this)

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
      new LlenaFuncionalExtendida[B](cabeza, cola ++ otraColección)
    }

    def elementos: String = s"$cabeza, ${cola.elementos}"

    def fold[B](valorInicial: B)(function: (B, A) => B): B = cola.fold(function(valorInicial, cabeza))(function)

    def zipWith[B, C](otraColección: ColecciónFuncionalExtendida[B], funciónCremallera: (A, B) => C): ColecciónFuncionalExtendida[C] = {
      if (otraColección.estáVacía) throw new RuntimeException("List do not have the same number of fields!")
      else new LlenaFuncionalExtendida[C](funciónCremallera(cabeza, otraColección.cabeza), cola.zipWith(otraColección.cola, funciónCremallera))

    }


    def sort(funciónDeOrdenación: (A, A) => Int): ColecciónFuncionalExtendida[A] = {
      def insertar(elemento: A, listaOrdenada: ColecciónFuncionalExtendida[A]): LlenaFuncionalExtendida[A] =
        if (listaOrdenada.estáVacía) LlenaFuncionalExtendida[A](elemento, VaciaFuncionalExtendia)
        else if (funciónDeOrdenación(elemento, listaOrdenada.cabeza) <= 0) LlenaFuncionalExtendida[A](elemento, listaOrdenada)
        else new LlenaFuncionalExtendida[A](listaOrdenada.cabeza, insertar(elemento, listaOrdenada.cola))

      val sortedTail = cola.sort(funciónDeOrdenación)
      insertar(cabeza, sortedTail)
    }


    def foreach(función: A => Unit): Unit = {
      función(cabeza)
      cola.foreach(función)
    }
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

    def fold[B](valorInicial: B)(function: (B, Nothing) => B): B = valorInicial

    def zipWith[B, C](otraColección: ColecciónFuncionalExtendida[B], funciónCremallera: (Nothing, B) => C): ColecciónFuncionalExtendida[C] = VaciaFuncionalExtendia

    def sort(funciónDeOrdenación: (Nothing, Nothing) => Int): ColecciónFuncionalExtendida[Nothing] = VaciaFuncionalExtendia

    def foreach(función: Nothing => Unit): Unit = ()

  }

}
