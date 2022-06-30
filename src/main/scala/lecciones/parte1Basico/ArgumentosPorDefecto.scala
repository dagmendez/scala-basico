package lecciones.parte1Basico

import scala.annotation.tailrec

/**
 * Lección 6 de la parte 1.
 * 
 * Scala permite que los argumentos de un método o de una clase tenga un valor por defecto.
 * Al llamar al método o al constructor de la clase, podemos obviar los valores que tienen un calor por defecto.
 * Eso sí, para que funcione el truco, los argumentos han de ser nombrados cuando se llama al método o se construye
 * la clase.
 */
object ArgumentosPorDefecto extends App {

  /**
   * Este es un método que calcula el factorial de un número. La implementación es recursiva de cola.
   * Muchas veces, el uso de llamadas recursivas de cola se logra definiendo un método interno con un acumulador.
   * Este método interno se llama con el parámetro del método inicial y un valor inicial para el acumulador.
   *
   * @param numero parámetro de entrada
   * @return valor de calcular el factorial del número
   */
  def factorialRecursivoDeCola(numero: Int): Int = {
    @tailrec
    def calculoConAcumulador(número: Int, acumulador: Int): Int = {
      if número <= 1 then acumulador
      else calculoConAcumulador(número - 1, número * acumulador)
    }

    calculoConAcumulador(numero, 1)
  }
  println(factorialRecursivoDeCola(10))

  /**
   * Este método es igual que el anterior, con la salvedad de que el acumulador tiene un valor definido por defecto
   * en la definición del método. Esto permite ahorrarnos tener que definir el método auxiliar dentro del cuerpo del
   * método principal.
   *
   * @param numero     parámetro de entrada
   * @param acumulador valor inicial del resultado
   * @return factorial del número
   */
  @tailrec
  def factorialRecursivoDeColaConValorPorDefecto(numero: Int, acumulador: Int = 1): Int = {
    if numero <= 1 then acumulador
    else factorialRecursivoDeColaConValorPorDefecto(numero - 1, numero * acumulador)
  }

  println(factorialRecursivoDeColaConValorPorDefecto(10))

  /**
   * Clase que representa un archivo de una imagen con valores por defecto
   *
   * @param formato formato de la imagen
   * @param alto    alto de la imagen en pixeles
   * @param ancho   ancho de la imagen en píxeles
   */
  class Imagen(formato: String = "jpg", alto: Int = 600, ancho: Int = 800) {

    /** Método que imprime por pantalla los datos de la imagen */
    def save(): Unit = {
      println(s"Saving ${alto}x$ancho.$formato")
    }
  }

  // Podemos generar una instancia de la clase sin ningún parámetro en el constructor
  val unaImagen =  new Imagen()
  unaImagen.save()

  // Podemos generar un instancia sobreescribiendo los parámetros por orden.
  val otraImagen = new Imagen("png", 1200)
  otraImagen.save()

  /** Si intentamos generar una instancia y no respetamos el orden:
  *   - 1) habrá un conflicto de tipos y no compilará
  *   - 2) no habrá conflicto de tipos, compilará, pero tendremos una instancia que no representa lo que queremos
  */
  //val yOtraImagenMas = new Imagen(1920, 1080) // falla porque 1920 no es tipo String
  
  // Generaremos esta instancia, pero la altura y la anchura están invertidas!!
  val yOtraImagenMas = new Imagen("JPEG", 1080, 1920)
  yOtraImagenMas.save()

  /**
   * Nombrar los parámetros permite inyectar los valores en el orden que nos parezca oportuno
   */
  val unaImagenBienGenerada1: Imagen = new Imagen(formato = "GIF")
  unaImagenBienGenerada1.save()
  val unaImagenBienGenerada2: Imagen = new Imagen(alto = 1080)
  unaImagenBienGenerada2.save()
  val unaImagenBienGenerada3: Imagen = new Imagen(ancho = 1920)
  unaImagenBienGenerada3.save()
  val unaImagenBienGenerada4: Imagen = new Imagen(ancho = 1920, formato = "GIF", alto = 1080)
  unaImagenBienGenerada4.save()

}
