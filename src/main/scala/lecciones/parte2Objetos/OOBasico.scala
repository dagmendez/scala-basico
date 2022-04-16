
package lecciones.parte2Objetos

/**
 * Lección 1 de la parte 2.
 *
 * Scala Orientada a objetos.
 * Scala es más OOP que Java, ya que cualquier elemento en Scala es un objeto.
 * 
 * @see Ejercicios disponibles en [[ejercicios.parte2Objetos.OOBasicoEnunciado]]
 */
object OOBasico extends App {

  /**
   * La clase es el elemento básico. Permite definir un estructura de datos y conjunto de funcionalidades.
   * Una vez definida una clase, podemos crear objetos (uno, dos, tres, 4000,...) a partir de esa clase.
   *
   * @param primerParámetro  una letra, sólo accesibles desde dentro de la propia clase.
   * @param segundoParámetro otra letra, sólo accesible desde dentro de la propia clase.
   */
  class ScalaRocks(primerParámetro: Char, segundoParámetro: String) {

    /** Los métodos definidos dentro de la clase tienen acceso a los parámetros del constructor. */
    def imprimir(longitud: Int): Unit = {
      println(s"Scala goes ${primerParámetro.toUpper}${segundoParámetro * longitud}!")
    }
  }

  new ScalaRocks('b', "r").imprimir(10)

  /**
   * Esta clase representa a una persona. Por ahora es muy básica.
   *
   * @param nombre es un parámetro del constructor y al estar precedido de la palabra "val", se convierte en un valor
   *               y es accesible desde el objeto.
   * @param edad   igual que el nombre.
   */
  class Persona(val nombre: String, val edad: Int = 0) {

    /**
     * Los valores de una clase son accesibles desde el objeto.
     */
    val significadoDeLaVida = 42

    /**
     * Los métodos pueden inyectar los parámetros del constructor, así como los valores definidos dentro de la clase.
     *
     * @param nombre parámetro del método
     */
    def saludo(nombre: String): Unit = {
      // this hace referencia al estado interno de la instancia de la clase en cada momento de la ejecución.
      println(s"${this.nombre} dice: ¡Hola $nombre!")
    }

    /**
     * Se pueden definir varios métodos con el mismo nombre y con una cantidad diferente de parámetros.
     * A esto se le conoce como sobrecarga de métodos.
     */
    def saludo(): Unit = {
      println(s"Hola, me llamo $nombre.")
    }

    /**
     * "def this" es una sobrecarga del constructor de la clase. Nos permite generar instancias de la clase con
     * diferentes parámetros o directamente sin ellos.
     *
     * @param nombre Nombre de la persona
     */
    def this(nombre: String) = {
      // Internamente, utilizamos el constructor base con los valores inyectados.
      this(nombre, 0)
    }

    /**
     * Los constructores se pueden seguir sobrecargando haciendo referencia dentro del bloque de código al constructor
     * original o a otros constructores sobrecargados.
     */
    def this() = {
      this ("John Doe")
    }
  }

  val daniel = new Persona("Daniel", 30)
  println(daniel.nombre)
  println(daniel.edad)
  println(daniel.significadoDeLaVida)
  daniel.saludo("John")
  daniel.saludo()

}

