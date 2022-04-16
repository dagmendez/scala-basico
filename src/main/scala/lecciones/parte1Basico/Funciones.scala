package lecciones.parte1Basico

import scala.annotation.tailrec

/**
 * Lección 2 de la parte 1.
 *
 * Las funciones en Scala se caracterizan por ser "ciudadanos de primera clase". Esto quiere decir que podemos definir
 * funciones donde queramos, sin necesidad de depender de una clase.
 *   - La función básica recibe parámetros y devuelve un valor.
 *   - La función que recibe otra función como parámetro se llama "High Order Function" (HOF). Algunos ejemplos son:
 *       - Map
 *       - FlatMap
 *       - Filter
 *   - Las funciones pueden devolvernos un valor (String, Int...) o nos pueden devolver otra función.
 *
 *   @see Ejercicios disponibles en [[ejercicios.parte1Basico.FuncionesEnunciado]]
 */
object Funciones extends App {

  /**
   * Esta forma de construir funciones (con la palabra "def") es la más común. Los parámetros de entrada van entre
   * paréntesis después del nombre.
   *
   * @param a un String
   * @param b un Int
   * @return la concatenación de a y b separados por un espacio
   */
  def unaFunción(a: String, b: Int) = s"$a $b"

  // Scala nos permite definir el mismo comportamiento (la función) de diferentes formas y con diferentes firmas.
  // Además de cambiar la firma, cambia la palabra "def" por "val"

  // Tipos informados en el lado izquierdo del igual
  val unaFunción_v1: (String, Int) => String = (a, b) => s"$a $b"
  // Tipos informados en el lado derecho del igual
  val unaFunción_v2 = (a: String, b: Int) => s"$a $b"

  println(unaFunción("hello", 0))
  println(unaFunción_v1("hello", 1))
  println(unaFunción_v2("hello", 2))

  /**
   * Las funciones también se pueden definir sin parámetros de entrada.
   * En ese caso, se añaden los paréntesis después del nombre pero se dejan vacíos.
   * @return 42
   */
  def unaFunciónSinParámetros(): Int = 42
  val unaFunciónSinParámetros_v1: Int  = 42
  val unaFunciónSinParámetros_v2 = 42

  println(unaFunciónSinParámetros())
  println(unaFunciónSinParámetros_v1)
  println(unaFunciónSinParámetros_v2)

  /**
   * Las funciones pueden llamarse a si mismas dentro de su bloque de código. Se denominan métodos/funciones recursivas.
   * @param s un String
   * @param n un Int
   * @return concatenación del String n veces
   */
  def unaFunciónRecursiva(s: String, n: Int): String = {
    if(n <= 1) s
    else s + unaFunciónRecursiva(s, n - 1)
  }
  val unaFunciónRecursiva_v1: (String, Int) => String = (s, n) => {
    if(n <= 1) s
    else s + unaFunciónRecursiva(s, n - 1)
  }
  val unaFunciónRecursiva_v2 = (s: String, n: Int) => {
    if(n <= 1) s
    else s + unaFunciónRecursiva(s, n - 1)
  }

  println(unaFunciónRecursiva("hello", 0))
  println(unaFunciónRecursiva_v1("hello", 1))
  println(unaFunciónRecursiva_v2("hello", 2))

  /**
   * Las funciones también pueden contener efectos ("side effects").
   * Estas son funciones impuras ya que no devuelven ningún valor (devuelven un valor de tipo Unit).
   * Estas funciones se utilizan para comunicarse con el mundo exterior a la aplicación.
   * @param s un String
   * @return imprime el string por consola
   */
  def unaFunciónConEfectos(s: String): Unit = println(s)
  val unaFunciónConEfectos_v1: String => Unit = s => println(s)
  val unaFunciónConEfectos_v2 = (s: String) => println(s)

  unaFunciónConEfectos("hello World!")
  unaFunciónConEfectos_v1("hello World!")
  unaFunciónConEfectos_v2("hello World!")

  /**
   * Las funciones también permiten definir otras funciones dentro de su bloque de código.
   * @param n un Int
   * @return suma al valor de n n-1 n veces
   */
  def unaFunciónGrande(n: Int): Int = {
    def unaFunciónPequeña(a: Int, b: Int): Int = a + b
    unaFunciónPequeña(n, n - 1)
  }
  val unaFunciónGrande_v1: Int => Int = n => {
    val unaFunciónPequeña_v1: (Int, Int) => Int = (a, b) => a + b
    unaFunciónPequeña_v1(n, n - 1)
  }
  val unaFunciónGrande_v2 = (n: Int) => {
    val unaFunciónPequeña_v2 = (a: Int, b: Int) => a + b
    unaFunciónPequeña_v2(n, n - 1)
  }
  println(unaFunciónGrande(0))
  println(unaFunciónGrande_v1(1))
  println(unaFunciónGrande_v2(2))

  /**
   * Ejercicios disponibles en [[ejercicios.parte1Basico.FuncionesEnunciado]]
   */

}
