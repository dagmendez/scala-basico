package ejercicios.parte3Funciones

import scala.annotation.targetName


/**
 * Solución de los ejercicios de [[ColeccionFuncionalEnunciado]].
 */
object CollecionFuncionalSolucion {

  def main(args: Array[String]): Unit = {
    val vacio = VacíaFuncional

    println(vacio.añadir("jaja").imprimirElementos)
    println(vacio.añadir(1).imprimirElementos)

    println(LlenaFuncional[Int](1, LlenaFuncional[Int](2, VacíaFuncional))
      .filter((element: Int) => element % 2 == 0).imprimirElementos)

    println(vacio.añadir("abc").añadir("def")
      .map[String](_.toUpperCase).imprimirElementos)

    println((vacio ++ LlenaFuncional[Int](10, vacio)).imprimirElementos)

    val unaLista = LlenaFuncional[Short](1,
      LlenaFuncional[Short](2,
        LlenaFuncional[Short](3,
          VacíaFuncional)))

    val otraLista = LlenaFuncional[Short](100, VacíaFuncional)
    println(unaLista.imprimirElementos)
    println(otraLista.imprimirElementos)

    val dosListas = unaLista ++ otraLista
    println(dosListas.imprimirElementos)
    println(dosListas.map(short => short * 2).imprimirElementos)
    println(dosListas.flatMap(short => {
      LlenaFuncional[Short](short, LlenaFuncional[Short](short, VacíaFuncional))
    }).imprimirElementos)

    println(LlenaFuncional[Int](1, LlenaFuncional[Int](2, LlenaFuncional[Int](3, LlenaFuncional[Int](4, VacíaFuncional))))
      .flatMap { element =>
        LlenaFuncional[Int](element - 1, LlenaFuncional[Int](element, LlenaFuncional[Int](element + 1, VacíaFuncional)))
      }.imprimirElementos)
  }


  sealed abstract class MiListaFuncional[+A] {
    def cabeza: A

    def cola: MiListaFuncional[A]

    def estáVacía: Boolean

    def añadir[B >: A](miembro: B): MiListaFuncional[B]

    @targetName("concatenar")
    def ++[B >: A](otraLista: MiListaFuncional[B]): MiListaFuncional[B]

    def imprimirElementos: String

    override def toString: String = s"[$imprimirElementos]"

    def map[B](f: A => B): MiListaFuncional[B]

    def filter(f: A => Boolean): MiListaFuncional[A]

    def flatMap[B](f: A => MiListaFuncional[B]): MiListaFuncional[B]
  }

  final case class LlenaFuncional[A](cabeza: A, cola: MiListaFuncional[A]) extends MiListaFuncional[A] {

    def estáVacía: Boolean = false

    def añadir[B >: A](miembro: B): LlenaFuncional[B] = LlenaFuncional[B](miembro, this)


    def map[B](f: A => B): MiListaFuncional[B] = LlenaFuncional[B](f(cabeza), cola.map(f))

    def filter(f: A => Boolean): MiListaFuncional[A] =
      if (f(cabeza)) LlenaFuncional[A](cabeza, cola.filter(f))
      else cola.filter(f)

    def flatMap[B](f: A => MiListaFuncional[B]): MiListaFuncional[B] = f(cabeza) ++ cola.flatMap(f)


    @targetName("concatenar")
    def ++[B >: A](otraLista: MiListaFuncional[B]): MiListaFuncional[B] = {
      LlenaFuncional[B](cabeza, cola ++ otraLista)
    }

    def imprimirElementos: String = s"$cabeza, ${cola.imprimirElementos}"

  }

  /**
   * VacíaExtendida un objeto porque representa la única instancia de la ListaExtendida cuando esta está vacía.
   */
  case object VacíaFuncional extends MiListaFuncional[Nothing] {
    def cabeza: Nothing = throw new NoSuchFieldError

    def cola: MiListaFuncional[Nothing] = throw new NoSuchFieldError

    def añadir[B >: Nothing](miembro: B): LlenaFuncional[B] = LlenaFuncional[B](miembro, this)

    def estáVacía: Boolean = true

    def filter(f: Nothing => Boolean): MiListaFuncional[Nothing] = this

    def map[B](f: Nothing => B): MiListaFuncional[B] = this

    def flatMap[B](f: Nothing => MiListaFuncional[B]): MiListaFuncional[B] = this

    @targetName("concatenar")
    def ++[B >: Nothing](otraLista: MiListaFuncional[B]): MiListaFuncional[B] = otraLista

    def imprimirElementos: String = ""

  }


}
