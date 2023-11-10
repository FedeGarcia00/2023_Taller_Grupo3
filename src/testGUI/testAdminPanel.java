package testGUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import util.Constantes;
import vista.PanelAdmin;
import vista.PanelRegistro;
import vista.Ventana;

public class testAdminPanel {

	Controlador controlador;
	Robot robot; 
	Ventana ventana;
	
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
		  ventana = (Ventana) controlador.getVista();
		  PanelAdmin panelAdmin = new PanelAdmin(controlador);
		  ventana.setContentPane(panelAdmin);
		 
	}

	@After
	public void tearDown() throws Exception {
		 ventana.setVisible(false);
	}

	@Test
	public void testModificarLimitesCompletoCorrecto() {
		robot.delay(TestUtils.getDelay());
		JTextField textoInferior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_INFERIOR);
		JTextField textoSuperior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_SUPERIOR);
		JButton modificarValores = (JButton) TestUtils.getComponentForName(ventana, Constantes.MODIFICAR_VALORES);
		TestUtils.clickComponent(textoInferior, robot);
		TestUtils.tipeaTexto("10", robot);	
		TestUtils.clickComponent(textoSuperior, robot);
		TestUtils.tipeaTexto("100", robot);	
		robot.delay(TestUtils.getDelay());
		Assert.assertTrue("El boton modificar valores deberia estar habilitado",modificarValores.isEnabled());
	}
	
	@Test
	public void testModificarLimitesSoloInferior() {
		 robot.delay(TestUtils.getDelay());
		JTextField textoInferior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_INFERIOR);
		JButton modificarValores = (JButton) TestUtils.getComponentForName(ventana, Constantes.MODIFICAR_VALORES);
		TestUtils.clickComponent(textoInferior, robot);
		TestUtils.tipeaTexto("10", robot);	
		Assert.assertFalse("El boton modificar valores deberia estar deshabilitado",modificarValores.isEnabled());
	}
	
	@Test
	public void testModificarLimitesSoloSuperior() {
		 robot.delay(TestUtils.getDelay());
		JTextField textoSuperior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_SUPERIOR);
		JButton modificarValores = (JButton) TestUtils.getComponentForName(ventana, Constantes.MODIFICAR_VALORES);
		TestUtils.clickComponent(textoSuperior, robot);
		TestUtils.tipeaTexto("100", robot);	
		Assert.assertFalse("El boton modificar valores deberia estar deshabilitado",modificarValores.isEnabled());
	}
	
	@Test
	public void testModificarLimitesInferiorNegativo() {
		 robot.delay(TestUtils.getDelay());
		JTextField textoInferior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_INFERIOR);
		JTextField textoSuperior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_SUPERIOR);
		JButton modificarValores = (JButton) TestUtils.getComponentForName(ventana, Constantes.MODIFICAR_VALORES);
		TestUtils.clickComponent(textoInferior, robot);
		TestUtils.tipeaTexto("-10", robot);	
		TestUtils.clickComponent(textoSuperior, robot);
		TestUtils.tipeaTexto("100", robot);	
		Assert.assertFalse("El boton modificar valores deberia estar deshabilitado",modificarValores.isEnabled());
	}
	
	@Test
	public void testModificarLimitesSuperiorMenorInferior() {
		robot.delay(TestUtils.getDelay());
		JTextField textoInferior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_INFERIOR);
		JTextField textoSuperior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_SUPERIOR);
		JButton modificarValores = (JButton) TestUtils.getComponentForName(ventana, Constantes.MODIFICAR_VALORES);
		TestUtils.clickComponent(textoInferior, robot);
		TestUtils.tipeaTexto("100", robot);	
		TestUtils.clickComponent(textoSuperior, robot);
		TestUtils.tipeaTexto("10", robot);	
		Assert.assertFalse("El boton modificar valores deberia estar deshabilitado", modificarValores.isEnabled());
	}
	
	@Test
	public void aplicarPromo() {
		// ver como testear que realmente llama al metodo correspondiente
	}
	
	@Test
	public void cerrarSesion() {
		// ver como testear que realmente llama al metodo correspondiente
	}
	
//	@Test
//	public void componentes() {
//		JTextField textoInferior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_INFERIOR);
//		JTextField textoSuperior = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTO_SUPERIOR);
//		
//		
//		JButton modificarValores = (JButton) TestUtils.getComponentForName(ventana, Constantes.MODIFICAR_VALORES);	
//		JButton gatillar = (JButton) TestUtils.getComponentForName(ventana, Constantes.GATILLAR);	
//		JButton aplicarPromo = (JButton) TestUtils.getComponentForName(ventana, Constantes.APLICAR_PROMO);	
//		JButton cerrarSesion = (JButton) TestUtils.getComponentForName(ventana, Constantes.CERRARSESION);	
//		
//		JCheckBox listaPostulantes = (JCheckBox) TestUtils.getComponentForName(ventana, Constantes.CHECK_BOX_LISTA_POSTULANTES);
//		
//		System.out.println(textoInferior.getText() + textoInferior.isEnabled());
//		System.out.println(textoSuperior.getText() + textoSuperior.isEnabled());
//		System.out.println(modificarValores.getText() + modificarValores.isEnabled());
//		TestUtils.clickComponent(textoInferior, robot);
//		TestUtils.tipeaTexto("10", robot);	
//		TestUtils.clickComponent(textoSuperior, robot);
//		TestUtils.tipeaTexto("100", robot);	
//		System.out.println(textoInferior.getText() + textoInferior.isEnabled());
//		System.out.println(textoSuperior.getText() + textoSuperior.isEnabled());
//		System.out.println(modificarValores.getText() + modificarValores.isEnabled());
//	}
	
}
