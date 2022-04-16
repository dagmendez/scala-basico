package ejercicios.parte1Basico

import scala.annotation.tailrec

object FuncionesSolucion {

  def main(args: Array[String]): Unit = {

    println(saludoParaNiños("Daniel", 100))

    println(factorial(3) + " should be 6")
    println(funciónFactorial(4) + " should be 24")

    println(s"Fibonacci: ${fibonacci(10)}")
    println(s"Fibonacci_v1: ${funciónFibonacci(5)}")

    println(esPrimo(19))
  }

  def saludoParaNiños(nombre: String, edad: Int): String = {
    "Hola, me llamo " + nombre + " y tengo " + edad + " años."
  }

  /**
   * Método que calcula el factorial de un número de forma recursiva.
   *
   * @param número Int, valor del que se calcula el factorial.
   * @return Factorial de "número" en tipo Int.
   */
  def factorial(número: Int): Int = {
    if (número <= 1) número
    else número * factorial(número - 1)
  }

  /**
   * Mismo método que [[factorial()]] pero con notación the función y estructura if/else de Scala 3 (uso de then).
   *
   * @return Factorial de "número" en tipo Int.
   */
  def funciónFactorial: Int => Int = {
        // Esta es la forma de escribir el código en Scala-2, con {}
    número => {
      if (número <= 1) {
        número
      } else {
        número * funciónFactorial(número - 1)
      }
    }
  }

  /**
   * Método recursivo que calcula el valor de Fibonacci del valor "número".
   *
   * @param número valor inicial, en Int
   * @return valor de Fibonacci para "número".
   */
  def fibonacci(número: Int): Int = {
    // Esta es la forma de escribir el código en Scala-3, sin {} y para el if else se usa then
    if número <= 2 then 1
    else fibonacci(número - 1) + fibonacci(número - 2)
  }

  /**
   * Mismo método que [[fibonacci()]] pero con notación the función y estructura if/else de Scala 3 (uso de then).
   *
   * @return valor de Fibonacci para "número".
   */
  def funciónFibonacci: Int => Int = {
    número => {
      if número <= 2 then 1
      else funciónFibonacci(número - 1) + funciónFibonacci(número - 2)
    }
  }

  /**
   * Método / Función recursiva de cola que nos indica si un número es primo o no.
   *
   * @param número número a analizar
   * @return booleano verdadero para primos y falso para no primos.
   */
  def esPrimo(número: Int): Boolean = {
    // Añadimos la anotación de @tailrec para indicarle al compilador que es una función recursiva de cola.
    // Este tipo de funciones es mejor definirlas así para que el compilador pueda realizar optimizaciones.
    @tailrec
    def esPrimoHasta(t: Int): Boolean = {
      if t <= 1 then true
      else número % t != 0 && esPrimoHasta(t - 1)
    }

    esPrimoHasta(número / 2)
  }

}
