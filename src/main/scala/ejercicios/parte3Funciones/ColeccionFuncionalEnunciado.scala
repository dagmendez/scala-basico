package ejercicios.parte3Funciones

import lecciones.parte3Funciones.QueEsUnaFuncion

import scala.annotation.targetName

/**
 * Ejercicio correspondiente a la teoría [[QueEsUnaFuncion]].
 *
 * * Ejercicios:
 *   - Transformar MiPredicado en una función (Function Type)
 *   - Transformar MiTransformador en una función (Function Type)
 *
 * Para ello, has de descomentar los tres métodos (map, flatMap y filter) e implementarlos.
 * Pista: map, flatMap y filter son HOF, por lo que reciben funciones por parámetro. 
 *
 * Soluciones disponibles en [[CollecionFuncionalSolucion]].
 */
object ColeccionFuncionalEnunciado {

  def main(args: Array[String]): Unit = {
    // Aquí puedes probar tu código.
  }

  /* Ya no podemos usar este trait, por lo que tendremos que utilizar function type donde usemos el trait*/
  //trait MiPredicado[-T] {
  //  def prueba(elemento: T): Boolean
  //}

  /* Ya no podemos usar este trait, por lo que tendremos que utilizar function type donde usemos el trait*/
  //trait MiTransformador[-A, B] {
  //  def transformar(elemento: A): B
  //}

  sealed abstract class MiListaFuncional[+A] {
    def cabeza: A

    def cola: MiListaFuncional[A]

    def estáVacía: Boolean

    def añadir[B >: A](miembro: B): MiListaFuncional[B]


    @targetName("concatenar")
    def ++[B >: A](otraLista: MiListaFuncional[B]): MiListaFuncional[B]

    def imprimirElementos: String

    override def toString: String = s"[$imprimirElementos]"

    //def map[B](???): MiListaFuncional[B]

    //def filter(???): MiListaFuncional[A]

    //def flatMap[B](???): MiListaFuncional[B]
  }

  final case class LlenaFuncional[A](cabeza: A, cola: MiListaFuncional[A]) extends MiListaFuncional[A] {

    def estáVacía: Boolean = false

    def añadir[B >: A](miembro: B): LlenaFuncional[B] = new LlenaFuncional[B](miembro, this)


    //def map[B](???): MiListaFuncional[B] = ???

    //def filter(???): MiListaFuncional[A] = ???

    //def flatMap[B](???): MiListaFuncional[B] = ???


    @targetName("concatenar")
    def ++[B >: A](otraLista: MiListaFuncional[B]): MiListaFuncional[B] = LlenaFuncional[B](cabeza, cola ++ otraLista)

    def imprimirElementos: String = s"$cabeza, ${cola.imprimirElementos}"

  }

  /**
   * VacíaExtendida un objeto porque representa la única instancia de la ListaExtendida cuando esta está vacía.
   */
  case object VacíaFuncional extends MiListaFuncional[Nothing] {
    def cabeza: Nothing = throw new NoSuchFieldError

    def cola: MiListaFuncional[Nothing] = throw new NoSuchFieldError

    def añadir[B >: Nothing](miembro: B): LlenaFuncional[B] = new LlenaFuncional[B](miembro, this)

    def estáVacía: Boolean = true

    //def filter(???): MiListaFuncional[Nothing] = ???

    //def map[B](???): MiListaFuncional[B] = ???

    //def flatMap[B](???): MiListaFuncional[B] = ???

    @targetName("concatenar")
    def ++[B >: Nothing](otraLista: MiListaFuncional[B]): MiListaFuncional[B] = otraLista

    def imprimirElementos: String = ""

  }

}
