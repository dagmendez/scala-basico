package ejercicios.parte2Objetos

/**
 * Este es el primer ejercicio de colecciones. Los siguientes ejercicios son:
 *   - [[ColeccionGenericaEnunciado]]
 *   - [[ColeccionExtendidaEnunciado]]
 *   - [[ColeccionFuncionalEnunciado]]
 *
 * @see Soluciones disponible en [[ColeccionSolucion]].
 */
object ColeccionEnunciado {

  def main(args: Array[String]): Unit = {
    // Prueba aquí tu código
  }

  /**
   * En este ejercicio vamos a generar una colección de tipo lista.
   * Las listas contienen dos elementos:
   *   - La cabeza (head)
   *   - La cola (tail)
   *
   * Además de estos dos elementos, las colecciones incluyen algunos métodos que nos permiten trabajar con ellas.
   * En este ejercicio vamos a crear los siguientes métodos:
   *   - estaVacía (isEmpty) de tipo boolean
   *   - añade (add) para añadir un nuevo elemento a la lista
   *   - imprimeElementos (printElements) que nos devuelve un string con los elementos de la lista.
   */
  abstract class MiLista {

    def cabeza: Int

    def cola: MiLista

    def estaVacía: Boolean

    def añade(i: Int): MiLista

    def imprimeElementos: String

    // Llamada polimórfica
    override def toString: String = s"[$imprimeElementos]"

  }

  /**
   * En caso de la la lista no está vacía, esta contiene dos elementos.
   *
   * @param cabeza El primer elemento de la lista, en este caso de tipo Int.
   * @param cola   El resto de la lista, que puede contener más elementos o estar vacía.
   */
  class Llena(val cabeza: Int, val cola: MiLista) extends MiLista {
    def estaVacía: Boolean = ???

    def añade(i: Int): Llena = ???

    def imprimeElementos: String = ???
  }

  /**
   * En caso de que la lista esté vacía, esta contiene un elemento de tipo Vacía (Nil).
   * Este elemento implementa los mismos métodos que si la lista no estuviese vacía.
   * Cada uno de estos métodos nos tiene que devolver algo cuyo tipo ha de concordar con el de la clase abstracta.
   */
  object Vacía extends MiLista {
    def cabeza: Int = ???

    def cola: MiLista = ???

    def estaVacía: Boolean = ???

    def añade(i: Int) = ???

    def imprimeElementos: String = ???
  }

}
