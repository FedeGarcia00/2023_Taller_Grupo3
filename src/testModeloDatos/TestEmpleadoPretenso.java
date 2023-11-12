package testModeloDatos;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import modeloDatos.EmpleadoPretenso;

public class TestEmpleadoPretenso {
	EmpleadoPretenso empleado;

	@Before
	public void setUp() throws Exception {
		empleado = new EmpleadoPretenso("Marto", "123", "Martin", "2236", "Gomez", 26);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmpleadoPretenso() {
		Assert.assertEquals("El constructor fallo, el username no es el correcto:", "Marto", empleado.getUsserName());
		Assert.assertEquals("El constructor fallo, la password es incorrecta:", "123", empleado.getPassword());
		Assert.assertEquals("El constructor fallo, el nombre real no es el correcto:", "Martin", empleado.getRealName());
		Assert.assertEquals("El constructor fallo, el numero de telefono no es el correcto:", "2236", empleado.getTelefono());
		Assert.assertEquals("El constructor fallo, el apellido no es el correcto:", "Gomez", empleado.getApellido());
		Assert.assertEquals("El constructor fallo, la edad no es la correcta:", 26, empleado.getEdad());
	}

}
