package lecciones.parte1Basico

/**
 * Lección 7 de la parte 1.
 * 
 * Scala incluye una serie de funcionalidades para los String que permiten hacer un uso más dinámico.
 * Además, incluye todas las funcionalidades existentes en Java.
 */
object OperacionesStrings extends App {

  val str: String = "Hola, Yo estoy aprendiendo Scala"

  /**
   * Scala puede utilizar todos los métodos disponibles en Java para trabajar con cadenas (String).
   * @see [[https://docs.oracle.com/javase/8/docs/api/java/lang/String.html Java-8 String]]
   * @see [[https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html Java-11 String]]
   */
  def javaApi(): Unit = {
    println(str.charAt(2))
    println(str.substring(7, 11))
    println(str.split(" ").mkString("Array(", ", ", ")"))
    println(str.replace(" ", "-"))
    println(str.toLowerCase())
    println(str.toUpperCase())
    println(str.length)
  }

  println("\nJavaApi:")
  javaApi()

  /**
   * Scala cuenta con algunos métodos adicionales. Piensa en cadenas (string) como si fuera una lista de caracteres.
   * Si pensamos así, podemos:
   *   - Añadir elementos por la cabeza con +:
   *   - Añadir elementos por la cola con :+
   *   - Darle la vuelta a la lista con reverse
   *   - Seleccionar los primeros "n" elementos de la lista con take
   * @see [[https://www.scala-lang.org/api/current/scala/collection/StringOps.html Scala-2 String-Ops]]
   * @see [[https://scala-lang.org/api/3.x/scala/collection/mutable/StringBuilder.html Scala-3 String-Builder]]
   */
  def scalaApi(): Unit = {
    val unNumeroEnFormatoCadena = "42"
    println('a' +: unNumeroEnFormatoCadena :+ 'z')
    println(str.reverse)
    println(str.take(2))
  }

  println("\nScalaApi:")
  scalaApi()

  // Interpoladores de cadenas (String) en Scala

  /**
   * El interpolador "s" permite inyectar expresiones o valores en una cadena (string).
   * Para utilizarlo, se añade una "s" delante de las comillas dobles.
   * La inyección se realiza utilizando el símbolo "$" delante del nombre de la variable (val).
   * También se pueden inyectar expresiones dentro de llaves "${}".
   * @see [[https://docs.scala-lang.org/overviews/core/string-interpolation.html#the-s-string-interpolator Scala-2 S-Interpolator]]
   */
  def sInterpolador(): Unit = {
    val nombre = "David"
    val edad = 12
    val saludo = s"Hola, mi nombre es $nombre y tengo $edad años"
    println(saludo)

    val otroSaludo = s"Hola, mi nombre is $nombre y voy a cumplir ${edad + 1} años"
    println(otroSaludo)
  }

  println("\nsInterpolador:")
  sInterpolador()

  /**
   * El interpolador "f" permite inyectar valores (val) en una cadena (string) con funcionalidades ocultas:
   *   - Permite forzar un formato especifico: fechas, decimales, comas flotantes...
   *   - Evalúa el tipo del valor inyectado: realiza una validación de tipos de forma automática
   * @see [[https://docs.scala-lang.org/overviews/core/string-interpolation.html#the-f-interpolator Scala-2 F-Interpolator]]
   */
  def fInterpolador(): Unit = {
    val nombre = "Daniel"
    val velocidad = 1.2f
    // %s evalúa que nombre es una cadena (String)
    // %2.2f evalúa que velocidad es una coma flotante (float) y lo fuerza a inyectarse con un formato de dos decimales
    val mito = f"$nombre%s puede comer $velocidad%2.2f hamburguesas por minuto"
    println(mito)
  }

  println("\nfInterpolador:")
  fInterpolador()


  //Raw interpolator

  /**
   * El interpolador raw hace que los caracteres especiales no se escapen (no realicen su función especial)
   * @see [[https://docs.scala-lang.org/overviews/core/string-interpolation.html#the-raw-interpolator Scala-2 Raw-Interpolator]]
   * */
  def rawInterpolador(): Unit = {
    println(raw"\n \t son impresos de forma literal")
  }
  println("\nrawInterpolador")
  println(rawInterpolador())

}
