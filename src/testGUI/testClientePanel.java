//COMO ENTRO AL PANEL CLIENTE SIN INICIAR SESION
package testGUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controlador.Controlador;
import util.Constantes;
import vista.Ventana;
import vista.IVista;
import vista.PanelAdmin;

public class testClientePanel {
	
	Controlador controlador;
	Robot robot; 
	Ventana ventana;
	//Robot robot;
	
	// constructor para alltest
	
	public testClientePanel() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		  robot.delay(6000);
		  controlador = new Controlador();
		  ventana = (Ventana) controlador.getVista();
		  //JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		  //TestUtils.clickComponent(registrar, robot);

		  //VER COMO OBTENER LA VISTA DEL CLIENTE SIN INICIAR SESION
	}

	@After
	public void tearDown() throws Exception {
		 ventana.setVisible(false);
	}

	@Test
	public void test() {
		
		 
	}
	
	
	}