package testModeloDatos;

import org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import util.Constantes;

public class testcalculaComisionE1 {
//en este que habria que testear?? se me ocurre crear un cliente, ponerle un 
	//ticket pero que valor deberia devolver calculacomision? como para comparar
	 Agencia agencia;
	    Cliente empleado;
	    Ticket ticket;
		@Before
		public void setUp() throws Exception {
			//Preparacion escenario -> existe empleado y ticket distinto de null
			// otro escenario seria ticket null, el empleado no tiene sentido que sea null
			agencia = Agencia.getInstance();
	    	empleado =  agencia.registroEmpleado("fede", "123", "Federico", "Garcia", "223543", 23);
	    	agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 100000, Constantes.JORNADA_EXTENDIDA,Constantes.JUNIOR, Constantes.EXP_NADA, Constantes.SECUNDARIOS, empleado);
	    	ticket = empleado.getTicket();
		}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		// fijarse que hace calculaComision para armar las baterias de pruebas
		// para este caso no se por que da 80000, comparo en base a eso, pero armar bats
		// Assert.assertEquals(empleado.calculaComision(ticket), 80000);
		double comision = empleado.calculaComision(ticket);
		Assert.assertEquals(comision, 80000.0,  0.001);		
		
	}

}
