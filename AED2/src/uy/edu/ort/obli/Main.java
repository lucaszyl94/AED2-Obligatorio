package uy.edu.ort.obli;

public class Main {

    public static void main(String[] args) throws Exception {

        TestsObligatorio test = new TestsObligatorio();
        test.setUp();
        test.testInicializarYDestruirSistema();
        test.testRegistroUsuarioOK();
        test.testRegistroUsuarioError1();
        test.testRegistroUsuarioError2();
        test.testBuscarUsuarioRaiz();
        test.testBuscarUsuarioRaizOrden();
        test.testBuscarUsuarioHoja();
        test.testBuscarUsuarioHojaOrden();
        test.testBuscarUsuarioHojaInterior();
        test.testBuscarUsuarioHojaInteriorOrden();
        test.testListarUsuarios();
        test.testRegistrarEsquina_OK();
        test.testRegistrarEsquinaError1();
        test.testRegistrarEsquinaError2();
        test.testRegistrarDelivery_OK();
        test.testRegistrarDeliveryError1();
        test.testRegistrarDeliveryError2();
        test.testRegistrarMovil_OK();
        test.testRegistrarMovilError1();
        test.testRegistrarMovilError2();
        test.testRegistrarTramoOK();
        test.testRegistrarTramoError1();
        test.testRegistrarTramoError2();
        test.testRegistrarTramoError3();
        test.testRegistrarTramoError4();
        test.testMovilMasCercanoOK1();
        test.testMovilMasCercanoError1();
        test.testMovilMasCercanoError2();
        test.testDeliveryMasCercanoOK1();
        test.testDeliveryMasCercanoError1();
        test.testDeliveryMasCercanoError2();
        test.testCaminoMinimoMovilOK1();
        test.testCaminoMinimoMovilError1();
        test.testCaminoMinimoDeliveryOK1();
        test.testCaminoMinimoDeliveryError1();

        //  Nodo<Usuario> nodo= new Nodo<Usuario>();
//    nodo.setDato(u1);
//   ControladorUsuarios cu=new ControladorUsuarios();
//    Movil mm= new Movil();
        //   Nodo<Movil> m = new Nodo<Movil>(mm);
        //  Nodo<Movil>nm=new Nodo<Movil>(mm);
        /*
    
    Sistema s = Sistema.getInstancia();
      Prueba p = new Prueba();
    s.inicializarSistema(10);
  s.registrarUsuario("da1@.com","da1","123456");
  s.registrarUsuario("da3@.com","da3","123456");
  s.registrarUsuario("da4@.com","da4","123456");
  s.registrarUsuario("da2@.com","da2","123456");
  s.registrarUsuario("da5@.com","da5","123456");

 p.ver(s.listarUsuarios().resultado, Retorno.Resultado.OK, s.listarUsuarios().valorString);
 p.ver(s.registrarMovil("aar8114", -34, -60).resultado, Retorno.Resultado.OK, "");
 p.ver(s.registrarDelivery("48292601", -33, -56).resultado, Retorno.Resultado.OK,"" );
  //p.ver(s.buscarUsuario("da4@.com").resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarEsquina( -12, -20).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarEsquina( -3, -50).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarEsquina( -33, -5).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarEsquina( -32, -56).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarEsquina( -90, -23).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarEsquina( -21, -3).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarTramo(-34, -56,-34, -5,1,10).resultado, Retorno.Resultado.OK,"" );
 p.ver(s.registrarTramo(4.94065645841246555, 7.94065645841246555,4.94065645841246544, 7.94065645841246544,1,10).resultado, Retorno.Resultado.OK,"" );
 System.out.println("hola");
 p.ver(s.movilMasCercano(-34.91, -56.17).resultado, Retorno.Resultado.OK, "");
 //p.ver(s.caminoMinimoMovil(1.0, 1.0, 7.0, 7.0, "CAP1891@gmail.com").resultado, Retorno.Resultado.OK, s.caminoMinimoMovil(1.0, 1.0, 7.0, 7.0, "CAP1891@gmail.com").valorString);
//   Usuario u1=new Usuario("da1@.com","da1","1234");
//cu.pertenece(u1);
  //p.ver(s.dibujarMapa().resultado, Retorno.Resultado.OK, s.dibujarMapa().valorString);
 //TestsObligatorio t= new TestsObligatorio();
 //t.setUp();
 //t.testInicializarYDestruirSistema();
 //t.testRegistroUsuarioOK();
  * 
         */
    }
}
