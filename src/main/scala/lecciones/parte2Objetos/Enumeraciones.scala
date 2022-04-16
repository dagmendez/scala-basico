package lecciones.parte2Objetos

/**
 * Lección 9 de la parte 2.
 *
 * Las enumeraciones se construyen utilizando la palabra "enum".
 * Desde un punto de vista formal, las enumeraciones son "sealed class".
 * Sin embargo, al utilizar enum, obtenemos algunos beneficios.
 */
object Enumeraciones extends App {

  /**
   * Al generar una enumeración, se genera también su objeto acompañante.
   * Este objeto nos permitirá acceder a los valores de la enumeración.
   */
  enum Permisos {
    /**
     * Si los valores de la enumeración son simplemente nombres, podemos definirlos todos en una sola línea.
     */
    case LECTURA, ESCRITURA, EJECUCIÓN, NINGUNO

    /**
     * Como "enum" es en realidad un clase sellada (sealed class), podemos definir valores o métodos.
     */

    // Valores
    val descripción: String = {
      "Esta enumeración contiene los permisos contemplados para los usuarios: lectura, escritura, ejecución y ninguno"
    }
    val númeroDePermisos: Short = 4

    // Métodos
    def abrirDocumento(): Unit = {
      if (this == LECTURA) println("opening document")
      else println("reading not allowed")
    }

    def guardarDocumento(): Unit = {
      if (this == ESCRITURA) println("saving document")
      else println("saving not allowed")
    }

    def ejecutar(): Unit = {
      if (this == EJECUCIÓN) println("running")
      else println("do not have rights to run")
    }
  }

  /**
   * El objeto acompañante incluye unos métodos que nos facilitan el uso de las enumeraciones:
   *   - ordinal: devuelve la posición en la lista de enumeraciones del caso
   *   - valueOf: devuelve el caso del permiso que corresponde con el nombre del mismo
   *   - values: devuelve un array con todos los casos de la enumeración
   */
  // Accedemos al permiso lectura desde el objeto acompañante
  val algúnPermiso: Permisos = Permisos.LECTURA
  // Accedemos a la posición del permiso en la lista definitoria tal cual ha sido escrita
  val algúnPermisoOrdinal: Int = algúnPermiso.ordinal
  println(algúnPermisoOrdinal)
  // Accedemos el permiso de tipo lectura usando el método "valueOf"
  val lecturaPermiso: Permisos = Permisos.valueOf("LECTURA")
  println(lecturaPermiso)
  // Accedemos a la lista de casos
  val todosLosCasos = Permisos.values
  println(todosLosCasos.mkString("Array(", ", ", ")"))

  /**
   * Los métodos y los valores definidos dentro del cuerpo de enum son accesibles desde una instancia de ese enum.
   */
  //Probamos los valores
  println(algúnPermiso.descripción)
  println(algúnPermiso.númeroDePermisos)

  //Probamos los métodos internos
  algúnPermiso.ejecutar()
  algúnPermiso.abrirDocumento()
  algúnPermiso.guardarDocumento()

  /**
   * Las enumeraciones también pueden tener constructor con múltiples parámetros.
   * En este caso, los permisos dependen del número de bits.
   */
  enum PermisosConBits(bits: Short) {
    case LECTURA extends PermisosConBits(4)   // 100
    case ESCRITURA extends PermisosConBits(2) // 010
    case EJECUCIÓN extends PermisosConBits(1) // 001
    case NINGUNO extends PermisosConBits(0)   // 000
  }

  /**
   * El objeto acompañante de enum no genera un método apply para nosotros.
   * Es por ello que si queremos implementar un patrón factoría, hemos de escribir el método apply nosotros mismos.
   */
  object PermisosConBits {
    // Método factoría
    def apply(bits: Short): PermisosConBits = bits match {
      case 0 => NINGUNO
      case 1 => EJECUCIÓN
      case 2 => ESCRITURA
      case 4 => LECTURA
    }
  }

  // Comprobando el patrón factoría
  assert(PermisosConBits(0) == PermisosConBits.NINGUNO) // Si no fueran iguales, se produciría un error al ejecutar.
  val lecturaConBits = PermisosConBits.LECTURA
  println(lecturaConBits.ordinal == PermisosConBits(4).ordinal)


}
