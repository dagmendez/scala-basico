package lecciones.parte1Basico

/**
 * Lección 1 de la parte 1.
 * 
 * En Scala se trabaja con expresiones. Muchas veces, la definición de "val" se usa para asignar un puntero a una
 * expresión que reutilizamos en múltiples lugares del código. El concepto de "referencial transparency" (referencias
 * transparentes) se refiere a que una referencia a una "val" se puede reemplazar por el lado derecho del igual de esa
 * "val"
 */
object Expresiones extends App {

  // Ambos lados representan la misma expression
  val `3`: Int = 1 + 2
  val x: Int = 1 + 2
  println((1 + 2) == (1 + 2))
  println(`3` == x)

  // Podemos anidar expresiones haciendo referencia a expresiones anteriormente definidas
  val `5`: Int = `3` + 2
  val cinco: Int = x + 2
  println(`5` == cinco)
  
  // cinco es igual a x + 2 que es igual a (1 + 2) + 2
  println(cinco == (1 + 2) + 2)


  // Podemos definir el valor de una val como resultado de una expresión if-else
  val unaCondicion: Boolean = true
  val unaCondicionCondicionada: Int = if unaCondicion then 5 else 3

  // También podemos definir una val dentro de un bloque de código -> { }
  val unValorDefinidoEnUnBloque: Unit = {
    val unChar: Char = 't'
    if 3 * 5 % 2 == 0 then println("Ahoy!")
    else Map(unChar -> unChar.toUpper).foreach(println)
  }
  println(unaCondicionCondicionada)

}
