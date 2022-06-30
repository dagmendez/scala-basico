
package lecciones.parte4Patrones

import ejercicios.parte4Patrones.PatternMatchingEnunciado

/**
 * Lección 3 de 4.
 * 
 * El Pattern Matching está presente dentro del propio lenguaje y que habilita muchas de las sintaxis dulces.
 * Ejercicios en [[PatternMatchingEnunciado]].
 */
object PatronesDondeNoLosEsperas extends App {

  /**
   * Los bloques de código try - catch - finally es pattern matching
   */
  try {
    println(10 / 0)
  } catch {
    case r: RuntimeException => println("error durante ejecución")
    case npe: NullPointerException => println("puntero nulo")
    case _ => println("otra cosa")
  }

  /**
   * Las for - comprehensions utilizan la descomposición de case clases y los if de seguridad (if guards)
   */

  val listaDeNúmeros = List(1, 2, 3, 4, 5, 6)
  val númerosImpares = for {
    x <- listaDeNúmeros if x % 2 == 0
  } yield 10 * x
  println(númerosImpares)

  /**
   * La asignación de valores a val definidas al mismo tiempo también usa patter matching
   */
  val tupla123 = (1, 2, 3)
  val (uno, dos, tres) = tupla123
  println(uno)
  println(dos)
  println(tres)

  /**
   * La descomposición de una lista en cabeza y cola sigue la misma lógica que con los valores asignados a val
   */
  val cabeza :: cola = listaDeNúmeros
  println(cabeza)
  println(cola)

  /**
   * El pattern matching se puede utilizar como una función anónima definida dentro de un bloque de código.
   */
  val listaTransformada = listaDeNúmeros.map {
    case v if v % 2 == 0 => s"$v es impar"
    case 1 => "el primero"
    case _ => "otro número"
  }
  println(listaTransformada)

  val listaTransformadaConNotaciónExplicita = listaDeNúmeros.map { //Aquí está el pattern matching
    case v if v % 2 == 0 => s"$v es impar"
    case 1 => "el primero"
    case _ => "otro número"
  }
  println(listaTransformadaConNotaciónExplicita)
  println(
    s"Las listas transformadas son iguales? ${listaTransformada.equals(listaTransformadaConNotaciónExplicita)}"
  )

}
