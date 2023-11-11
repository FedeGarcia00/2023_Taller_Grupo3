package testIntegracion;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;

import java.util.ArrayList;
import java.util.HashMap;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Usuario;
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
    empleador1 = agencia.registroEmpleador(
      "santi",
      "456",
      "Santiago",
      "43234",
      Constantes.JURIDICA,
      Constantes.SALUD
    );
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

    // Se le crea un ticket al empleador 1
    agencia.crearTicketEmpleador(
      Constantes.HOME_OFFICE,
      50000,
      Constantes.JORNADA_MEDIA,
      Constantes.JUNIOR,
      Constantes.EXP_MEDIA,
      Constantes.TERCIARIOS,
      empleador1
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
  }

  @After
  public void tearDown() throws Exception {}

  // 1) El administrador se loguea, setea los limites de remuneracion,
  // gatilla ronda y cierra sesion

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
    Assert.assertEquals(agencia.getTipoUsuario(), -1);
    Assert.assertEquals(agencia.getLimiteInferior(), 1000);
    Assert.assertEquals(agencia.getLimiteSuperior(), 1500);
    Assert.assertEquals(agencia.getEstado(), Mensajes.AGENCIA_EN_CONTRATACION.getValor());
  }

  // 2) Un empleado se registra,se loguea,el estado de contratacion esta en false, por lo tanto crea un ticket
  // y luego cierra sesion
  @Test
  public void testCaso2() {
	  try {
		agencia.registroEmpleado("Lio","2022gloriaeterna" ,"Messi", "Lionel", "+5423234", 36);
	} catch (NewRegisterException | ImposibleCrearEmpleadoException e) {
		e.printStackTrace();
	}
	  try {
		agencia.login("Lio", "2022gloriaeterna");
	} catch (ContraException | NombreUsuarioException e) {
		e.printStackTrace();
	}
	try {
		agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 1200, Constantes.JORNADA_MEDIA, Constantes.JUNIOR, Constantes.SECUNDARIOS, Constantes.EXP_NADA, empleado1);
	} catch (ImposibleModificarTicketsException e) {
		e.printStackTrace();
	}
	System.out.println(agencia.getTipoUsuario());
   //hacer los assert
	
  }

  // 3) Un empleador se loguea, el estado de contratacion esta en false, elimina un ticket y cierra sesion
  @Test
  public void testCaso3() {
   
	agencia.setEstadoContratacion(false);
		try {
			Empleador usuario = (Empleador) agencia.login("santi", "456");
		} catch (ContraException | NombreUsuarioException e) {
			e.printStackTrace();
		}
		
		try {
			agencia.eliminarTicket();
		} catch (ImposibleModificarTicketsException e) {
			e.printStackTrace();
		}
		agencia.cerrarSesion();
		//faltan los asssert
	
	  
  }

  // 4) Un empleador se loguea, el estado de contratacion esta en true, selecciona candidato y cierra sesion
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
			  Assert.assertEquals(candidato, usuario.getCandidato());
			  agencia.gatillarRonda();
			  
		} catch (ContraException | NombreUsuarioException e) {
			e.printStackTrace();
		}
		
	
		//faltan los asssert
	  
	
		  
		  
  }
  
  // 5)El admin se loguea, gatilla ronda y se hacen matchs
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
	
	  //assert de que el estado de contratacion pasa a false
	  //assert de que getListaContrataciones.length>0
	  //assert para cada empleado de que no tienen tickets
	  //assert tipoUsuario = -1
  }
}
