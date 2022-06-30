package ejercicios.parte3Funciones

import lecciones.parte3Funciones.TuplasYMapas

import scala.annotation.tailrec

/**
 * Ejercicio de la parte teórica [[TuplasYMapas]].
 *
 * Tareas:
 *   - Añadir una persona a la red.
 *   - Eliminar una persona de la red.
 *   - Añadir amigo (la persona se añade en ambas listas de amigos de las dos personas amigas).
 *   - Eliminar amigo (igual que añadir).
 *   - Consultar el número de amigos de una persona dentro de la red.
 *   - Consultar que persona tiene el mayor número de amigos.
 *   - Consultar cuantas persona no tienen ningún amigo agregado.
 *   - Consultar si existe alguna relación entre dos personas a través de sus amigos.
 *
 * Solución en [[RedSocialConMapasSolucion]].
 */
object RedSocialConMapasEnunciado {

  // Añadimos tipos para que los métodos sean más legibles
  type Persona = String // String
  type Amigos = Set[Persona] // Set[String]
  type Red = Map[Persona, Amigos] // Map[String, Set[String]]


  def main(args: Array[Persona]): Unit = {
    // Aquí tus pruebas

  }

  def añadirCuenta(red: Red, persona: Persona): Red = ???

  def añadirAmigo(red: Red, a: Persona, b: Persona): Red = ???

  def borrarCuenta(red: Red, persona: Persona): Red = ???

  def eliminarAmigo(red: Red, a: Persona, b: Persona): Red = ???

  def númeroDeAmigos(red: Red, persona: Persona): Int = ???

  def mayorNúmeroDeAmigos(red: Red): Persona = ???

  def númeroDePersonasSinAmigos(red: Red): Int = ???
  
  def conexiónSocial(red: Red, a: Persona, b: Persona): Boolean = ???


}
