package ejercicios.parte4Patrones

import lecciones.parte4Patrones.PatronesDondeNoLosEsperas


/**
 * Ejercicio del bloque teórico de Patter Matching [[PatronesDondeNoLosEsperas]].
 *
 * Construir un método del tipo Expresión => String.
 * El String es una representación de como una persona escribiría una operación de suma y multiplicación.
 * Por ejemplo:
 *   - 3 por 5 más 2 => 3 * 5 + 2
 *   - 2 + 4 y después por 5 => (2 + 4) * 5
 *
 * Solución disponible en [[PatternMatchingSolucion]].
 */
object PatternMatchingEnunciado {

  def main(args: Array[String]): Unit = {
    val test_1 = Suma(Multiplicación(Número(2), Número(1)), Número(3))
    //Debería resultar en 2 * 1 + 3
    val test_2 = Suma(Multiplicación(Suma(Número(3), Número(2)), Número(4)), Multiplicación(Número(1), Número(2)))
    // Debería resultar en (3 + 2) * 4 + 1 * 2
  }

  sealed trait Expresión

  case class Número(n: Int) extends Expresión

  case class Suma(e1: Expresión, e2: Expresión) extends Expresión

  case class Multiplicación(e1: Expresión, e2: Expresión) extends Expresión

  def transcripciónHumana(expresión: Expresión): String = ???


}
