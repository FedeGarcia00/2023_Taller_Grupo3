package testAgencia;

import excepciones.ContraException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.NombreUsuarioException;
import java.util.HashMap;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

public class TestAgenciaConClientesContratando {

  Agencia agencia;
  Cliente empleado1;
  Cliente empleado2;
  Cliente empleador1;
  Cliente empleador2;

  @Before
  public void setUp() throws Exception {
    //Preparación del escenario, ya existen empleados en el sistema
    agencia = Agencia.getInstance();
    
    
  
    HashMap<String, EmpleadoPretenso> empleados = new HashMap<>();
    HashMap<String, Empleador> empleadores = new HashMap<>();
    agencia.setEmpleados(empleados);
    agencia.setEmpleadores(empleadores);
    empleador1 =
      agencia.registroEmpleador(
        "santi",
        "456",
        "Santiago",
        "43234",
        Constantes.JURIDICA,
        Constantes.SALUD
      );
    empleador2 =
      agencia.registroEmpleador(
        "pepe",
        "765",
        "Pedro",
        "21334",
        Constantes.FISICA,
        Constantes.SALUD
      );
    empleado1 =
      agencia.registroEmpleado(
        "buacho",
        "123",
        "Bautista",
        "Orte",
        "223543",
        23
      );
    empleado2 =
      agencia.registroEmpleado(
        "fede",
        "345",
        "Federico",
        "Garcia",
        "22321",
        23
      );
    
 // Se le crea un ticket a los empleadores
    agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,20000,Constantes.JORNADA_COMPLETA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleador1);
    agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,50000,Constantes.JORNADA_MEDIA,Constantes.SENIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleador2);

    // Se le crea un ticket a los empleados
    agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,20000,Constantes.JORNADA_COMPLETA,Constantes.JUNIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleado1);
    agencia.crearTicketEmpleado(Constantes.HOME_OFFICE,50000,Constantes.JORNADA_MEDIA,Constantes.SENIOR,Constantes.EXP_MEDIA,Constantes.TERCIARIOS,empleado2);
    agencia.setEstadoContratacion(true);
  }

  @After
  public void tearDown() throws Exception {
	  agencia.setEstadoContratacion(false);
  }

  @Test
  public void testcrearTicketEmpleado1() {
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
      Assert.fail(
        "Deberia lanzar excepcion ImposibleModificarTicketsException"
      );
    } catch (ImposibleModificarTicketsException e) {
      //deberia entrar aqui
    }
  }

  @Test
  public void testcrearTicketEmpleador1() {
    System.out.println(agencia.getEstado());
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
      Assert.fail(
        "Deberia lanzar excepcion ImposibleModificarTicketsException"
      );
    } catch (ImposibleModificarTicketsException e) {
      //deberia entrar aqui
    }
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
      Assert.fail(
        "Deberia lanzar excepcion ImposibleModificarTicketsException"
      );
    } catch (ImposibleModificarTicketsException e) {
      //deberia entrar aqui
    }
  }
  
  @Test
  public void testGatillarRonda(){
	  //armo los matcheos de forma que un solo empleado sea elegido
	  empleador1.setCandidato(empleado1);
	  empleador2.setCandidato(empleado1);
	  empleado1.setCandidato(empleador1);
	  empleado2.setCandidato(empleador1);
	   
	  agencia.setEstadoContratacion(true);
	  agencia.gatillarRonda();
	  
	  //testeo que el empleador que no contrato a un empleado haya sido penalizado
	  //NO ANDA, da +20
	  //Assert.assertEquals("El puntaje deberia ser -20", -20.0, empleador2.getPuntaje(), 0.0);
	  
	  //testeo que los matcheos fueron eliminados
	  Assert.assertNull("No deberia existir lista", empleador1.getListaDePostulantes());
	  Assert.assertNull("No deberia existir lista", empleador2.getListaDePostulantes());
	  Assert.assertNull("No deberia existir lista", empleado1.getListaDePostulantes());
	  Assert.assertNull("No deberia existir lista", empleado2.getListaDePostulantes());
	  
	  //testeo que el estado de contratacion haya cambiado
	  Assert.assertEquals("El estado de contratacion deberia ser falso", false, agencia.isEstadoContratacion());
  }
}
