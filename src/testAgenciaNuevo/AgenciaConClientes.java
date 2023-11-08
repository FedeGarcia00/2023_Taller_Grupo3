package testAgenciaNuevo;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContraException;
import excepciones.NombreUsuarioException;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class AgenciaConClientes {
	 Agencia agencia;
	@Before
	public void setUp() throws Exception {
		//Preparación del escenario, ya existen empleados en el sistema
		agencia = Agencia.getInstance();
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<>();
		HashMap<String, Empleador> empleadores = new HashMap<>();
    	agencia.setEmpleados(empleados);
    	agencia.setEmpleadores(empleadores);
        agencia.registroEmpleado("buacho", "123", "Bautista", "Orte", "223543", 23);
        agencia.registroEmpleado("fede", "345", "Federico", "Garcia", "22321", 23);
    	agencia.registroEmpleador("santi", "456", "Santiago", "43234", Constantes.JURIDICA,Constantes.SALUD);
    	agencia.registroEmpleador("pepe", "765", "Pedro", "21334", Constantes.FISICA,Constantes.SALUD);
        
	}

	@After
	public void tearDown() throws Exception {
	}


//	
//	@Test
//	public void testaplicarPromo1() {
//		fail("Not yet implemented");
//	}
//	@Test
//	public void testaplicarPromo2() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void  testcalculaPremiosCastigosAsignaciones() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void  testcerrarSesion() {
		// para testear el cierre de sesion hacemos un login, que ya tiene su propio test
		try {
			agencia.login("fede", "345");
		} catch (ContraException | NombreUsuarioException e) {
			// no deberia entrar aca, testeado en su propio metodo
		}
		agencia.cerrarSesion();
		Assert.assertEquals("El tipo de usuario al cerrar sesion deberia ser -1",-1, agencia.getTipoUsuario());
	}
	
	@Test
	public void  testcrearTicketEmpleado() {
		
	}
	
}
