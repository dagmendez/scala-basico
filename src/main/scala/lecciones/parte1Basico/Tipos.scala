package lecciones.parte1Basico

import scala.util.Try

/**
 * Lección 3 de la parte 1.
 * 
 * Scala comparte muchos de los tipos existentes en otros lenguajes. La lengua más cercana es Java.
 * Scala tiene sus propios tipos, como Nothing.
 */
object Tipos extends App {

  // Caracteres y cadenas
  val unChar: Char = 't'
  println(unChar)
  val unaCadena: String = "tTtTtTt"
  println(unaCadena)

  // Numerales
  val unNumeroCorto: Short = -1
  println(unNumeroCorto)
  
  val unNumero: Int = 8500050
  println(unNumero)
  
  val unNumeroNegativo: Int = -29
  println(unNumeroNegativo)
  
  val unGranNumero: BigInt = 999905964
  println(unGranNumero)
  
  val unNumeroLargo: Long = 999999999999L
  println(unNumeroLargo)
  
  val unNumeroConComaFlotante: Float = 3.1415F
  println(unNumeroConComaFlotante)
  
  val unNumeroDecimal: BigDecimal = BigDecimal(5.1884825028)
  println(unNumeroDecimal)

  // Booleanos
  val verdadero: Boolean = true
  val falso: Boolean = false

  // Nada -> Nothing  No lo imprimimos para evitar que se rompa la ejecución
  def unaExcepción: Nothing = throw new RuntimeException("Esta excepción es de tipo Nothing")
  def unError: Nothing = throw new MatchError("No se encuentra el tipo... o sí? Es tipo Nothing")

  /**
   * Algunos envoltorios -> Wrappers
   */

  // Colecciones
  val unaLista: List[AnyVal] = List('a',20, true) // se accede de forma secuencial
  val unaSecuencia: Seq[Any] = Seq(1,2,3,1,2,3, "Hola", Some(60)) // puede contener valores repetidos
  val unSet: Set[AnyVal] = Set('a','b', 1, 2) // no contiene valores repetidos
  val unVector: Vector[String] = Vector("hola", "adios", "David", "Daniel") // se accede en forma de árbol


  // Opciones -> Option[T], Some(x:T), None
  val unaOpción: Option[String] = if "a".nonEmpty then Some("b") else None
  println(unaOpción)
  
  val otraOpción: Option[Int] = if 3 % 2 != 0 then None else Some(1)
  println(otraOpción)

  // Intentos -> Try, Success y Failure, necesitan ser importados
  val unIntento: Try[Int] = Try(1/0) // devuelve un Failure
  println(unIntento)
  
  val otroIntento: Try[Double] = Try(Math.PI / 2) // devuelve un Some(x: Double)
  println(otroIntento)

  // Los intentos se pueden usar para encapsular errores o excepciones de tipo Nothing
  println(Try(unaExcepción))
  println(Try(unError))
}
