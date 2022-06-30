
package lecciones.parte4Patrones

import scala.util.Random

/**
 * Lección 1 de 4.
 *
 * El pattern matching es usado de forma intensiva por los desarrollados Scala profesionales.
 * @note La validación se realiza de forma secuencial. Podemos establecer un orden preferencial.
 * @see En la sección [[PatronesDondeNoLosEsperas]] veremos como Scala lo usa sin que nosotros nos demos cuenta.
 */
object PatternMatching extends App {

  /**
   * El pattern matching funciona como un switch en esteroides.
   */
  val generadorAleatorio: Random = new Random()
  val númeroAleatorio = generadorAleatorio.nextInt(10)

  val descripción = númeroAleatorio match {
    case 1 => "el primero"
    case 2 => "doble o nada"
    case 3 => "a la tercera va la vencida"
    case _ => "otro número" // _ representa cualquier otro caso no contemplado de forma específica
  }

  println(númeroAleatorio)
  println(descripción)

  /**
   * Cuando usamos pattern matching con case classes, Scala hace uso del método unapply que genera para nosotros,
   * sin que nos demos cuenta, en la case class. Esto permite hacer descomposición.
   */

  case class Persona(nombre: String, edad: Int)
  val roberto: Persona = Persona("Roberto", 20)

  def saludo(p: Persona) = p match {
    case Persona(nombre, edad) if edad < 18 => s"Hola, me llamo $nombre y tengo $edad años. No puedo conducir coches."
    case Persona(nombre, edad) => s"Hola, me llamo $nombre y tengo $edad años"
    case null => "No sé quien soy"
  }

  def saludo_mal(p: Persona) = p match {
    // Si escribimos primero el caso general, nunca llegaremos al caso particular
    case Persona(n, a) => s"Hola, me llamo $n y tengo $a años"
    // Este es el caso particular. Solo se cumple en el caso de que el if sea true
    // El compilador nos avisa de que este caso es "unreachable"
    case Persona(n, a) if a < 21 => s"Hola, me llamo $n y tengo $a años y no puedo emborracharme en EEUU - USA"
    case null => "No sé quien soy"
  }

  println(saludo(roberto))
  println(saludo(null))
  println(saludo_mal(roberto))

  /**
   * El pattern matching también se utiliza en jerarquías de clases.
   * Esto permite definir métodos que reciben instancias de un tipo elevado en la jerarquía y que serán evaluadas
   * a un valor diferente en función del subtipo.
   */
  sealed class Animal

  case class Perro(raza: String) extends Animal

  case class Loro(saludo: String) extends Animal

  val animal: Animal = Perro("Terra Nova")

  def tipoAnimalMensaje(animal: Animal): Unit = animal match {
    case Perro(r) => println(s"Este animal es un perro de la raza $r")
    // Si utilizas es tipo de patrón y comentas uno de los casos, el compilador te advertirá de que no es exhaustivo
    case Loro(s) => println(s"Este animal es un loro que te dice: $s")
  }

  println(tipoAnimalMensaje(animal))

  /**
   * Aviso a navegantes
   * Aunque el pattern matching es muy poderoso, hay que tener cuidado de cómo lo usamos.
   */

  // Esta implementación devuelve el resultado que queremos, pero es muy ineficiente
  val númeroImpar_pm: Boolean = númeroAleatorio match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // Podemos implementarlo con if else, pero se puede mejorar
  val númeroPar_condicional: Boolean = if (númeroAleatorio % 2 == 0) true else false

  // Esta es la implementación correcta en Scala
  val númeroPar_bien: Boolean = númeroAleatorio % 2 == 0
  // "númeroAleatorio % 2 == 0" es una expresión que devuelve un valor de tipo Boolean

}
