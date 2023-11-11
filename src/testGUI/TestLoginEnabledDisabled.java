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

public class TestLoginEnabledDisabled {
	
	Controlador controlador;
	Robot robot; 
	
	//Robot robot;
	
	// constructor para alltest
	
	public TestLoginEnabledDisabled() {
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
	public void testLogSoloUser() {
		 //referencia a ventana
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  	//referencias a los componentes
		  	JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
			JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
	        //lleno campos
	        TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("messi", robot);
	        
	        Assert.assertFalse("Boton de login deberia estar deshabilitado", login.isEnabled()); 
	}
	
	@Test
	public void testLogSoloPass() {
		 //referencia a ventana
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
 	
			JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
			JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("messi", robot);
	        
	        Assert.assertFalse("Boton de login deberia estar deshabilitado", login.isEnabled()); 
	}
	
	@Test
	public void testLogTodoLLeno() {
		 //referencia a ventana
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  
		    JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
			JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
			JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
	        TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("messi", robot);
	        
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("goat", robot);
	        
	        Assert.assertTrue("Boton de login deberia estar habilitado", login.isEnabled()); 
	}
	
	@Test
	public void testLogDatosCorrectos() {
		 // al apretar el boton login no deberia mostrar ningun modal
		 //referencia a ventana
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  
		    JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
			JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
			JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
		
	        TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("messi", robot);
	        
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("goat", robot);
	        
	        Assert.assertTrue("Boton de login deberia estar habilitado", login.isEnabled()); 
	        //test de que pasa al clickear login?
	}
	
	public void testBotonRegistro() {
		//el boton registro tiene que estar siempre habilitado y debe pasar al panel registro
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  JButton registro = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		  Assert.assertTrue("Boton de registrar deberia estar siempre habilitado", registro.isEnabled()); 
	}
	
	//test de que pasa al clickear registro?
	

}
