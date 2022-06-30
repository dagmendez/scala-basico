package lecciones.parte1Basico

/**
 * Lección 5 de la parte 1.
 * 
 * CBN: Call-by-Name: Llamada por nombre
 * CBV: Call-by-Value: Llamada por valor
 * En esta lección revisamos las diferencias de llamar por nombre o por valor en Scala así como sus usos más habituales.
 */
object CBNvsCBV extends App {

  /**
   * Esta es la forma más habitual de generar método en Scala. La llamada a X se hace por su valor.
   * Esto quiere decir que antes de que se ejecute el cuerpo del método, el parámetro x es evaluado.
   * Su valor (el valor de x) se calcula antes de ser inyectado en el cuerpo del método.
   *
   * @param x parámetro de entrada del método
   * @return Imprime por pantalla dos veces el mismo valor del parámetro x.
   */
  def llamadaPorValor(x: Long): Unit = {
    println(s"por valor $x")
    println(s"por valor $x")
  }

  // Imprime dos veces el mismo valor
  llamadaPorValor(System.nanoTime())

  /**
   * La llamada por nombre suele aparecer con más frecuencia en proyectos que trabajan en tiempo real.
   * La llamada a X se hace por su nombre de forma literal. La definición de X se inyecta dentro del cuerpo del método.
   * Esto quiere decir que la evaluación del valor de X se produce dentro del cuerpo del método.
   * Su valor (el valor de x) se calcula cada vez que X es utilizada.
   *
   * @param x parámetro de entrada del método
   * @return Imprime por pantalla dos veces el valor de X correspondiente al momento en el tiempo de ejecución del hilo.
   */
  def llamadaPorNombre(x: => Long): Unit = {
    println(s"por nombre $x")
    println(s"por nombre $x")
  }

  // Imprime un valor diferente cada vez
  llamadaPorNombre(System.nanoTime())

  //Vamos a ver algunos usos:

  /** Este método es recursivo y no tiene condición de finalización. Si se llama, se producirá un error de
   * desbordamiento de la pila (Stack OverFlow).
   * @return Exception in thread "main" java.lang.StackOverflowError at davidagm.lecciones.parte1basico.CBNvsCBV$
   *         .infinito(CBNvsCBV.scala:51)
   */
  def infinito(): Int = 1 + infinito()

  /**
   * Método que imprime por pantalla el primer elemento, que es llamado por valor. Ignora el segundo método, llamado por
   * nombre.
   *
   * @param x parámetro llamado por valor
   * @param y parámetro llamado por nombre
   * @return imprime por pantalla el valor de x
   */
  def imprimirElPrimero(x: Int, y: => Int): Unit = {
    println(x)
  }

  // Si x es igual a un valor no infinito, podremos ejecutar
  imprimirElPrimero(34, infinito())

  // Ejecuta este código para validar el error
  //imprimirElPrimero(infinito(), 34)
}
