package lecciones.parte2Objetos

/**
 * Lección 4 de la parte 2.
 * 
 * @see Ejercicios disponibles en [[ejercicios.parte2Objetos.ColeccionEnunciado]]
 */
object Herencia extends App {

  /**
   * La clase Persona cuenta con varios constructores auxiliares. Esto nos permite extenderla de varias formas.
   * @param nombre Nombre de la persona
   * @param edad edad de la persona
   */
  class Persona(val nombre: String, val edad: Short) {
    /**
     * Este constructor auxiliar permite generar instancias de la clase Persona con un solo parámetro.
     * @param name Nombre de la persona
     */
    def this(name: String) = this(name, 0)
    /**
     * Este constructor auxiliar permite generar instancias de la clase Persona sin parámetros.
     * Los dos parámetros del constructor son valores por defecto.
     */
    def this() = this("David", 100)
  }

  /**
   * Las clase Adulto extiende la clase Persona utilizando el constructor completo.
   * @param nombre Nombre de la persona
   * @param edad edad de la persona
   * @param ID identificador de persona física
   */
  class Adulto_V1(nombre: String, edad: Short, ID: String) extends Persona(nombre, edad)


  /**
   * Las clase Adulto extiende la clase Persona utilizando el constructor que sólo recibe el nombre como parámetro.
   * @param nombre Nombre de la persona
   * @param edad edad de la persona
   * @param ID identificador de persona física
   */
  class Adulto_V2(nombre: String, edad: Short, ID: String) extends Persona(nombre)


  /**
   * Las clase Adulto extiende la clase Persona utilizando el constructor que no recibe ningún parámetro.
   * @param nombre Nombre de la persona
   * @param edad edad de la persona
   * @param ID identificador de persona física
   */
  class Adulto_V3(nombre: String, edad: Short, ID: String) extends Persona

  /**
   * Las instancias de adulto pueden acceder al nombre y a la edad a través de la clase Persona.
   * Los parámetros del constructor persona están precedidos de "val", lo que los convierte en valores accesibles.
   * Si intentas acceder a los parámetros de la clase adulto, eliminando le herencia de persona, verás que no podrás.
   */
  val adulto_V1 = new Adulto_V1("Daniel", 20, "ABC012")
  // El nombre y la edad coincidirán con los parámetros del constructor de adulto
  println(s"${adulto_V1.nombre} = Daniel - ${adulto_V1.edad} = 20")
  val adulto_V2 = new Adulto_V2("Daniel", 20, "ABC012")
  // El nombre coincidirá con los parámetros del constructor de adulto, pero la edad es la de Persona (0)
  println(s"${adulto_V2.nombre} = Daniel - ${adulto_V2.edad} = 0")
  val adulto_V3 = new Adulto_V3("Daniel", 20, "ABC012")
  // El nombre y la edad serán lo de la Persona ("David" y 100)
  println(s"${adulto_V3.nombre} = David - ${adulto_V3.edad} = 100")

  //Herencia de una sola clase

  /**
   * La clase Animal es la super clase. Esta será heredada por [[Gato]], [[Perro]] y [[PerroRefinado]].
   * Esta clase puede contener tanto valores como métodos.
   */
  class Animal {
    val tipoDeCriatura = "salvaje"

    def comer(): Unit = println("nomnomnom")
  }

  /**
   * La clase gato puede acceder a los métodos y los valores de la superclase [[Animal]].
   */
  class Gato extends Animal {
    /**
     * Este método utiliza el método comer y le suma el println()
     */
    def crunch(): Unit = {
      comer()
      println("crunchcrunch")
    }
  }

  /**
   * Las clase Perro puede acceder a los métodos y los valores de la superclase [[Animal]].
   */
  class Perro extends Animal {
    /**
     * El perro es un animal doméstico, por lo que el valor [[tipoDeCriatura]] de la superclase NO
     * proporciona una descripción adecuada. Es por eso que utilizamos la palabra clave "override"
     * para sobreescribir su valor.
     */
    override val tipoDeCriatura: String = "domestico"

    /**
     * El el cao del método comer, a diferencia de la clase Gato, donde se llama al método comer dentro de un método
     * propio de gato, aquí queremos utilizar el mismo nombre pero con diferente resultado. Usamos "override".
     */
    override def comer(): Unit = {
      println("crunch crunch")
    }
  }

  /**
   * El perro refinado es una clase especializada de perro y hereda de Animal, aunque podría heredar de Perro.
   * El perro refinado es muy elegante y cuando come, expresa su alegría de forma muy educada.
   *
   * @param tipoDeCriatura El perro refinado puede ser de diferente raza o entrenamiento. Por eso, hacemos "override"
   *                       del tipo de criatura (valor de la clase [[Animal]]) en el constructor.
   */
  class PerroRefinado(override val tipoDeCriatura: String) extends Animal {
    override def comer(): Unit = println("¡Que delicioso! ¡Ñam, ñam!")
  }

  // Polimorfismo
  /**
   * Si el constructor de Animal no fuera accesible, podríamos generar una instancia de tipo Animal
   * si utilizamos un constructor disponible de alguna de sus subclases.
   */
  val animalDesconocido: Animal = new PerroRefinado("K9")
  animalDesconocido.comer()

  /**
   * El polimorfismo también permite que una función pueda recibir instancias de cualquiera de las subclases.
   *
   * @param algúnAnimal Una instancia de [[Animal]] o [[Gato]] o [[Perro]] o [[PerroRefinado]]
   * @return el tipo de Animal
   */
  def tipoDeAnimal(algúnAnimal: Animal): String = {
    algúnAnimal.tipoDeCriatura match {
      case "salvaje" => "gato o desconocido"
      case "domestico" => "perro"
      case _ => "perro refinado"
    }
  }

  println(s"${tipoDeAnimal(animalDesconocido)} = perro refinado")

  val gato = new Gato
  gato.crunch()
  val perro = new Perro
  perro.comer()
  println(perro.tipoDeCriatura)
  val perroRefinado = new PerroRefinado("guía")
  println(s"${perroRefinado.tipoDeCriatura} = guía")
  
  /** 
   * Como prevenir los "override":
   *   1) Usar "final" en val y def
   *   2) Usar "final" en class
   *   3) Usar "seal" en class: Sólo podrás extender la clase a las clases que estén presentes en el mismo archivo.
   */
}
