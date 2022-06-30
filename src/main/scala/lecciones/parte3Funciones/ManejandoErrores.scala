package lecciones.parte3Funciones

import scala.util.{Failure, Random, Success, Try}
import ejercicios.parte3Funciones.ManejandoErroresEnunciado

/**
 * Lección 8 de 8.
 * 
 * Scala permite manejar computaciones que potencialmente pueden terminar en un error con la clase [[Try]].
 * Las instancias de la clase Try[T] son instancias de [[Success]][T] o [[Failure]][T]. Si la computación resulta
 * exitosa, obtendremos un Success(valor de la computación) y si se produce un error que no es fatal, obtendremos
 * un Failure(valor del error o excepción).
 * 
 * Ejercicios en [[ManejandoErroresEnunciado]].
 */
object ManejandoErrores extends App {

  // Instancias de Success y Failure se pueden implementar directamente
  val unÉxito = Success(42)
  val unFracaso = Failure(new RuntimeException("¡Super fallo!"))
  println(unÉxito)
  println(unFracaso)

  /**
   * Esto es un ejemplo de un método que tendría que devolver un String pero falla durante la ejecución
   * @return String, pero produce un error durante la ejecución.
   */
  def métodoNoSeguro(): String = throw new RuntimeException("No hay String para ti, ¡tramposo!")
  //Si descomentamos la la llamada al método, se producirá el error
  //métodoNoSeguro()

  /**
   * La clase Try nos permite encapsular el resultado de la ejecución.
   * En caso de que se produzca el error, la ejecución continúa.
   */
  val errorPotencial = Try(métodoNoSeguro())
  // Ahora, aunque se sigue produciendo el error, este se queda atrapado en una variable.
  println(errorPotencial)

  // La clase Try también permite bloques de código
  val otroErrorPotencial = Try {
    //code that might throw
    métodoNoSeguro()
  }
  println(otroErrorPotencial)

  /**
   * Utilidades de la clase Try.
   * Las instancias de la clase Try tienen métodos que nos ayudan a gestionar si son Success o Failure. Por ejemplo:
   *   - isSuccess: evalúa a booleano true si es Success o booleano false si es Failure
   *   - orElse: nos permite llamar a un método secundario en caso de que se produzca un error en el primer método.
   */

  println(errorPotencial.isSuccess)
  def métodoAlternativo(): String = "Un resultado válido"
  val intentoConMétodoAlternativo = Try(métodoNoSeguro()).orElse(Try(métodoAlternativo()))
  println(intentoConMétodoAlternativo)

  /**
   * La clase Try, es considerada como en Scala como una colección de un sólo elemento.
   * Esto quiere decir que tenemos a nuestra disposición map, flatMap y filter.
   * A su vez, esto quiere decir que podemos utilizar esta clase en for - comprehensions.
   * @see for - comprehensions: [[ManejandoErroresEnunciado]]
   */

  println(unÉxito.map(_*2))
  println(unÉxito.flatMap(x => Success(x*10)))
  println(unÉxito.filter(_ > 10))

  /**
   * En caso de que estés pensando en crear una librería, Try nos permite escribir APIs más seguras,
   */
  def mejorMétodoNoSeguro(): Try[String] = Failure(new RuntimeException("Fallo"))
  def mejorMétodoAlternativo(): Try[String] = Success("Un resultado válido")
  val mejorIntentoConMétodoAlternativo = mejorMétodoNoSeguro() orElse mejorMétodoAlternativo()
  println(mejorIntentoConMétodoAlternativo)

}
