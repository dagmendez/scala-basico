package ejercicios.parte2Objetos

import lecciones.parte2Objetos.OOBasico.Persona

import scala.annotation.tailrec

/**
 * @see Soluciones disponible en [[OOBasicoSolucion]].
 */
object OOBasicoEnunciado {

  def main(args: Array[String]): Unit = {
    // Ejecuta el código aquí
  }

  /**
   * Clase de Escritor. Vamos a sobrecargar el constructor para que acepte personas.
   *
   * @param nombre          vuelve a ser un parámetro del constructor y es sólo visible dentro de la clase
   * @param apellido        lo mismo que el nombre
   * @param añoDeNacimiento al estar precedido de la palabra "val", es un valor y es accesible desde el objeto.
   */
  class Escritor(nombre: String, apellido: String, val añoDeNacimiento: Int) {

    /**
     * Método que nos devuelve el nombre completo del escritor 
     */
    def nombreCompleto: String = ???

    /**
     * Constructor que acepta una instancia de Persona y el año de nacimiento y genera un Escritor
     */
    //def this(persona: Persona, añoDeNacimiento: Int) = ??? // Está comentado para que compile

    /**
     * Constructor que acepta una instancia de Persona y genera un escritor
     */
    //def this(persona: Persona) = ??? // Está comentado para que compile
  }

  /**
   * Clase Novela. Permite generar una estancia nueva en base a una nueva edición.
   *
   * @param nombre           Nombre de la novela.
   * @param añoDePublicación Año de publicación de la novela.
   * @param autor            Información sobre el autor de la novela.
   * @see [[Escritor]]
   */
  class Novela(nombre: String, añoDePublicación: Int, autor: Escritor) {

    /**
     * Calcula la edad del autor al publicarse la novela o ediciones posteriores.
     *
     * @return Int de la edad del autor.
     */
    def edadDelAutorAlPublicar: Int = ???

    /**
     * Genera una nueva instancia para la novela que representa una nueva edición de la misma.
     *
     * @param añoPublicaciónEdición Año de la publicación de la nueva edición.
     * @return Nueva instancia de Novela
     */
    def nuevaEdición(añoPublicaciónEdición: Int): Novela = ???

    /**
     * Devuelve un booleano que confirma si la novela fue escrita por un autor o no.
     *
     * @param autor Autor a validar.
     * @return Booleano.
     */
    def fueEscritaPor(autor: Escritor): Boolean = ???
  }


  /**
   * Esta clase permite ir añadiendo uno a un número inicial cada vez que se llama al método [[incrementar]] o ir
   * reduciendo en uno el valor del contador al llamar al método [[decrementar]]
   *
   * @param cuenta Valor inicial del contador
   */
  class Contador(val cuenta: Int = 0) {
    // inmutabilidad: No devuelvas el mismo contador, genera uno nuevo ;-)
    def incrementar: Contador = ???

    def decrementar: Contador = ???

    // Sobrecargamos los métodos para que funcionen recursivamente con cualquier valor a sumar o restar

    def incrementar(incremento: Int): Contador = ???

    def decrementar(decremento: Int): Contador = ???

    def print(): Unit = ???
  }

}
