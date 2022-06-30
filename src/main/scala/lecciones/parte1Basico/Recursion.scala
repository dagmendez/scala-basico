package lecciones.parte1Basico

import scala.annotation.tailrec

/**
 * Lección 4 de la parte 1.
 * 
 * La recursión es la forma correcta de iterar en el paradigma funcional.
 * Los bucles while, loop, do... aunque existen, no se recomienda su uso.
 * Esta lección contiene implementaciones de recursión de cola para método implementados en la lección de Funciones.
 *
 * @see Ejercicios disponibles en [[ejercicios.parte1Basico.RecursionEnunciado]]
 */
object Recursion extends App {

  /**
   * Aquí tenemos de nuevo la implementación de factorial con un llamada recursiva normal.
   * Estas llamadas dan problemas cuando la recursión es muy grande.
   * En esos casos, podemos producir el famoso error "Stack Overflow".
   *
   * @param número Si el número es lo suficientemente grande, provocaremos el Stack Overflow
   * @return factorial de un número
   */
  def factorial(número: Int): Int = {
    if (número <= 0) 1
    else {
      // Se añaden las impresiones en pantalla para que veas el funcionamiento de la pila en la JVM.
      println(s"Computing factorial of $número - I first need to compute factorial of ${número - 1}")
      val result: Int = número * factorial(número - 1)
      println(s"Computing factorial of $número")
      result
    }
  }

  factorial(5) //funciona
  //eliminar el comentario en la siguiente linea para provocar el error
  //factorial(10000) //<- stack overflow

  /**
   * Para evitar el error de Stack Overflow, se recurre a las llamadas recursivas de cola.
   * Estas llamadas funcionan en colecciones muy grandes.
   *
   * @param i número del cual calcular su factorial
   * @return factorial del número "i"
   */
  def factorialRecursivoDeCola(i: Int): BigInt = {
    /** *
     * Normalmente, en los métodos recursivo de cola, nos encontraremos métodos auxiliares con acumuladores.
     * Estos acumuladores nos permiten guardar estados de computación intermedios de cara al valor final.
     *
     * @param x           valor de entrada, heredado de la función global
     * @param accumulator valor inicial del acumulador. Al final del cálculo, contiene el resultado.
     * @return el valor final del acumulador.
     */
    @tailrec
    def factorialAyudante(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialAyudante(x - 1, x * accumulator)
    }
    // Llamamos a la función auxiliar en la última línea del bloque de código de la función principal
    factorialAyudante(i, 1)
  }
  // Ahora ya no falla la llamada con número grandes
  println(factorialRecursivoDeCola(5000))
  
}
