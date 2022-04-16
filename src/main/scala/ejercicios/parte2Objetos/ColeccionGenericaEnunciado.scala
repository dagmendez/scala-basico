package ejercicios.parte2Objetos

/**
 * Este es el segundo ejercicio de colecciones.
 * El primero es [[ColeccionEnunciado]]. 
 * Los siguientes ejercicios son:
 *   - [[ColeccionExtendidaEnunciado]]
 *   - [[ColeccionFuncionalEnunciado]]
 *
 * @see La solución está disponible en [[ColeccionGenericaSolucion]].
 */
object ColeccionGenericaEnunciado {

  def main(args: Array[String]): Unit = {
    // Ejecuta aquí tu código
    // Crea una lista de números (Int) y otra de palabras (String).
  }

  /**
   * Tanto el objeto Vacía y la clase Llena tienen que implementar estos métodos con la misma firma (tipo de salida).
   *
   * @tparam A tipo genérico
   */
  abstract class MiListaCovariante[+A] {

    def cabeza: A

    def cola: MiListaCovariante[A]

    def estáVacía: Boolean

    def añade[B >: A](i: B): MiListaCovariante[B]

    def imprimeElementos: String

    // Llamada polimórfica
    override def toString: String = s"[$imprimeElementos]"

  }

  /**
   * En este caso no es preciso usar el tipo Nothing.
   *
   * @param cabeza elemento inicial de tipo genérico
   * @param cola   resto de la colección, de tipo genérico
   * @tparam A tipo genérico
   */
  class LlenaCovariante[+A](val cabeza: A, val cola: MiListaCovariante[A]) extends MiListaCovariante[A] {
    def estáVacía: Boolean = ???

    def añade[B >: A](i: B): MiListaCovariante[B] = ???

    def imprimeElementos: String = ???
  }

  /**
   * Pequeña ayuda: El tipo Nothing es un sub-tipo general.
   * Esto permite que tanto una lista de Int como de String tengan métodos que devuelven algo de tipo Nothing.
   */
  object VacíaCovariante extends MiListaCovariante[Nothing] {
    def cabeza: Nothing = ???

    def cola: MiListaCovariante[Nothing] = ???

    def estáVacía: Boolean = ???

    def añade[B >: Nothing](i: B): MiListaCovariante[B] = ???

    def imprimeElementos: String = ???
  }

}
