package ejercicios.parte2Objetos

object ColeccionGenericaSolucion {

  def main(args: Array[String]): Unit = {
    val listaDeNúmeros = {
      new LlenaCovariante(1,
        new LlenaCovariante(2,
          new LlenaCovariante(3,
            VacíaCovariante)))
    }
    println(listaDeNúmeros.cola.cabeza)
    println(listaDeNúmeros.añade(4).cabeza)
    println(listaDeNúmeros.toString)
    println(VacíaCovariante.toString)


    val listaDePalabras = {
      new LlenaCovariante("Hello",
        new LlenaCovariante(" ",
          new LlenaCovariante("world",
            VacíaCovariante)))
    }
    println(listaDePalabras.cola.cabeza)
    println(listaDePalabras.toString)
    println(listaDePalabras.añade(" ").añade("!").toString)
    println(VacíaCovariante.toString)

  }

  abstract class MiListaCovariante[+A] {

    def cabeza: A

    def cola: MiListaCovariante[A]

    def estáVacía: Boolean

    def añade[B >: A](i: B): MiListaCovariante[B]

    def imprimeElementos: String

    // Llamada polimórfica
    override def toString: String = s"[$imprimeElementos]"

  }

  class LlenaCovariante[+A](val cabeza: A, val cola: MiListaCovariante[A]) extends MiListaCovariante[A] {
    def estáVacía = false

    def añade[B >: A](i: B): MiListaCovariante[B] = new LlenaCovariante[B](i, this)

    def imprimeElementos: String = s"$cabeza, ${cola.imprimeElementos}"
  }

  object VacíaCovariante extends MiListaCovariante[Nothing] {
    def cabeza: Nothing = throw new NoSuchElementException

    def cola: MiListaCovariante[Nothing] = throw new NoSuchElementException

    def estáVacía = true

    def añade[B >: Nothing](i: B): MiListaCovariante[B] = new LlenaCovariante(i, VacíaCovariante)

    def imprimeElementos: String = ""
  }
}
