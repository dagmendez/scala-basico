package ejercicios.parte2Objetos

import scala.annotation.targetName


object ColeccionExtendidaSolucion {

  def main(args: Array[String]): Unit = {
    /**
     * Ejercicios una vez implementados los métodos:
     *   - 1) Genera una lista vacía y luego añádele nuevos elementos
     *   - 2) Mapea una lista no vacía con un transformador anónimo
     *   - 3) Filtra una lista no vacía
     *   - 4) Concatena dos listas
     *   - 5) Utiliza flatMap
     *
     * Una vez hechos los ejercicios anteriores, añadir la palabra "case" delante del objeto Vacía y de la clase Llena.
     * Esto hará que nuevos métodos estén disponibles "gratis" (el compilador programa por nosotros).
     * Entre estos métodos, está el método equals o "==".
     * Ejercicio:
     *   - 1) Crear dos instancias de lista no vacías con los mismos valores.
     *   - 2) Comparar las listas con el operador "=="
     */

    val vacía: VacíaExtendida.type = VacíaExtendida

    println(vacía.añadir("jaja").imprimirElementos)
    println(vacía.añadir(1).imprimirElementos)

    println(
      vacía // Lista vacía
        .añadir("abc") // Añadimos un elemento
        .añadir("def") // Añadimos otro elemento
        .map[String]( // aplicamos una función de tipo Transformador a cada elemento
          new MiTransformador[String, String] { // generamos una instancia anónima del transformador
            def transformar(element: String): String = element.toUpperCase // implementamos el método abstracto
          })
        .imprimirElementos // Convertimos la colección en un String amigable
    )

    val filtrada: String = {
      LlenaExtendida(1,
        LlenaExtendida(2, VacíaExtendida)
      ).filter((element: Int) => element % 2 == 0) // Filtramos utilizando una función lambda
        .imprimirElementos
    }
    println(filtrada)


    println(
      (vacía ++ new LlenaExtendida[Int](10, vacía)) // Concatenamos dos listas
        .imprimirElementos
    )

    val unaLista = LlenaExtendida[Short](1, LlenaExtendida[Short](2, LlenaExtendida[Short](3, VacíaExtendida)))
    val otraLista = LlenaExtendida[Short](100, VacíaExtendida)
    println(unaLista.imprimirElementos)
    println(otraLista.imprimirElementos)

    val dosListas = unaLista ++ otraLista
    println(dosListas.imprimirElementos)
    println(dosListas.map(short => short * 2).imprimirElementos)
    println(dosListas.flatMap(
      short => LlenaExtendida[Short](short,
        LlenaExtendida[Short](short, 
          VacíaExtendida))
    ).imprimirElementos)

    println(
      LlenaExtendida[Int](1,
        LlenaExtendida[Int](2,
          LlenaExtendida[Int](3,
            LlenaExtendida[Int](4, VacíaExtendida))))
        .flatMap(new MiTransformador[Int, MiListaExpandida[Int]] { // Generamos un transformador anónimo
          def transformar(element: Int): MiListaExpandida[Int] = { // Implementamos el método abstracto
            LlenaExtendida[Int](
              element - 1, 
              LlenaExtendida[Int](
                element, 
                LlenaExtendida[Int](
                  element + 1, VacíaExtendida)))
          }
        }
        ).imprimirElementos)

    val cloneUno = LlenaExtendida[String]("a",
      LlenaExtendida[String]("b",
        LlenaExtendida[String]("c",
          VacíaExtendida)))
    val clonDos = LlenaExtendida[String]("a",
      LlenaExtendida[String]("b",
        LlenaExtendida[String]("c",
          VacíaExtendida)))
    // Si las lista no son iguales, el assert arrojará un error durante la ejecución!!
    assert(cloneUno == clonDos)
  }


  /**
   * Las instancias de tipo MiPredicado se van a usar como argumento del método filter.
   *
   * @tparam T es un tipo genérico que permite usar este Trait para cualquier tipo de lista.
   */
  trait MiPredicado[-T] {
    def prueba(elemento: T): Boolean
  }

