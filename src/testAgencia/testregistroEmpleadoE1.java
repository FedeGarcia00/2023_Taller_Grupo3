/// no anda
package testAgencia;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.NewRegisterException;
import bin.modeloDatos.EmpleadoPretenso;
import bin.modeloNegocio.Agencia;


public class testregistroEmpleadoE1 {
	  
	private Agencia agencia;

	@Before
	public void setUp() throws Exception {
		modeloNegocio.Agencia agencia = bin.modeloNegocio.Agencia.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1() {
	
	}

	
	@Test
	public void test2() {
		agencia.aplicaPromo(false);
	}
}
