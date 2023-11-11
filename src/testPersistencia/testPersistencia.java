package testPersistencia;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import persistencia.PersistenciaXML;
import util.Constantes;
import util.Mensajes;

public class testPersistencia {

	  Agencia agencia;
	  EmpleadoPretenso empleado1;
	  EmpleadoPretenso empleado2;
	  Empleador empleador1;
	  Empleador empleador2;
	  int limiteInferior;
	  int limiteSuperior;
	  PersistenciaXML persistencia;

	@Before
	public void setUp() throws Exception {
	    
		agencia = Agencia.getInstance();
		persistencia = new  PersistenciaXML();
		//Creamos datos para cargar la agencia en un archivo
	    empleador1 = (Empleador) agencia.registroEmpleador("santi","456","Santiago", "43234",Constantes.JURIDICA, Constantes.SALUD);
	    empleador2 = (Empleador) agencia.registroEmpleador("pepe","765", "Pedro","21334",Constantes.FISICA,Constantes.SALUD);
	    empleado1 = (EmpleadoPretenso) agencia.registroEmpleado("buacho", "123","Bautista","Orte","223543",23);
	    empleado2 = (EmpleadoPretenso) agencia.registroEmpleado("fede","345","Federico","Garcia","22321",23);
	    limiteInferior = 1500;
	    limiteSuperior = 2000;
	    agencia.setLimitesRemuneracion(limiteInferior, limiteSuperior);
	    agencia.setEstadoContratacion(true);
	    agencia.setPersistencia(persistencia);
	    agencia.guardarAgencia("testPersistencia.xml");
	    
	    // Ponemos datos para luego verificar si se sobrescriben
	    agencia.setEmpleados(new HashMap<String, EmpleadoPretenso>());
	    agencia.setEmpleadores(new HashMap<String, Empleador>());
	    agencia.setLimitesRemuneracion(1600, 2100);
	    
//		System.out.println(agencia.getEmpleados());
//		System.out.println(agencia.getEmpleadores());
	}
	
	@After
	 public void tearDown() throws Exception {
	    agencia.getEmpleadores().clear();
	    agencia.getEmpleados().clear();
	  }
	

	@Test
	public void testComprobacionGuardado() {
		//el archivo deberia existir
		 File archivo = new File("testPersistencia.xml");
		 boolean existe = archivo.exists();
		 Assert.assertTrue("No existe el archivo", existe);
	}
	
	@Test
	public void testArchivoNoExiste() {
		try {
			agencia.cargarAgencia("noExiste.jpg");
			Assert.fail("Deberia lanzar excepcion IOException");
		} catch (ClassNotFoundException e) {
			Assert.fail("Deberia lanzar excepcion IOException");
		} catch (IOException e) {
		}
	}
	
	
	@Test
	public void testCargaEmpleados() {
		try {
			agencia.cargarAgencia("testPersistencia.xml");
			 HashMap<String, EmpleadoPretenso> empleados = agencia.getEmpleados();
			 Assert.assertEquals("Deberia haber dos empleados",2 ,empleados.size() ); 
			
			 //Comparacion de los datos de UNO solo
			 EmpleadoPretenso empleado = empleados.get("fede");
			 Assert.assertEquals("",empleado2.getPassword(), empleado.getPassword());
			 Assert.assertEquals("",empleado2.getRealName(), empleado.getRealName());
			 Assert.assertEquals("",empleado2.getApellido(), empleado.getApellido());
			 Assert.assertEquals("",empleado2.getTelefono(), empleado.getTelefono());
			 Assert.assertEquals("",empleado2.getEdad(), empleado.getEdad());
			 
		} catch (ClassNotFoundException | IOException e) {
			Assert.fail("No deberia entrar aqui, el deberia existir.");
		}
		
	}
	
	@Test
	public void testCargaEmpleadores() {
		try {
			
			 agencia.cargarAgencia("testPersistencia.xml");
			 HashMap<String, Empleador> empleadores = agencia.getEmpleadores();
			 Assert.assertEquals("Deberia haber dos empleados", empleadores.size() , 2); 
			 //Comparacion de los datos de UNO solo
			 Empleador empleador = empleadores.get("santi");
			 Assert.assertEquals("",empleador1.getPassword(), empleador.getPassword());
			 Assert.assertEquals("",empleador1.getRealName(), empleador.getRealName());
			 Assert.assertEquals("",empleador1.getTelefono(), empleador.getTelefono());
			 Assert.assertEquals("",empleador1.getTipoPersona(), empleador.getTipoPersona());
			 Assert.assertEquals("",empleador1.getRubro(), empleador.getRubro());
 
		} catch (ClassNotFoundException | IOException e) {
			Assert.fail("No deberia entrar aqui, el deberia existir.");
		}
		
	}
	
	@Test
	public void testLimiteSuperior() {
		try {
			agencia.cargarAgencia("testPersistencia.xml");
			 Assert.assertEquals("",limiteSuperior, agencia.getLimiteSuperior());	 
		} catch (ClassNotFoundException | IOException e) {
			Assert.fail("No deberia entrar aqui, el deberia existir.");
		}
	}
	
	@Test
	public void testLimiteInferior() {
		try {
			agencia.cargarAgencia("testPersistencia.xml");
			 Assert.assertEquals("",limiteInferior, agencia.getLimiteInferior());	 
		} catch (ClassNotFoundException | IOException e) {
			Assert.fail("No deberia entrar aqui, el deberia existir.");
		}
	}
	
	@Test
	public void testEstadoContratacion() {
		try {
			agencia.cargarAgencia("testPersistencia.xml");
			 Assert.assertEquals("",Mensajes.AGENCIA_EN_CONTRATACION.getValor(), agencia.getEstado());	 
		} catch (ClassNotFoundException | IOException e) {
			Assert.fail("No deberia entrar aqui, el deberia existir.");
		}
	}

}


