package ejercicios.parte2Objetos

import lecciones.parte2Objetos.OOBasico.Persona

object OOBasicoSolucion {

  def main(args: Array[String]): Unit = {

    /**
     * Escritor
     */

    val autor_v1 = new Escritor("Charles", "Dickens", 1812)
    // Año de nacimiento 1812, año actual 2022. El cálculo está hardcodeado (edad = 210)
    val autor_v2 = new Escritor(new Persona("Charles Dickens", 210), 1812)
    val autor_v3 = new Escritor(new Persona("Charles Dickens", 210))
    // Verificamos que el año de nacimiento es el mismo
    if (autor_v1.añoDeNacimiento == autor_v2.añoDeNacimiento && autor_v2.añoDeNacimiento == autor_v3.añoDeNacimiento) {
      println("Todos los autores nacieron el mismo año.")
    } else println("Han nacido en años diferentes.")

    /**
     * Novela
     */

    val autor = autor_v1
    val novela = new Novela("Great Expectations", 1861, autor)
    println(novela.fueEscritaPor(autor))
    println(novela.edadDelAutorAlPublicar)

    //Generamos un autor impostor con la misma información que el primero.
    val impostor = autor_v2
    // aunque los datos internos son los mismos, las instancias de las clases no lo son
    println(novela.fueEscritaPor(impostor))

    /**
     * Contador
     */

    val contador = new Contador()
    contador.print()
    contador.incrementar(5).print()
    // se resta desde la instancia inicial, no desde la instancia que tiene counter = 5
    contador.decrementar(2).print()
    // la instancia inicial sigue teniendo el mismo valor
    contador.print()
  }

  /**
   * Clase de Escritor. Vamos a sobrecargar el constructor para que acepte personas.
   *
   * @param nombre          vuelve a ser un parámetro del constructor y es sólo visible dentro de la clase
   * @param apellido        lo mismo que el nombre
   * @param añoDeNacimiento al estar precedido de la palabra "val", es un valor y es accesible desde el objeto.
   */
  class Escritor(nombre: String, apellido: String, val añoDeNacimiento: Int) {

    def nombreCompleto: String = s"$nombre $apellido"

    def this(persona: Persona, añoDeNacimiento: Int) = {
      this(persona.nombre.split(" ")(0), persona.nombre.split(" ")(1), añoDeNacimiento)
    }

    def this(persona: Persona) = this(persona, 2022 - persona.edad)
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
    def edadDelAutorAlPublicar: Int = {
      añoDePublicación - autor.añoDeNacimiento
    }

    /**
     * Genera una nueva instancia para la novela que representa una nueva edición de la misma.
     *
     * @param añoPublicaciónEdición Año de la publicación de la nueva edición.
     * @return Nueva instancia de Novela
     */
    def nuevaEdición(añoPublicaciónEdición: Int): Novela = {
      new Novela(nombre, añoPublicaciónEdición, autor)
    }

    /**
     * Devuelve un booleano que confirma si la novela fue escrita por un autor o no.
     *
     * @param autor Autor a validar.
     * @return Booleano.
     */
    def fueEscritaPor(autor: Escritor): Boolean = {
      autor == this.autor
    }
  }


  /**
   * Esta clase permite ir añadiendo uno a un número inicial cada vez que se llama al método [[incrementar]] o ir
   * reduciendo en uno el valor del contador al llamar al método [[decrementar]]
   *
   * @param cuenta Valor inicial del contador
   */
  class Contador(val cuenta: Int = 0) {
    // inmutabilidad
    def incrementar: Contador = {
      new Contador(cuenta + 1)
    }

    def decrementar: Contador = new Contador(cuenta - 1)

    // Sobrecargamos los métodos para que funcionen recursivamente con cualquier valor a sumar o restar
    def incrementar(incremento: Int): Contador = {
      if (incremento <= 0) this
      else incrementar.incrementar(incremento - 1)
    }

    def decrementar(decremento: Int): Contador = {
      if (decremento <= 0) this
      else decrementar.decrementar(decremento - 1)
    }

    def print(): Unit = println(cuenta)
  }

}
