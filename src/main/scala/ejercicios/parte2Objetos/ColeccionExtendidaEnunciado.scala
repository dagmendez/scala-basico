package ejercicios.parte2Objetos

import scala.annotation.targetName

/**
 * Este es el segundo ejercicio de colecciones. Los siguientes ejercicios son:
 *   - [[ColeccionGenericaEnunciado]]
 *   - [[ColeccionExtendidaEnunciado]]
 *   - [[ColeccionFuncionalEnunciado]]
 *
 * @see Soluciones disponible en [[ColeccionExtendidaSolucion]].
 */
object ColeccionExtendidaEnunciado {

  def main(args: Array[String]): Unit = {
    /**
     * Ejercicios una vez implementados los métodos (map, flatMap, filter):
     *   - 1) Genera una lista vacía y luego añádele nuevos elementos
     *   - 2) Mapea una lista no vacía con un transformador anónimo
     *   - 3) Filtra una lista no vacía
     *   - 4) Concatena dos listas
     *   - 5) Utiliza flatMap
     *
     * Una vez hechos los ejercicios anteriores, añadir la palabra "case" delante del objeto Vacía y de la clase Llena.
     * Esto hará que nuevos métodos estén disponibles "gratis" (el compilador programa por nosotros).
     * Entre estos métodos, está el método equals o "==".
     * Ejercicio:
     *   - 1) Crear dos instancias de lista no vacías con los mismos valores.
     *   - 2) Comparar las listas con el operador "=="
     */
  }


  /**
   * Las instancias de tipo MiPredicado se van a usar como argumento del método filter.
   *
   * @tparam T es un tipo genérico que permite usar este Trait para cualquier tipo de lista.
   */
  trait MiPredicado[-T] {
    def prueba(elemento: T): Boolean
  }

  /**
   * Las instancias de tipo MiTransformador se van a usar como argumento del método map y flatMap.
   *
   * @tparam A es el tipo genérico de la lista original.
   * @tparam B Si el transformador se usa en un map, el tipo B es el tipo de resultado.
   *           Si el transformador se usa en un flatMap, el tipo B es de tipo MiListaExtendida[B]
   */
  trait MiTransformador[-A, B] {
    def transformar(elemento: A): B
  }

  /**
   * Esta es la superclase de la lista y al mismo tiempo actúa como contrato. Tanto Llena como Vacía tienen que
   * implementar todos los métodos definidos en esta clase abstracta.
   *
   * @tparam A El tipo es Covariante para que pueda compilar. La varianza es un tema avanzado. No eliminar el +.
   */
  abstract class MiListaExpandida[+A] {
    def cabeza: A

    def cola: MiListaExpandida[A]

    def estáVacía: Boolean

    def añadir[B >: A](member: B): MiListaExpandida[B]


    @targetName("concatenar")
    def ++[B >: A](other: MiListaExpandida[B]): MiListaExpandida[B]

    def imprimirElementos: String

    override def toString: String = s"[$imprimirElementos]"

    /**
     * Añade los métodos:
     *   - map(transformer) => MyList
     *   - filter(predicate) => MyList
     *   - flatMap(transformer[A, MyList[B]) => MyList[B]
     */
    def map[B](transformer: MiTransformador[A, B]): MiListaExpandida[B]

    def filter(predicate: MiPredicado[A]): MiListaExpandida[A]

    def flatMap[B](transformer: MiTransformador[A, MiListaExpandida[B]]): MiListaExpandida[B]
  }

  /**
   * LlenaExtendida es la clase que genera instancias del tipo MiListaExtendida.
   *
   * @param cabeza elemento del tipo A
   * @param cola   resto de los elementos de tipo A que están contenidos en otra instancia de MiListaExtendida.
   * @tparam A El tipo es Covariante para que pueda compilar. La varianza es un tema avanzado. No eliminar el +.
   */
  class LlenaExtendida[A](val cabeza: A, val cola: MiListaExpandida[A]) extends MiListaExpandida[A] {

    def estáVacía: Boolean = false

    def añadir[B >: A](member: B): LlenaExtendida[B] = new LlenaExtendida[B](member, this)

    def map[B](transformer: MiTransformador[A, B]): MiListaExpandida[B] = ???

    def filter(predicate: MiPredicado[A]): MiListaExpandida[A] = ???

    def flatMap[B](transformer: MiTransformador[A, MiListaExpandida[B]]): MiListaExpandida[B] = ???

    @targetName("concatenar")
    def ++[B >: A](other: MiListaExpandida[B]): MiListaExpandida[B] = {
      new LlenaExtendida[B](cabeza, cola ++ other)
    }

    def imprimirElementos: String = s"$cabeza, ${cola.imprimirElementos}"

  }

  /**
   * VacíaExtendida un objeto porque representa la única instancia de la ListaExtendida cuando esta está vacía.
   */
  object VacíaExtendida extends MiListaExpandida[Nothing] {
    def cabeza: Nothing = throw new NoSuchFieldError

    def cola: MiListaExpandida[Nothing] = throw new NoSuchFieldError

    def añadir[B >: Nothing](member: B): LlenaExtendida[B] = new LlenaExtendida[B](member, this)

    def estáVacía: Boolean = true

    def filter(predicate: MiPredicado[Nothing]): MiListaExpandida[Nothing] = ???

    def map[B](transformer: MiTransformador[Nothing, B]): MiListaExpandida[B] = ???

    def flatMap[B](transformer: MiTransformador[Nothing, MiListaExpandida[B]]): MiListaExpandida[B] = ???

    @targetName("concatenar")
    def ++[B >: Nothing](other: MiListaExpandida[B]): MiListaExpandida[B] = other

    def imprimirElementos: String = ""

  }

}
