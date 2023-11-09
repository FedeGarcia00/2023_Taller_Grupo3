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

public class testRegistroPanel {
	
	Controlador controlador;
	Robot robot; 
	 Ventana ventana;
	//Robot robot;
	
	// constructor para alltest
	
	public testRegistroPanel() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		  robot.delay(TestUtils.getDelay());
		  controlador = new Controlador();
		  ventana = (Ventana) controlador.getVista();
		  JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		  TestUtils.clickComponent(registrar, robot);
	}

	@After
	public void tearDown() throws Exception {
		 ventana.setVisible(false);
	}

	@Test
	public void testRegEmpleadorSoloUser() {
		 JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);
		
		 TestUtils.clickComponent(empleador, robot);
		 
			JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
			JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
			TestUtils.clickComponent(user, robot);
			TestUtils.tipeaTexto("manu", robot);
			
			Assert.assertFalse("Boton de registrar deberia estar deshabilitado.", registrarAccion.isEnabled());	 
	}
	
	
	@Test
	public void testRegEmpleadorSoloRealName() {
		
		Ventana ventana = (Ventana) controlador.getVista();
		
		robot.delay(TestUtils.getDelay());
		 
		
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);
		TestUtils.clickComponent(empleador, robot);
		 
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Emanuel", robot);
			
		Assert.assertFalse("Boton de registrar deberia estar deshabilitado.", registrarAccion.isEnabled());	 
	}
	
	public void testRegEmpleadorSoloPhone() {
		Ventana ventana = (Ventana) controlador.getVista();
		robot.delay(TestUtils.getDelay());	
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);
		TestUtils.clickComponent(empleador, robot);	 
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("manu", robot);	
		Assert.assertFalse("Boton de registrar deberia estar deshabilitado.", registrarAccion.isEnabled());
	}
	
	// falta testear solo pass, solo confirmacionpass, combinaciones y permutaciones pero hacemos recorte
	
	@Test
	public void testRegEmpleadorTodoLleno() {
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
		TestUtils.clickComponent(user, robot);
		TestUtils.tipeaTexto("manu", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Emanuel", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(empleador, robot);
		Assert.assertTrue("Boton de registrar deberia estar habilitado.", registrarAccion.isEnabled());
		 
	}
	
	@Test
	public void testRegEmpleadorTodoLlenoMenosPass() {
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
		TestUtils.clickComponent(user, robot);
		TestUtils.tipeaTexto("manu", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Emanuel", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(empleador, robot);
		Assert.assertTrue("Boton de registrar deberia estar habilitado.", registrarAccion.isEnabled());
		 
	}
	
	@Test
	public void testRegEmpleadorTodoLlenoMenosUser() {
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Emanuel", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(empleador, robot);
		Assert.assertTrue("Boton de registrar deberia estar habilitado.", registrarAccion.isEnabled());
		 
	}
	
	@Test
	public void testRegEmpleadorTodoLlenoMenosNombre() {
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
		TestUtils.clickComponent(user, robot);
		TestUtils.tipeaTexto("manu", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(empleador, robot);
		Assert.assertTrue("Boton de registrar deberia estar habilitado.", registrarAccion.isEnabled()); 
	}
	
	@Test
	public void testRegEmpleadorTodoLlenoMenosPhone() {
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
		TestUtils.clickComponent(user, robot);
		TestUtils.tipeaTexto("manu", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Emanuel", robot);
		TestUtils.clickComponent(empleador, robot);
		Assert.assertTrue("Boton de registrar deberia estar habilitado.", registrarAccion.isEnabled()); 
	}
	
	@Test
	public void testRegEmpleadoTodoLlenoEdadPositiva() {
		 	JRadioButton empleado = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADO);	
		 	TestUtils.clickComponent(empleado, robot); 
			JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
			JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
			JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
			JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
			JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
			JTextField apellido = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_APELLIDO);
			JTextField edad = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_EDAD);
			JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
			TestUtils.clickComponent(user, robot);
			TestUtils.tipeaTexto("manu", robot);
			TestUtils.clickComponent(password, robot);
			TestUtils.tipeaTexto("123", robot);
			TestUtils.clickComponent(passwordConf, robot);
			TestUtils.tipeaTexto("123", robot);
			TestUtils.clickComponent(realname, robot);
			TestUtils.tipeaTexto("Emanuel", robot);
			TestUtils.clickComponent(phone, robot);
			TestUtils.tipeaTexto("5422354", robot);
			TestUtils.clickComponent(apellido, robot);
			TestUtils.tipeaTexto("Ginobili", robot);
			TestUtils.clickComponent(edad, robot);
			TestUtils.tipeaTexto("20", robot);
			Assert.assertTrue("Boton de registrar deberia estar habilitado.", registrarAccion.isEnabled());
	}
	
	@Test
	public void testRegEmpleadoTodoLlenoEdadNegativa() {
		 	JRadioButton empleado = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADO);	
		 	TestUtils.clickComponent(empleado, robot); 
			JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
			JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
			JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
			JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
			JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
			JTextField apellido = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_APELLIDO);
			JTextField edad = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_EDAD);
			JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
			TestUtils.clickComponent(user, robot);
			TestUtils.tipeaTexto("manu", robot);
			TestUtils.clickComponent(password, robot);
			TestUtils.tipeaTexto("123", robot);
			TestUtils.clickComponent(passwordConf, robot);
			TestUtils.tipeaTexto("123", robot);
			TestUtils.clickComponent(realname, robot);
			TestUtils.tipeaTexto("Emanuel", robot);
			TestUtils.clickComponent(phone, robot);
			TestUtils.tipeaTexto("5422354", robot);
			TestUtils.clickComponent(apellido, robot);
			TestUtils.tipeaTexto("Ginobili", robot);
			TestUtils.clickComponent(edad, robot);
			TestUtils.tipeaTexto("-20", robot);
			Assert.assertFalse("Boton de registrar deberia estar deshabilitado porque la edad es negativa.", registrarAccion.isEnabled());
	}
	
	@Test
	public void testRegEmpleadoTodoLlenoEdad0() {
		//probamos valor limite
		 	JRadioButton empleado = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADO);	
		 	TestUtils.clickComponent(empleado, robot); 
			JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
			JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
			JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
			JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
			JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
			JTextField apellido = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_APELLIDO);
			JTextField edad = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_EDAD);
			JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);		
			TestUtils.clickComponent(user, robot);
			TestUtils.tipeaTexto("manu", robot);
			TestUtils.clickComponent(password, robot);
			TestUtils.tipeaTexto("123", robot);
			TestUtils.clickComponent(passwordConf, robot);
			TestUtils.tipeaTexto("123", robot);
			TestUtils.clickComponent(realname, robot);
			TestUtils.tipeaTexto("Manuel", robot);
			TestUtils.clickComponent(phone, robot);
			TestUtils.tipeaTexto("5422354", robot);
			TestUtils.clickComponent(apellido, robot);
			TestUtils.tipeaTexto("Ginobili", robot);
			TestUtils.clickComponent(edad, robot);
			TestUtils.tipeaTexto("0", robot);
			Assert.assertFalse("Boton de registrar deberia estar deshabilitado porque la edad es 0.", registrarAccion.isEnabled()); 
	}
	
	//faltarian probar permutaciones de algunos campos vacios en el caso de empleador
	
	

}
