package ejercicios.parte2Objetos

import scala.annotation.targetName
import scala.language.postfixOps

object NotacionesSolucion {



  def main(args: Array[String]): Unit = {
    /**
     * Ejercicios:
     *   - 1) Genera instancias del estudiante con todos los métodos apply
     *   - 2) Utiliza combinaciones de las instancias para probar todos los métodos
     */

    val davidEstudiante = new Estudiante("David", "Patrones de Diseño", 2012)
    println(davidEstudiante())
    println(davidEstudiante(9))
    println(davidEstudiante `estáMatriculad@`)
    println(davidEstudiante `le gusta` "Patrones de Diseño")
    println(davidEstudiante.aprende("un poquito de Scala cada día"))
    println(davidEstudiante `aprende Scala`)
    println(!davidEstudiante)
    // Los accesos al constructor tienen preferencia sobre los métodos de la clase. Es necesario añadir paréntesis.
    println(s"${davidEstudiante.promoción} => ${(+davidEstudiante).promoción}")

    val danielEstudiante = new Estudiante("David", "Patrones de Diseño", 2012)
    println(davidEstudiante + danielEstudiante)

    println(davidEstudiante + "Scala Rock Star")
  }

  /**
   * La clase Estudiante contiene métodos que se asemejan al lenguaje humano cotidiano. Scala permite esto.
   *
   * @param nombre             Nombre del estudiante
   * @param asignaturaFavorita Su asignatura favorita
   * @param promoción          El año de su promoción. Valor inicial = 2022.
   */
  class Estudiante(val nombre: String, val asignaturaFavorita: String, val promoción: Int = 2022) {

    /**
     * Generamos un método apply que al ser llamado devuelve un String con la información de la instancia.
     *
     * @return String
     */
    def apply(): String = s"Nombre: $nombre, asignatura favorita: $asignaturaFavorita, promoción: $promoción."

    /**
     * Sobrecargamos el método apply para que nos informe de la nota sobre 10 de su asignatura favorita.
     *
     * @param nota Número del 0 al 10 con la valoración del conocimiento de la asignatura favorita.
     * @return String
     */
    def apply(nota: Int): String = s"La nota de $asignaturaFavorita de $nombre ha sido de un $nota."

    // Post-fix notation: la forma más habitual de usar métodos.

    /**
     * Evalúa si el estudiante está matriculado.
     *
     * @return true
     */
    def `estáMatriculad@`: Boolean = true

    /**
     * Evalúa si el estudiante está estudiando la asignatura inyectada en el argumento.
     *
     * @param asignatura asignatura a evaluar
     * @return Booleano
     */
    def `le gusta`(asignatura: String): Boolean = asignaturaFavorita == asignatura

    /**
     * Devuelve un string con la información sobre que tema está aprendiendo el estudiante
     *
     * @param tema el tema en cuestión
     * @return String
     */
    def aprende(tema: String): String = s"$nombre está aprendiendo $tema."

    /**
     * Mantenemos el código seco (DRY) creando un caso específico para el método anterior.
     *
     * @return String con tema = "Scala"
     */
    def `aprende Scala`: String = aprende("Scala")

    // Pre-fix notation: se usa por delante de la instancia, normalmente para generar un cambio de estado
    //NOTA: el prefijo unary_  sólo funciona con estos símbolos: + - ! ~

    /**
     * Si negamos a la instancia añadiendo un signo de exclamación por delante, generamos un mensaje de sorpresa.
     *
     * @return String
     */
    @targetName("sorpresa")
    def unary_! : String = s"¡$nombre está sorprendido!"

    /**
     * Si "sumamos" a la instancia añadiendo un signo de suma por delante, añadimos un año a la promoción.
     *
     * @return El mismo estudiante que promocionará un año más tarde
     */
    @targetName("promociona un año más tarde")
    def unary_+ : Estudiante = Estudiante(nombre, asignaturaFavorita, promoción + 1)

    // In-fix notation: Se utiliza para realizar operaciones con dos instancias de la misma clase.

    /**
     * Utilizando un símbolo de suma entre dos instancias de la clase Estudiante, generamos un mensaje con datos de
     * las dos instancias (nombre y asignatura favorita).
     * Este tipo de operador se suele usar en clases con valores numéricos para generar una nueva instancia.
     *
     * @param estudiante otro estudiante
     * @return String con datos de ambos
     */
    @targetName("información de dos estudiantes")
    def +(estudiante: Estudiante): String = {
      s"La asignatura favorita de $nombre es $asignaturaFavorita y la de ${estudiante.nombre} es ${estudiante.asignaturaFavorita}."
    }

    /**
     * Utilizando un símbolo de suma entre la instancia y un literal String podemos generar un mensaje
     *
     * @param apodo El apodo del estudiante
     * @return String con un mensaje que nos informa del apodo
     */
    @targetName("También conocido como")
    def +(apodo: String): String = s"$nombre, también conocido como $apodo."
  }

}
