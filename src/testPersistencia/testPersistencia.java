package testPersistencia;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import persistencia.PersistenciaXML;
import util.Constantes;

public class testPersistencia {

	  Agencia agencia;
	  Cliente empleado1;
	  Cliente empleado2;
	  Cliente empleador1;
	  Cliente empleador2;
	  PersistenciaXML persistencia;

	@Before
	public void setUp() throws Exception {
		 //Preparación del escenario, ya existen clientes en el sistema y tickets asociados.
		//persistencia = new PersistenciaXML();
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
	        "AAAPrueba",
	        "31",
	        "Bau",
	        "ASD",
	        "223543",
	        23
	      );
	    empleado2 =
	      agencia.registroEmpleado(
	        "pruebaPersisss",
	        "345",
	        "PRU",
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
	public void tearDown() throws Exception {
	}

	@Test
	public void testguardarAgencia1() {
		agencia.setPersistencia(persistencia);
		try {
			agencia.guardarAgencia("Test2.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			this.agencia = null;
			this.agencia = Agencia.getInstance();
			System.out.println(agencia.getEmpleados());
		
	}

}
