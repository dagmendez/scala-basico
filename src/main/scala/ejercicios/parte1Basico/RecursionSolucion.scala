package ejercicios.parte1Basico

import scala.annotation.tailrec

object RecursionSolucion {

  def main(args: Array[String]): Unit = {

    println(concatenaciónRecursivaDeCola("/\\", 3))

    println(esPrimoRecursivoDeCola(17))

    println(fibonacciRecursivoDeCola(8))
  }

  /**
   * Este método / función concatena el mismo string (s) un número de veces (v).
   * Podemos evitar la creación de funciones auxiliares si realizamos dos pasos:
   *   - incluir el acumulador como parámetro en la función
   *   - asignarle al acumulador un valor por defecto en el constructor de la función
   *
   * @param s      string a concatenar
   * @param veces  número de veces que lo concatenamos
   * @param result es el acumulador. El valor inicial para los string es el string vacío.
   * @return un string con el valor "s" concatenado "v" veces.
   */
  @tailrec
  def concatenaciónRecursivaDeCola(s: String, veces: Int, result: String = ""): String = {
    if veces <= 0 then result
    else concatenaciónRecursivaDeCola(s, veces - 1, s"$s$result")
  }

  /**
   * En este implementación recursiva de cola para el la identificación de números primos, necesitamos un sub-método.
   * Esto se debe a dos motivos:
   *  - necesitamos dividir el número de entrada por dos para eliminar los posibles número pares
   *  - existe una condición interna que evalúa el número inicial (n) y el número interno (t) => n % t != 0
   *
   * @param n número a evaluar
   * @return Boolean true si el número es primo, false si no lo es
   */
  def esPrimoRecursivoDeCola(n: Int): Boolean = {
    @tailrec
    def todavíaPrimo(t: Int, isStillPrime: Boolean = true): Boolean = {
      if !isStillPrime then false
      else if t <= 1 then true
      else todavíaPrimo(t - 1, n % t != 0 && isStillPrime)
    }
    // Podemos seguir asignando un valor por defecto al acumulador
    todavíaPrimo(n / 2)
  }

  /**
   * En esta función recursiva incluimos una condición previa a la llamada del método auxiliar y 3 acumuladores
   *
   * @param número número sobre el que calcular Fibonacci
   */
  def fibonacciRecursivoDeCola(número: Int): Int = {
    /**
     * En este método recursivo de cola para calcular el valor de Fibonacci de un número, usamos 3 valores por defecto:
     *
     * @param númeroEnLaIteración el valor resultante de la primera suma de Fibonacci (2)
     * @param último              el último valor de la línea superior (1)
     * @param penúltimo           el penúltimo valor de la línea superior (1)
     * @return el valor de Fibonacci del número
     */
    @tailrec
    def auxiliar(númeroEnLaIteración: Int = 2, último: Int = 1, penúltimo: Int = 1): Int = {
      // inyectamos el valor "número" en la condición de salida de la llamada recursiva 
      if númeroEnLaIteración >= número then último
      else auxiliar(númeroEnLaIteración + 1, último + penúltimo, último)
    } // Si el número es menor de 2, entonces su valor de Fibonacci es 1 y no tenemos que llamar a la función auxiliar

    if número <= 2 then 1
    // en otro caso, llamamos a la función auxiliar sin parámetros, ya que el valor externo (número) está inyectado.
    else auxiliar()
  }

}
