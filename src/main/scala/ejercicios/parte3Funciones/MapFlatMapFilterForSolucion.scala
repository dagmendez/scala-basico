package ejercicios.parte3Funciones

import ejercicios.parte3Funciones.ColeccionFuncionalExtendidaSolucion._


/**
 * Solución de los ejercicios de [[MapFlatMapFilterForEnunciado]].
 */
object MapFlatMapFilterForSolucion {

  def main(args: Array[String]): Unit = {

    val colecciónDeNúmeros = LlenaFuncionalExtendida(5,
      LlenaFuncionalExtendida(4,
        LlenaFuncionalExtendida(3,
          LlenaFuncionalExtendida(2,
            LlenaFuncionalExtendida(1, VaciaFuncionalExtendia)))))

    val colecciónDeNúmerosNegativos = for {
      c <- colecciónDeNúmeros
      positive = scala.math.abs(c)
    } yield positive * -1

    println(colecciónDeNúmerosNegativos.toString)


    for {
      nc <- colecciónDeNúmerosNegativos.filter(_ % 2 == 0)
    } println(nc)


    val unaListaDeUnElemento = ColecciónConUnSoloElementoLlena[Int](1)
    val otraListaDeUnElemento = ColecciónConUnSoloElementoLlena[Int](2)

    val map = for {
      s <- unaListaDeUnElemento
      string = s"$s"
    } yield Map[Int, String](s -> string)

    println(map)
    println(unaListaDeUnElemento.fold(100)(_+_))
    unaListaDeUnElemento.zipWith(otraListaDeUnElemento,((x, y)=> x+1)).foreach(println)
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

    def estáVacía: Boolean = false

    def map[B](transformador: T => B): ColecciónConUnSoloElementoLlena[B] = ColecciónConUnSoloElementoLlena[B](transformador(cabeza))

    def filter(evaluador: T => Boolean): ColecciónConUnSoloElemento[T] = {
      if (evaluador(cabeza)) this
      else ColecciónConUnSoloElementoVacía
    }

    def flatMap[B](transformer: T => ColecciónConUnSoloElemento[B]): ColecciónConUnSoloElemento[B] = transformer(cabeza)


    def elemento: String = s"$cabeza"

    def fold[B](valorInicial: B)(function: (B, T) => B): B = function(valorInicial, cabeza)

    def foreach(función: T => Unit): Unit = función(cabeza)

    def zipWith[B, C](otra: ColecciónConUnSoloElemento[B], funciónZipper: (T, B) => C): ColecciónConUnSoloElemento[C] = {
      ColecciónConUnSoloElementoLlena(funciónZipper(cabeza, otra.cabeza))
    }
  }

  object ColecciónConUnSoloElementoVacía extends ColecciónConUnSoloElemento[Nothing] {

    def cabeza: Nothing = throw new NoSuchFieldError

    def estáVacía: Boolean = true

    def filter(evaluador: Nothing => Boolean): ColecciónConUnSoloElemento[Nothing] = this

    def map[B](transformador: Nothing => B): ColecciónConUnSoloElemento[B] = this

    def flatMap[B](transformer: Nothing => ColecciónConUnSoloElemento[B]): ColecciónConUnSoloElemento[B] = this

    def elemento: String = ""

    def fold[B](valorInicial: B)(function: (B, Nothing) => B): B = valorInicial

    def foreach(función: Nothing => Unit): Unit = ()

    def zipWith[B, C](otra: ColecciónConUnSoloElemento[B], funciónZipper: (Nothing, B) => C): ColecciónConUnSoloElemento[C] = {
      throw new NoSuchMethodException("No puedes zippear algo vacío")
    }

  }


}
