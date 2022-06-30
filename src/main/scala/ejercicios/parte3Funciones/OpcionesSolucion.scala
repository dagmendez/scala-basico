package ejercicios.parte3Funciones

import scala.util.Random

/**
 * Solución al ejercicio [[OpcionesEnunciado]].
 */
object OpcionesSolucion {

  def main(args: Array[String]): Unit = {
    // 1) Imprimir la conexión
    println(conexión)

    // 2) Imprimir el estado de la conexión
    println(estadoDeLaConexión)
    estadoDeLaConexión.foreach(println)

    // 3) Imprimir estado de la conexión utilizando llamadas en cadenadas
    soluciónEncadenada.foreach(println)

    // 4) Imprimir el estado de la conexión utilizando for-omprehension
    println(soluciónConForComprehension)
  }
  
  val configuración: Map[String, String] = Map(
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
  val ip: Option[String] = configuración.get("ip")
  val puerto: Option[String] = configuración.get("puerto")
  val conexión: Option[Conexión] = {
    ip.flatMap(h => // if host is not null
      puerto.flatMap(p => // if port is not null
        Conexión.apply(h, p) // then Some(Connection.apply(host, port))
      )) // else None
  }
  
  /* 2) Llamar al método conexión e imprimir el estado de la conexión en la consola. */
  val estadoDeLaConexión: Option[String] = conexión.map(_.conectar)

  /* 3) Imprimir el estado de la conexión utilizando llamadas encadenadas. */
  val soluciónEncadenada: Option[String] = {
    configuración.get("ip")
      .flatMap{ ip =>
        configuración.get("puerto")
          .flatMap{ puerto =>
            Conexión(ip, puerto)
          }
      }.map(connection => connection.conectar)
  }

  /* 4) Imprimir el estado de la conexión utilizando for-comprehension. */
  val soluciónConForComprehension: Option[Conexión] = for {
    ip <- configuración.get("ip") // Option[String]
    puerto <- configuración.get("puerto") // Option[String]
    // añadimos la construcción dentro para devolver el tipo adecuado
    conexión <- Conexión.apply(ip, puerto) //Option[Connection]
  } yield conexión
  
}
