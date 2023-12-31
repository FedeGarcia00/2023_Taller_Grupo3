package testModeloNegocio;

import excepciones.ContraException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.NombreUsuarioException;
import java.util.HashMap;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

public class TestAgenciaConClientesBusqueda {

  Agencia agencia;
  Cliente empleado1;
  Cliente empleado2;
  Cliente empleador1;
  Cliente empleador2;

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
    empleador2 =agencia.registroEmpleador("pepe","765","Pedro","21334",Constantes.FISICA,Constantes.SALUD);
    empleado1 = agencia.registroEmpleado("baucho","123","Bautista","Orte","223543",23);
    empleado2 = agencia.registroEmpleado("fede","345","Federico","Garcia","22321",23);

    // Se le crea un ticket al empleado 2
    agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,50000,Constantes.JORNADA_MEDIA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleado2);
    // Se le crea un ticket al empleador 2
    agencia.crearTicketEmpleador(Constantes.HOME_OFFICE,50000,Constantes.JORNADA_MEDIA,Constantes.JUNIOR,Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador2);
  }

  @After
  public void tearDown() throws Exception {
	 empleado1.setTicket(null);
	 empleado2.setTicket(null);
  }

  @Test
  public void testcrearTicketEmpleado1() {
    System.out.println(agencia.getEstado());
    try {
      agencia.crearTicketEmpleado(
        Constantes.PRESENCIAL,
        70000,
        Constantes.JORNADA_COMPLETA,
        Constantes.JUNIOR,
        Constantes.EXP_NADA,
        Constantes.SECUNDARIOS,
        empleado1
      );
    } catch (ImposibleModificarTicketsException e) {
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleado1.getTicket();
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.PRESENCIAL,ticketNuevo.getLocacion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",70000, ticketNuevo.getRemuneracion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.JORNADA_COMPLETA,ticketNuevo.getJornada());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ", Constantes.JUNIOR,ticketNuevo.getPuesto() );
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.EXP_NADA,ticketNuevo.getExperiencia());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.SECUNDARIOS ,ticketNuevo.getEstudios());
  }

  @Test
  public void testcrearTicketEmpleado2() {
    try {
      agencia.crearTicketEmpleado(
        Constantes.PRESENCIAL,
        70000,
        Constantes.JORNADA_COMPLETA,
        Constantes.JUNIOR,
        Constantes.EXP_NADA,
        Constantes.SECUNDARIOS,
        empleado2
      );
    } catch (ImposibleModificarTicketsException e) {
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleado2.getTicket();
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.PRESENCIAL,ticketNuevo.getLocacion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",70000, ticketNuevo.getRemuneracion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.JORNADA_COMPLETA,ticketNuevo.getJornada());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ", Constantes.JUNIOR,ticketNuevo.getPuesto() );
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.EXP_NADA,ticketNuevo.getExperiencia());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.SECUNDARIOS ,ticketNuevo.getEstudios());
  }

  @Test
  public void testcrearTicketEmpleador1() {
    try {
      agencia.crearTicketEmpleador(
        Constantes.PRESENCIAL,
        70000,
        Constantes.JORNADA_COMPLETA,
        Constantes.JUNIOR,
        Constantes.EXP_NADA,
        Constantes.SECUNDARIOS,
        empleador1
      );
    } catch (ImposibleModificarTicketsException e) {
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleador1.getTicket();
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.PRESENCIAL,ticketNuevo.getLocacion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",70000, ticketNuevo.getRemuneracion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.JORNADA_COMPLETA,ticketNuevo.getJornada());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.JUNIOR,ticketNuevo.getPuesto() );
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.EXP_NADA,ticketNuevo.getExperiencia());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.SECUNDARIOS ,ticketNuevo.getEstudios());
  }

  @Test
  public void testcrearTicketEmpleador2() {
    try {
      agencia.crearTicketEmpleado(
        Constantes.PRESENCIAL,
        70000,
        Constantes.JORNADA_COMPLETA,
        Constantes.JUNIOR,
        Constantes.EXP_NADA,
        Constantes.SECUNDARIOS,
        empleador2
      );
    } catch (ImposibleModificarTicketsException e) {
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleador2.getTicket();
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.PRESENCIAL,ticketNuevo.getLocacion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",70000, ticketNuevo.getRemuneracion());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.JORNADA_COMPLETA,ticketNuevo.getJornada());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.JUNIOR,ticketNuevo.getPuesto() );
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.EXP_NADA,ticketNuevo.getExperiencia());
    Assert.assertEquals("El valor del ticket no coincide con el creado: ",Constantes.SECUNDARIOS ,ticketNuevo.getEstudios());
  }

  @Test
  public void testeliminarTicket1() {
    try {
      agencia.login("pepe", "765");
    } catch (ContraException | NombreUsuarioException e) {
      // no deberia tirar excepcion, ya fue testeado en su propio metodo
    }
    try {
      agencia.eliminarTicket();
    } catch (ImposibleModificarTicketsException e) {
      Assert.fail("No deberia lanzar la excepcion, deberia eliminar ticket" + e.getMessage());
    }
    Assert.assertEquals("No deberia existir ticket despues de eliminarlo", null,empleador2.getTicket() );
  }
  
  @Test
  public void testGatillarRonda(){
	  
	try {
		agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,20000,Constantes.JORNADA_COMPLETA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleador1);
	} catch (ImposibleModificarTicketsException e) {	
	}  
	    try {
			agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,20000,Constantes.JORNADA_COMPLETA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleado1);
	} catch (ImposibleModificarTicketsException e) {
	}
	  agencia.gatillarRonda();  
	  //testeo que se hayan creado las listas de postulantes
	  Assert.assertEquals("El usuario tiene q coincidir", "baucho", empleador1.getListaDePostulantes().get(0).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "fede", empleador1.getListaDePostulantes().get(1).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "baucho", empleador2.getListaDePostulantes().get(0).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "fede", empleador2.getListaDePostulantes().get(1).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "santi", empleado1.getListaDePostulantes().get(0).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "pepe", empleado1.getListaDePostulantes().get(1).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "santi", empleado2.getListaDePostulantes().get(0).getCliente().getUsserName());
	  Assert.assertEquals("El usuario tiene q coincidir", "pepe", empleado2.getListaDePostulantes().get(1).getCliente().getUsserName());
	  
	  //testeo que el estado de contratacion haya cambiado
	  Assert.assertEquals("El estado de contratacion deberia ser true", true, agencia.isEstadoContratacion());
	  
	  //testeo que se hayan aplicado los puntajes
	  Assert.assertEquals("El puntaje debe coincidir", 0.0, empleador2.getPuntaje(), 0.0);
	  Assert.assertEquals("El puntaje debe coincidir", 10.0, empleado1.getPuntaje(), 0.0);
	  Assert.assertEquals("El puntaje debe coincidir", 5.0, empleado2.getPuntaje(), 0.0);
	  Assert.assertEquals("El puntaje debe coincidir", -20.0, empleador1.getPuntaje(), 0.0);
  }
}
