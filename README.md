# scala-basico
Curso básico de Scala para los recien llegados a este lenguaje de programación.

Proyecto basado en las lecciones del curso "Scala & Functional Programming Essentials" de Rock The JVM
([Daniel Ciocîrlan](https://github.com/rockthejvm)). Este proyecto contiene parte del código que Daniel utiliza en su
curso. Además, contiene ejemplos adicionales desarrollados por mí y toda la documentación en castellano.

## Instalación

Es necesario instalar Scala 3 para poder ejecutar este proyecto. Es posible ejecutarlo con Scala 2, pero es necesario
comentar algunas partes del código. Para instalar Scala 3, visita
[Install Scala 3](https://scala-lang.org/download/scala3.html). Scala 3 está disponible para Linux, MacOS y Windows.

En cuanto al IDE, IntelliJ Idea ofrece soporte para Scala 2 y 3. [Install IntelliJ](https://www.jetbrains.com/idea/).

## Uso

Cada objeto extiende App o tiene un método main implementado, por lo que se puede ejecutar el código directamente en el
IDE. También se puede ejecutar compilando el código y especificando la clase que se quiere ejecutar.

## Soporte

Si algunos de los temas presentados en este proyecto no terminan de quedar claros, recomiendo visitar el canal de
[YouTube de Daniel](https://www.youtube.com/c/rockthejvm) o comprar algunos de sus cursos.

## Guía de contenido

El proyecto está dividido en 5 bloques:
 - [Básico](https://github.com/dagmendez/scala-basico/tree/main/src/main/scala/lecciones/parte1Basico):
   - [Expresiones](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/Expresiones.scala)
   - [Funciones](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/Funciones.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte1Basico/FuncionesEnunciado.scala)
   - [Tipos](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/Tipos.scala)
   - [Recursión](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/Recursion.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte1Basico/RecursionEnunciado.scala)
   - [CBN (Call-By-Name) CBV (Call-By-Value)](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/CBNvsCBV.scala)
   - [Argumentos por defecto](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/ArgumentosPorDefecto.scala)
   - [Operaciones string](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte1Basico/OperacionesStrings.scala)
 - [Objetos]():
   - [Orientación a objetos básica (OOBásico)](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/OOBasico.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte2Objetos/OOBasicoEnunciado.scala)
   - [Notaciones](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/Notaciones.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte2Objetos/NotacionesEnunciado.scala)
   - [Objetos](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/Objetos.scala)
   - [Herencia](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/Herencia.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte2Objetos/ColeccionEnunciado.scala)
   - [Tipos de datos abstractos](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/TiposDeDatosAbstractos.scala)
   - [Genéricos](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/Genericos.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte2Objetos/ColeccionGenericaEnunciado.scala)
   - [Clases anónimas](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/ClasesAnonimas.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte2Objetos/ColeccionExtendidaEnunciado.scala)
   - [Case clases](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/CaseClasses.scala)
   - [Enumeraciones (Scala 3)](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/Enumeraciones.scala)
   - [Excepciones](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/Excepciones.scala) - [Ejercicios](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/ejercicios/parte2Objetos/ExcepcionesEnunciado.scala)
   - [Paquetes e imports](https://github.com/dagmendez/scala-basico/blob/main/src/main/scala/lecciones/parte2Objetos/PaquetesEImports.scala)
 - Funciones:
   - ¿Qué es una función?
   - Funciones anónimas
   - Funciones de alto orden (HOF - High Order Function) y curries
   - Map, flatMap, filter y for-comprehension
   - Colecciones: Seq, List, Array y Vector
   - Tuples y mapas
   - Opciones
   - Manejando errores
 - Patrones:
   - Pattern Matching
   - Todos los patrones
   - Patrones donde no te los esperas
   - Sintaxis sin llaves {} (Scala 3)

## Contribución

Si quieres colaborar, puedes contactarme para que te dé acceso al proyecto.

## Autores y agradecimientos

Este proyecto está basado en el trabajo de Daniel Ciocîrlan. El código adicional y la documentación en castellano han
sido realizados por:

- David A. Gil Mendez

## Situación del proyecto

Trabajo en curso.