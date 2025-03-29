import ManiobrasTrenes._
val e1 = (List('A', 'B', 'C', 'D'), Nil, Nil)

// Prueba 1: Mover dos vagones a la segunda lista
val e2 = aplicarMovimiento(e1, Uno(2))
assert(e2 == (List('A', 'B'), List('C', 'D'), Nil), s"Error en Prueba 1: $e2")

//Prueba 2: Mover un vagón de la segunda lista de vuelta a la primera
val e3 = aplicarMovimiento(e2, Uno(-1))
assert(e3 == (List('A', 'B','C' ), List('D'), Nil), s"Error en Prueba 2: $e3")

// Prueba 3: Mover un vagón de la primera lista a la tercera lista
val e4 = aplicarMovimiento(e3, Dos(1))
assert(e4 == (List('A', 'B'), List('D'), List('C')), s"Error en Prueba 3: $e4")

// Prueba 4: Intentar mover 0 vagones (debe mantenerse igual)
val e5 = aplicarMovimiento(e4, Uno(0))
assert(e5 == e4, s"Error en Prueba 4: $e5")


// Prueba 5: Intentar mover más vagones de los que hay disponibles en la primera lista
val e6 = aplicarMovimiento(e5, Uno(5))
assert(e6 == (Nil, List('A', 'B', 'D'), List('C')), s"Error en Prueba 5: $e6")

// Prueba 6: Intentar mover un vagón de la tercera lista de vuelta a la primera
val e7 = aplicarMovimiento(e6, Dos(-1))
assert(e7 == (List('C'), List('A', 'B', 'D'), Nil), s"Error en Prueba 6: $e7")


val emov = (List('a', 'b'), List('c'), List('d'))
val resultado = aplicarMovimientos(emov, List(Uno(1), Dos(1), Uno(-2)))

val esperado = List(
  (List('a', 'b'), List('c'), List('d')),
  (List('a'), List('b', 'c'), List('d')),
  (List(), List('b', 'c'), List('a', 'd')),
  (List('b', 'c'), List(), List('a', 'd'))
)
assert(resultado == esperado)  // Esto debería funcionar



definirManiobra(List("c","b","a","d"), List("b","c","d","a"))

aplicarMovimientos((List("c","b","a","d"),Nil,Nil), List(Uno(3), Dos(1), Uno(-3), Dos(-1)))
println("Todas las pruebas pasaron correctamente.")