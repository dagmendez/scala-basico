package lecciones.parte3Funciones

import ejercicios.parte3Funciones.{QuizasEnunciado, MapFlatMapFilterForEnunciado}

/**
 * Lección 4 de 8.
 * 
 * map, flatMap, filter son HOFs y las for - comprehension son formas sintácticas de jugar con ellas.
 * Estos 3 elementos son de máxima importancia en Scala. En esta sección hay ejemplos de como usarlas.
 * 
 * Ejercicios en [[QuizasEnunciado]] y [[MapFlatMapFilterForEnunciado]].
 */
object MapFlatMapFilterFor extends App {

  // Definimos dos listas para hacer operaciones con ellas
  val listaDeNúmeros = List(1, 2, 3, 4)
  val listaDeCaracteres = List('a', 'b', 'c', 'd')

  // Ejemplos de map
  println(listaDeNúmeros
    .map(_ * -1) // multiplicamos todos los elementos por -1
    .map(Math.abs) // convertimos todos los elementos a su valor absoluto
  )
  println(listaDeNúmeros
    .map((i: Int) => s"$i es un número") // Construimos un String para cada número de la lista
  )

  // Ejemplos con flatMap
  val listaIdentidadYMásUno = (x: Int) => List(x, x + 1) // función del tipo flatMap

  println(listaDeNúmeros
    .flatMap(listaIdentidadYMásUno) // Genera una lista con dos elementos: el mismo y el mismo +1
  )

  val listaDeNegativos = (x: Int) => List(x, x * -1) // función del tipo flatMap
  println(listaDeNúmeros
    .flatMap(listaDeNegativos) // Genera una lista con dos elementos: el mismo y su opuesto
  )



  // Generamos un string para cada combinación de los números y los caracteres
  println(listaDeCaracteres
    .flatMap( //generamos una lista de listas
      (c: Char) => {
        listaDeNúmeros // por cada carácter, llamamos a la lista de números
          .map( // transformamos el carácter y el número
            (i: Int) => s"$c$i")
      } // en un string concatenando sus valores
    ))

  /**
   * foreach ejecuta un función de tipo T => Unit en cada uno de los elementos de la colección.
   * El tipo T => Unit quiere decir "cualquier tipo => efecto secundario (side effect)
   * El side effect más común es alguna operación de escritura en almacenamiento, logs o consola.
   */
  listaDeNúmeros.foreach(println)

  /**
   * For - comprehension es un azúcar sintáctico (syntactic sugar) que hace más legibles los map y flatMap
   */
  val forComprehension = for {
    c <- listaDeCaracteres // flatMap en lista de caracteres
    n <- listaDeNúmeros // map en lista de números
  } yield s"$c$n" // transformación que deseamos computar

  forComprehension.foreach(println)

  // El foreach también se puede escribir en formato for-comprehension
  for {n <- listaDeNúmeros} println(n)

  // Esta es la combinación de los dos for-comprehension anteriores
  for {
    string <- for {
      c <- listaDeCaracteres // flatMap en lista de caracteres 
      n <- listaDeNúmeros // map en lista de números
    } yield s"$c$n"
  } println(string)

}
