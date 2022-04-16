package ejercicios.parte2Objetos

object ColeccionSolucion {

  def main(args: Array[String]): Unit = {

    val lista = new Llena(
      1, new Llena( // Primera cabeza = 1
        2, new Llena( // Segunda cabeza = 2
          3, Vacía))) // Tercera cabeza = 3, cerramos la lista con el objeto Vacía

    println(lista.cola.cabeza)
    println(lista.añade(4).cabeza)
    println(lista.toString)
    println(Vacía.toString)
  }

  abstract class MyList {

    def cabeza: Int

    def cola: MyList

    def estaVacía: Boolean

    def añade(i: Int): MyList

    def imprimeElementos: String

    // Llamada polimórfica
    override def toString: String = s"[$imprimeElementos]"

  }

  class Llena(val cabeza: Int, val cola: MyList) extends MyList {
    def estaVacía = false

    def añade(i: Int): Llena = new Llena(i, this)

    def imprimeElementos: String = s"$cabeza, ${cola.imprimeElementos}"
  }

  object Vacía extends MyList {
    def cabeza: Int = throw new NoSuchElementException

    def cola: MyList = throw new NoSuchElementException

    def estaVacía = true

    def añade(i: Int) = new Llena(i, Vacía)

    def imprimeElementos: String = "Nil"
  }

}
