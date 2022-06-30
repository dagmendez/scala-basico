package lecciones.parte3Funciones

import ejercicios.parte3Funciones.OpcionesEnunciado

/**
 * Lección 7 de 8.
 * 
 * El primer caso de uso para las opciones en Scala es la gestión de los valores nulos (null).
 * [[Option]] es una clase que funciona como una colección de un solo elemento. Al igual que el [[Try]], tienes 2
 * implementaciones. En este caso son el [[Some]] y el [[None]]:
 *   - Some: Envuelve el valor de la computación, si existe.
 *   - None: Instancia del objeto que se devuelve en caso de no existir el valor (p.ej., en caso de null)
 *
 * Al funcionar como una colección, tenemos disponibles los métodos de map, flatMap, filter y for - comprehension.
 * 
 * Ejercicios en [[OpcionesEnunciado]].
 */
object Opciones extends App {

  // Instancias básicas
  def unaOpción: Option[Int] = Some(4)

  def unaNoOpción: Option[Int] = None

  println(unaOpción)

  /**
   * En Scala se trabaja con librerías Java, donde el uso del null está muy extendido. Al usar estas librerías hay que
   * ser cuidadoso para no generar ningún puntero nulo. Un patrón habitual es el siguiente.
   */
  def métodoNoSeguro(): String = null
  //val result = Some(null) // Esto no funciona
  // Si llamamos el método NO seguro dentro de un Option, podemos controlar su posible valor
  val resultado = Option(métodoNoSeguro())
  println(resultado)

  /**
   * Podemos encadenar varios métodos para asegurarnos de obtener el resultado que queremos.
   * Si algún valor no está disponible en un sistema, quizás podamos recuperarlo en otro.
   */
  def métodoInseguro(): String = null

  def métodoSeguro(): String = "Un resultado válido"

  def resultadoEncadenado = {
    Option(métodoNoSeguro()) // null
      .orElse(Option(métodoInseguro())) // null
      .orElse(Option(métodoSeguro())) // resultado válido
  }

  println(resultadoEncadenado)

  /**
   * El ejemplo anterior se puede mejorar si integramos en nuestro software una capa que gestione los potenciales nulos.
   * En lugar de tratar con valores de tipo String o Int u otro, trabajamos con Option[T].
   */

  def unMejorMétodoInseguro(): Option[String] = None

  def unMejorMétodoDeRespaldo(): Option[String] = Some("un valor aceptable")

  def unMejorResultadoEncadenado = unMejorMétodoInseguro().orElse(unMejorMétodoDeRespaldo())

  println(unMejorResultadoEncadenado)

  /**
   * Funcionalidades básicas de los Option:
   *   - isEmpty
   *   - map
   *   - flatMap
   *   - filter
   */
  println(unaOpción.isEmpty)
  println(unaOpción.map(_*2)) // Some(8)
  println(unaNoOpción.map(_*2))// None
  println(unaOpción.flatMap(x=> Option(x, x-1))) // Some((4,3))
  println(unaOpción.filter(x => x > 10)) // None

}
