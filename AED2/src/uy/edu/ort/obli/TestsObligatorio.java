package uy.edu.ort.obli;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestsObligatorio {

	private ISistema sistema;
	private Retorno retorno;
	
	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();
	}

	@Test
	public void testInicializarYDestruirSistema() {
		assertEquals(Retorno.Resultado.OK, sistema.inicializarSistema(10).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.destruirSistema().resultado);
	}

	
	// TESTS USUARIOS
	@Test
	public void testRegistroUsuarioOK() {
		sistema.inicializarSistema(10);
		//Datos de la prueba
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar","").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas","").resultado);
	}

	@Test
	public void testRegistroUsuarioError1() {
		sistema.inicializarSistema(10);
		//Formatos de Email incorrectos
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("11111", "a","").resultado); 
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("aaaaa", "a","").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("rrr@", "a","").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("@wwwww", "a","").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("a@b", "a","").resultado);

	}
	
	@Test
	public void testRegistroUsuarioError2() {
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria","").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarUsuario("maria@gmail.com", "Maria","").resultado);
	}
	

	@Test
	public void testBuscarUsuarioRaiz() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar","").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas","").resultado);
		
		retorno = sistema.buscarUsuario("omar@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("omar@gmail.com;Omar", retorno.valorString);		
		
	}
	
	@Test
	public void testBuscarUsuarioRaizOrden() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar","").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas","").resultado);
		
		retorno = sistema.buscarUsuario("omar@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("omar@gmail.com;Omar", retorno.valorString);
		assertEquals(1, retorno.valorEntero);
		
	}
	
	@Test
	public void testBuscarUsuarioHoja() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar","").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia","").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas","").resultado);
		
		retorno = sistema.buscarUsuario("nicolas@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("nicolas@gmail.com;Nicolas", retorno.valorString);	
		assertEquals(4, retorno.valorEntero);	
		
	}
	
	@Test
	public void testBuscarUsuarioHojaOrden() {
		
		sistema.inicializarSistema(10);

		sistema.registrarUsuario("AAA1111@gmail.com", "Omar","");	
		sistema.registrarUsuario("BBB1111@gmail.com", "Jorge","");
		sistema.registrarUsuario("UUU1111@gmail.com", "Maria","");
		sistema.registrarUsuario("FFF1111@gmail.com", "Ximena","");
		sistema.registrarUsuario("TTT1111@gmail.com", "Analia","");
		sistema.registrarUsuario("CAP1891@gmail.com", "Nicolas","");
		
		retorno = sistema.buscarUsuario("CAP1891@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("CAP1891@gmail.com;Nicolas", retorno.valorString);
		assertEquals(5, retorno.valorEntero);
		
	}
	
	@Test
	public void testBuscarUsuarioHojaInterior() {
		
		sistema.inicializarSistema(10);

		sistema.registrarUsuario("AAA1111@gmail.com", "Omar","");	
		sistema.registrarUsuario("BBB1111@gmail.com", "Jorge","");
		sistema.registrarUsuario("UUU1111@gmail.com", "Maria","");
		sistema.registrarUsuario("FFF1111@gmail.com", "Ximena","");
		sistema.registrarUsuario("TTT1111@gmail.com", "Analia","");
		sistema.registrarUsuario("CAP1891@gmail.com", "Nicolas","");
		
		retorno = sistema.buscarUsuario("FFF1111@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("FFF1111@gmail.com;Ximena", retorno.valorString);
		
	}
	

	
	@Test
	public void testBuscarUsuarioHojaInteriorOrden() {
		
		sistema.inicializarSistema(10);

		sistema.registrarUsuario("AAA1111@gmail.com", "Omar","");	
		sistema.registrarUsuario("BBB1111@gmail.com", "Jorge","");
		sistema.registrarUsuario("UUU1111@gmail.com", "Maria","");
		sistema.registrarUsuario("FFF1111@gmail.com", "Ximena","");
		sistema.registrarUsuario("TTT1111@gmail.com", "Analia","");
		sistema.registrarUsuario("CAP1891@gmail.com", "Nicolas","");
		
		retorno = sistema.buscarUsuario("FFF1111@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("FFF1111@gmail.com;Ximena", retorno.valorString);
		assertEquals(4, retorno.valorEntero);
		
	}
	

	@Test
	public void testListarUsuarios() {
		sistema.inicializarSistema(10);

		sistema.registrarUsuario("AAA1111@gmail.com", "Omar","");	
		sistema.registrarUsuario("BBB1111@gmail.com", "Jorge","");
		sistema.registrarUsuario("UUU1111@gmail.com", "Maria","");
		sistema.registrarUsuario("FFF1111@gmail.com", "Ximena","");
		sistema.registrarUsuario("TTT1111@gmail.com", "Analia","");
		sistema.registrarUsuario("CAP1891@gmail.com", "Nicolas","");
		
		retorno = sistema.listarUsuarios();
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("AAA1111@gmail.com;Omar|BBB1111@gmail.com;Jorge|CAP1891@gmail.com;Nicolas|FFF1111@gmail.com;Ximena|TTT1111@gmail.com;Analia|UUU1111@gmail.com;Maria", retorno.valorString);
	}

	@Test
	public void testRegistrarEsquina_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);

	}

	@Test
	public void testRegistrarEsquinaError1() {
	
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(4.0, 4.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarEsquina(6.0, 6.0).resultado);
		
	}
	
	@Test
	public void testRegistrarEsquinaError2() {
	
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarEsquina(5.0, 5.0).resultado);
		
	}
	
	@Test
	public void testRegistrarDelivery_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarDelivery("001", 1.0, 1.0).resultado);
		
	}

	@Test
	public void testRegistrarDeliveryError1() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarDelivery("001", 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarDelivery("002", 2.0, 2.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarDelivery("003", 3.0, 3.0).resultado);
	}

	@Test
	public void testRegistrarDeliveryError2() {
		
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarDelivery("001", 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarDelivery("002", 1.0, 1.0).resultado); // mismas coordenadas
	}
	
	@Test
	public void testRegistrarMovil_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMovil("001", 1.0, 1.0).resultado);
		
	}

	@Test
	public void testRegistrarMovilError1() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMovil("001", 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMovil("002", 2.0, 2.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarMovil("003", 3.0, 3.0).resultado);
	}

	@Test
	public void testRegistrarMovilError2() {
		
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMovil("001", 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarMovil("002", 1.0, 1.0).resultado); // mismas coordenadas
	}
	
	@Test
	public void testRegistrarTramoOK() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10, 1).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 5, 1).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 3, 1).resultado);
	}

	//Metros incorrecto
	@Test
	public void testRegistrarTramoError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, -1, 1).resultado);
	}

	//Minutos incorrecto
	@Test
	public void testRegistrarTramoError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 1, -1).resultado);
	}
	
	//Punto no existe
	@Test
	public void testRegistrarTramoError3() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarTramo(9.0, 9.0, 2.0, 2.0, 10, 1).resultado);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarTramo(3.0, 3.0, 8.0, 8.0, 10, 1).resultado);
	}

	// Tramo ya existe
	@Test
	public void testRegistrarTramoError4() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10, 1).resultado);
		assertEquals(Retorno.Resultado.ERROR_4, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10, 1).resultado);
	}

	@Test
	public void testMovilMasCercanoOK1() {
		
		sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarEsquina(8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);

		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 5, 5);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 3, 3);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 1, 1);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 11, 11);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 2, 2);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 19, 19);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 14, 14);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 6, 6);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 1, 1);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 3, 3);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 8, 8);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 8, 8); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 5, 5); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 5, 5); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 4, 4);
		

		retorno = sistema.movilMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(23, retorno.valorEntero);

		retorno = sistema.movilMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(25, retorno.valorEntero);
		
	}

	@Test
	public void testMovilMasCercanoError1() {
		
		sistema.inicializarSistema(10);
	
		retorno = sistema.movilMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
	}

	@Test
	public void testMovilMasCercanoError2() {
		
		sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarEsquina(8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);

		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 5, 5);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 3, 3);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 1, 1);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 11, 11);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 2, 2);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 19, 19);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 14, 14);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 6, 6);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 1, 1);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 3, 3);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 8, 8);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 8, 8); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 5, 5); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 5, 5); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 4, 4);
		

		retorno = sistema.movilMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(23, retorno.valorEntero);

		retorno = sistema.movilMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(25, retorno.valorEntero);

		retorno = sistema.movilMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.ERROR_2, retorno.resultado);
		
	}

	@Test
	public void testDeliveryMasCercanoOK1() {
		
		sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarEsquina(8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);

		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 5, 5);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 3, 3);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 1, 1);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 11, 11);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 2, 2);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 19, 19);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 14, 14);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 6, 6);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 1, 1);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 3, 3);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 8, 8);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 8, 8); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 5, 5); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 5, 5); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 4, 4);
		

		retorno = sistema.deliveryMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(1, retorno.valorEntero);
		assertEquals("4.0;4.0", retorno.valorString);

		retorno = sistema.deliveryMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(5, retorno.valorEntero);
		assertEquals("10.0;10.0", retorno.valorString);
		
	}

	@Test
	public void testDeliveryMasCercanoError1() {
		
		sistema.inicializarSistema(10);
	
		retorno = sistema.deliveryMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
	}

	@Test
	public void testDeliveryMasCercanoError2() {
		
		sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarEsquina(8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);

		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 5, 5);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 3, 3);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 1, 1);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 11, 11);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 2, 2);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 19, 19);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 14, 14);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 6, 6);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 1, 1);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 3, 3);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 8, 8);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 8, 8); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 5, 5); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 5, 5); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 4, 4);
		
		retorno = sistema.deliveryMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(1, retorno.valorEntero);
		assertEquals("4.0;4.0", retorno.valorString);

		retorno = sistema.deliveryMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(5, retorno.valorEntero);
		assertEquals("10.0;10.0", retorno.valorString);

		retorno = sistema.deliveryMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.ERROR_2, retorno.resultado);
		
	}

	@Test
	public void testCaminoMinimoMovilOK1() {
		
		sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarMovil("1", 1.0, 1.0);
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarEsquina(8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);

		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 5, 9999);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 3, 9999);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 1, 9999);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 11, 9999);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 2, 9999);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 19, 9999);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 14, 9999);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 6, 9999);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 1, 9999);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 3, 9999);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 8, 9999);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 8, 9999); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 5, 9999); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 5, 9999); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 4, 9999);
		

		retorno = sistema.caminoMinimoMovil(1.0, 1.0, 7.0, 7.0, "CAP1891@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(23, retorno.valorEntero);
		assertEquals("1.0;1.0|2.0;2.0|3.0;3.0|6.0;6.0|5.0;5.0|8.0;8.0|7.0;7.0", retorno.valorString);
		
	}

	@Test
	public void testCaminoMinimoMovilError1() {
		
		sistema.inicializarSistema(10);

		sistema.registrarMovil("1", 1.0, 1.0);

		retorno = sistema.caminoMinimoMovil(1.0, 1.0, 2.0, 2.0, "CAP1891@gmail.com");
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
		retorno = sistema.caminoMinimoMovil(2.0, 2.0, 1.0, 1.0, "CAP1891@gmail.com");
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
	}

	@Test
	public void testCaminoMinimoDeliveryOK1() {
		
		sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarDelivery("1", 1.0, 1.0);
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarEsquina(8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);

		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 9999, 5);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 9999, 3);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 9999, 1);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 9999, 11);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 9999, 2);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 9999, 19);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 9999, 14);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 9999, 6);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 9999, 1);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 9999, 3);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 9999, 8);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 9999, 8); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 9999, 5); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 9999, 5); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 9999, 4);

		retorno = sistema.caminoMinimoDelivery(1.0, 1.0, 10.0, 10.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(15, retorno.valorEntero);
		assertEquals("1.0;1.0|2.0;2.0|3.0;3.0|6.0;6.0|9.0;9.0|10.0;10.0", retorno.valorString);
		
	}

	@Test
	public void testCaminoMinimoDeliveryError1() {
		
		sistema.inicializarSistema(10);

		sistema.registrarDelivery("1", 1.0, 1.0);

		retorno = sistema.caminoMinimoDelivery(1.0, 1.0, 2.0, 2.0);
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
		retorno = sistema.caminoMinimoDelivery(2.0, 2.0, 1.0, 1.0);
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
	}

}