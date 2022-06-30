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
 *   - Consultar cuantas persona no tienen ningún amigo agregado.
 *   - Consultar si existe alguna relación entre dos personas a través de sus amigos.
 */
object RedSocialConMapasSolucion {

  // Añadimos tipos para que los métodos sean más legibles
  type Persona = String // String
  type Amigos = Set[Persona] // Set[String]
  type Red = Map[Persona, Amigos] // Map[String, Set[String]]


  def main(args: Array[Persona]): Unit = {
    // Aquí tus pruebas

    val redVacía: Red = Map()
    val red: Red = añadirCuenta(añadirCuenta(redVacía, "Roberto"), "María")
    val pruebaRed: Red = {
      añadirAmigo(
        añadirAmigo(
          añadirCuenta(red, "Jaime"),
          "Jaime", "Roberto"),
        "Roberto", "María")
    }

    println(red)
    println(pruebaRed)
    println(añadirAmigo(red, "Roberto", "María"))
    println(eliminarAmigo(añadirAmigo(red, "Roberto", "María"), "Roberto", "María"))
    println(borrarCuenta(añadirAmigo(red, "Roberto", "María"), "Roberto"))

    println(númeroDeAmigos(pruebaRed, "Roberto"))
    println(mayorNúmeroDeAmigos(pruebaRed))
    println(númeroDePersonasSinAmigos(pruebaRed))
    println(númeroDePersonasSinAmigos(eliminarAmigo(pruebaRed, "Jaime", "Roberto")))

    println(conexiónSocial(pruebaRed, "María", "Jaime"))
  }

  def añadirCuenta(red: Red, persona: Persona): Red = red + (persona -> Set())

  def añadirAmigo(red: Red, a: Persona, b: Persona): Red = {
    val amigosDeA: Amigos = red(a)
    val amigosDeB: Amigos = red(b)
    red + (a -> (amigosDeA + b)) + (b -> (amigosDeB + a))
  }

  def borrarCuenta(red: Red, persona: Persona): Red = {
    val friends: Amigos = red(persona)

    @tailrec
    def borrarCuentaAuxiliar(amigos: Amigos, nuevaRed: Red): Red = {
      if (amigos.isEmpty) nuevaRed
      else borrarCuentaAuxiliar(amigos.tail, eliminarAmigo(nuevaRed, persona, amigos.head))
    }

    val redConLaCuentaBorrada: Red = borrarCuentaAuxiliar(friends, red)
    redConLaCuentaBorrada - persona
  }


  def eliminarAmigo(red: Red, a: Persona, b: Persona): Red = {
    val amigosDeA: Amigos = red(a)
    val amigosDeB: Amigos = red(b)
    red + (a -> (amigosDeA - b)) + (b -> (amigosDeB - a))
  }

  def númeroDeAmigos(red: Red, persona: Persona): Int = {
    if (red.contains(persona)) red(persona).size
    else 0
  }


  def mayorNúmeroDeAmigos(red: Red): Persona = {
    red.maxBy((p: Persona, f: Amigos) => f.size)._1
  }

  def númeroDePersonasSinAmigos(red: Red): Int = {
    red.count((p: Persona, f: Amigos) => f.isEmpty)
  }


  def conexiónSocial(red: Red, a: Persona, b: Persona): Boolean = {
    @tailrec
    def bfs(objetivo: Persona, candidatos: Amigos, descubrimientos: Amigos): Boolean = {
      if (descubrimientos.isEmpty) false
      else {
        val person: Persona = descubrimientos.head
        if (person == objetivo) true
        else if (candidatos.contains(person)) bfs(objetivo, candidatos, descubrimientos.tail)
        else bfs(objetivo, candidatos + person, descubrimientos.tail ++ red(person))
      }
    }

    bfs(b, Set(), red(a) + a)
  }


}
