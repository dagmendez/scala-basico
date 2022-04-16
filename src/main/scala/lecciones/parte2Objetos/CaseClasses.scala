package lecciones.parte2Objetos

/**
 * Lección 8 de la parte 2.
 *
 * Las case class ofrecen una serie de beneficios que las hacen muy atractivas.
 * Estos beneficios se obtienen al generarse código invisible para nosotros de forma automática.
 */
object CaseClasses extends App {

  case class Persona(nombre: String, edad: Int)

  /**
   * Las case classes nos permiten generar instancias sin necesidad de utilizar la palabra "new"
   */
  val jaime = Persona("Jaime", 34)

  /**
   * Nos permiten acceder directamente a los miembros del constructor
   */
  println(jaime.nombre)
  println(jaime.edad)

  /**
   * Incorporan un método toString que es amigable con el desarrollador.
   */
  println(jaime.toString)

  /**
   * La llamada el método toString se produce si llamamos directamente al objeto en un logger o en un print
   */
  println(jaime) // launches .toString

  /**
   * Las case class incorporan el método hashCode
   */
  println(jaime.hashCode())

  /**
   * Incorporo un método equals que compara los valores del constructor para ver si dos instancias son iguales.
   */
  val jaime2 = Persona("Jaime", 34)
  println(jaime.equals(jaime2).toString + " = true")

  /**
   * Incorporan un método copy que permite actualizar valores del constructor.
   * Esto permite reemplazar la mutación de la instancia por el patrón "actualiza mientras copias" (update as you copy)
   */
  val jaime3 = jaime.copy()
  val juan = jaime.copy("Juan")
  println(juan)

  /**
   * Generan de forma oculta un objeto acompañante que tiene un método apply implementado.
   * Esto es lo que permite generar nuevas instancias sin la palabra "new".
   * En realidad estamos utilizando el método apply del objeto acompañante.
   */
  // El tipo propiamente dicho. Es el objeto acompañante
  val thePerson: Persona.type = Persona
  // Una nueva instancia
  val maría: Persona = Persona("María", 21)

  /**
   * Las case class son serializables, lor que permite que se puedan usar en computación en paralelo:
   *   - Akka
   *   - Spark
   *   - y mucho más.
   */

  /**
   * Por último, las case class se pueden utilizar en el macheo de patrones (pattern matching)
   */

  maría match {
    case Persona(nombre, _) => println(s"Soy $nombre")
  }


}
