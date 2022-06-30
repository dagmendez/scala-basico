
package lecciones.parte4Patrones

/**
 * Lección 4 de 4.
 * 
 * Scala 3 ha introducido un cambio en la sintaxis del lenguaje.
 * Las llaves "{}" sirven para definir bloques de código.
 * Pero también se usan para "encerrar" la parte derecha del igual. Esto ha cambiado en Scala3.
 * Ahora se pueden definir los bloques de código correspondientes a la parte derecha del igual siempre que se
 * respeten las indentaciones:
 * 2 espacios por cada nivel de profundidad.
 * También se han incluido palabras como "then" y "end" para booleanos y objetos/clases
 */
object SintaxisSinLlaves extends App {

  /**
   * Expresiones booleanas
   */

  println("=== Expresiones con Booleanos ===")

  // Java
  val javaBooleano: String =
    if (2 > 3) {
      "más grande"
    } else {
      "más pequeño"
    }
  println(s"JavaBooleano: $javaBooleano")

  // Scala 2 - expandido
  val scala2BooleanoExpandido: String = {
    if (2 > 3) "más grande"
    else "más pequeño"
  }
  println(s"Scala2BooleanoExpandido: $scala2BooleanoExpandido")

  // Scala 3 - expandido
  val scala3BooleanoExpandido: String =
    if 2 > 3 then
      "más grande"
    else
      "más pequeño"
  println(s"Scala3BooleanoExpandido: $scala3BooleanoExpandido")

  // Scala 2 - compacto
  val scala2BooleanoCompacto: String = if (2 > 3) "más grande" else "más pequeño"
  println(s"Scala2BooleanoCompacto: $scala2BooleanoCompacto")

  // Scala 3 - compacto
  val scala3BooleanoCompacto: String = if 2 > 3 then "más grande" else "más pequeño"
  println(s"Scala3BooleanoCompacto: $scala3BooleanoCompacto")

  /**
   * For comprehensions
   */

  println("=== For Comprehensions ===")

  // Scala 2
  val forComScala2: List[String] = for {
    n <- List(1, 2, 3)
    s <- List("negro", "blanco")
  } yield s"$n$s"
  println(s"ForComScala2: $forComScala2")

  // Scala 3
  val forComScala3: List[String] =
    for
      n <- List(1, 2, 3)
      s <- List("negro", "blanco")
    yield s"$n$s"
  println(s"ForComScala3: $forComScala3")

  /**
   * Macheo de patrones
   */

  println("=== Macheo de patrones ===")

  val significadoDeLaVida: Int = 42

  // Scala 2
  val macheoScala2: String = significadoDeLaVida match {
    case 1 => "el elegido"
    case 2 => "doble o nada"
    case 42 => "significado de la vida"
    case _ => "otra cosa"
  }
  println(s"MacheoScala2: $macheoScala2")

  // Scala 3
  val macheoScala3: String =
    significadoDeLaVida match
      case 1 => "el elegido"
      case 2 => "doble o nada"
      case 42 => "significado de la vida"
      case _ => "otra cosa"
  println(s"MacheoScala3: $macheoScala3")

  /**
   * Métodos
   */

  println("=== Métodos ===")
  val animalAnónimoScala2: AnimalScala2 = new AnimalScala2 {
    def comer(): Unit = {
      println("Estoy Comiendo")
    }

    def crecer(): Unit = {
      println("Estoy comiendo")
    }
  }

  println(s"SignificadoDeLaVidaScala2: ${significadoDeLaVidaScala2(0)}")
  val animalAnónimoScala3: AnimalScala3 = new AnimalScala3 :
    override def comer(): Unit = println("Como más rápido")

    override def crecer(): Unit = println("Crezco más rápido")
    // end es opcional, se recomiendo su uso en clases muy grandes
  end animalAnónimoScala3

  println(s"SignificadoDeLaVidaScala3: ${significadoDeLaVidaScala3(0)}")

  /**
   * Clases, Objetos, Enums, Traits....
   */

  println("=== Clases, Objetos, Traits, Enums... ===")

  // Scala 2
  def significadoDeLaVidaScala2(arg: Int): Int = {
    val resultadoParcial = 40
    40 + 2
  }

  // Scala 3
  def significadoDeLaVidaScala3(arg: Int): Int =
    val resultadoParcial = 40
    40 + 2
  println(animalAnónimoScala2.comer())
  println(animalAnónimoScala2.crecer())

  // Scala 2
  abstract class AnimalScala2 {

    def comer(): Unit

    def crecer(): Unit

    // 1000 líneas de código
  }

  // Scala 3
  abstract class AnimalScala3:
    def comer(): Unit

    def crecer(): Unit

    // 1000 líneas de código

  end AnimalScala3
  
  println(animalAnónimoScala3.comer())
  println(animalAnónimoScala3.crecer())

}
