package ejercicios.parte2Objetos

import scala.annotation.targetName

/**
 * @see Solución disponible en [[ejercicios.parte2Objetos.ExcepcionesSolucion]]
 */
object ExcepcionesEnunciado {
  
  def main(args: Array[String]): Unit = {
    /**
     * Ejercicios:
     *   - 1) Suma de dos enteros
     *   - 2) Suma del valor máximo de la clase Int (Int.MaxValue) y cero
     *   - 3) Suma del valor máximo de la clase Int y un número mayor de cero debe arrojar OverFlowException
     *   - 4) Resta de dos enteros
     *   - 5) Resta de un número negativo y el valor mínimo de la clase Int (Int.MinValue)
     *   - 6) Resta de un número positivo y el valor mínimo de la clase Int debe arrojar UnderFlowException
     *   - 7) Multiplicación de dos números enteros pequeños
     *   - 8) Multiplicación de un número positivo y el valor máximo de la clase Int debe arrojar OverFlowException
     *   - 9) Multiplicación de un número negativo y el valor mínimo de la clase Int debe arrojar UnderFlowException
     *   - 10) División de dos números cualquiera
     *   - 11) División de un número por cero debe arrojar MathCalculationException
     */

    // Ejecuta tu código aquí

  }

  /**
   * Creamos las clases para los posibles errores:
   *   - Desbordamiento de la clase Integer -> OverFlowException
   *   - "Underflow" de la clase Integer -> UnderFlowException
   *   - División por cero -> MathCalculationException
   */
  //class ??? extends Exception
  //class ??? extends Exception
  //class ??? extends Exception("Dividir por cero")

  /**
   * Creamos nuestro objeto con las operaciones básicas de la calculadora: suma, resta, multiplicación y división
   */
  object CalculadoraDeBolsillo {
    /**
     * Método que realiza la suma. Sí, se puede llamar "+"
     *
     * @param x primer elemento
     * @param y segundo elemento
     * @return suma de x e y
     */
    @targetName("suma")
    def +(x: Int, y: Int): Int = ???

    /**
     * Método que realiza la resta. Sí, se puede llamar "-"
     *
     * @param x minuendo
     * @param y sustraendo
     * @return diferencia (minuendo - sustraendo)
     */
    @targetName("resta")
    def -(x: Int, y: Int): Int = ???

    // Misma lógica que suma y resta
    @targetName("multiplicación")
    def *(x: Int, y: Int): Int = ???

    // Misma lógica que suma y resta pero con un error diferente.
    @targetName("división")
    def /(x: Int, y: Int): Int = ???
  }

}