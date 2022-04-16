package ejercicios.parte1Basico

import scala.annotation.tailrec

/**
 * @see Solución disponible en [[RecursionSolucion]].
 */
object RecursionEnunciado {

  def main(args: Array[String]): Unit = {
    //Ejecuta aquí
  }


  /**
   * Este método / función concatena el mismo string (s) un número de veces (v).
   * @param s      string a concatenar.
   * @param veces  número de veces que lo concatenamos.
   * @param result es el acumulador.
   * @return un string con el valor "s" concatenado "v" veces.
   */

  def concatenaciónRecursivaDeCola(s: String, veces: Int, result: String = ""): String = ???

  /**
   * En este implementación recursiva de cola para el la identificación de números primos, necesitamos un sub-método.
   * Esto se debe a dos motivos:
   *  - necesitamos dividir el número de entrada por dos para eliminar los posibles número pares.
   *  - existe una condición interna que evalúa el número inicial (n) y el número interno.
   *
   * @param n número a evaluar.
   * @return Boolean true si el número es primo, false si no lo es.
   */
  def esPrimoRecursivoDeCola(n: Int): Boolean =  ???

  /**
   * Pista: En esta función recursiva incluimos una condición previa a la llamada del método auxiliar y 3 acumuladores.
   * @param número número sobre el que calcular Fibonacci.
   */
  def fibonacciRecursivoDeCola(número: Int): Int = ???

}
