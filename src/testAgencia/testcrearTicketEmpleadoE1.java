package testAgencia;

import org.junit.Assert;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Ticket;
import modeloDatos.Usuario;
import modeloNegocio.Agencia;
import util.Constantes;

public class testcrearTicketEmpleadoE1 {


    Agencia agencia;
    Cliente empleado;
    Ticket ticketInicial;

    @Before
    public void setUp() throws Exception {
        // Preparación del escenario, empleado tiene ticket activo
    	agencia = Agencia.getInstance();
    	empleado =  agencia.registroEmpleado("buacho", "123", "Bautista", "Orte", "223543", 23);
    	agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 10000, Constantes.JORNADA_EXTENDIDA,Constantes.JUNIOR, Constantes.EXP_NADA, Constantes.SECUNDARIOS, empleado);
    	ticketInicial = empleado.getTicket();
    }

    @After
    public void tearDown() throws Exception {
    	agencia.getEmpleados().clear();
    }
    
	@Test
	public void test1() {
		// se agrega otro ticket al empleado
		try {
			 // error aca!!!! menda null como cliente
			 System.out.println(empleado.getTicket().toString());
			 System.out.println(empleado.toString());
			 agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 70000, Constantes.JORNADA_COMPLETA,Constantes.JUNIOR, Constantes.EXP_NADA, Constantes.SECUNDARIOS, empleado);
			 Ticket ticketNuevo = empleado.getTicket();
			 // El ticket nuevo deberia sobrescribir al inicial, es decir, deberian ser diferentes
			 Assert.assertNotEquals(ticketNuevo, ticketInicial);
		} catch (ImposibleModificarTicketsException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		// se agrega otro ticket al empleado
		try {
			 // error aca!!!! menda null como cliente
			 System.out.println(empleado.getTicket().toString());
			 System.out.println(empleado.toString());
			 agencia.crearTicketEmpleado("", 70000, Constantes.JORNADA_COMPLETA,Constantes.JUNIOR, Constantes.EXP_NADA, Constantes.SECUNDARIOS, empleado);
			 Ticket ticketNuevo = empleado.getTicket();
			 // El ticket nuevo deberia sobrescribir al inicial, es decir, deberian ser diferentes
			 Assert.assertNotEquals(ticketNuevo, ticketInicial);
		} catch (ImposibleModificarTicketsException e) {
			e.printStackTrace();
		}
	}

	//PASA EL MISMO ERROR PARA TODOS LOS METODOS
}
