# Taller 3: Reconocimiento de Patrones - Maniobras de Trenes

Proyecto en Scala para el reconocimiento de patrones aplicado a la resolución de maniobras de trenes en una estación de maniobras.

## Descripción

Este taller tiene como objetivo adquirir destreza para escribir código funcional usando la técnica de reconocimiento de patrones. El problema consiste en reordenar los vagones de un tren con la ayuda de estaciones auxiliares para obtener una configuración deseada del tren.

### El Problema

Se está a cargo de realizar maniobras en los vagones de un tren. Cada vagón se puede maniobrar individualmente y el tren no tiene un motor específico. La tarea consiste en:

- Dado un tren inicial y un tren deseado
- Reordenar los vagones del tren dado usando estaciones auxiliares
- Computar una secuencia de movimientos de maniobra que logre el objetivo

### La Estación de Maniobras

La estación tiene:
- Un trayecto **principal** (donde está el tren inicial y debe quedar el tren final)
- Dos trayectos auxiliares: **"uno"** y **"dos"** (para maniobras intermedias)

## Modelo de Datos

### Tipos Base

```scala
type Vagon = Any          // Un vagón es un dato de cualquier tipo
type Tren = List[Vagon]   // Un tren es una lista de vagones (sin duplicados)
type Estado = (Tren, Tren, Tren)  // (principal, uno, dos)
```

### Movimientos

Los movimientos se representan con clases case:

```scala
trait Movimiento
case class Uno(n: Int) extends Movimiento  // Movimientos al trayecto "uno"
case class Dos(n: Int) extends Movimiento  // Movimientos al trayecto "dos"
```

**Reglas de movimiento:**
- `Uno(n)` con `n > 0`: mueve los `n` vagones de más a la derecha del trayecto principal al trayecto "uno"
- `Uno(n)` con `n < 0`: mueve los `|n|` vagones de más a la izquierda del trayecto "uno" al trayecto principal
- `Uno(0)`: no tiene efecto
- Análogo para movimientos `Dos(n)`

### Maniobra

```scala
type Maniobra = List[Movimiento]
```

## Funciones a Implementar

### 1. `aplicarMovimiento`

Aplica un movimiento a un estado y devuelve el estado resultante.

```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado
```

### 2. `aplicarMovimientos`

Aplica una lista de movimientos a un estado y devuelve la lista de estados por los que pasa la estación.

```scala
def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado]
```

### 3. `definirManiobra`

Dados dos trenes (inicial y deseado), devuelve la maniobra necesaria para convertir el tren inicial en el deseado.

```scala
def definirManiobra(t1: Tren, t2: Tren): Maniobra
```

**Condiciones:**
- Estado inicial: `(t1, Nil, Nil)`
- Estado final: `(t2, Nil, Nil)`
- Los trayectos auxiliares deben quedar desocupados al final

## Requisitos

- Scala 3.3.5
- sbt 1.10.11
- Java 8 o superior

## Estructura del Proyecto

```
train-maneuver-pattern-recognition/
├── build.sbt                 # Configuración del proyecto
├── project/
│   └── build.properties      # Versión de sbt
└── src/
    ├── main/
    │   └── scala/
    │       └── ManiobrasTrenes/
    │           └── package.scala  # Implementación de las funciones
    └── test/
        └── scala/
            └── Pruebas.sc    # Casos de prueba (worksheet)
```

## Instalación y Uso

### Compilar el proyecto

```bash
sbt compile
```

### Ejecutar pruebas

El archivo `Pruebas.sc` es un worksheet de Scala que contiene casos de prueba. Para ejecutarlo:

1. Abre el proyecto en IntelliJ IDEA
2. Abre el archivo `Pruebas.sc`
3. Ejecuta el worksheet

### Ejemplo de uso

```scala
import ManiobrasTrenes._

val e1 = (List('a', 'b', 'c', 'd'), Nil, Nil)
val e2 = aplicarMovimiento(e1, Uno(2))
// Resultado: (List('a', 'b'), List('c', 'd'), List())

val maniobra = definirManiobra(
  List('a', 'b', 'c', 'd'), 
  List('d', 'b', 'c', 'a')
)
// Devuelve la secuencia de movimientos necesaria
```

## Técnica de Programación

Este taller utiliza **reconocimiento de patrones** (pattern matching) como técnica principal de programación funcional en Scala. Todas las funciones deben implementarse esencialmente usando esta técnica.

## Entrega del Taller

El proyecto debe incluir:

1. **package.scala**: Implementación de las funciones en el paquete `ManiobrasTrenes`
2. **Pruebas.sc**: Worksheet con casos de prueba
3. **Informe PDF** con:
   - Informe de uso del reconocimiento de patrones
   - Informe de corrección (argumentación formal y casos de prueba)
   - Conclusiones

## Autores

Taller desarrollado por:
- Juan Francisco Díaz Frías
- Emily Núñez

Marzo 2025

## Licencia

Proyecto académico - Taller de Programación Funcional
