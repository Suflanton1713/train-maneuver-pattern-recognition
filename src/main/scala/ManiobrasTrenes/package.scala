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


    def mover(n:Int, c:Int, ps:Tren, ss:Tren, nomueve:Tren): (Tren,Tren,Tren) = {
      if(n>0 && n!=c){
        ps match{
          case Nil => (ps,ss,nomueve)
          case x::xs=> mover(n,c+1,ps.init, (ps.last)::ss, nomueve)
        }
      }else if(n<0 && n!=c){
        ss match {
          case Nil => (ps,ss,nomueve)
          case x :: xs => mover(n, c - 1, ps ++ List(ss.head), ss.tail, nomueve)
        }
      }else{
        (ps,ss,nomueve)
      }
    }


    m match {
      case Uno(x) => x match {
        case 0  => e
        case x => 
          val resultado = mover(x,0,e._1,e._2, e._3);
          (resultado._1, resultado._2,resultado._3)

      }

      case Dos(x) => x match{
        case 0 => e
        case x =>
          val resultado = mover(x,0,e._1,e._3, e._2);
          (resultado._1, resultado._3,resultado._2)
    }
    }

  }
/*
  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    // Implementación aquí
  }

  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    // Dados dos trenes t1 (el original) y t2 (el deseado), devuelve la maniobra que se necesita hacer
    // para pasar una estación de maniobras del estado (t1, List(), List()) al estado (t2, List(), List()).
    // Implementación aquí
  }
  */
 
}
