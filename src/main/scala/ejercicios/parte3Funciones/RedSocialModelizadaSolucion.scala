package ejercicios.parte3Funciones

import lecciones.parte3Funciones.TuplasYMapas

import scala.annotation.tailrec

/**
 * Solución del ejercicio [[RedSocialModelizadaEnunciado]].
 */
object RedSocialModelizadaSolucion {

  type Persona = String
  type Amigos = Set[Persona]
  type datosUsuario = Map[Persona, Amigos]

  def main(args: Array[String]): Unit = {
    // === Testing the class ===

    println(" ===  Red Vacía === ")
    val redVacía: RedSocial = RedVacía
    println(redVacía)
    println(redVacía.mayorNúmeroDeAmigos)
    println(redVacía.númeroDeGenteSinAmigos)

    println(" === Operaciones básicas de la red === ")
    val red: RedSocial = redVacía.añadirMiembro(Map("Roberto" -> Set[Persona]())).añadirMiembro("María")
    val robertoYMaría = red.añadirAmigo("Roberto", "María")
    println(red)
    println(robertoYMaría)
    println(robertoYMaría.eliminarAmigo("Roberto", "María"))
    println(robertoYMaría.darDeBaja("Roberto"))

    println(" === Dos constructores de redes === ")
    val redDePruebas1: RedSocial = robertoYMaría.añadirMiembro("Jaime").añadirAmigo("Jaime", "Roberto")
    val redDePruebas2: RedSocial = {
      redVacía.añadirMiembro(Map("Roberto" -> Set("María, Jaime")))
        .añadirMiembro(Map("María" -> Set("Roberto")))
        .añadirMiembro(Map("Jaime" -> Set("Roberto")))
    }
    println(redDePruebas1)
    println(redDePruebas2)

    println(" === Operaciones avanzadas de la red === ")
    println(redDePruebas1.númeroDeAmigos("Roberto"))
    println(redDePruebas1.mayorNúmeroDeAmigos)
    println(redDePruebas1.númeroDeGenteSinAmigos)
    println(redDePruebas1.eliminarAmigo("Jaime", "Roberto").númeroDeGenteSinAmigos)
    println(redDePruebas1.conexiónSocial("María", "Jaime"))
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

    def añadirMiembro(otro: datosUsuario): RedSocial = Red(datos ++ otro)

    def añadirMiembro(otro: Persona): RedSocial = Red(datos ++ Map(otro -> Set[Persona]()))

    def añadirAmigo(a: Persona, b: Persona): RedSocial = {
      Red(datos + (a -> (datos(a) + b)) + (b -> (datos(b) + a)))
    }

    def eliminarAmigo(a: Persona, b: Persona): RedSocial = {
      Red(datos + (a -> (datos(a) - b)) + (b -> (datos(b) - a)))
    }

    def darDeBaja(a: Persona): RedSocial = {
      val friendsOfA: Amigos = datos(a)
      if (friendsOfA.isEmpty) Red(datos - a)
      else Red(datos ++ friendsOfA.map((p: Persona) => p -> (datos(p) - a)) - a)
    }

    def númeroDeAmigos(a: Persona): Int = if (datos.contains(a)) datos(a).size else 0

    def mayorNúmeroDeAmigos: Persona = datos.maxBy(_._2.size)._1

    def númeroDeGenteSinAmigos: Int = datos.count(_._2.isEmpty)

    def conexiónSocial(a: Persona, b: Persona): Boolean = {
      @tailrec
      def bfs(objetivo: Persona, consideredPeople: Amigos, discoveredPeople: Amigos): Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val persona: Persona = discoveredPeople.head
          if (persona == objetivo) true
          else if (consideredPeople.contains(persona)) bfs(objetivo, consideredPeople, discoveredPeople.tail)
          else bfs(objetivo, consideredPeople + persona, discoveredPeople.tail ++ datos(persona))
        }
      }

      bfs(b, Set(), datos(a) + a)
    }
  }


  object RedVacía extends RedSocial {

    def estáVacía: Boolean = true

    def añadirMiembro(otro: datosUsuario): RedSocial = Red(otro)

    def añadirMiembro(otro: Persona): RedSocial = Red(Map(otro -> Set[Persona]()))

    def añadirAmigo(a: Persona, b: Persona): RedSocial = this

    def eliminarAmigo(a: Persona, b: Persona): RedSocial = this

    def darDeBaja(a: Persona): RedSocial = this

    def númeroDeAmigos(a: Persona): Int = 0

    def mayorNúmeroDeAmigos: Persona = "Red vacía"

    def númeroDeGenteSinAmigos: Int = 0

    def conexiónSocial(a: Persona, b: Persona): Boolean = false
  }

}
