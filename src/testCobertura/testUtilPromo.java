package testCobertura;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Iterator;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;

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
	public void test1() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;
		aplicaPromo(false, empleados, empleadores);
	}
	
	@Test
	public void test2() {
		HashMap<String, EmpleadoPretenso> empleados = new HashMap<String, EmpleadoPretenso>();
		HashMap<String, Empleador> empleadores = new HashMap<String, Empleador>() ;
		aplicaPromo(true, empleados, empleadores);
		prueba.aplicaPromo(true, empleados, empleadores);
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