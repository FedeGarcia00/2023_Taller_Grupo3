package testAgenciaNuevo;

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

public class AgenciaConClientesBusqueda {

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

    // Se le crea un ticket al empleado 2
    agencia.crearTicketEmpleado(
      Constantes.HOME_OFFICE,
      50000,
      Constantes.JORNADA_MEDIA,
      Constantes.JUNIOR,
      Constantes.EXP_MEDIA,
      Constantes.TERCIARIOS,
      empleado2
    );
    // Se le crea un ticket al empleador 2
    agencia.crearTicketEmpleador(
      Constantes.HOME_OFFICE,
      50000,
      Constantes.JORNADA_MEDIA,
      Constantes.JUNIOR,
      Constantes.EXP_MEDIA,
      Constantes.TERCIARIOS,
      empleador2
    );
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testcrearTicketEmpleado1() {
    //ANDA
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
      // El usuario no tiene ticket, por lo tanto no deberia tirar excepcion
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleado1.getTicket();
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getLocacion(),
      Constantes.PRESENCIAL
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getRemuneracion(),
      70000
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getJornada(),
      Constantes.JORNADA_COMPLETA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getPuesto(),
      Constantes.JUNIOR
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getExperiencia(),
      Constantes.EXP_NADA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getEstudios(),
      Constantes.SECUNDARIOS
    );
  }

  @Test
  public void testcrearTicketEmpleado2() {
    //NO ANDA, TIRA UN NULL POINTER EXCEPTION, (REVISAR PQ AHORA ANDA Y NO TOQUE NADA XD)
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
      // El usuario no tiene ticket, por lo tanto no deberia tirar excepcion
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleado2.getTicket();
    // tienen que quedar en e
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getLocacion(),
      Constantes.PRESENCIAL
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getRemuneracion(),
      70000
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getJornada(),
      Constantes.JORNADA_COMPLETA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getPuesto(),
      Constantes.JUNIOR
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getExperiencia(),
      Constantes.EXP_NADA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getEstudios(),
      Constantes.SECUNDARIOS
    );
  }

  @Test
  public void testcrearTicketEmpleador1() {
    //ANDA
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
      // El usuario no tiene ticket, por lo tanto no deberia tirar excepcion
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleador1.getTicket();
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getLocacion(),
      Constantes.PRESENCIAL
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getRemuneracion(),
      70000
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getJornada(),
      Constantes.JORNADA_COMPLETA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getPuesto(),
      Constantes.JUNIOR
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getExperiencia(),
      Constantes.EXP_NADA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getEstudios(),
      Constantes.SECUNDARIOS
    );
  }

  @Test
  public void testcrearTicketEmpleador2() {
    //NO ANDA, TIRA UN NULL POINTER EXCEPTION (REVISAR PQ AHORA ANDA Y NO TOQUE NADA XD)
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
      // El usuario no tiene ticket, por lo tanto no deberia tirar excepcion
      Assert.fail("No deberia tirar excepcion: " + e.getMessage());
    }
    Ticket ticketNuevo = empleador2.getTicket();
    // tienen que quedar en e
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getLocacion(),
      Constantes.PRESENCIAL
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getRemuneracion(),
      70000
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getJornada(),
      Constantes.JORNADA_COMPLETA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getPuesto(),
      Constantes.JUNIOR
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getExperiencia(),
      Constantes.EXP_NADA
    );
    Assert.assertEquals(
      "El valor del ticket no coincide con el creado: ",
      ticketNuevo.getEstudios(),
      Constantes.SECUNDARIOS
    );
  }

  @Test
  public void testeliminarTicket1() {
    //ANDA
    try {
      agencia.login("pepe", "765");
    } catch (ContraException | NombreUsuarioException e) {
      // no deberia tirar excepcion, ya fue testeado en su propio metodo
    }
    try {
      agencia.eliminarTicket();
    } catch (ImposibleModificarTicketsException e) {
      Assert.fail(
        "No deberia lanzar la excepcion, deberia eliminar ticket" +
        e.getMessage()
      );
    }
    Assert.assertEquals(
      "No deberia existir ticket despues de eliminarlo",
      null,
      empleador2.getTicket()
    );
  }
}
