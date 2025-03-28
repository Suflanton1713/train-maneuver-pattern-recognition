package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {

    /*
        def trenMenosDerecha(tren: Tren): Tren = tren match {
          case t :: Nil => Nil
          case t :: ts => (t)::(trenMenosDerecha(ts))
        }

        def trenMenosIzquierda(tren: Tren): Tren = tren match {
          case t :: Nil => Nil
          case t :: ts => ts
        }

        def sacarPorDerecha(tren: Tren): Vagon = tren match{
          case t::Nil => t
          case t::ts => sacarPorDerecha(ts)
        }
        def sacarPorIzquierda(tren: Tren): Vagon = tren match {
          case t :: ts => t
        }
    */


    def mover(n:Int, ps:Tren, ss:Tren): (Tren,Tren) = {
      if(n>0){
        ps match{
          case Nil => (ps,ss)
          case x::Nil => (Nil, x :: ss)
          case x::xs=> ps.splitAt(math.max(0, ps.length - n)) match {
            case (left,right) => (left, right ++ ss)          }
            //(ps take (ps.length - n), (ps takeRight n)++ss )
        }
      }else if(n<0){
        ss match {
          case Nil => (ps,ss)
          case x::Nil => (ps:::List(x),Nil)
          case x :: xs => ss.splitAt(-1*n) match{
            case (left,right) => (ps++left, right)
          }

        }
      }else{
        (ps,ss)
      }
    }


    m match {
      case Uno(x) => x match {
        case 0  => e
        case x => 
          val resultado = mover(x, e._1,e._2);
          (resultado._1, resultado._2,e._3)

      }

      case Dos(x) => x match{
        case 0 => e
        case x =>
          val resultado = mover(x, e._1,e._3);
          (resultado._1, e._2,resultado._2)
    }
      case _ => e
    }

  }

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    movs match{
      case Nil => List(e)
      case x::Nil => e :: List(aplicarMovimiento(e,x))
      case x::xs => e :: aplicarMovimientos(aplicarMovimiento(e,x),xs)
      case _ => List(e)
      /* Se podría añadirle un case que revise que x es un movimiento válido (Uno(m), Dos(m)) en caso de no serlo
      no ejecutar el movimiento, es decir no hacer, e::aplicarMOvimientos(...), sin embargo,
      hasta ahora si algo así ocurre lo que pasaría es que repetiría el estado
       */
    }
  }
  /*
  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    // Dados dos trenes t1 (el original) y t2 (el deseado), devuelve la maniobra que se necesita hacer
    // para pasar una estación de maniobras del estado (t1, List(), List()) al estado (t2, List(), List()).
    // Implementación aquí
  }
  */
 
}
