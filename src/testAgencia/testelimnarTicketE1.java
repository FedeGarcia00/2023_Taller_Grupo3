package testAgencia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContraException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.NombreUsuarioException;
import modeloDatos.Cliente;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import util.Constantes;

public class testelimnarTicketE1 {
    Agencia agencia;
    Cliente empleado;
    Ticket ticketInicial;
	@Before
	public void setUp() throws Exception {
		//Preparacion escenario -> el estado de contratacion esta en un estado valido
		// como pongo la contratacion en un estado invalido
		agencia = Agencia.getInstance();
    	empleado =  agencia.registroEmpleado("fede", "123", "Federico", "Garcia", "223543", 23);
    	agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 10000, Constantes.JORNADA_EXTENDIDA,Constantes.JUNIOR, Constantes.EXP_NADA, Constantes.SECUNDARIOS, empleado);
    	ticketInicial = empleado.getTicket();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() throws ImposibleModificarTicketsException {
		try {
			agencia.login("fede", "123");
		} catch (ContraException | NombreUsuarioException e) {
		}
		agencia.eliminarTicket();
		Assert.assertEquals(null, empleado.getTicket());		
	}

}
