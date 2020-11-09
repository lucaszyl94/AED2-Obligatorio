
package uy.edu.ort.obli;


public class Main {
    public static void main(String[] args){
  //  Nodo<Usuario> nodo= new Nodo<Usuario>();

//    nodo.setDato(u1);
//   ControladorUsuarios cu=new ControladorUsuarios();
//    Movil mm= new Movil();
 //   Nodo<Movil> m = new Nodo<Movil>(mm);
  //  Nodo<Movil>nm=new Nodo<Movil>(mm);
    
    
    Sistema s = Sistema.getInstancia();
      Prueba p = new Prueba();
    s.inicializarSistema(10);
  s.registrarUsuario("da1@.com","da1","123456");
  s.registrarUsuario("da3@.com","da3","123456");
  s.registrarUsuario("da4@.com","da4","123456");
  s.registrarUsuario("da2@.com","da2","123456");
  s.registrarUsuario("da5@.com","da5","123456");

 p.ver(s.listarUsuarios().resultado, Retorno.Resultado.OK, s.listarUsuarios().valorString);
 p.ver(s.registrarMovil("aar8114", 4.94065645841246544, 7.94065645841246544).resultado, Retorno.Resultado.OK, "");
 p.ver(s.registrarDelivery("48292601", 4.94065645841246544, 7.94065645841246544).resultado, Retorno.Resultado.OK,"" );
  p.ver(s.buscarUsuario("da4@.com").resultado, Retorno.Resultado.OK,"" );
    p.ver(s.registrarEsquina( 4.94065645841246555, 7.94065645841246555).resultado, Retorno.Resultado.OK,"" );
        p.ver(s.registrarTramo(4.94065645841246555, 7.94065645841246555,4.94065645841246544, 7.94065645841246544,1,10).resultado, Retorno.Resultado.OK,"" );
        p.ver(s.registrarTramo(4.94065645841246555, 7.94065645841246555,4.94065645841246544, 7.94065645841246544,1,10).resultado, Retorno.Resultado.OK,"" );
//   Usuario u1=new Usuario("da1@.com","da1","1234");
//cu.pertenece(u1);
    }
}
