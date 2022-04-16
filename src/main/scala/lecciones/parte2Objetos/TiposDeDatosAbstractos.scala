
package lecciones.parte2Objetos

/**
 * Scala nos proporciona dos formas de extender componentes abstractos:
 *   - clases abstractas
 *   - traits
 * Las clases solo pueden extender una clase abstracta mientras que pueden extender muchos traits.
 *
 * Es por ello que la clase abstracta se utiliza normalmente para definir la funcionalidad interna de la clase.
 * Por otra parte, los traits se utilizan para extender comportamientos que pueden ser validos para cualquier tipo de clases.
 */
object TiposDeDatosAbstractos extends App {

  /**
   * Clase abstracta que engloba una serie de métodos y valores que serán heredados por todas las clases hijas.
   * Los métodos pueden estar implementados o no.
   */
  abstract class Animal {

    // Valor abstracto que será implementado por las clases hijas
    val tipoDeCriatura: String
    // Método implementado que se resuelve para cada clase hija
    def saludo: String = s"Hola, soy un Animal de tipo $tipoDeCriatura."
    // Método no implementado
    def comer(): Unit
  }

  /**
   * Perro es la clase hija de Animal.
   * Para terminar su construcción, tenemos que implementar los métodos y valores abstractos.
   * Los métodos o valores implementados en la clase abstracta están disponibles para instancias de esta clase.
   */
  class Perro extends Animal {
    // Los métodos abstractos no necesitan el modificador "overwrite"
    val tipoDeCriatura: String = "Canino"
    def comer(): Unit = println("Crunch Crunch")
  }

  val perro = new Perro
  println(perro.saludo)
  println("Soy un perro y estoy comiendo.")
  perro.comer()

  /**
   * Gato es otra clase que hereda de animal.
   */
  class Gato extends Animal {
    val tipoDeCriatura: String = "Felino"
    def comer(): Unit = println("Mlask Mlask")
  }

  val gato = new Gato
  println(gato.saludo)
  println("Soy un gato y estoy comiendo.")
  gato.comer()

  /**
   * Los traits pueden ser "heredados" (extendidos) por múltiples clases. Una misma clase puede extender muchos traits.
   * Estas estructuras se utilizan para encapsular comportamientos.
   * En este caso, los carnívoros comen otros animales.
   */
  trait Carnívoro {
    val comidaPreferida: String = "Carne fresca"
    def comer(animal: Animal): Unit
  }

  class Cocodrilo extends Animal with Carnívoro {
    val tipoDeCriatura: String = "Cocodrilo"
    def comer(): Unit = println("nomnomnom")
    // Al extender el trait Carnívoro, tenemos un segundo método comer, pero que recibe un parámetro.
    def comer(animal: Animal): Unit = println(s"Soy un $tipoDeCriatura y estoy comiendo ${animal.tipoDeCriatura}.")
  }

  val cocodrilo = new Cocodrilo
  cocodrilo.comer(perro)
  println(cocodrilo.saludo)

  /**
   * Los trait pueden extender otros trait. En este caso, este trait sobreescribe el valor de comidaPreferida.
   * Mantiene el acceso a los demás componentes del trait Carnívoro.
   */
  trait Carroñero extends Carnívoro {
    override val comidaPreferida: String = "Carne putrefacta"
  }

  /**
   * Esta clase, Buitre, tiene acceso a los miembros (métodos, valores, funciones):
   *   - clase abstracta Animal
   *   - trait Carroñero
   *   - trait Carnívoro ( a través de trait Carroñero)
   */
  class Buitre extends Animal with Carroñero {
    val tipoDeCriatura: String = "Ave"
    def comer(): Unit = println("Clack Clack")
    def comer(animal: Animal): Unit =
      println(s"Soy un $tipoDeCriatura y me alimentos de los restos de un ${animal.tipoDeCriatura}.")
  }

  val buitre = new Buitre
  println(buitre.saludo)
  buitre.comer(gato)

}
