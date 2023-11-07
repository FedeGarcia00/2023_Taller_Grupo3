package testAgencia;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.NewRegisterException;
import modeloDatos.EmpleadoPretenso;
import modeloNegocio.Agencia;

public class testregistroEmpleadoE1 {

    Agencia agencia = Agencia.getInstance();

    @Before
    public void setUp() throws Exception {
        // Preparación del escenario, ya existen empleados en el sistema
        agencia.registroEmpleado("buacho", "123", "Bautista", "Orte", "223543", 23);
        agencia.registroEmpleado("fede", "345", "Federico", "Garcia", "22321", 23);
    }

    @After
    public void tearDown() throws Exception {
    	HashMap<String, EmpleadoPretenso> empleadosVacio = new HashMap<>();
    	agencia.setEmpleados(empleadosVacio);;
    }

    @Test
    public void test1() { // Todos los parámetros correctos, no debería lanzar excepción
        try {
            EmpleadoPretenso empleadoCreado = (EmpleadoPretenso) agencia.registroEmpleado("santi", "3121", "Santiago", "Carmen", "32131", 24);  
            // Verifica que el empleado se haya creado correctamente
            assertNotNull(empleadoCreado);
        } catch (ImposibleCrearEmpleadoException e) {
            Assert.fail(e.getMessage());
        } catch (NewRegisterException e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void test2() { // Empezamos a darle valores errones de las baterias de pruebas ...
        try {
            EmpleadoPretenso empleadoCreado = (EmpleadoPretenso) agencia.registroEmpleado(null, "3121", "Santiago", "Carmen", "32131", 24);  
            // Verifica que el empleado se haya creado correctamente
            // assertNotNull(empleadoCreado);
        } catch (ImposibleCrearEmpleadoException e) {
            Assert.fail(e.getMessage());
        } catch (NewRegisterException e) {
            Assert.fail(e.getMessage());
        }
    }
    
    
}