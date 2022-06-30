package ejercicios.parte3Funciones

import lecciones.parte3Funciones.TuplasYMapas

import scala.annotation.tailrec

/**
 * Ejercicio de la parte teórica [[TuplasYMapas]].
 *
 * Las tareas son las mismas que en el ejercicio [[RedSocialConMapasEnunciado]] pero aquí vamos a modelizarlo en una clase.
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
 * Solución en [[RedSocialModelizadaSolucion]].
 */
object RedSocialModelizadaEnunciado {

  type Persona = String
  type Amigos = Set[Persona]
  type datosUsuario = Map[Persona, Amigos]

  def main(args: Array[String]): Unit = {
   // Crea aquí tu red y haz pruebas
  }

  sealed abstract class RedSocial {
    def estáVacía: Boolean

    def añadirMiembro(otro: datosUsuario): RedSocial

    def añadirMiembro(otro: Persona): RedSocial

    def añadirAmigo(a: Persona, b: Persona): RedSocial

    def eliminarAmigo(a: Persona, b: Persona): RedSocial

    def darDeBaja(a: Persona): RedSocial

    def númeroDeAmigos(a: Persona): Int

    def mayorNúmeroDeAmigos: Persona

    def númeroDeGenteSinAmigos: Int

    def conexiónSocial(a: Persona, b: Persona): Boolean
  }


  case class Red(datos: datosUsuario) extends RedSocial {
    def estáVacía: Boolean = false

    def añadirMiembro(otro: datosUsuario): RedSocial = ???

    def añadirMiembro(otro: Persona): RedSocial = ???

    def añadirAmigo(a: Persona, b: Persona): RedSocial = ???

    def eliminarAmigo(a: Persona, b: Persona): RedSocial = ???

    def darDeBaja(a: Persona): RedSocial = ???

    def númeroDeAmigos(a: Persona): Int = ???

    def mayorNúmeroDeAmigos: Persona = ???

    def númeroDeGenteSinAmigos: Int = ???

    def conexiónSocial(a: Persona, b: Persona): Boolean = ???
  }


  object RedVacía extends RedSocial {

    def estáVacía: Boolean = ???

    def añadirMiembro(otro: datosUsuario): RedSocial = ???

    def añadirMiembro(otro: Persona): RedSocial = ???

    def añadirAmigo(a: Persona, b: Persona): RedSocial = ???

    def eliminarAmigo(a: Persona, b: Persona): RedSocial = ???

    def darDeBaja(a: Persona): RedSocial = ???

    def númeroDeAmigos(a: Persona): Int = ???

    def mayorNúmeroDeAmigos: Persona = ???

    def númeroDeGenteSinAmigos: Int = ???

    def conexiónSocial(a: Persona, b: Persona): Boolean = ???
  }

}
