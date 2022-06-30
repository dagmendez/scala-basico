
package lecciones.parte3Funciones

import scala.annotation.tailrec
import ejercicios.parte3Funciones.{HOFsYCurriesEnunciado, ColeccionFuncionalExtendidaEnunciado}

/**
 * Lección 3 de 8.
 * 
 * Las HOFs (Funciones de alto orden) son funciones que reciben otras funciones como parámetro. Las más conocidas son:
 *   - map
 *   - flatMap
 *   - filter
 *
 * Currying es un técnica que permite componer funciones con varios grupos de parámetros.
 * Estas dos técnicas del paradigma funcional permiten generalizar y reutilizar código.
 * 
 * Ejercicios en [[HOFsYCurriesEnunciado]] y [[ColeccionFuncionalExtendidaEnunciado]].
 */
object HOFsYCurries extends App {

  // Uno de los parámetros de esta función es otra función
  val hofEjemplo: (String, (Int, Boolean => String) => Double) => (Int, Int) = null

  // Equivalente, pero en notación "def"
  def hofEjemplo(s: String, f: (Int, Boolean => String) => Double): (Int, Int) = {
    null
  }

  /**
   * En este ejemplo, tenemos una método que ejecuta n veces una función sobre un número.
   * Este método toma una función (f) como parámetro de entrada.
   *
   * @param f la función
   * @param n la cantidad de veces que esa función se ejecuta
   * @param x el número sobre el que se ejecuta la función
   * @return un número
   */
  @tailrec
  def nVeces(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nVeces(f, n - 1, f(x))
  }

  // Definimos la función que suma 1 al número que recibe por parámetro
  val másUno = (x: Int) => x + 1
  
  // Resultado = 10
  println(nVeces(másUno, 9, 1))

  /**
   * En este ejemplo, tenemos un método que recibe una función por parámetro y el número dde veces que se ejecuta.
   * Sin embargo, no tenemos número inicial. ¿Qué sucede?
   *
   * @param f función a ejecutar n veces sobre un número
   * @param n número de veces que se ejecuta la función
   * @return Una función que recibe un número y devuelve el resultado de aplicar f a ese número n veces.
   */
  def nVecesFunción(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nVecesFunción(f, n - 1)(f(x))
  }

  // Llamando a nVecesFunción generamos una función.
  val más10: Int => Int = nVecesFunción(másUno, 10)
  
  // El resultado es 12
  println(más10(2))

  /**
   * La técnica de "currying" de funciones permite aprovechar una definición general para generar casos específicos.
   */

  val sumaCurrificada = (x: Int) => (y: Int) => x + y
  
  // Llamamos a suma currificada con solo el valor de x. Así generamos un caso específico donde el valor de x es 3
  val suma3 = sumaCurrificada(3)
  
  // Una vez que tenemos el caso específico, aportamos el valor de y y se genera el resultado
  println(suma3(2) == 5)
  
  // Si queremos usar la función currificada, tenemos que aportar el valor de x y de y en dos bloques de paréntesis.
  println(sumaCurrificada(9)(1) == 10)

  /**
   * Este método tiene dos grupos de parámetros. Es un método currificado. El orden de los bloques suele ser:
   *   - 1er bloque: el valor que se suele repetir en muchos casos
   *   - 2do bloque: el valor que varia para cada caso
   *
   * @note Los bloques pueden tener varios parámetros cada uno
   * @param formato       formato con el que se quiere mostrar el número
   * @param númeroDecimal número a formatear
   * @return número formateado en String
   */
  def formateadorCurrificado(formato: String)(númeroDecimal: Double): String = {
    formato.format(númeroDecimal)
  }
  
  // Podemos hacer una llamada al método general con los dos grupos de parámetros
  println(formateadorCurrificado("%10.8f")(Math.PI))
  
  // Podemos definir formatos que se usará para muchos casos
  val formatoTradicional = formateadorCurrificado("%4.2f")
  val formatoDePrecisión = formateadorCurrificado("%10.8f")
  
  // Una vez definidos los formatos, los usamos para los casos variables
  println(formatoTradicional(Math.E))
  println(formatoDePrecisión(Math.E))

}
