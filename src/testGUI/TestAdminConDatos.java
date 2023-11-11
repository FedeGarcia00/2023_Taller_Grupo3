package testGUI;


import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import controlador.Controlador;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class TestAdminConDatos {
	
	Controlador controlador;
	Robot robot; 
	FalsoOptionPane op = new FalsoOptionPane();
	Agencia agencia;
	Ventana ventana;
	EmpleadoPretenso empleado;
	
	public TestAdminConDatos() {
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
		  empleado = (EmpleadoPretenso) agencia.registroEmpleado("fede","123","Federico","Garcia","223543",23);

	}

	@After
	public void tearDown() throws Exception {
		 Ventana ventana = (Ventana) controlador.getVista();
		 ventana.setVisible(false);
	}

	
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
		JButton cerrarSesion = (JButton) TestUtils.getComponentForName(ventana, Constantes.CERRARSESION);
		TestUtils.clickComponent(cerrarSesion, robot);
		Assert.assertEquals("El tipo de user deberia quedar en -1", -1, agencia.getTipoUsuario()); 
	}   
	
	@Test
	public void aplicaPromo1() {
		//sin lista postulantes
		//hay un solo empleado, deberia retornar en una ventana el .toString() de este	   
		JButton aplicaPromo = (JButton) TestUtils.getComponentForName(ventana, Constantes.APLICAR_PROMO);
		TestUtils.clickComponent(aplicaPromo, robot);
		Assert.assertEquals("Deberia mostar un pane con el cliente", empleado.toString(), op.getMensaje()); 
	}   
	@Test
	public void aplicaPromo2() {
		//con lista postulantes
		//hay un solo empleado, deberia retornar en una ventana el .toString() de este	   
		JButton aplicaPromo = (JButton) TestUtils.getComponentForName(ventana, Constantes.APLICAR_PROMO);
		JCheckBox listaPostulantes = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_BOX_LISTA_POSTULANTES);
		TestUtils.clickComponent(listaPostulantes, robot);
		TestUtils.clickComponent(aplicaPromo, robot);
		Assert.assertEquals("Deberia mostar un pane con el cliente", empleado.toString(), op.getMensaje()); 
	}   

	}   
	

