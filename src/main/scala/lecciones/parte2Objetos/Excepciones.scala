package lecciones.parte2Objetos

/**
 * Lección 9 de la parte 2.
 * 
 * Scala permite gestionar las excepciones asignándolas a un valor.
 * Esto nos permite continuar con la ejecución independientemente de que se produzca una excepción.
 * La forma de gestionar de forma efectiva valores nulos o errores se revisa en la parte 3, en los apartados:
 *   - Opciones
 *   - ManejandoErrores
 *
 * @see Ejercicio disponible en [[ejercicios.parte2Objetos.ExcepcionesEnunciado]].
 */
object Excepciones extends App {

  /**
   * Los nulos existen en Scala pero su uso está desaconsejado.
   * De todas formas, cuando recibimos información del exterior, podemos recibir nulos.
   * También podemos generar nulos dentro de nuestra aplicación si usamos APIs no seguras.
   */
  val unaCadenaNula: String = null

  // Si ejecutas la siguiente línea, la ejecución se interrumpirá en este punto
  //unaCadenaNula.length

  /**
   * Podemos crear métodos que en alguna situación generen como salido una excepción.
   * En el caso de este método, lanzamos directamente una excepción de puntero nulo.
   * @return NullPointerException (de tipo Nothing en Scala).
   */
  def unValorExtraño: Nothing = throw new NullPointerException

  //unValorExtraño //Si descomentas la línea, se produce la excepción y se termina la ejecución.


  /**
   * El método anterior es una simplificación. Normalmente, tenemos métodos que devuelven algo o bien lanzan una excepción.
   * @param conExcepción booleano que decide si devolvemos un valor o lanzamos una excepción.
   * @return el tipo de retorno del método es Int, aunque pueda lanzar una excepción (de tipo Nothing).
   */
  def conseguirEntero(conExcepción: Boolean): Int = {
    if (conExcepción) throw new RuntimeException("¡No conseguirás ningún entero hoy!")
    else 42
  }

  // ¿Cómo podemos capturar excepciones durante la ejecución?

  /**
   * Scala permite crear bloques de código dentro del patrón try {} catch {} finally {}.
   *
   * El bloque de código del try contiene la lógica que puede producir una excepción o devolver un valor.
   *
   * El bloque de código del catch es un macheo de patrón. Si el bloque del try produce un [[Throwable]],
   * machea su tipo contra los casos definidos en su bloque de código.
   * A la derecha del match podemos generar cualquier tipo de salida, incluso seguir con la ejecución.
   *
   * El bloque de finally es opcional. Es un código que se ejecuta siempre, independientemente del try o el catch.
   * Suele usarse para logging u otras operaciones de I/O
   */
  try {
    conseguirEntero(true)
  } catch {
    case r: RuntimeException => println("¡Cazado un error durante la ejecución!")
  } finally {

    println("¡Scala es fenomenal!")
  }

  /**
   * Scala nos permite generar clases específica para cada excepción contemplada en nuestro código.
   * Para ello, sólo tenemos que extender [[Exception]].
   */
  class MyException extends Exception

  /**
   * Podemos generar instancias de nuestra excepción particular.
   */
  val exception = new MyException

}