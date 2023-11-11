package testGUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controlador.Controlador;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.PanelRegistro;
import vista.Ventana;

public class testRegisterConDatos {
	
	Controlador controlador;
	Robot robot; 
	FalsoOptionPane op = new FalsoOptionPane();
	Agencia agencia;
	 Ventana ventana;
	
	//Robot robot;
	
	// constructor para alltest
	
	public testRegisterConDatos() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		
//		 robot.delay(TestUtils.getDelay());
//		  controlador = new Controlador();
//		  ventana = (Ventana) controlador.getVista();
//		  PanelRegistro panelRegistro = new PanelRegistro(controlador);
//		  ventana.setContentPane(panelRegistro);
		
		
		  robot.delay(TestUtils.getDelay());
		  
		  controlador = new Controlador();
		  
		  ventana = (Ventana) controlador.getVista();
		  
//		  esto no me anda, seria la forma correcta de hacerlo, lo fuerzo con boton de registro
//		  ventana = (Ventana) controlador.getVista();
//		  PanelRegistro panelRegistro = new PanelRegistro(controlador);
//		  ventana.setContentPane(panelRegistro);
		  
		  //fuerzo ir a la ventana de registro
		  JButton registrar = (JButton) TestUtils.getComponentForName(ventana, Constantes.REGISTRAR);
		  TestUtils.clickComponent(registrar, robot);
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

	//registro empleado contrasenia no coinciden
	//test registro empleado valido
	//test registro empleado existente
	//test registro empleador valido
	//test registro empleador existente
	// test registro empleado con algun parametro null
	//test boton cancelar vuelve al panel login
	
	@Test
	public void testRegContrasNoCoincidenEmpleador() {
		//probar lo mismo con empleado
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
		TestUtils.tipeaTexto("45678", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Emanuel", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(empleador, robot);
		TestUtils.clickComponent(registrarAccion, robot);
		
//	        System.out.println(Mensajes.PASS_ERRONEO.getValor());
//	        System.out.println(op.getMensaje());
	        Assert.assertEquals("Deberia decir mensaje de pass erroneo:",Mensajes.PASS_ERRONEO.getValor(), op.getMensaje());

	}      
	
	
	@Test
	public void testRegEmpladoValido() {
		int empleadosRegistrados = agencia.getEmpleados().size();
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
		TestUtils.tipeaTexto("pepitooo", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Pedro", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(apellido, robot);
		TestUtils.tipeaTexto("Messi", robot);
		TestUtils.clickComponent(edad, robot);
		TestUtils.tipeaTexto("20", robot);
		TestUtils.clickComponent(registrarAccion, robot);
		Assert.assertEquals("Tiene que haber un empleado mas en el sistema",empleadosRegistrados+1 ,agencia.getEmpleados().size());
		Assert.assertEquals("Tiene quedar logueado con el tipo de usuario correcto",0 ,agencia.getTipoUsuario());
	} 
	
	@Test
	public void testRegEmpladorValido() {
		int empleadoresRegistrados = agencia.getEmpleadores().size();
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		TestUtils.clickComponent(user, robot);
		TestUtils.tipeaTexto("Manu", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Manuel", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("5422354", robot);
		TestUtils.clickComponent(registrarAccion, robot);
		Assert.assertEquals("Tiene que haber un empleado mas en el sistema",empleadoresRegistrados+1 ,agencia.getEmpleadores().size());
		Assert.assertEquals("Tiene quedar logueado con el tipo de usuario correcto",1 ,agencia.getTipoUsuario());
	}      
	
	@Test
	public void testRegEmpladoExistente() {
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
		TestUtils.tipeaTexto("fede", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("123", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Federico", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("223543", robot);
		TestUtils.clickComponent(apellido, robot);
		TestUtils.tipeaTexto("Garcia", robot);
		TestUtils.clickComponent(edad, robot);
		TestUtils.tipeaTexto("23", robot);
		TestUtils.clickComponent(registrarAccion, robot);
		Assert.assertEquals("Deberia decir mensaje de pass erroneo:",Mensajes.USUARIO_REPETIDO.getValor(), op.getMensaje());	
	} 
	
	@Test
	public void testRegEmpladorExistente() {
		JRadioButton empleador = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EMPLEADOR);	
		TestUtils.clickComponent(empleador, robot); 
		JTextField user = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_USSER_NAME);
		JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_PASSWORD);
		JTextField passwordConf = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_CONFIRM_PASSWORD);
		JTextField realname = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_REAL_NAME);
		JTextField phone = (JTextField) TestUtils.getComponentForName(ventana, Constantes.REG_TELEFONO);
		JButton registrarAccion = (JButton) TestUtils.getComponentForName(ventana, Constantes.REG_BUTTON_REGISTRAR);
		TestUtils.clickComponent(user, robot);
		TestUtils.tipeaTexto("santi", robot);
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("456", robot);
		TestUtils.clickComponent(passwordConf, robot);
		TestUtils.tipeaTexto("456", robot);
		TestUtils.clickComponent(realname, robot);
		TestUtils.tipeaTexto("Santiago", robot);
		TestUtils.clickComponent(phone, robot);
		TestUtils.tipeaTexto("43234", robot);
		TestUtils.clickComponent(registrarAccion, robot);
		Assert.assertEquals("Deberia decir mensaje de pass erroneo:",Mensajes.USUARIO_REPETIDO.getValor(), op.getMensaje());	
	}     
	
	
	
	}   
	

