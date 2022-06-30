
package lecciones.parte3Funciones

import scala.util.Random

/**
 * Lección 5 de 8.
 * 
 * Scala cuenta de forma nativa con una funcionalidad extensa para las colecciones.
 * Muchos métodos están disponibles, entre ellos: map, flatMap y filter.
 * Aquí veremos algunos ejemplos introductorios.
 */
object Colecciones extends App {

  /**
   * En Scala hay varios tipos de colecciones. Una de la más genéricas es [[Seq]].
   * Seq hereda de [[Iterable]].
   * Seq es un Trait, por lo que cuando se genera una instancia de Seq[T], se implementa otra sub-colección.
   * Algunas sub-colecciones son las listas, los vectores, los rangos...
   * Las secuencias, como sus sub-clases, cuentan con ciertas implementaciones que nos ayudan en el día a día
   */

  val unaSecuencia = Seq(true, false, false, true)

  // La secuencia de Boolean se convierte a String para poder ser imprimible
  println(unaSecuencia)

  // Las secuencias se pueden invertir
  println(unaSecuencia.reverse)

  // El método apply de una secuencia nos devuelve el elemento que está guardado en esa posición
  println(unaSecuencia(2))

  // El operador ++ genera una nueva secuencia al juntar dos de forma secuencial
  println(unaSecuencia ++ Seq(true, true, true))

  // Las secuencias se pueden ordenar, siempre que haya disponible una función de orden para el tipo de dato
  println(unaSecuencia.sorted)

  /**
   * Rangos: Un rango es una secuencia ordenada de enteros (Int) en intervalos constantes.
   * @example “1, 2, 3,” es un rango, así como “5, 8, 11, 14.” El primero con intervalo 1 y el segundo con intervalo 3.
   * Los rangos se pueden crear utilizando las palabra "to", "until" y "by" o con los métodos accesorios de [[Range]]
   *
   * Ya que los rangos se crean con, como máximo 3 parámetros, son estructuras muy rápidas.
   */


  // Rango del 10 al 20, ambos incluidos
  val rangoInclusivo = 10 to 20
  println(s"rangoInclusivo: ${rangoInclusivo.mkString(",")}")

  // Rango del 0 al 3, ambos incluidos y de 3 en 3
  val rangoInclusivo3 = 0 to 3 by 3
  println(s"rangoInclusivo3: ${rangoInclusivo3.mkString(",")}")

  // Rango del 1 al 10, 10 excluido
  val rangoExclusivo = 1 until 10
  println(s"rangoExclusivo: ${rangoExclusivo.mkString(",")}")

  // Concatenación de rangos
  println(rangoExclusivo ++ rangoInclusivo)

  // Inclusión de un elemento al principio del rango
  println(0 +: rangoExclusivo)

  // Inclusión de un elemento al final del rango
  println(rangoExclusivo :+ 10)

  // Inclusión de un elemento tanto al principio como al final del rango
  println(0 +: rangoExclusivo :+ 10)

  /**
   * Los rangos suelen ser utilizados en acciones que se han de repetir o que siguen cierta numeración ordenada.
   */
  // Imprimo diez veces que estudio Scala cada día
  rangoExclusivo.foreach(x => println("¡Estudio Scala cada día!"))

  // Imprimo diez veces de mayor a menor los días que restan para ser un maestro de Scala
  rangoExclusivo.reverse.foreach(x => println(s"¡$x días restantes para convertirme en un maestro de Scala!"))

  /**
   * Listas: Una lista es una secuencia finita inmutable.
   * Proporciona acceso en tiempo constante al primer elemento y al resto de la lista y append de un elemento.
   * Otras operaciones, como prepend o acceder a un elemento en medio de la lista necesitan tiempo linear.
   */

  val unaLista = List('b','c','d','e')
  // Para añadir elementos a una lista , se utiliza "+:" y ":+" estando el "+" del lado del elemento y el ":" del
  // lado de la lista
  println(unaLista)
  println('a'+:unaLista:+'f')

  // Para generar una lista, se puede utilizar "::" con último elemento "Nil"
  val otraLista = 'f'::'g' :: Nil
  println(otraLista)

  // Para generar una nueva lista de dos existentes, usamos ":::"
  val dosListas = unaLista ::: otraLista
  println(dosListas)

  // Ejemplo de uso de listas de objetos creados por nosotros y utilización de map
  case class Manzana(nombre: String) {
    val manzanaOrgullosa: String = s"¡Estoy muy orgullosa de ser una manzana $nombre!"
  }
  // fill rellena un lista de longitud $primerParámetro (5) con objetos de $segundoParámetro (Manzana)
  val `5applesList` = List.fill(5)(Manzana("Golden"))
  println(`5applesList`)
  println(`5applesList`.map((a: Manzana) => a.manzanaOrgullosa).mkString("\n"))

