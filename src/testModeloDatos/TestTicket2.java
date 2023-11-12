package testModeloDatos;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ImposibleModificarTicketsException;
import org.junit.Assert;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestTicket2 {
	  Agencia agencia;
	  Cliente empleado1;
	  Cliente empleador1;

	@Before
	public void setUp() throws Exception {
		agencia = Agencia.getInstance();
	    agencia.setEstadoContratacion(false);
	    agencia.setLimitesRemuneracion(1000, 1500);
	    HashMap<String, EmpleadoPretenso> empleados = new HashMap<>();
	    HashMap<String, Empleador> empleadores = new HashMap<>();
	    agencia.setEmpleados(empleados);
	    agencia.setEmpleadores(empleadores);
	    empleador1 =
	    	      agencia.registroEmpleador(
	    	        "pepe",
	    	        "765",
	    	        "Pedro",
	    	        "21334",
	    	        Constantes.FISICA,
	    	        Constantes.SALUD
	    	      );
	    agencia.crearTicketEmpleador(
	    	      Constantes.PRESENCIAL,
	    	      1250,
	    	      Constantes.JORNADA_COMPLETA,
	    	      Constantes.SENIOR,
	    	      Constantes.EXP_MEDIA,
	    	      Constantes.SECUNDARIOS,
	    	      empleador1
	    	    );
	    empleado1 =
	    	      agencia.registroEmpleado(
	    	        "fede",
	    	        "345",
	    	        "Federico",
	    	        "Garcia",
	    	        "22321",
	    	        23
	    	      );
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetComparacionEstudios1() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MEDIA,
			  Constantes.PRIMARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionEstudios(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);
		
	}
	@Test
	public void testgetComparacionEstudios2() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MEDIA,
			  Constantes.SECUNDARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionEstudios(otro);
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);
		
	}
	@Test
	public void testgetComparacionEstudios3() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MEDIA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionEstudios(otro);
	    Assert.assertEquals("El resultado deberia ser 1.5",resultado, 1.5, 0.1);	
	}

	
	@Test
	public void testgetComparacionExperiencia1() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_NADA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionExperiencia(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
	}
	
	@Test
	public void testgetComparacionExperiencia2() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MEDIA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionExperiencia(otro);
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
	}
	
	@Test
	public void testgetComparacionExperiencia3() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionExperiencia(otro);
	    Assert.assertEquals("El resultado deberia ser 1.5",resultado, 1.5, 0.1);	
	}
	@Test
	public void testgetComparacionJornada1() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionJornada(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
	}
	@Test
	public void testgetComparacionJornada2() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_COMPLETA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionJornada(otro);
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
	}
	
	@Test
	public void testgetComparacionJornada3() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionJornada(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
	}
	
	@Test
	public void testgetComparacionLocacion1() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionLocacion(otro);
	    Assert.assertEquals("El resultado deberia ser -1",resultado, -1, 0.1);	
	}
	@Test
	public void testgetComparacionLocacion2() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.PRESENCIAL,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionLocacion(otro);
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
	}
	@Test
	public void testgetComparacionLocacion3() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionLocacion(otro);
	    Assert.assertEquals("El resultado deberia ser -1",resultado, -1, 0.1);	
	}
	
	@Test
	public void testgetComparacionPuesto1() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionPuesto(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
	}
	
	@Test
	public void testgetComparacionPuesto2() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.SENIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionPuesto(otro);
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
	}
	
	@Test
	public void testgetComparacionPuesto3() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  50000,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.MANAGMENT,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionPuesto(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
	}
	
	@Test
	public void testgetComparacionRemuneracion1() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  500,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionRemuneracion(otro);
	    Assert.assertEquals("El resultado deberia ser 1",1, resultado, 0.1);	
	}
	
	@Test
	public void testgetComparacionRemuneracion2() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  1250,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionRemuneracion(otro);
	    Assert.assertEquals("El resultado deberia ser 1",1, resultado, 0.1);	
	}
	
	@Test
	public void testgetComparacionRemuneracion3() {
	    // Se le crea un ticket al empleado 1
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.INDISTINTO,
			  1750,
			  Constantes.JORNADA_EXTENDIDA,
			  Constantes.JUNIOR,
			  Constantes.EXP_MUCHA,
			  Constantes.TERCIARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionRemuneracion(otro);
	    Assert.assertEquals("El resultado deberia ser -0.5",-0.5, resultado, 0.1);	
	}

	
	@Test
	public void testgetComparacionTotal() {	
		// Usamos mock porque algunas comparaciones no andan correctamente
		// de esta manera nos aseguramos si el resultado del metodo que estamos
		// testeando ahora es el esperado
	   Ticket mockTicket = mock(Ticket.class);
	   when(mockTicket.getComparacionEstudios(mockTicket)).thenReturn(-0.5);
	   when(mockTicket.getComparacionExperiencia(mockTicket)).thenReturn(-0.5);
	   when(mockTicket.getComparacionJornada(mockTicket)).thenReturn(-0.5);
	   when(mockTicket.getComparacionLocacion(mockTicket)).thenReturn(-1.0);
	   when(mockTicket.getComparacionPuesto(mockTicket)).thenReturn(-0.5);
	   when(mockTicket.getComparacionRemuneracion(mockTicket)).thenReturn(1.0);
	   
	    try {
			agencia.crearTicketEmpleado(
			  Constantes.HOME_OFFICE,
			  500,
			  Constantes.JORNADA_MEDIA,
			  Constantes.JUNIOR,
			  Constantes.EXP_NADA,
			  Constantes.PRIMARIOS,
			  empleado1
			);
		} catch (ImposibleModificarTicketsException e) {
			// ya fue testeado, no deberia lanzar excep aqui
			Assert.fail("No deberia entrar aqui");
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionTotal(otro);
	    //double esperado = ticket.getComparacionEstudios(otro)+ticket.getComparacionExperiencia(otro)+ticket.getComparacionJornada(otro)+ticket.getComparacionLocacion(otro)+ticket.getComparacionPuesto(otro)+ticket.getComparacionRemuneracion(otro);
	    Assert.assertEquals("El resultado deberia ser:",-2, resultado, 0.1);	
	}
}
