package lecciones.parte2Objetos

import scala.language.postfixOps

/**
 * Lección 2 de la parte 2.
 * 
 * Scala es muy flexible a la hora de nombrar métodos. Podemos usar:
 *   - letras con acento gráfico
 *   - símbolos como +,-,*,/,@,!,=...
 *   - varias palabras separadas por espacios
 *   - números como primer elemento del nombre
 *   
 * @see Ejercicios disponibles en [[ejercicios.parte2Objetos.NotacionesEnunciado]]  
 */
object Notaciones extends App {

  /**
   * La clase persona contiene métodos que se asemejan al lenguaje humano cotidiano. Scala permite esto.
   *
   * @param nombre           Nombre de la persona
   * @param películaFavorita Su película favorita
   * @param edad             Su edad. Valor inicial = 0.
   */
  class Persona(val nombre: String, películaFavorita: String, val edad: Int = 0) {

    /**
     * Generamos un método apply que al ser llamado devuelve un String con la información de la instancia.
     *
     * @return String
     */
    def apply(): String = {
      s"Hola, me llamo $nombre y me gusta $películaFavorita"
    }

    /**
     * Sobrecargamos el método apply para que nos informe de cuantas veces la persona de la instancia ha visto su
     * película favorita.
     *
     * @param veces Número de visualizaciones completas de la película favorita
     * @return String
     */
    def apply(veces: Int): String = {
      s"Hola, me llamo $nombre y he visto $películaFavorita $veces veces"
    }

    // Post-fix notation: la forma más habitual de usar métodos.

    /**
     * Evalúa si la persona está viva.
     *
     * @return true
     */
    def `estáViv@`: Boolean = {
      true
    }

    /**
     * Evalúa si a la persona le gusta la película inyectada en el argumento.
     *
     * @param película película a evaluar
     * @return Booleano
     */
    def `le gusta`(película: String): Boolean = {
      película == películaFavorita
    }

    /**
     * Devuelve un string con la información sobre que tema está aprendiendo la persona
     *
     * @param tema el tema en cuestión
     * @return String
     */
    def aprende(tema: String): String = {
      s"$nombre está aprendiendo $tema"
    }

    /**
     * Mantenemos el código seco (DRY) creando un caso específico para el método anterior.
     *
     * @return String con tema = "Scala"
     */
    def `aprende Scala`: String = {
      aprende("Scala")
    }

    // Pre-fix notation: se usa por delante de la instancia, normalmente para generar un cambio de estado
    //NOTA: el prefijo unary_  sólo funciona con estos símbolos: + - ! ~

    /**
     * Si negamos a la instancia añadiendo un signo de exclamación por delante, generamos un mensaje de sorpresa.
     *
     * @return String
     */
    def unary_! : String = {
      s"$nombre, ¡¿Qué pasa contigo?!"
    }

    /**
     * Si "sumamos" a la instancia añadiendo un signo de suma por delante, añadimos un año a la edad de la persona.
     *
     * @return La misma persona con un año más (cambio de estado)
     */
    def unary_+ : Persona = {
      new Persona(nombre, películaFavorita, edad + 1)
    }

    // In-fix notation: Se utiliza para realizar operaciones con dos instancias de la misma clase.

    /**
     * Utilizando un símbolo de suma entre dos instancias de la clase Persona, generamos un mensaje con datos de ambas.
     * Este tipo de operador se suele usar en clases con valores numéricos para generar una nueva instancia.
     *
     * @param persona otra persona
     * @return String con datos de ambos
     */
    def +(persona: Persona): String = {
      s"${this.nombre} queda con ${persona.nombre}"
    }

    /**
     * Utilizando un símbolo de suma entre la instancia y un literal String podemos generar un mensaje
     *
     * @param apodo El apodo de la persona
     * @return String con un mensaje que nos informa del apodo
     */
    def +(apodo: String): Persona = {
      new Persona(s"$nombre $apodo", películaFavorita, edad)
    }
  }

  val maría = new Persona("María", "Inception")
  val tomás = new Persona("Tomás", "Fight Club")

  println(maría.`le gusta`("Inception"))
  println(maría `le gusta` "Inception")

  println(maría + tomás)

  println(-1 == 1.unary_-)

  println(maría.unary_!)
  println(!maría)

  println(maría.`estáViv@`)
  println(maría `estáViv@`)

  println(maría.apply())
  println(maría())

  println(maría.edad)
  println((+maría).edad)

  println((maría + "the Rock Star!") ())

  println(maría(4))

  println(maría `aprende Scala`)

}

