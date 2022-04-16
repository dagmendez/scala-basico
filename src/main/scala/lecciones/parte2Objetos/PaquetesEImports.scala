
package lecciones.parte2Objetos

import lecciones.parte1Basico.Tipos.unaCadena

/**
 * Lección 11 de la parte 2.
 */
object PaquetesEImports extends App {

  /**
   * Scala incluye una serie de paquetes que siempre están disponibles:
   *   - java.lang
   *   - scala
   *   - scala.preDef
   *   - otros...
   */

  // Podemos utilizar el nombre completo del paquete o simplemente el tipo final.
  val javaEntero: java.lang.Integer = 10
  val otroJavaEntero: Integer = 20
  // Validamos que son la misma clase
  assert(javaEntero.getClass == otroJavaEntero.getClass)
  println(s"$javaEntero == $otroJavaEntero")

  val scalaEntero: scala.Int = 10
  val otroScalaEntero: Int = 20
  // Validamos que son la misma clase
  assert(scalaEntero.getClass == otroScalaEntero.getClass)
  println(s"$scalaEntero == $otroScalaEntero")

  /**
   * Dentro de cada paquete, Scala nos permite crear un objeto especial, llamado "package object"
   * Los valores y métodos que definamos en ese objeto estarán disponibles en todos los archivos dentro del paquete.
   * Para acceder a ellos, los llamamos directamente por su nombre.
   */

  val holaScala: Unit = diHola() // este método está definido en el "package object"

  /**
   * Todos los objetos, clases, trait, etc. contenidos dentro del mismo paquete son accesibles utilizando su nombre.
   * Del mismo modo, podemos acceder a las clases, objetos, métodos y valores definidos dentro de ellos.
   */


  // OOBasico
  val persona = new OOBasico.Persona("David Gil", 2021)
  println(persona.nombre)

  /**
   * En caso de importar algo proveniente de una libreria externa o de otras partes de nuestro código,
   * podemos hacerlo de dos formas:
   *   - nombre cualificado
   *   - import en la cabecera del archivo
   */

  // nombre cualificado
  //val unaT: Char = lecciones.parte1basico.Tipos.unChar //Nota: si lo descomentas, verás un print de toda la clase

  // import al principio del archivo
  // -> import lecciones.parte1basico.Tipos.unaCadena
  //val unasTs: String = unaCadena //Nota: si lo descomentas, verás un print de toda la clase

}
