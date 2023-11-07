
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

public class testregistroEmpleadoE3 {

    Agencia agencia = Agencia.getInstance();

    @Before
    public void setUp() throws Exception {
        // Preparación del escenario, hay un empleado en el sistema
    	HashMap<String, EmpleadoPretenso> empleadosVacio = new HashMap<>();
    	agencia.setEmpleados(empleadosVacio);;
    	agencia.registroEmpleado("buacho", "123", "Bautista", "Orte", "223543", 23);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1() { // Todos los parámetros correctos, deberia lanzar excepcion
        try {
            EmpleadoPretenso empleadoCreado = (EmpleadoPretenso) agencia.registroEmpleado("buacho", "123", "Bautista", "Orte", "223543", 23);
        } catch (ImposibleCrearEmpleadoException e) {
           
        } catch (NewRegisterException e) {
         
        }
    }
  
}
