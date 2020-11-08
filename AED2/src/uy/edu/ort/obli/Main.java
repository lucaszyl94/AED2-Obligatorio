
package uy.edu.ort.obli;


public class Main {
    public static void main(String[] args){
  //  Nodo<Usuario> nodo= new Nodo<Usuario>();

//    nodo.setDato(u1);
   ControladorUsuarios cu=new ControladorUsuarios();
//    Movil mm= new Movil();
 //   Nodo<Movil> m = new Nodo<Movil>(mm);
  //  Nodo<Movil>nm=new Nodo<Movil>(mm);
    
    
    Sistema s = Sistema.getInstancia();
      Prueba p = new Prueba();
    s.inicializarSistema(10);
  s.registrarUsuario("123456","da1","da1@.com"); 
  s.registrarUsuario("123456","da3","da3@.com");
  s.registrarUsuario("123456","da4","da4@.com");
  s.registrarUsuario("123456","da2","da2@.com");
  s.registrarUsuario("123456","da5","da5@.com");

 p.ver(s.listarUsuarios().resultado, Retorno.Resultado.OK, s.listarUsuarios().valorString);
 //p.ver(s.registrarMovil("aar8114", 4.94065645841246544, 7.94065645841246544).resultado.OK, Retorno.Resultado.OK, "");
 p.ver(s.registrarDelivery("48292601", 4.94065645841246544, 7.94065645841246544).resultado, Retorno.Resultado.OK,s.registrarDelivery("81148114", 4.94065645841246544, 7.94065645841246544).valorString );
//   Usuario u1=new Usuario("da1@.com","da1","1234");
//cu.pertenece(u1);
    }
}
