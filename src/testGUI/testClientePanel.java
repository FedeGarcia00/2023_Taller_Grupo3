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

public class testClientePanel {
	
	Controlador controlador;
	Robot robot; 
	Ventana ventana;
	//Robot robot;
	
	
	
	public testClientePanel() {
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
		  //Obtengo una vista del empledor
		  Cliente empleador = new EmpleadoPretenso("Lio", "123", "Lionel", "549223", "Messi", 35);
		  PanelCliente panelCliente = new PanelCliente(empleador, controlador, 0, empleador);
		  ventana.setContentPane(panelCliente);
		
	}

	@After
	public void tearDown() throws Exception {
		 ventana.setVisible(false);
	}

	
	@Test
	public void testClickNuevoTicket() {
		robot.delay(600);
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
		System.out.println(nuevoTicket.getText() + nuevoTicket.isEnabled());
		TestUtils.clickComponent(nuevoTicket, robot);
		System.out.println(nuevoTicket.getText() + nuevoTicket.isEnabled());
		
	}
	
	
//	@Test
//	public void testClickNuevoTicket() {
//		
//		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);
//		
//		//los componentes antes de hacer click deberian estar deshabilitados
//		JRadioButton jornadaMedia = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JORNADA_MEDIA);	
//		JRadioButton jornadaCompleta = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JORNADA_COMPLETA);
//		JRadioButton jornadaExtendida = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JORNADA_EXTENDIDA);
//		JRadioButton expNada = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EXP_NADA);
//		JRadioButton expMedia = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EXP_MEDIA);
//		JRadioButton expMucha = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.EXP_MUCHA);
//		JRadioButton terciarios = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.TERCIARIOS);
//		JRadioButton secundarios = (JRadioButton) TestUtils.getComponentForName(ventana,Constantes.SECUNDARIOS);
//		JRadioButton primarios = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PRIMARIOS);
//		JRadioButton junior = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.JUNIOR);
//		JRadioButton senior = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.SENIOR);
//		JRadioButton managment = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.MANAGMENT);
//		JRadioButton presencial = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PRESENCIAL);
//		JRadioButton homeOffice = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.HOME_OFFICE);
//		JRadioButton indistinto = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.INDISTINTO);
//		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
//		JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
//		
//		Assert.assertFalse("El componente deberia estar deshabilitado",jornadaMedia.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",jornadaCompleta.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",jornadaExtendida.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",expNada.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",expMedia.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",expMucha.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",terciarios.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",secundarios.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",primarios.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",junior.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",senior.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",managment.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",presencial.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",homeOffice.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",indistinto.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",remuneracion.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",confirmarNuevoTicket.isEnabled());
//		
//		System.out.println(remuneracion.isEnabled());
//		TestUtils.clickComponent(nuevoTicket, robot);
//		
//		System.out.println(remuneracion.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",jornadaMedia.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",jornadaCompleta.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",jornadaExtendida.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",expNada.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",expMedia.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",expMucha.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",terciarios.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",secundarios.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",primarios.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",junior.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",senior.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",managment.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",presencial.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",homeOffice.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",indistinto.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",remuneracion.isEnabled());
//		Assert.assertFalse("El componente deberia estar deshabilitado",confirmarNuevoTicket.isEnabled());
//		
//		//System.out.println(jornadaMedia.isEnabled());
//		
//	}
	

	public void utilsElementos() {
//Dejo este metodo con los elementos para copiarlos y pegarlos mas rapido
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
			JRadioButton mangment = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.MANAGMENT);
			JRadioButton presencial = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.PRESENCIAL);
			JRadioButton homeOffice = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.HOME_OFFICE);
			JRadioButton indistinto = (JRadioButton) TestUtils.getComponentForName(ventana, Constantes.INDISTINTO);

			
			JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
			
			
			JButton seleccionarCandidato = (JButton) TestUtils.getComponentForName(ventana, Constantes.SELECCIONAR_CANDIDATO);	
			JButton eliminarTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.ELIMINAR_TICKET);	
			JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);	
			JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
			JButton cerrarSesion = (JButton) TestUtils.getComponentForName(ventana, Constantes.CERRARSESION);
			
			TestUtils.clickComponent(nuevoTicket, robot);
			TestUtils.clickComponent(nuevoTicket, robot);
			TestUtils.clickComponent(nuevoTicket, robot);
			
			System.out.println(nuevoTicket.getText());
			//faltarian los textarea
		 
	}
	
	
	}