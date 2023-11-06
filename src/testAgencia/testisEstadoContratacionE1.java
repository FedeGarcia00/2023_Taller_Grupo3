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


public class testisEstadoContratacionE1 {
	  
	private Agencia agencia;

	@Before
	public void setUp() throws Exception {
		modeloNegocio.Agencia agencia = bin.modeloNegocio.Agencia.getInstance();
		try {
			agencia.registroEmpleado("fede", "123", "Federico", "Garcia", "223456", 23);
			agencia.registroEmpleado("santi", "121", "Santiago", "Carmenes", "2231256", 23);
			try {
				agencia.registroEmpleador("Baucho", "121", "Bautista", "223131256", "Fisica", "Salud");
			} catch (ImposibleCrearEmpleadorException e) {
				e.getMessage();
			}
		} catch (NewRegisterException e) {
			e.getMessage();
		} catch (ImposibleCrearEmpleadoException e) {
			e.getMessage();
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test1() {
		//HashMap<String, modeloDatos.EmpleadoPretenso> empleados =  agencia.getEmpleados();
		agencia.aplicaPromo(true);
	}

	
	@Test
	public void test2() {
		agencia.aplicaPromo(false);
	}
}
