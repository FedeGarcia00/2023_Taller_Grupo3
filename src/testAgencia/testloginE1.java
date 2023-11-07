package testAgencia;

import org.junit.Assert;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Usuario;
import modeloNegocio.Agencia;
import util.Constantes;

public class testloginE1 {


    Agencia agencia;

    @Before
    public void setUp() throws Exception {
        // Preparación del escenario, hay un empleado en el sistema
    	agencia = Agencia.getInstance();
    	agencia.registroEmpleado("buacho", "123", "Bautista", "Orte", "223543", 23);
    	agencia.registroEmpleador("fede", "456", "Federico", "43234", Constantes.JURIDICA,Constantes.SALUD);	
    }

    @After
    public void tearDown() throws Exception {
    	agencia.getEmpleados().clear();
    }
    
	@Test
	public void test1() { // usuario empleado con datos correctos
		try {
			agencia.login("buacho", "123");
			int tipoUser = agencia.getTipoUsuario();
			Assert.assertEquals(0, tipoUser);
		} catch (ContraException | NombreUsuarioException e) {
			Assert.fail("Error, no deberia tirar excepcion " + e.getMessage());
		}
	}
	
	@Test
	public void test2() { // usuario empleado con password incorrecto
		try {
			agencia.login("buacho", "324");
			int tipoUser = agencia.getTipoUsuario();
			Assert.assertEquals(0, tipoUser);
		} catch (ContraException | NombreUsuarioException e) {
			//deberia tirar la excepcion
			System.out.print(e.getMessage());
		}
	}
	
	@Test
	public void test3() { // usuario empleador con datos correctos
		try {
			agencia.login("fede", "456");
			int tipoUser = agencia.getTipoUsuario();
			Assert.assertEquals(1, tipoUser);
		} catch (ContraException | NombreUsuarioException e) {
			Assert.fail("Error, no deberia tirar excepcion " + e.getMessage());
		}
	}
	
	@Test
	public void test4() { // usuario empleador con password incorrecta
		try {
			agencia.login("fede", "3123");
			int tipoUser = agencia.getTipoUsuario();
			Assert.assertEquals(1, tipoUser);
		} catch (ContraException | NombreUsuarioException e) {
			System.out.print(e.getMessage());
		}
	}
	
	@Test
	public void test5() { // usuario admin con datos correctos
		try {
			agencia.login("admin", "admin");
			int tipoUser = agencia.getTipoUsuario();
			Assert.assertEquals(2, tipoUser);
		} catch (ContraException | NombreUsuarioException e) {
			Assert.fail("Error, no deberia tirar excepcion " + e.getMessage());
		}
	}
	
	@Test
	public void test6() { // usuario inexistente
		try {
			agencia.login("pepito", "asdfg");
			int tipoUser = agencia.getTipoUsuario();
			Assert.assertEquals(2, tipoUser);
		} catch (ContraException | NombreUsuarioException e) {
			// deberia tirar excepcion usuario inexistente
			System.out.print(e.getMessage());
			
		}
	}

}
