package ejercicios.parte3Funciones

import scala.util.{Random, Try}
import lecciones.parte3Funciones.ManejandoErrores

/**
 * Ejercicio de la parte teórica [[ManejandoErrores]].
 *
 * Tareas: En caso de que consigas una conexión válida, tienes que imprimirla por pantalla.
 *   - Utiliza un wrapper manual
 *   - Utiliza un for-comprehension con wrapper manuales
 *   - Utiliza un for-comprehension usando los métodos de las clases
 *
 * Solución en [[ManejandoErroresSolucion]].
 */
object ManejandoErroresEnunciado {

  def main(args: Array[String]): Unit = {

    println("Utiliza un wrapper manual")
    val httpService: Try[Conexión] = ???
    httpService
      .map(???)
      .foreach(???)


    println("Utiliza un for-comprehension con wrapper manuales")

    val htmlForCom: Try[String] = ???
    //No podemos utilizar directamente Try[String] como String en  renderizarHTML
    //renderizarHTML(htmlForCom) //No va a funcionar

    //si usamos foreach/map, ¡funciona!
    htmlForCom.foreach(???)

    println("Utiliza un for-comprehension usando los métodos de las clases")

    //for {
    //  connection <- ???
    //  html <- ???
    //} renderizarHTML(html)

  }


  val nombreDelServidor: String = "localhost"
  val puerto: String = "8080"

  def renderizarHTML(page: String): Unit = println(page)

  class Conexión {
    def obtener(url: String): String = {
      val aleatorio = new Random(System.nanoTime())
      if (aleatorio.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Conexión interrumpida")
    }

    def obtenerSeguro(url: String): Try[String] = Try {
      val aleatorio = new Random(System.nanoTime())
      if (aleatorio.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

  }

  object ServicioHTTP {
    val aleatorio = new Random(System.nanoTime())

    def obtenerConexión(host: String, port: String): Conexión = {
      if (aleatorio.nextBoolean()) new Conexión
      else throw new RuntimeException("Alguien está usando ese puerto")
    }

    def obtenerConexiónSegura(host: String, port: String): Try[Conexión] = Try {
      if (aleatorio.nextBoolean()) new Conexión
      else throw new RuntimeException("Alguien está usando ese puerto")
    }
  }


}