  /**
   * Las instancias de tipo MiTransformador se van a usar como argumento del método map y flatMap.
   *
   * @tparam A es el tipo genérico de la lista original.
   * @tparam B Si el transformador se usa en un map, el tipo B es el tipo de resultado.
   *           Si el transformador se usa en un flatMap, el tipo B es de tipo MiListaExtendida[B]
   */
  trait MiTransformador[-A, B] {
    def transformar(elemento: A): B
  }

  /**
   * Esta es la superclase de la lista y al mismo tiempo actúa como contrato. Tanto Llena como Vacía tienen que
   * implementar todos los métodos definidos en esta clase abstracta.
   *
   * @tparam A El tipo es Covariante para que pueda compilar. La varianza es un tema avanzado. No eliminar el +.
   */
  abstract class MiListaExpandida[+A] {
    def cabeza: A

    def cola: MiListaExpandida[A]

    def estáVacía: Boolean

    def añadir[B >: A](member: B): MiListaExpandida[B]


    @targetName("concatenar")
    def ++[B >: A](other: MiListaExpandida[B]): MiListaExpandida[B]

    def imprimirElementos: String

    override def toString: String = s"[$imprimirElementos]"

    /**
     * Añade los métodos:
     *   - map(transformer) => MyList
     *   - filter(predicate) => MyList
     *   - flatMap(transformer[A, MyList[B]) => MyList[B]
     */
    def map[B](transformer: MiTransformador[A, B]): MiListaExpandida[B]

    def filter(predicate: MiPredicado[A]): MiListaExpandida[A]

    def flatMap[B](transformer: MiTransformador[A, MiListaExpandida[B]]): MiListaExpandida[B]
  }

  /**
   * LlenaExtendida es la clase que genera instancias del tipo MiListaExtendida.
   *
   * @param cabeza elemento del tipo A
   * @param cola   resto de los elementos de tipo A que están contenidos en otra instancia de MiListaExtendida.
   * @tparam A El tipo es Covariante para que pueda compilar. La varianza es un tema avanzado. No eliminar el +.
   */
  case class LlenaExtendida[A](val cabeza: A, val cola: MiListaExpandida[A]) extends MiListaExpandida[A] {

    def estáVacía: Boolean = false

    def añadir[B >: A](member: B): LlenaExtendida[B] = new LlenaExtendida[B](member, this)

    def transformar[B <: A]: B = cabeza.asInstanceOf[B]

    def map[B](transformer: MiTransformador[A, B]): MiListaExpandida[B] = {
      new LlenaExtendida[B](transformer.transformar(cabeza), cola.map(transformer))
    }

    def filter(predicate: MiPredicado[A]): MiListaExpandida[A] = {
      if (predicate.prueba(cabeza)) new LlenaExtendida[A](cabeza, cola.filter(predicate))
      else cola.filter(predicate)
    }

    def flatMap[B](transformer: MiTransformador[A, MiListaExpandida[B]]): MiListaExpandida[B] = {
      transformer.transformar(cabeza) ++ cola.flatMap(transformer)
    }

    @targetName("concatenar")
    def ++[B >: A](other: MiListaExpandida[B]): MiListaExpandida[B] = {
      new LlenaExtendida[B](cabeza, cola ++ other)
    }

    def imprimirElementos: String = s"$cabeza, ${cola.imprimirElementos}"

  }

  /**
   * VacíaExtendida un objeto porque representa la única instancia de la ListaExtendida cuando esta está vacía.
   */
  case object VacíaExtendida extends MiListaExpandida[Nothing] {
    def cabeza: Nothing = throw new NoSuchFieldError

    def cola: MiListaExpandida[Nothing] = throw new NoSuchFieldError

    def añadir[B >: Nothing](member: B): LlenaExtendida[B] = new LlenaExtendida[B](member, this)

    def estáVacía: Boolean = true

    def filter(predicate: MiPredicado[Nothing]): MiListaExpandida[Nothing] = this

    def map[B](transformer: MiTransformador[Nothing, B]): MiListaExpandida[B] = this

    def flatMap[B](transformer: MiTransformador[Nothing, MiListaExpandida[B]]): MiListaExpandida[B] = this

    @targetName("concatenar")
    def ++[B >: Nothing](other: MiListaExpandida[B]): MiListaExpandida[B] = other

    def imprimirElementos: String = ""

  }

}
