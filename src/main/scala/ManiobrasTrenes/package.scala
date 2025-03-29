package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento

  case class Uno(n: Int) extends Movimiento

  case class Dos(n: Int) extends Movimiento

  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    def mover(n: Int, ps: Tren, ss: Tren): (Tren, Tren) = {
      if (n > 0) {
        ps match {
          case Nil => (ps, ss)
          case x :: Nil => (Nil, x :: ss)
          case x :: xs => ps.splitAt(math.max(0, ps.length - n)) match {
            case (left, right) => (left, right ++ ss)
          }
        }
      } else if (n < 0) {
        ss match {
          case Nil => (ps, ss)
          case x :: Nil => (ps ::: List(x), Nil)
          case x :: xs => ss.splitAt(-1 * n) match {
            case (left, right) => (ps ++ left, right)
          }

        }
      } else {
        (ps, ss)
      }
    }
    m match {
      case Uno(x) => x match {
        case 0 => e
        case x =>
          val resultado = mover(x, e._1, e._2);
          (resultado._1, resultado._2, e._3)
      }
      case Dos(x) => x match {
        case 0 => e
        case x =>
          val resultado = mover(x, e._1, e._3);
          (resultado._1, e._2, resultado._2)
      }
      case _ => e
    }

  }

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    movs match {
      case Nil => List(e)
      case x :: Nil => e :: List(aplicarMovimiento(e, x))
      case x :: xs => e :: aplicarMovimientos(aplicarMovimiento(e, x), xs)
      case _ => List(e)
    }
  }

  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    def maniobrar(t1: Tren, t2: Tren, m: Maniobra): Maniobra = {
      t1 match {
        case Nil => m
        case p :: ps =>
          val vagon_buscado = t2.head
          p match {
            case `vagon_buscado` => maniobrar(t1.tail, t2.tail, m)
            case _ =>
              val maniobras = List(
                Uno(t1.length - t1.indexOf(t2.head)),
                Dos(t1.indexOf(t2.head)),
                Uno(-1 * (t1.length - t1.indexOf(t2.head))),
                Dos(-1 * t1.indexOf(t2.head))
              )
              val nuevoEstado = aplicarMovimientos((t1, Nil, Nil), maniobras)
              maniobrar(nuevoEstado.last.head.tail, t2.tail, m ++ maniobras)
          }
      }
    }

    maniobrar(t1, t2, Nil)
  }



}
