package lecciones.parte2Objetos

/**
 * Lección 5 de la parte 2.
 * 
 * El uso de genéricos en Scala permite definir clases y métodos que se pueden reutilizar para tipos distintos.
 * El uso de genéricos es extensivo en el lenguaje base de Scala y en sus librerías.
 * Esta será una pequeña introducción.
 * 
 * @see Ejercicios disponibles en [[ejercicios.parte2Objetos.ColeccionGenericaEnunciado]]
 */
object Genericos extends App {

  /**
   * Podemos generar una lista que recibe un tipo genérico "A" (puede tener cualquier nombre)
   *
   * @tparam A tparam el el parámetro que hace referencia al tipo.
   * @note Más detalles sobre la variancia próximamente.
   * @note La implementación de la lista no es importante para discutir el poder de los genéricos.
   */
  class MiLista[A](val elementos: List[A] = Nil) {
    def añadirElemento(elemento: A): MiLista[A] = new MiLista[A](elemento +: elementos)
  }

  // nueva instancia de la clase
  val listaDeEnteros = new MiLista[Int]
  // no tiene elementos
  println(listaDeEnteros.elementos)
  // tiene un elemento (pero es una nueva instancia)
  println(listaDeEnteros.añadirElemento(1).elementos)
  // la instancia original no ha sufrido cambios, sigue vacía
  println(listaDeEnteros.elementos)

  // Lo mismo para cadenas
  val listaDeCadenas = new MiLista[String]
  println(listaDeCadenas.añadirElemento("Hola Scala").elementos)

  // Lo mismo para booleanos
  val listaDeBooleanos = new MiLista[Boolean](List(true))
  println(listaDeBooleanos.añadirElemento(false).elementos)

  /**
   * Las letras que se suelen utilizar para las clases genéricas son A, B, C, T, U, V...
   * Pero no hay límites. Por ejemplo, podemos llamar a los tipos de la siguiente manera:
   *
   * @tparam Clave el tipo de las claves
   * @tparam Valor el tipo de los valores
   */
  class MiMapa[Clave, Valor](val valores: Map[Clave, Valor] = Map[Clave, Valor]()) {
    // Recordatorio de que Scala permite sobrecargar métodos con el mismo nombre y diferentes parámetros
    def añadirValores(tuple2: (Clave, Valor)): MiMapa[Clave, Valor] = {
      new MiMapa[Clave, Valor](valores + (tuple2._1 -> tuple2._2))
    }

    def añadirValores(mapa: Map[Clave, Valor]): MiMapa[Clave, Valor] = {
      new MiMapa[Clave, Valor](valores ++ mapa)
    }
  }

  // Probamos
  val unMapa = new MiMapa[String, Int](Map("David" -> 42))
  println(unMapa.valores)
  println(unMapa.añadirValores(("Daniel", 45)).valores)
  println(unMapa.añadirValores(Map("Scala" -> 3)).valores)

  /**
   * Si intentamos añadir un nuevo registro que no coincide con el tipo definido, el compilador protestará.
   * Es un problema de varianza de tipos.
   */
  //unMapa.añadirValores(Map(42 -> "David"))

  /**
   * Una forma de escapar a estas limitaciones es utilizar Any (super tipo de todos los tipos en Scala).
   * Pero, ¿qué ganamos con esto? Perdemos los beneficios de saber que tipo estamos utilizando.
   * Si queremos aceptar determinados tipos, tenemos que jugar con las reglas de la varianza.
   */
  val unMapaQueLoAguantaTodo = new MiMapa[Any, Any]
  println(
    unMapaQueLoAguantaTodo
      .añadirValores(Map("Helicóptero" -> true))
      .añadirValores(Map(17 -> 't'))
      .añadirValores(Map(10L -> "10 tipo Long"))
      .valores
  )

  /**
   * Vamos a ver el problema presentado por la varianza de tipos con un ejemplo.
   * La clase Animal va a ser la super clase de Gato y Perro.
   * La varianza es de 3 tipos:
   *   - Covariante
   *   - Invariante
   *   - Contravariante
   */
  class Animal

  class Gato extends Animal

