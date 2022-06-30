
package lecciones.parte3Funciones

/**
 * Lección 2 de 8.
 * 
 * Las funciones anónimas, también llamadas "lambda", se utilizan de forma intensa en Scala.
 * En lugar habitual donde se pueden encontrar es dentro de HOFs como map, flatMap y filter y muchas otras.
 */
object FuncionesAnonimas extends App {

  // 1) Funciones sin parámetro
  val hazAlgo: () => Int = () => 3
  // ¡Cuidado!
  println(hazAlgo) // esto es una referencia a la propia función y nos informa de su localización en memoria
  println(hazAlgo()) // esto evalúa la función

  // 2) Funciones con un parámetro
  val doble: Int => Int = x => x * 2
  println(List(1,2).map(doble))

  // 3) Funciones con 2 o más parámetros
  val suma: (Int, Int) => Int = (a, b) => a + b
  println(List(1,-1).reduce(suma))

  /**
   * Syntactic Sugar == Sintaxis dulce
   */
  // Se pueden escribir funciones anónimas como bloques de código
  // Scala 2
  val stringAEntero_s2 = { (str: String) =>
    str.toInt
  }
  // Scala3
  val stringAEntero_s3 = (str: String) =>
    str.toInt

  // Al definir la firma de la función, no es necesario indicar el tipo del parámetro. El parámetro es la barra baja.
  val incrementaDeUnoEnUno: Int => Int = _+1 // igual a: (x: Int) => x + 1

  // Con dos parámetros también es posible utilizar las barras bajas
  val sumaAnónima: (Int, Int) => Int = _+_ // igual a: (x, y) => x+y

}
