package ejercicios.parte3Funciones

import scala.util.Random
import lecciones.parte3Funciones.Opciones

/**
 * Ejercicio de la parte teórica [[Opciones]].
 * Consta de 4 tareas que están detalladas en el cuerpo del código.
 *   
 * Solución disponible en [[OpcionesSolucion]].
 */
object OpcionesEnunciado {

  def main(args: Array[String]): Unit = {
    // 1) Imprimir la conexión
    //println(conexión)
    
    // 2) Imprimir el estado de la conexión
    //println(estadoDeLaConexión)
    //estadoDeLaConexión.foreach(println)
    
    // 3) Imprimir estado de la conexión utilizando llamadas en cadenadas
    //soluciónEncadenada.foreach(println)
    
    // 4) Imprimir el estado de la conexión utilizando for-omprehension
    //println(soluciónConForComprehension)
  }
  
  val configuración: Map[String, String] = 
    Map(
      "ip" -> "175.24.26.1", 
      "puerto" -> "22"
    )

  class Conexión {
    def conectar = "Conectado"
  }

  object Conexión {
    val generadorDeValoresAleatorios = new Random(System.nanoTime())

    def apply(ip: String, puerto: String): Option[Conexión] =
      if (generadorDeValoresAleatorios.nextBoolean()) Some(new Conexión)
      else None
  }

  /* 1) Intenta establecer una conexión. Si existe, imprime la instancia del objeto Conexión en la consola. */
  val ip: Option[String] = ???
  val puerto: Option[String] = ???
  val conexión: Option[Conexión] = ???
 
  
  /* 2) Llamar al método conexión e imprimir el estado de la conexión en la consola. */
  val estadoDeLaConexión: Option[String] = ???
  

  /* 3) Imprimir el estado de la conexión utilizando llamadas encadenadas. */
  val soluciónEncadenada: Option[String] = ???
  soluciónEncadenada.foreach(println)

  /* 4) Imprimir el estado de la conexión utilizando for-comprehension. */
  val soluciónConForComprehension: Option[Conexión] = ???

}