  /**
   * Arrays
   * Los array son un tipo de colección especial en Scala. Por una parte, son exactamente iguales a los array de Java.
   * Esto quiere decir que un Array[Int] se representa como un Java int[] a para cualquier otro tipo primitivo de Java.
   * Pero al mismo tiempo, los Arrays en Scala ofrecen mucho más:
   *   - Pueden ser genéricos: Array[T]
   *   - Son compatibles con las secuencias (Seq[T]) por lo que puedes usar Array[T] donde la firma pida una Seq[T]
   *   - Tienes a tu disposición todas las operaciones propias de Seq[T]
   */


  // 1.1) Creamos un array desde una colección que hereda de Seq[T]
  val números = Range.inclusive(20,30).toArray
  println(números.mkString("Array(", ", ", ")"))

  // 1.2) Creamos una Seq[Int] desde un Array[Int] utilizando las conversiones implícitas de Scala
  val numberSeq: Seq[Int] = números //conversión implícita
  println(numberSeq) //ArraySeq Type

  // 2) Creamos un array de Int. No hace falta indicar el tipo. Scala lo infiere.
  val pocosNúmeros = Array(0,1,2,3)
  println(pocosNúmeros.mkString("Array(", ", ", ")"))

  // 3) Creamos un array con un límite de elementos (hace falta indicar el tipo, al no poder inferirse)
  val cuatroNúmerosVacio = Array.ofDim[Int](4)

  // 3.1) Sin imprimimos el array "vacío", estará lleno del elemento "neutro" de ese tipo. Para Int es 0
  println(cuatroNúmerosVacio.mkString("Array(", ", ", ")"))

  // 3.2) Para Boolean, el valor "neutro" es false
  println(Array.ofDim[Boolean](2).mkString("Array(", ", ", ")"))

  // 3.3) Para cualquier tipo NO primitivo de Java, el valor "neutro" es null
  println(Array.ofDim[String](2).mkString("Array(", ", ", ")"))
  println(Array.ofDim[Manzana](2).mkString("Array(", ", ", ")"))

  // 4) Mutación: Los array son mutables, por lo que se recomienda su uso de forma cuidadosa.
  cuatroNúmerosVacio(0) = 10
  println(cuatroNúmerosVacio.mkString("Array(", ", ", ")"))

  Range.inclusive(0,3).foreach((i: Int) => cuatroNúmerosVacio(i) = i)
  println(cuatroNúmerosVacio.mkString("Array(", ", ", ")"))

  /**
   * Vectores: son secuencias indexadas inmutables. El hecho de que sea indexado significa que permite acceder
   * de forma aleatoria a cualquier elemento y actualizarlo en tiempo constante.
   * @note La implementación de Vector fue reescrita en la version 2.13 de Scala. En versiones anteriores, el vector
   *       no tiene buen rendimiento. A partir de la 2.13, el vector es la colección mejor balanceada para cualquier
   *       tipo de operación de todas las disponibles.
   */

  val unVector: Vector[Any] = Vector("jaja", 'a', 5, 10L, Manzana("Herrero"))
  println(unVector)

  // Las implementaciones de añadir elementos son idénticas a las de la lista: "+:" y ":+"
  val otroVector = "principio" +: unVector :+ "fin"
  println(otroVector)

  /**
   * Este objeto demuestra que ante operaciones de Update, el vector tiene mejor rendimiento que la lista.
   */
  object VectorVsList {
    /**
     *
     * @param colección Seq[Int] de 0 a n ([[longitudColección]])
     * @param longitudColección número máximo de elementos de la colección
     * @param ejecuciones número de veces que se ejecuta la actualización completa de la lista y el vector
     * @return Porcentaje sin formato
     */
    def obtenerTiempoDeEscritura(colección: Seq[Int], longitudColección: Int, ejecuciones: Short): Double = {
      val aleatorio = new Random()
      val tiempoDeActualización: IndexedSeq[Long] = for {
        run <- 1 to ejecuciones
      } yield {
        val currentTime = System.nanoTime()
        colección.updated(aleatorio.nextInt(longitudColección), aleatorio.nextInt())
        System.nanoTime() - currentTime
      }
      tiempoDeActualización.sum.toDouble / ejecuciones
    }

    /**
     * Devuelve el resultado con una traza formateada.
     * @param longitudColección número de elementos de la lista y el vector
     * @param ejecuciones número de ejecuciones de actualización completa en las colecciones
     */
    def resultados(longitudColección: Int, ejecuciones: Short): Unit = {
      val colección: Seq[Int] = Range.inclusive(0, longitudColección)
      val duraciónLista: Double = obtenerTiempoDeEscritura(colección.toList, longitudColección, ejecuciones)
      val duraciónVector: Double = obtenerTiempoDeEscritura(colección.toVector, longitudColección, ejecuciones)
      val porcentaje = f"${(duraciónLista/duraciónVector)*100}%8.2f"
      println(s"El Vector es un $porcentaje% más rápido que la lista")
    }
  }

  VectorVsList.resultados(1000, 10)

}
