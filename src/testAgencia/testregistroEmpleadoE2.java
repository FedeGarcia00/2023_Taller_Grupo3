
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

public class testregistroEmpleadoE2 {

    Agencia agencia = Agencia.getInstance();

    @Before
    public void setUp() throws Exception {
        // Preparación del escenario, no hay empleados en el sistema
    	HashMap<String, EmpleadoPretenso> empleadosVacio = new HashMap<>();
    	agencia.setEmpleados(empleadosVacio);;
    }

    @After
    public void tearDown() throws Exception {
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
            assertNotNull(empleadoCreado);
        } catch (ImposibleCrearEmpleadoException e) {
            Assert.fail(e.getMessage());
        } catch (NewRegisterException e) {
            Assert.fail(e.getMessage());
        }
    }
}
