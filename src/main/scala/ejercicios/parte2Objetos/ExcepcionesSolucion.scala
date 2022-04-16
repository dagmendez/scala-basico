package ejercicios.parte2Objetos

import scala.annotation.targetName

object ExcepcionesSolucion {

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


    // Suma
    println(CalculadoraDeBolsillo.+(4, 5)) // OK
    println(CalculadoraDeBolsillo.+(Int.MaxValue, 0)) // OK
    //println(CalculadoraDeBolsillo.+(Int.MaxValue, 1)) // KO

    // Resta
    println(CalculadoraDeBolsillo.-(2, 3)) // OK
    println(CalculadoraDeBolsillo.-(-2, Int.MinValue)) // OK
    //println(CalculadoraDeBolsillo.-(Int.MinValue, 3)) // KO

    // Multiplicación
    println(CalculadoraDeBolsillo.*(2, -3)) // OK
    //println(CalculadoraDeBolsillo.*(2, Int.MaxValue)) //KO
    //println(CalculadoraDeBolsillo.*(Int.MaxValue, -2)) //KO

    // División
    println(CalculadoraDeBolsillo./(4, -4)) // OK
    //println(CalculadoraDeBolsillo./(2, 0)) // KO

  }

  /**
   * Creamos las clases para los posibles errores:
   *   - Desbordamiento de la clase Integer -> OverFlowException
   *   - "Underflow" de la clase Integer -> UnderFlowException
   *   - División por cero -> MathCalculationException
   */
  class OverFlowException extends Exception

  class UnderFlowException extends Exception

  class MathCalculationException extends Exception("Dividir por cero")

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
    def +(x: Int, y: Int): Int = {
      // Atrapamos el resultado de la suma en un val
      val result = x + y
      // Validamos que el resultado no produce un OverFlowException (superar el valor máximo de la clase Int)
      if (x > 0 && y > 0 && result < 0) throw new OverFlowException
      else result
    }

    /**
     * Método que realiza la resta. Sí, se puede llamar "-"
     *
     * @param x minuendo
     * @param y sustraendo
     * @return diferencia (minuendo - sustraendo)
     */
    @targetName("resta")
    def -(x: Int, y: Int): Int = {
      // Atrapamos el resultado de la resta en un val
      val result = x - y
      // Validamos si se ha desbordado el valor máximo de la clase Int
      if (x > 0 && y < 0 && result < 0) throw new OverFlowException
      // Validamos si se ha desbordado el valor mínimo de la clase Int
      else if (x < 0 && y > 0 && result > 0) throw new UnderFlowException
      else result
    }

    // Misma lógica que suma y resta
    @targetName("multiplicación")
    def *(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && result < 0) throw new OverFlowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderFlowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderFlowException
      else result
    }

    // Misma lógica que suma y resta pero con un error diferente.
    @targetName("división")
    def /(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

}
