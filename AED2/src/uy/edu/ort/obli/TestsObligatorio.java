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
		
	}

}
