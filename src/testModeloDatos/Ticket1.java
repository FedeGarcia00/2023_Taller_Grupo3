package testModeloDatos;



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

public class Ticket1 {
	  Agencia agencia;
	  Cliente empleado1;
	  Cliente empleador1;

	@Before
	public void setUp() throws Exception {
		agencia = Agencia.getInstance();
	    agencia.setEstadoContratacion(false);
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
	    	      Constantes.HOME_OFFICE,
	    	      50000, // ver remuneracion
	    	      Constantes.JORNADA_MEDIA,
	    	      Constantes.JUNIOR,
	    	      Constantes.EXP_NADA,
	    	      Constantes.PRIMARIOS,
	    	      empleador1
	    	    );
	    //agencia.login("pepe", "765");
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
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);
		
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
	    Assert.assertEquals("El resultado deberia ser 1.5",resultado, 1.5, 0.1);
		
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
	    Assert.assertEquals("El resultado deberia ser 2",resultado, 2, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser 1.5",resultado, 1.5, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser 2",resultado, 2, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser -1",resultado, -1, 0.1);	
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
			Assert.fail("No deberia entrar aqui");;
		}
	    Ticket ticket = empleador1.getTicket();
	    Ticket otro = empleado1.getTicket();
	    double resultado = ticket.getComparacionLocacion(otro);
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser -1",resultado, -1, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser 1",resultado, 1, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser -0.5",resultado, -0.5, 0.1);	
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
	    Assert.assertEquals("El resultado deberia ser -1",resultado, -1, 0.1);	
	}

}
