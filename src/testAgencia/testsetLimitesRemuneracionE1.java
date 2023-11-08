package testAgencia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import modeloDatos.Cliente;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;

public class testsetLimitesRemuneracionE1 {
	 Agencia agencia;
	    Cliente empleado;
	    Ticket ticketInicial;
	    
	@Before
	public void setUp() throws Exception {
		agencia = Agencia.getInstance();
		agencia.setLimitesRemuneracion(500, 1500);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {// valores correctos
		try {
			agencia.setLimitesRemuneracion(700, 1700);
			Assert.assertEquals(agencia.getLimiteInferior(),700);
			Assert.assertEquals(agencia.getLimiteSuperior(),1700);
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			// no deberia entrar aqui
			Assert.fail(e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			// no deberia entrar aqui
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void test2() {// valores superior incorrecto
		try {
			agencia.setLimitesRemuneracion(1700, 700);
			Assert.fail("Deberia tirar excepcion 'LimiteSuperiorRemuneracionInvalidaException'");
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			//deberia entrar aqui
			System.out.println(e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			// no deberia entrar aqui
			Assert.fail("No debe tirar esta excepcion:" + e.getMessage());
		}
	}
	
	@Test
	public void test3() {// valores inferior incorrecto
		try {
			agencia.setLimitesRemuneracion(-1700, 700);
			Assert.fail("Deberia tirar excepcion 'LimiteInferiorRemuneracionInvalidaException'");
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			// no deberia entrar aqui
			Assert.fail("No debe tirar esta excepcion:" + e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			//deberia entrar aqui
			System.out.println(e.getMessage());
		}
	}

}
