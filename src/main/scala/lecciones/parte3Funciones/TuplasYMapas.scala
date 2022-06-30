package lecciones.parte3Funciones

import ejercicios.parte3Funciones.{RedSocialModelizadaEnunciado, RedSocialConMapasEnunciado}

object TuplasYMapas extends App {
  
  /**
   * Lección 6 de 8.
   * 
   * En Scala, una tupla es un valor que contiene un número fijo de elementos,
   * cada uno de ellos puede ser de distinto tipo. Las tuplas son inmutables.
   * Las tuplas son especialmente útiles para retornar múltiples valores desde un método.
   *
   * Ejercicios en [[RedSocialConMapasEnunciado]] y [[RedSocialModelizadaEnunciado]].
   * 
   * @note Scala 2: se accede a los valores con la notación x._1, x._2, x._n; n = ultimo elemento de la tupla.
   * @note Scala 3: se accede a los valores con la notación x(1), x(2), x(n); n = ultimo elemento de la tupla.
   */

  val unaTupla = Tuple2(4, 'a')

  // acceder a los valores de la tupla
  println(unaTupla._1)
  println(unaTupla(1))

  // actualizar mientras copias
  println(unaTupla.copy(_2 = 'b'))

  // invertir la tupla
  println(unaTupla.swap)

  // representación en String
  println(unaTupla.toString())

  // suma de tuplas
  println((unaTupla ++ unaTupla).toString())

  // número de elementos
  println(unaTupla.productArity)

  
  /**
   * Un mapa es un [[Iterable]] cuyos elementos son parejas de claves y valores (key-value).
   * Estas parejas se pueden escribir como una tupla: (clave, valor)
   * También se pueden escribir así: clave -> valor
   *
   * @example Map("x" -> 24, "y" -> 25, "z" -> 26) es igual a Map(("x", 24), ("y", 25), ("z", 26))
   *
   *          Las operaciones fundamentales del mapa son similares a las de los sets. Estas son:
   *   - Recuperación de valores: apply, get, getOrElse, contains, isDefinedAt
   *   - Inclusión de valores: +, ++
   *   - Actualización: update
   *   - Borrado: -, --
   *   - Generación de sub-colecciones: keys, keySet, keysIterator, values, valuesIterator
   *   - Transformaciones: filterKeys, mapValues
   */

  val unMapa: Map[String, Any] = Map("David" -> 'd', "Daniel" -> "l", "42" -> 2)
  println(unMapa)

  val otroMapa: Map[String, Float] = Map()
  println(otroMapa)

  val listaDeContactos: Map[String, Int] = Map(("Jaime", 555), ("Juan", 777))
  println(listaDeContactos)

  // Verificar si existe la llave/key
  println(listaDeContactos.contains("Jaime"))
  println(listaDeContactos.contains("Juan"))

  // añadir un nuevo contacto - Los mapa son inmutables, por lo que creamos uno nuevo
  val nuevoContacto = Map("María" -> 999)
  println(nuevoContacto)
  val nuevaListaDeContactos = nuevoContacto ++ listaDeContactos
  println(nuevaListaDeContactos)

  /**
   * Los mapa son colecciones, por lo que los métodos de map, flatMap y filter están disponibles
   */

  val usandoMap: Map[String, Int] = nuevaListaDeContactos.map(
      (x: String, y: Int) => (x.toLowerCase(), y * 100)  // Función anónima - Lambda
    )
  println(usandoMap)

  val usandoFlatMap: Map[String, Int] = nuevaListaDeContactos.flatMap{
    // Como los elementos de un mapa son tuplas, utilizamos el ._1 y ._2
    tupla => Map((tupla._1.toLowerCase(), tupla._2), (tupla._1.toUpperCase(), tupla._2))
  }
  println(usandoFlatMap)

  val usandoFilter: Map[String, Int] = nuevaListaDeContactos.filter(pair => pair._1 == "María")
  println(usandoFilter)

  /**
   * Maps incluye algunos métodos propios como filterKeys y mapValues.
   * @note mapValues tiene bugs conocidos en versiones antiguas de Scala.
   */

  val usandoFilterKeys: Map[String, Int] = nuevaListaDeContactos.view.filterKeys(_.equals("Juan")).toMap
  println(usandoFilterKeys)

  val usandoMapValues: Map[String, String] = nuevaListaDeContactos.view.mapValues((v: Int) => s"+34-$v").toMap
  println(usandoMapValues)

  /**
   * Los mapas se pueden convertir a otro tipo de colecciones y viceversa.
   */

  val deMapaALista_v1 = listaDeContactos.toList
  println(deMapaALista_v1)
  val deMapaALista_v2 = List("Manuel" -> 456, "Sara" -> 983).toMap
  println(deMapaALista_v2)

  /**
   * Las operaciones de agrupación (groupBy y similares) en colecciones generan mapas.
   */

  val listaDeNombres = List("Roberto", "Jaime", "Angela", "Antonio", "Barbara", "Juana")
  val listaAgrupadoPorInicial: Map[Char, List[String]] = listaDeNombres.groupBy(nombre => nombre.charAt(0))
  println(s"Lista agrupada por inicial: $listaAgrupadoPorInicial")
  val listaAgrupadaPorSegundaLetra: Map[Char, List[String]] = listaDeNombres.groupBy((nombre: String) => nombre.charAt(1))
  println(s"Lista agrupada por segunda letra: $listaAgrupadaPorSegundaLetra")

  // Los mapa también se pueden agrupar
  val agrupadoPorInicial: Map[Char, Map[String, Int]] = nuevaListaDeContactos.groupBy(_._1.charAt(0))
  println(agrupadoPorInicial)
  val agrupadoPorSegundaLetra: Map[Char, Map[String, Int]] = nuevaListaDeContactos.groupBy((x: String, _) => x.charAt(1))
  println(agrupadoPorSegundaLetra)

}
