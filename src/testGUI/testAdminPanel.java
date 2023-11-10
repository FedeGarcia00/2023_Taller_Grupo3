package testGUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import vista.Ventana;

public class testAdminPanel {

	Controlador controlador;
	Robot robot; 
	
	public testAdminPanel() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		  controlador = new Controlador();
		  
	}

	@After
	public void tearDown() throws Exception {
		 Ventana ventanaActual = (Ventana) controlador.getVista();
		 ventanaActual.setVisible(false);
	}

	@Test
	public void testModificarLimitesCompletoCorrecto() {
		// aca el boton deberia habilitarse
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificarLimitesSoloInferior() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificarLimitesSoloSuperior() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificarLimitesInferiorNegativo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testModificarLimitesSuperiorMenorInferior() {
		fail("Not yet implemented");
	}
}
