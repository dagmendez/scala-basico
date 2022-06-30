package ejercicios.parte4Patrones

import lecciones.parte4Patrones.PatronesDondeNoLosEsperas


/**
 * Solución de  [[PatternMatchingEnunciado]].
 */
object PatternMatchingSolucion {

  def main(args: Array[String]): Unit = {
    println(transcripciónHumana(Suma(Multiplicación(Número(2), Número(1)), Número(3))))
    println(transcripciónHumana(Suma(Multiplicación(Suma(Número(3), Número(2)), Número(4)), Multiplicación(Número(1), Número(2)))))
  }

  sealed trait Expresión

  case class Número(n: Int) extends Expresión

  case class Suma(e1: Expresión, e2: Expresión) extends Expresión

  case class Multiplicación(e1: Expresión, e2: Expresión) extends Expresión

  def transcripciónHumana(expresión: Expresión): String = {
    // Una solución
    método(expresión)
    // Otra solución
    función(expresión)
  }

  /**
   * Definido como una función
   */
  val función: Expresión => String = {
    case Número(n) => n.toString
    case Suma(e1, e2) => s"${función(e1)} + ${función(e2)}"
    case Multiplicación(e1, e2) =>
      val añadeParéntesis: Expresión => String = {
        case e@Multiplicación(_, _) => función(e)
        case e@Número(_) => función(e)
        case e => s"(${función(e)})"
      }
      s"${añadeParéntesis(e1)} * ${añadeParéntesis(e2)}"

  }

  /**
   * Definido como un método
   * @param expresión expresión en clases
   * @return expresión humana
   */
  def método(expresión: Expresión): String = {
    // Método auxiliar para los paréntesis
    def quizásAñadeParéntesis(expr: Expresión) = expr match {
      case Multiplicación(_, _) => método(expr)
      case Número(_) => método(expr)
      case _ => "(" + método(expr) + ")"
    }
    
    expresión match {
      case Número(n) => n.toString
      case Suma(e1, e2) => método(e1) + " + " + método(e2)
      case Multiplicación(e1, e2) => quizásAñadeParéntesis(e1) + " * " + quizásAñadeParéntesis(e2)
    }
  }



}