  class Perro extends Animal

  /**
   * Primer caso, covariante. Se representa con un "+" delante del tipo genérico.
   * Permite que instancias de subclases sean utilizadas en valores con tipo de la superclase.
   * @tparam A es una A precedida de un "+"
   */
  class ListaCovariante[+A]
  // Gato es un subtipo (subclase) de Animal
  val animal: Animal = new Gato
  // Una lista de Animales acepta como valor una lista de Gatos
  val listaDeAnimales: ListaCovariante[Animal] = new ListaCovariante[Gato]

  /**
   * Segundo caso, invariante. Es el modo por defecto de la varianza. No hay que añadir nada.
   * Permite que instancias de un tipo sólo puedan tener valores del mismo tipo.
   * @tparam A tal cual
   */
  class ListaInvariante[A]
  // Correcto
  val listaDeAnimalesInvariante: ListaInvariante[Animal] = new ListaInvariante[Animal]
  // Incorrecto. No compila
  //val listaDeGatosInvariante: ListaInvariante[Gato] = new ListaInvariante[Animal]
  //val listaDePerrosInvariante: ListaInvariante[Animal] = new ListaInvariante[Perro]

  /**
   * Tercer caso, contravariante.
   * Permite que valores cuyo tipo es una subclase reciban como valor un tipo de superclase.
   *
   * @tparam A la letra que describe el tipo genérico va precedida de un sigo menos "-"
   */
  class ListaContraVariante[-A]
  // ¿Tiene sentido que una lista de gatos pueda ser una lista de Animales que puede contener solo perros?
  val listaContraVariante: ListaContraVariante[Gato] = new ListaContraVariante[Animal]

  /**
   * El tipo de la clase Adiestrador nos indica que tipo de animales puede adiestrar.
   *
   * @tparam A Es contravariante
   */
  class Adiestrador[-A]
  // Ahora si tiene sentido que un adiestrador de Animales pueda adiestrar gatos
  val adiestradorAnimal: Adiestrador[Gato] = new Adiestrador[Animal]

  /**
   * La clase PerroPequeño es una subclase de Perro, que a su vez lo es de Animal.
   * Estos ejemplos ayudan a comprender como usar la contravarianza.
   */
  class PerroPequeño extends Perro
  // Un adestrador de perros pequeños puede ser un adiestrador de Perros en general
  val adiestradorPerruno: Adiestrador[PerroPequeño] = new Adiestrador[Perro]
  // Esto es correcto
  val adiestradoPerros: Adiestrador[Perro] = new Adiestrador[Animal]
  // Esto también es correcto
  val adiestradorPerroPequeño: Adiestrador[PerroPequeño] = adiestradoPerros

  /**
   * Hasta ahora nos centramos en los tipos genéricos de las clases, pero no utilizamos ninguna clase con parámetros.
   * Esta clase es un ejemplo de limitación de tipos válidos (bounded types)
   *
   * @param animal parámetro del constructor
   * @tparam A tipo del parámetro del constructor, limitado por "<:" que significa:
   *           Tipos validos son las subclases del tipo a la derecha incluido el tipo de la derecha.
   */
  class Jaula[A <: Animal](animal: A)
  val jaulaParaAnimales: Jaula[Animal] = new Jaula(new Animal)
  // Esto funciona por que los tipos coinciden y ambos tipos son válidos
  val jaulaParaPerrosPequeños_v1: Jaula[PerroPequeño] = new Jaula(new PerroPequeño)
  // Esto funciona por que el tipo asociado es el de una superclase de la instancia que hemos creado
  val jaulaParaPerrosPequeños_v2: Jaula[Animal] = new Jaula(new PerroPequeño)
  // Esto no funciona por que el tipo del valor es una subclase de la instancia que hemos creado
  //val jaulaParaPerrosPequeños_v3: Jaula[PerroPequeño] = new Jaula(new Animal)

  /**
   * Esta clase no es una subclase de Animal, por lo que si intentamos hacer una jaula de coches, fracasaremos.
   */
  class Coche
  //val jaulaParaCoches: Jaula[Coche] = new Jaula(new Coche) // No compila

}
