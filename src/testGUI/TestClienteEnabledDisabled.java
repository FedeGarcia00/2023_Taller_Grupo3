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

import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
//import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import controlador.Controlador;
import util.Constantes;
import vista.Ventana;
import vista.IVista;
import vista.PanelAdmin;
import vista.PanelCliente;

public class TestClienteEnabledDisabled {
	
	Controlador controlador;
	Robot robot; 
	Ventana ventana;
	//Robot robot;
	
	
	
	public TestClienteEnabledDisabled() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		  controlador = new Controlador();
		  ventana = (Ventana) controlador.getVista(); 
		  
		  
		//fuerzo ir a la ventana de cliente porque el setContentPane me da problemas
//		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
//		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
//		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);
//	       
//		  TestUtils.clickComponent(username, robot);
//	      TestUtils.tipeaTexto("admin", robot);
//	      
//	      TestUtils.clickComponent(password, robot);
//	      TestUtils.tipeaTexto("admin", robot);
//	
//	      TestUtils.clickComponent(login, robot);
		
		 
		  //Obtengo una vista del empleado
		  Cliente empleador = new EmpleadoPretenso("Lio", "123", "Lionel", "549223", "Messi", 35);
		  PanelCliente panelCliente = new PanelCliente(empleador, controlador, 0, empleador);
		  ventana.setContentPane(panelCliente);	
		  ventana = (Ventana) controlador.getVista();
	}

	@After
	public void tearDown() throws Exception {
		 ventana = (Ventana) controlador.getVista(); 
		 ventana.setVisible(false);
	}

	@Test
	public void testClickCrearTicket() {
		//al clickear crear ticket deberia quedar deshabilitado	
		robot.delay(TestUtils.getDelay());		
		
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
		TestUtils.clickComponent(nuevoTicket, robot);
		Assert.assertFalse("El boton deberia quedar deshabilitado:", nuevoTicket.isEnabled());	
	}
	
	@Test
	public void testClickCrearTicket2() {
		robot.delay(TestUtils.getDelay());
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
		//los componentes antes de hacer click deberian estar deshabilitados
		JRadioButton jornadaMedia = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JORNADA_MEDIA);	
		JRadioButton jornadaCompleta = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JORNADA_COMPLETA);
		JRadioButton jornadaExtendida = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JORNADA_EXTENDIDA);
		JRadioButton expNada = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EXP_NADA);
		JRadioButton expMedia = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EXP_MEDIA);
		JRadioButton expMucha = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EXP_MUCHA);
		JRadioButton terciarios = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.TERCIARIOS);
		JRadioButton secundarios = (JRadioButton) TestUtils.getComponentForName(ventana,Constantes.SECUNDARIOS);
		JRadioButton primarios = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PRIMARIOS);
		JRadioButton junior = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JUNIOR);
		JRadioButton senior = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.SENIOR);
		JRadioButton managment = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.MANAGMENT);
		JRadioButton presencial = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PRESENCIAL);
		JRadioButton homeOffice = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.HOME_OFFICE);
		JRadioButton indistinto = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.INDISTINTO);
		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
		
		
		TestUtils.clickComponent(nuevoTicket, robot);
		//verifico que los componentes queden habilitados excepto el boton de nuevoticket
		Assert.assertTrue("El componente deberia estar habilitado",jornadaMedia.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",jornadaCompleta.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",jornadaExtendida.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",expNada.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",expMedia.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",expMucha.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",terciarios.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",secundarios.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",primarios.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",junior.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",senior.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",managment.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",presencial.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",homeOffice.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",indistinto.isEnabled());
		Assert.assertTrue("El componente deberia estar habilitado",remuneracion.isEnabled());		
	}
	
	@Test
	public void testCrearTicket1() {
		//test con entero negativo
		//deberia estar deshabilitado boton confirmarNuevoTicket
		robot.delay(TestUtils.getDelay());
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
		TestUtils.clickComponent(nuevoTicket, robot);
		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
		TestUtils.clickComponent(remuneracion, robot);
		TestUtils.tipeaTexto("-50", robot);
		JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
		//robot.delay(TestUtils.getDelay());
		Assert.assertFalse("El boton deberia seguir deshabilitado:", confirmarNuevoTicket.isEnabled());
	}
	
	@Test
	public void testCrearTicket2() {
		//test con 0
		//deberia estar deshabilitado boton confirmarNuevoTicket
		robot.delay(TestUtils.getDelay());
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
		TestUtils.clickComponent(nuevoTicket, robot);
		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
		TestUtils.clickComponent(remuneracion, robot);
		TestUtils.tipeaTexto("0", robot);
		JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
		//robot.delay(TestUtils.getDelay());
		Assert.assertFalse("El boton deberia seguir deshabilitado:", confirmarNuevoTicket.isEnabled());
	
		
	}
	@Test
	public void testCrearTicket3() {
		//test con entero positivo
		//deberia habilitarse boton confirmarNuevoTicket
		robot.delay(TestUtils.getDelay());
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
		TestUtils.clickComponent(nuevoTicket, robot);
		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
		TestUtils.clickComponent(remuneracion, robot);
		TestUtils.tipeaTexto("200", robot);
		JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
		//robot.delay(TestUtils.getDelay());
		//System.out.println(confirmarNuevoTicket.isEnabled());
		Assert.assertTrue("El boton deberia quedar habilitado:", confirmarNuevoTicket.isEnabled());
	}
	
}