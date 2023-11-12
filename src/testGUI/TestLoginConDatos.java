package testGUI;


import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JTextField;

import controlador.Controlador;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class TestLoginConDatos {
	
	Controlador controlador;
	Robot robot; 
	FalsoOptionPane op = new FalsoOptionPane();
	Agencia agencia;

	public TestLoginConDatos() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		  controlador = new Controlador();
		  controlador.setMyOptionPane(op);
		  agencia = Agencia.getInstance();
		  HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		  HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>();
		  agencia.setEmpleados(empleados);
		  agencia.setEmpleadores(empleadores);
		  agencia.registroEmpleador("santi","456","Santiago","43234",Constantes.JURIDICA,Constantes.SALUD);
		  agencia.registroEmpleado("fede","123","Federico","Garcia","223543",23);

	}

	@After
	public void tearDown() throws Exception {
		 Ventana ventana = (Ventana) controlador.getVista();
		 ventana.setVisible(false);
	}

	@Test
	public void testLogContraInvalida() {
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
	       
		  TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("santi", robot);
	      
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("mal", robot);
	
	        TestUtils.clickComponent(login, robot);
//	        System.out.println(Mensajes.PASS_ERRONEO.getValor());
	      //  System.out.println(op.getMensaje());
	        Assert.assertEquals("Deberia decir mensaje de pass erroneo:",Mensajes.PASS_ERRONEO.getValor(), op.getMensaje());

	}      
	
	@Test
	public void testLogUserInvalido() {
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
	       
		  TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("messi", robot);
	      
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("mal", robot);
	
	        TestUtils.clickComponent(login, robot);
//	        System.out.println(Mensajes.PASS_ERRONEO.getValor());
	       // System.out.println(op.getMensaje());
	        Assert.assertEquals("Deberia decir mensaje de user invalido:",Mensajes.USUARIO_DESCONOCIDO.getValor(), op.getMensaje());

	}   
	
	@Test
	public void testLogCorrecto() {
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
	       
		  TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("fede", robot);
	      
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("123", robot);
	
	        TestUtils.clickComponent(login, robot);
//	        System.out.println(Mensajes.PASS_ERRONEO.getValor());
//	        System.out.println(op.getMensaje());
	        Assert.assertNotEquals("El estado de tipo usuario deberia ser distitno de -1",-1, agencia.getTipoUsuario());
	        Assert.assertEquals("Coincidir el tipo de usuario logeado al de empleado",0, agencia.getTipoUsuario());

	}   
	
	@Test
	public void testLogIncorrectoAdmin() {
		  Ventana ventana = (Ventana) controlador.getVista();
		  robot.delay(TestUtils.getDelay());
		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
	       
		  TestUtils.clickComponent(username, robot);
	        TestUtils.tipeaTexto("admin", robot);
	      
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("123", robot);
	
	        TestUtils.clickComponent(login, robot);

	        Assert.assertEquals("Deberia decir mensaje de pass erroneo:",Mensajes.PASS_ERRONEO.getValor(), op.getMensaje());

	}  

}
