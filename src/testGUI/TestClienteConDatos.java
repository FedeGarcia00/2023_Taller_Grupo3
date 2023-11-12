package testGUI;


import static org.mockito.Mockito.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.Controlador;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class TestClienteConDatos {
	
	Controlador controlador;
	Robot robot; 
	FalsoOptionPane op = new FalsoOptionPane();
	Agencia agencia;
	Ventana ventana;
	Empleador empleador;
	
	public TestClienteConDatos() {
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
		  
		  //fuerzo ir a la ventana de cliente
		  JTextField username = (JTextField) TestUtils.getComponentForName(ventana, Constantes.NOMBRE_USUARIO);
		  JTextField password = (JTextField) TestUtils.getComponentForName(ventana, Constantes.PASSWORD);
		  JButton login = (JButton) TestUtils.getComponentForName(ventana, Constantes.LOGIN);

		  agencia = Agencia.getInstance();  
		  HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		  HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>();
		  agencia.setEmpleados(empleados);
		  agencia.setEmpleadores(empleadores);
		  agencia.setEstadoContratacion(false);
		  empleador = (Empleador) agencia.registroEmpleador("santi","456","Santiago","43234",Constantes.JURIDICA,Constantes.SALUD);
		  agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS, empleador);
		  agencia.registroEmpleado("fede","123","Federico","Garcia","223543",23);
		  
		  TestUtils.clickComponent(username, robot);
	      TestUtils.tipeaTexto("santi", robot);
	      
	      TestUtils.clickComponent(password, robot);
	      TestUtils.tipeaTexto("456", robot);
	
	      TestUtils.clickComponent(login, robot);
		  controlador.setMyOptionPane(op);

	}

	@After
	public void tearDown() throws Exception {
		 Ventana ventana = (Ventana) controlador.getVista();
		 ventana.setVisible(false);
	}

	
	@Test
	public void testSeleccionarCandidato1() {

		Ventana vistaMock = mock(Ventana.class);
		
		EmpleadoPretenso candidato = new EmpleadoPretenso("Lio", "123", "Lionel", "+5492223141", "Messi", 36);
        when(vistaMock.getCandidato()).thenReturn(candidato);
        controlador.setVista(vistaMock);

	    JButton seleccionarCandidato = (JButton) TestUtils.getComponentForName(ventana, Constantes.SELECCIONAR_CANDIDATO);
	    TestUtils.clickComponent(seleccionarCandidato, robot);  
	 
	    //System.out.println(candidato.toString());
	    Assert.assertEquals("Deberia mostrar el .toString() de un cliente llamado Lio:",candidato.toString(), op.getMensaje());
	}
	
	@Test
	public void testCrearTicket1() {
		//crea el ticket correctamente
		agencia.setEstadoContratacion(false);	
		JTextArea textAreaTicket = (JTextArea) TestUtils.getComponentForName(ventana, Constantes.TEXT_AREA_TICKET);	
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);	
		TestUtils.clickComponent(nuevoTicket, robot); 
		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
		TestUtils.clickComponent(remuneracion, robot);
		TestUtils.tipeaTexto("200", robot);
		JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
		TestUtils.clickComponent(confirmarNuevoTicket, robot);
		Assert.assertEquals("Deberia mostrar el detalle del ticket",empleador.getTicket().toString(), textAreaTicket.getText());
	}
	
	
	@Test
	public void testCrearTicket2() {
		//deberia tirar error, pues la agencia no permite creacion de nuevos tickets
		agencia.setEstadoContratacion(true);		
		JButton nuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.NUEVOTICKET);	
		TestUtils.clickComponent(nuevoTicket, robot); 
		JTextField remuneracion = (JTextField) TestUtils.getComponentForName(ventana, Constantes.TEXTFIELD_REMUNERACION);
		TestUtils.clickComponent(remuneracion, robot);
		TestUtils.tipeaTexto("200", robot);
		JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.CONFIRMARNUEVOTICKET);
		TestUtils.clickComponent(confirmarNuevoTicket, robot);
		Assert.assertEquals("Deberia mostrar mensaje de error",Mensajes.ERROR_AGENCIA_EN_CONTRATACION.getValor() ,op.getMensaje());
	}
	
	@Test
	public void testEliminarTicket1() {
		//se deberia eliminar el ticket correctamente sin mostrar ningun mensaje
		//la habilitacion y deshabilitacion de los botones ya fue testeada en su respectiva clase
		agencia.setEstadoContratacion(false);
		JTextArea textAreaTicket = (JTextArea) TestUtils.getComponentForName(ventana, Constantes.TEXT_AREA_TICKET);	
		JButton eliminarTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.ELIMINAR_TICKET);
		TestUtils.clickComponent(eliminarTicket, robot);
		Assert.assertEquals("Deberia mostrar mensaje de no hay ticket",Mensajes.SIN_TICKET.getValor(), textAreaTicket.getText());
	}
	
	@Test
	public void testEliminarTicket2() {
		//deberia mostrar mensaje de agencia en contratacion
		agencia.setEstadoContratacion(true);
		JButton eliminarTicket = (JButton) TestUtils.getComponentForName(ventana, Constantes.ELIMINAR_TICKET);
		TestUtils.clickComponent(eliminarTicket, robot);
		Assert.assertEquals("Deberia mostrar mensaje de error",Mensajes.ERROR_AGENCIA_EN_CONTRATACION.getValor() ,op.getMensaje());
	}
	

	
	}   
	

