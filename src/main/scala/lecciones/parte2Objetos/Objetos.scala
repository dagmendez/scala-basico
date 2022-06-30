
package lecciones.parte2Objetos

/**
 * Lección 3 de la parte 2.
 * 
 * Scala no cuenta con funcionalidad estática a nivel de clase (class-level functionality - static).
 * Los objetos en Scala son instancias Singleton.
 */
object Objetos extends App {

  /**
   * Objeto "compañero" de la clase Persona. Para que el compilador reconozca este objeto como compañero,
   * tiene que tener el mismo nombre que la clase y estar definido en el mismo alcance que la clase.
   */
  object Persona {
    /**
     * La funcionalidad estática (static) son los valores y los métodos del objeto que no reciben parámetros.
     */
    val NUMERO_OJOS: Short = 2
    val NUMERO_OREJAS: Short = 2
    val NUMERO_NARIZ: Short = 1
    def puedeVolar: Boolean = false
    def puedeCaminar: Boolean = true

    // Patrón factoría

    /**
     * Los métodos que reciben parámetros y devuelven una instancia de la clase que acompaña al objeto se denominan
     * factorías. Son una parte fundamental del patrón factoría.
     * La implementación de este método está simplificada.
     * @param nombre nombre de la nueva persona
     * @param madre instancia de la clase persona con los datos del padre
     * @param padre instancia de la clase persona con los datos de la madre
     * @return nueva instancia de la clase Persona.
     */
    def aPartirDe(nombre: String, madre: Persona, padre: Persona): Persona = {
      // En España esta es la tradición (nombre + primer apellido padre + primer apellido madre)
      // Aunque ahora se puede invertir el orden y utilizar el apellido de la madre primero
      val apellido_paterno = padre.apellidos.head
      val apellido_materno = madre.apellidos.head
      new Persona(nombre, Array(apellido_paterno, apellido_materno))
    }
    /**
     * La terminología más utilizada para el patrón factoría es la de un método apply en el objeto.
     * Para no copiar código, vamos a llamar al método from.
     */
    def apply(nombre: String, madre: Persona, padre: Persona): Persona = aPartirDe(nombre, padre, madre)

  }

  /**
   * Clase persona. Para construir una instancia necesitamos el nombre en formato string y los apellidos en formato
   * array de strings.
   * @param nombre    Nombre de la persona
   * @param apellidos Apellidos de la persona
   */
  class Persona(val nombre: String, val apellidos: Array[String]) {
    //Funcionalidad a nivel de instancia
    // No la cubrimos en este apartado
  }

  //Llamadas a los atributos dentro del objeto Singleton
  println(Persona.NUMERO_OJOS)
  println(Persona.NUMERO_OREJAS)
  println(Persona.NUMERO_NARIZ)
  println(Persona.puedeVolar)
  println(Persona.puedeCaminar)

  // Generamos un padre y una madre
  val padre = new Persona("José", Array("García", "Herrero"))
  val madre = new Persona("María", Array("López", "Méndez"))

  // Generamos un hijo utilizando el patrón factoría del objeto acompañante
  // Usando from
  val miguel_from = Persona.aPartirDe("Miguel", padre, madre)
  // Usando apply
  val miguel_apply = Persona.apply("Miguel", padre, madre)
  // Usando apply de la forma en la que se usa en Scala
  val miguel = Persona("Miguel", padre, madre)

  println(miguel_from.nombre)
  println(miguel_apply.apellidos.mkString(";"))
  println(miguel.nombre.equals(miguel_from.nombre) && miguel.nombre.equals(miguel_apply.nombre))
}
