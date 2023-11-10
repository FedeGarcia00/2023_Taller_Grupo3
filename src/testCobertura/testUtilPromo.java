package testCobertura;




import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import util.Constantes;

public class testUtilPromo {

	UtilPromo prueba;
	@Before
	public void setUp() throws Exception {
		prueba = new UtilPromo();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testC1() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;
		aplicaPromo(false, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(false, empleados, empleadores);
		Assert.assertEquals("Deberia retornar null",null, resultado);
	}
	
	@Test
	public void testC2() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;
		aplicaPromo(true, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(true, empleados, empleadores);
		Assert.assertEquals("Deberia retornar null",null, resultado);
	}
////////	
////	
	@Test
	public void testC3() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;
		Empleador empleador1 = new Empleador("fede", "123", "Federico", "22345", Constantes.SALUD, Constantes.FISICA);
		empleador1.setPuntaje(20);
		Empleador empleador2 = new Empleador("baucho", "456", "Bautista", "22378", Constantes.SALUD, Constantes.FISICA);
		empleador2.setPuntaje(10);
		empleadores.put("1", empleador1);
		empleadores.put("2", empleador2);
		aplicaPromo(false, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(false, empleados, empleadores);
		Assert.assertEquals("Deberia retornar el primer elemento del hashmap",resultado.getUsserName(), "fede");
	}
//	
//	
//	
	@Test
	public void testC4() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;	
		Empleador empleador1 = new Empleador("fede", "123", "Federico", "22345", Constantes.SALUD, Constantes.FISICA);
		empleador1.setPuntaje(20);
		empleador1.setListaDePostulantes(null);
		empleadores.put("1", empleador1);
		aplicaPromo(true, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(true, empleados, empleadores);
		Assert.assertEquals("Deberia retornar null",null, resultado);
	}
//	
	@Test
	public void testC5() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;	
		EmpleadoPretenso empleado1 = new EmpleadoPretenso("santi", "123", "Santiago", "22456", "qwewrty", 23);
		empleado1.setPuntaje(20);
		empleado1.setListaDePostulantes(null);
		empleados.put("1", empleado1);
		aplicaPromo(true, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(true, empleados, empleadores);
		Assert.assertNotEquals("Deberia retornar el cliente del hashmap",null, resultado);
	}
	
	@Test
	public void testC6() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;	
		
		Empleador empleador1 = new Empleador("fede", "123", "Federico", "22345", Constantes.SALUD, Constantes.FISICA);
		empleador1.setPuntaje(20);
		ClientePuntaje clientepuntaje = new ClientePuntaje();
		ArrayList<ClientePuntaje> listaPostulantes = new ArrayList<ClientePuntaje>();
		listaPostulantes.add(clientepuntaje);
		empleador1.setListaDePostulantes(listaPostulantes);
		empleadores.put("1", empleador1);
		aplicaPromo(true, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(true, empleados, empleadores);
		System.out.println(resultado);
		Assert.assertNotEquals("Deberia retornar el cliente del hashmap",null, resultado);
	}
	
	@Test
	public void testC7() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;		
		EmpleadoPretenso empleado1 = new EmpleadoPretenso("santi", "123", "Santiago", "22456", "qwewrty", 23);
		empleado1.setPuntaje(20);
		ClientePuntaje clientepuntaje = new ClientePuntaje();
		ArrayList<ClientePuntaje> listaPostulantes = new ArrayList<ClientePuntaje>();
		listaPostulantes.add(clientepuntaje);
		empleado1.setListaDePostulantes(listaPostulantes);
		empleados.put("1", empleado1);
		aplicaPromo(true, empleados, empleadores);
		Cliente resultado = prueba.aplicaPromo(true, empleados, empleadores);
		System.out.println(resultado);
		Assert.assertNotEquals("Deberia retornar el cliente del hashmap",null, resultado);
	}
	


	public Cliente aplicaPromo(boolean promoPorListaDePostulantes, 
			HashMap<String, EmpleadoPretenso> empleados, HashMap<String, Empleador> empleadores)
	{
		Iterator clientes = null;
		int contadorEmpleador = 0;
		int contadorEmpleadoPretenso = 0;
		Cliente clienteBeneficiado = null;

		if (promoPorListaDePostulantes)
		{
			Iterator<Empleador> itEmpleadores = empleadores.values().iterator();
			while (itEmpleadores.hasNext())
			{
				Empleador empleador = itEmpleadores.next();
				if (empleador.getListaDePostulantes() != null)
					contadorEmpleador += empleador.getListaDePostulantes().size();
			}
			Iterator<EmpleadoPretenso> itEmpleados = empleados.values().iterator();
			while (itEmpleados.hasNext())
			{
				EmpleadoPretenso empleadoPretenso = itEmpleados.next();
				if (empleadoPretenso.getListaDePostulantes() != null)
					contadorEmpleadoPretenso += empleadoPretenso.getListaDePostulantes().size();
			}
		} else
		{
			contadorEmpleador = empleadores.size();
			contadorEmpleadoPretenso = empleados.size();
		}

		if (contadorEmpleador > contadorEmpleadoPretenso)
			clientes = empleadores.values().iterator();
		else
			clientes = empleados.values().iterator();

		int puntajeMaximo = Integer.MIN_VALUE;
		while (clientes.hasNext())
		{
			Cliente cl = (Cliente) clientes.next();
			if (cl.getPuntaje() > puntajeMaximo)
			{
				puntajeMaximo = cl.getPuntaje();
				clienteBeneficiado = cl;
			}
		}
		return clienteBeneficiado;
	}

}