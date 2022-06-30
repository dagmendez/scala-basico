
package lecciones.parte3Funciones

import lecciones.parte2Objetos.ClasesAnonimas
import ejercicios.parte3Funciones.ColeccionFuncionalEnunciado

/**
 * Lección 1 de 8.
 *
 * Scala se diseño para lograr que las funciones sean "ciudadanos de primera clase".
 * Esto quiere decir que las funciones se pudieran definir en el más alto nivel jerárquico.
 * En definitiva, que no dependieran de una clase o un objeto.
 * Para lograr compatibilizar el paradigma OOP y FP, los creadores de Scala hicieron un truco de magia.
 *
 * Ejercicios en [[ColeccionFuncionalEnunciado]].
 */
object QueEsUnaFuncion extends App {

  /**
   * El primer paso es definir un trait que solo tiene un método.
   * Este trait recibe un parámetro de tipo A y devuelve un valor del tipo B.
   */
  trait MiFunción[A, B] {
    def apply(elemento: A): B
  }

  /**
   * El segundo paso es generar una instancia del trait, tal y como vimos en las clases anónimas [[ClasesAnonimas]].
   * En este ejemplo, utilizamos como parámetro de entrada un entero (Int) y como valor de salida un entero (Int).
   */
  val doble = new MiFunción[Int, Int] {
    override def apply(elemento: Int): Int = elemento * 2
  }
  println(doble(2))

  /**
   * El trait tiene dos tipos, A y B. Para demostrar que los tipos pueden ser diferentes, hacemos este segundo ejemplo.
   * Tomamos un entero (Int) y devolvemos una cadena (String)
   */
  val conversorEnteroAString = new MiFunción[Int, String] {
    override def apply(elemento: Int): String = elemento.toString
  }
  println(conversorEnteroAString(42))

  /**
   * El tercer paso es utilizar la misma lógica que la de las clases anónimas para generar funciones anónimas.
   * Este procedimiento permite generar una instancia de una clase con un solo método si informamos en el lado derecho
   * del igual la implementación del único método de la clase (trait en este caso).
   */
  val triple: MiFunción[Int, Int] = (x: Int) => x * 3
  assert(triple(3) == 9)
  println(triple(5) + " = 15")

  /**
   * Scala hace esta magia con el trait [[Function0]], [[Function1]], ..., [[Function22]]
   * Las siguientes implementaciones de suma son equivalentes.
   */
  val suma_v1 = new ((Int, Int) => Int) {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  val suma_v2: (Int, Int) => Int = (x, y) =>  x + y
  val suma_v3: (Int, Int) => Int = (x, y) => x + y
  val suma_v4 = (x: Int, y: Int) => x + y

  println(suma_v1(3, -3))
  println(suma_v2(3, -3))
  println(suma_v3(3, -3))
  println(suma_v4(3, -3))

  /**
   * Las funciones pueden anidarse unas dentro de otras. Esto permite currificarlas.
   * @note Leer más en [[HOFsYCurries]].
   */
    // Primero recibe un Int, que devuelve un Int que se usa como input en otra función que devuelve otro Int
  val superSuma_v1: Int => Int => Int = {
      // Primera función
      (v1: Int) => {
        // Segunda función
        (v2: Int) => v1 + v2
      }
  }
  // Función currificada (dos listas de parámetros)
  println(superSuma_v1(1)(1))

  /**
   * Las misma función se puede escribir usando la sintaxis azucarada de Scala (syntactic sugar)
   */
  val superSuma_v2: Int => Int => Int = v1 => v2 => v1 + v2
  println(superSuma_v2(1)(1))
}
