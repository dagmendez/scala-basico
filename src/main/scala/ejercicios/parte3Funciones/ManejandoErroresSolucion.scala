package ejercicios.parte3Funciones

import lecciones.parte3Funciones.ManejandoErrores

import scala.util.{Random, Try}


/**
 * Solución del ejercicio [[ManejandoErroresEnunciado]] que se corresponde con la parte teórica [[ManejandoErrores]].
 */
object ManejandoErroresSolucion {

  def main(args: Array[String]): Unit = {

    println("Utiliza un wrapper manual")
    val httpService: Try[Conexión] = Try(ServicioHTTP.obtenerConexión(nombreDelServidor, puerto))
    httpService
      .map(connection => connection.obtener("/home"))
      .foreach(página => renderizarHTML(s"flatMap/map: $página"))


    println("Utiliza un for-comprehension con wrapper manuales")

    val htmlForCom: Try[String] = for {
      connection <- Try(ServicioHTTP.obtenerConexión(nombreDelServidor, puerto))
      html <- Try(connection.obtener("/home"))
    } yield html
    //No podemos utilizar directamente Try[String] como String en  renderizarHTML
    //renderizarHTML(htmlForCom) //No va a funcionar

    //si usamos foreach/map, ¡funciona!
    htmlForCom.foreach(página => renderizarHTML(s"for-com: $página"))

    println("Utiliza un for-comprehension usando los métodos de las clases")

    for {
      connection <- ServicioHTTP.obtenerConexiónSegura(nombreDelServidor, puerto)
      html <- connection.obtenerSeguro("/home")
    } renderizarHTML(html)

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
