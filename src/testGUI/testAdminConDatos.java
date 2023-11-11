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
import vista.PanelAdmin;
import vista.PanelRegistro;
import vista.Ventana;

public class testAdminConDatos {
	
	Controlador controlador;
	Robot robot; 
	FalsoOptionPane op = new FalsoOptionPane();
	Agencia agencia;
	Ventana ventana;
	
	public testAdminConDatos() {
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
		  
//		  esto no me anda, seria la forma correcta de hacerlo, lo fuerzo iniciando sesion
//		  ventana = (Ventana) controlador.getVista();
//		  PanelAdmin panelAdmin = new PanelAdmin(controlador);
//		  ventana.setContentPane(panelAdmin);
		  
		  //fuerzo ir a la ventana de admin
		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
	       
		  TestUtils.clickComponent(username, robot);
	      TestUtils.tipeaTexto("admin", robot);
	      
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("admin", robot);
	
	      TestUtils.clickComponent(login, robot);
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

	
//	Al pulsar el botón GATILLAR se invoca al método gatillar de la clase Controlador.
//	Al pulsar el botón APLICAR_PROMO se invoca al método aplicarPromo de la clase Controlador.
//	Al pulsar el botón CERRARSESION se invoca al método cerrarSesion de la clase Controlador y se regresa al panel de login
	
	
	//gatillar 1, con estado contratacion false
	//gatillar 2, con estado contratacion true, 
	//aplicar promo no se como
	//cerrar sesion
	
	
	@Test
	public void testGatillar1() {
	// el estado de contratacion inicialmente esta en false
		agencia.setEstadoContratacion(false);
		JButton gatillar = (JButton) TestUtils.getComponentForName(ventana, Constantes.GATILLAR);
		TestUtils.clickComponent(gatillar, robot);
	    Assert.assertEquals("Deberia decir mensaje de agencia en contratacion:", Mensajes.AGENCIA_EN_CONTRATACION.getValor(), op.getMensaje()); 
	}      
	
	@Test
	public void testGatillar2() {
		// el estado de contratacion inicialmente esta en true
		agencia.setEstadoContratacion(true);
		JButton gatillar = (JButton) TestUtils.getComponentForName(ventana, Constantes.GATILLAR);
		TestUtils.clickComponent(gatillar, robot);
	    Assert.assertEquals("Deberia decir mensaje de agencia en contratacion:", Mensajes.AGENCIA_EN_BUSQUEDA.getValor(), op.getMensaje()); 
	}   
	
	@Test
	public void testCerrarSesion() {
	
	}   
	
	@Test
	public void aplicaPromo() {
	
	}   
	
	
	}   
	

