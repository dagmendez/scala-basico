package ejercicios.parte3Funciones

import lecciones.parte3Funciones.HOFsYCurries

/**
 * Ejercicios de la parte teórica [[HOFsYCurries]].
 *   - Definir dos métodos que permita currificar y descurrificar funciones.
 *   - Definir dos métodos que nos permitan anidar funciones, haciendo primero una y luego la otra o al revés.
 * 
 * Soluciones en [[HOFsYCurriesSolucion]].
 */
object HOFsYCurriesEnunciado {

  def main(args: Array[String]): Unit = {
    // Prueba aquí tu código
  }

  def currificar[A](f: (A, A) => A): A => A => A = ???
  
  def restar: (Int, Int) => Int = ???
  
  def restaCurrificada: Int => Int => Int = ???
  
  def descurrificar[A](f: A => A => A): (A, A) => A = ???

  def componer[A, B, T](f: A => B, g: T => A): T => B = ???

  def yDespués[A, B, C](f: A => B, g: B => C): A => C = ???

}
