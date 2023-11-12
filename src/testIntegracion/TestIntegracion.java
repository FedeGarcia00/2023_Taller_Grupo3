package testIntegracion;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;

import java.util.ArrayList;
import java.util.HashMap;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

public class TestIntegracion {

  Agencia agencia;
  Cliente empleador1;
  Cliente empleado1;
  Cliente empleado2;

  @Before
  public void setUp() throws Exception {
    //Preparación del escenario, ya existen empleados en el sistema 
    agencia = Agencia.getInstance();
    agencia.setEstadoContratacion(false);
    HashMap<String, EmpleadoPretenso> empleados = new HashMap<>();
    HashMap<String, Empleador> empleadores = new HashMap<>();
    agencia.setEmpleados(empleados);
    agencia.setEmpleadores(empleadores);
    empleador1 = agencia.registroEmpleador("santi","456","Santiago","43234",Constantes.JURIDICA,Constantes.SALUD);
    agencia.registroEmpleador("pepe","765","Pedro","21334",Constantes.FISICA,Constantes.SALUD);
    empleado1 = agencia.registroEmpleado("buacho","123","Bautista","Orte","223543",23);
    empleado2 = agencia.registroEmpleado("fede","345","Federico","Garcia","22321",23);

    // Se le crea un ticket al empleador 1
    agencia.crearTicketEmpleador(Constantes.HOME_OFFICE,50000,Constantes.JORNADA_MEDIA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleador1);
    // Se le crea un ticket al empleado 2
    agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,50000,Constantes.JORNADA_MEDIA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleado2);
    
    agencia.setContrataciones(new ArrayList<Contratacion>());
  }

  @After
  public void tearDown() throws Exception {}


  @Test
  public void testCaso1() {  
    try {
		agencia.login("admin", "admin");
	} catch (ContraException | NombreUsuarioException e) {
		e.printStackTrace();
	}
    try {
    	agencia.setLimitesRemuneracion(1000, 1500);
    } catch (LimiteSuperiorRemuneracionInvalidaException | LimiteInferiorRemuneracionInvalidaException e) {
    	e.printStackTrace();
    }
    agencia.gatillarRonda();
    agencia.cerrarSesion();

    // verificamos solo los estados finales, pues se supone que los metodos ya fueron testeados en test unitarios
    Assert.assertEquals("El estado del usuario al cerrar sesion debe ser -1", -1,agencia.getTipoUsuario());
    Assert.assertEquals(agencia.getLimiteInferior(), 1000);
    Assert.assertEquals(agencia.getLimiteSuperior(), 1500);
    Assert.assertEquals(agencia.getEstado(), Mensajes.AGENCIA_EN_CONTRATACION.getValor());
  }

  
  @Test
  public void testCaso2() {
	 EmpleadoPretenso empleado = null; 
	// int cantidadEmpleados = agencia.getEmpleados().size(); 
	 try {	
		empleado = (EmpleadoPretenso) agencia.registroEmpleado("Lio","2022gloriaeterna" ,"Messi", "Lionel", "+5423234", 36);
		agencia.login("Lio", "2022gloriaeterna");	
		agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 1200, Constantes.JORNADA_MEDIA, Constantes.JUNIOR, Constantes.SECUNDARIOS, Constantes.EXP_NADA, empleado);	
		agencia.cerrarSesion();
		Assert.assertNotNull("El empleado no deberia ser null", empleado);
		System.out.println(empleado.getTicket());
		Assert.assertNotNull("El ticket no deberia ser null, pues el empleado creo uno", empleado.getTicket());
		Assert.assertEquals("El estado del usuario al cerrar sesion debe ser -1", -1,agencia.getTipoUsuario());
	 } catch (ImposibleModificarTicketsException |NewRegisterException | ImposibleCrearEmpleadoException | ContraException | NombreUsuarioException e) {
		e.printStackTrace();
	}
  }


  @Test
  public void testCaso3() {
   
	agencia.setEstadoContratacion(false);
	Empleador usuario = null;
	try {
		usuario = (Empleador) agencia.login("santi", "456");
	} catch (ContraException | NombreUsuarioException e) {
		e.printStackTrace();
	}
	try {
		agencia.eliminarTicket();
	} catch (ImposibleModificarTicketsException e) {
		e.printStackTrace();
	}
	agencia.cerrarSesion();

	Assert.assertNull("El ticket deberia ser null", usuario.getTicket());
	Assert.assertEquals("El estado del usuario al cerrar sesion debe ser -1", -1,agencia.getTipoUsuario());
	  
  }

  @Test
  public void testCaso4() {
	  agencia.setEstadoContratacion(true);
	  try {	  	  
			  Empleador usuario = (Empleador) agencia.login("santi", "456");
			  agencia.generaPostulantes();
			  ArrayList<ClientePuntaje> clientes = usuario.getListaDePostulantes();  
			  //Se elije el primero...
			  Cliente candidato = clientes.get(0).getCliente();
			  usuario.setCandidato(candidato);	
			  agencia.cerrarSesion();
			  Assert.assertEquals(candidato, usuario.getCandidato());
			  Assert.assertEquals("El estado del usuario al cerrar sesion debe ser -1", -1,agencia.getTipoUsuario());
		} catch (ContraException | NombreUsuarioException e) {
			e.printStackTrace();
		} 
  }
  

  @Test
  public void testCaso5() {
	  agencia.setEstadoContratacion(true);
	  try {
		agencia.login("admin","admin");
	} catch (ContraException | NombreUsuarioException e) {
		e.printStackTrace();
	}
	  empleado1.setCandidato(empleador1);
	  empleador1.setCandidato(empleado1);
	  //gatillar ronda indirectamente testea el match...
	  agencia.gatillarRonda();
	  agencia.cerrarSesion();
	
	  Assert.assertEquals(agencia.getTipoUsuario(), -1);
	  Assert.assertEquals("Tiene que haber una contratacion en la agencia", 1, agencia.getContrataciones().size());
  }
}
