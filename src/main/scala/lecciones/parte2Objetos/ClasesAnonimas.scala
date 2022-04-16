package lecciones.parte2Objetos

/**
 * Lección 7 de la parte 2.
 *
 * Scala, como otros muchos lenguajes de programación, incluye las funciones anónimas, conocidas como lambda.
 * Además de las lambda, Scala también permite generar clases anónimas (¿lambda classes?)
 * 
 * @see Ejercicios disponibles en [[ejercicios.parte2Objetos.ColeccionExtendidaEnunciado]]
 */
object ClasesAnonimas extends App {

  /**
   * Las clases anónimas se construyen a partir de clases abstractas que tienen un solo método.
   */
  abstract class Animal {
    def comer(): Unit
  }

  /**
   * Nuestra primera clase anónima es un Animal.
   * El lado derecho del igual es la implementación del único método de la clase (def comer(): Unit).
   * Como el tipo es unit, el lado izquierdo de la flecha => son unos paréntesis () que es equivalente a Unit.
   */
  val animalDivertido : Animal = () => println("Muahahahahaha")
  println(animalDivertido.comer())
  println(animalDivertido.getClass)

  /**
   * Nuesta segunda clase, es una clase que se asemeja a las clases Function1, Function2, ect.
   * De hecho, las funciones anónimas (lambda) son instancias de las clases [[Function0]], [[Function1]], etc.
   * que tienen un sólo método (apply). Eso lo veremos con más detalle en otro apartado.
   */
  abstract class UnaFunciónDeNúmeros{
    def apply(x: Int, y: Int): Int
  }

  /**
   * Esta es una función lambda para sumas.
   * En este caso vemos que las funciones anónimas son en realidad clases anónimas.
   */
  val sumar: UnaFunciónDeNúmeros = (x: Int, y: Int) => x + y
  println(s"${sumar(4,4)} = 8 ")

  /**
   * Esta clase tiene un parámetro de entrada, por lo que no podremos generar instancias de la misma forma.
   * @param nombre Nombre de la persona
   */
  class Persona(nombre: String) {
    def diHola(): Unit = println(s"Hola, me llamo $nombre. ¿Cómo podría ayudarte?")
  }

  // Forma habitual
  val persona = new Persona("Alberto")
  persona.diHola()

  /**
   * Jaime funciona como una subclase anónima de Persona.
   * El método "diHola" está sobreescrito pero solo para el valor "jaime".
   */
  val jaime = new Persona("Jaime") {
    override def diHola(): Unit = println("¡Soy Jaime!")
  }
  jaime.diHola()

}
