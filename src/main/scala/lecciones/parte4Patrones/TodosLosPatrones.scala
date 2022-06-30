
package lecciones.parte4Patrones

import ejercicios.parte3Funciones.ColeccionFuncionalExtendidaSolucion.{LlenaFuncionalExtendida, ColecciónFuncionalExtendida, VaciaFuncionalExtendia}


/**
 * Lección 2 de 4.
 * En este bloque vemos los posibles usos de pattern-matching con:
 *   - Constantes
 *   - Variables
 *   - Tuplas
 *   - Case Class
 *   - Listas
 *   - Tipos
 *   
 *   También veremos algunas técnicas que enriquecen el pattern-matching:
 *   - Asignación de nombre al match
 *   - Multiple patrón
 *   - Filtros
 */
object TodosLosPatrones extends App {

  // 1) Constantes

  val x: Any = "Scala"
  val constantes: String = x match {
    case 1 => "un número"
    case "Scala" => "Scala"
    case true => "La Verdad"
    case TodosLosPatrones => "Un objeto Singleton"
  }
  println(constantes)

  // 2) machea cualquier cosa

  // 2.1) la barra baja "_"
  val macheaCualquierCosa: Any = x match {
    case _ => x
  }
  println(macheaCualquierCosa)

  // 2.2) Variables
  val macheaVariables: String = x match {
    case algo => s"He encontrado $algo"
  }
  println(macheaVariables)

  //3) Tuplas

  val unaTupla: (Int, Int) = (1, 2)
  val macheUnaTupla: Any = unaTupla match {
    case (1, 1) => "Nada"
    case (algo, 2) => s"He encontrado $algo"
  }
  println(macheUnaTupla)

  val tuplasAnidadas: (Int, (Int, Int)) = (1, (2, 3))
  val macheaTuplasAnidadas: Any = tuplasAnidadas match {
    case (_, (2, v)) => s"2$v"
  }
  println(macheaTuplasAnidadas)

  // 4) Case Classes - patrón del constructor

  val unaListaFuncional: ColecciónFuncionalExtendida[Int] = LlenaFuncionalExtendida(1, LlenaFuncionalExtendida(2, VaciaFuncionalExtendia))
  val macheaLista: Option[Int] = unaListaFuncional match {
    case VaciaFuncionalExtendia => None
    case LlenaFuncionalExtendida(cabeza, VaciaFuncionalExtendia) => Some(cabeza)
    case LlenaFuncionalExtendida(cabeza, LlenaFuncionalExtendida(segundaCabeza, segundaCola)) => Some(segundaCabeza)
  }
  println(macheaLista)

  // 5) Patrones para listas

  val unaListaNormal = List(1, 2, 3, 42)
  val macheaUnaListaNormal: Int = unaListaNormal match {
    case List(1, _, _, 4) => 1 // patrón de extracción - avanzado
    case List(2, _*) => 1 // listas de longitud arbitraria - avanzado
    case 1 :: List(_) => 1 // patrón de prefijo (primer elemento)
    case List(1, 2, 3) :+ 42 => 42 // patrón de sufijo (último elemento)
  }
  println(macheaUnaListaNormal)

  // 6) Especificadores de tipo

  val tipoDesconocido: Any = 2
  val macheoDesconocido = tipoDesconocido match {
    case lista: List[Int] => lista.mkString(";") // especificador del tipo explícito
    case cualquierTipo@_ => s"$cualquierTipo"
  }
  println(macheoDesconocido)

  // 7) Asignación de un nombre para el patrón - name binding
  val macheoPorNombre = unaListaFuncional match {
    case noVacía@LlenaFuncionalExtendida(head, tail) => noVacía
    case LlenaFuncionalExtendida(1, elResto@LlenaFuncionalExtendida(2, VaciaFuncionalExtendia)) => elResto
  }
  println(macheoPorNombre)

  // 8) Multi patrón
  val multiPatrón = unaListaFuncional match {
    case VaciaFuncionalExtendia | LlenaFuncionalExtendida(0, _) => 0 // patrón compuesto
    case LlenaFuncionalExtendida(1, _) => 1
  }
  println(multiPatrón)

  // 9) Filtros - if guards
  val segundoElementoEspecial = unaListaFuncional match {
    case LlenaFuncionalExtendida(_, LlenaFuncionalExtendida(elementoEspecial, _)) if elementoEspecial % 2 == 0 => true
    case _ => false
  }
  println(segundoElementoEspecial)

  // Precaución: La JVM elimina los tipos internos de las colecciones: List, Seq... - Type Erasure

  /*
  val res0: List[Int] = List(1)

scala> res0 match {
     | case ls: List[String] => "str"
     | case li: List[Int] => "int"
     | case _ => "other"
     | }
-- Unchecked Warning: ----------------------------------------------------------
2 |case ls: List[String] => "str"
  |     ^
  |     the type test for List[String] cannot be checked at runtime
  */

  /**
   * El IDE nos avisa de que este macheo de patrones no es exhaustivo y
   * que no podrá validar los tipos durante la ejecución (borrado de tipos)
   */
  val unaListaDeEnteros = List[Int](1, 2, 3, 4, 5)
  
  //match may not be exhaustive.  It would fail on pattern case: List(_, _*)
  val macheoDeLista = unaListaDeEnteros match {
    
    //the type test for List[String] cannot be checked at runtime
    case listaDeCadena: List[String] => listaDeCadena
    
    //the type test for List[Boolean] cannot be checked at runtime
    case listaDeBooleanos: List[Boolean] => listaDeBooleanos
  }
  
  println(unaListaDeEnteros)

  val unaOpciónDeBooleanos = Option[Boolean](true)
  val optionMatcher = unaOpciónDeBooleanos match {
    case opciónDeCadena: Option[String] => opciónDeCadena
    case opciónDeBooleano: Option[Boolean] => opciónDeBooleano
  }
  println(unaOpciónDeBooleanos)

  val unaSecuenciaDeBooleanos = Seq[Boolean](true)
  val macheoDeSecuencia = unaSecuenciaDeBooleanos match {
    case secuenciaDeCadena: Seq[String] => secuenciaDeCadena
    case secuenciaDeBooleano: Seq[Boolean] => secuenciaDeBooleano
  }
  println(unaSecuenciaDeBooleanos)


}
