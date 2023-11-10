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

public class AgenciaConClientesContratando {

  Agencia agencia;
  Cliente empleado1;
  Cliente empleado2;
  Cliente empleador1;
  Cliente empleador2;

  @Before
  public void setUp() throws Exception {
    //Preparación del escenario, ya existen empleados en el sistema
    agencia = Agencia.getInstance();
    agencia.setEstadoContratacion(true);
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
  }

  @After
  public void tearDown() throws Exception {}

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
}
