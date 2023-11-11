package testModeloDatos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import util.Constantes;

public class TestEmpleador {
	
	Empleador empleador;
	@Before
	public void setUp() throws Exception {
		empleador = new Empleador("Lio", "123", "Lionel", "5492236", Constantes.SALUD, Constantes.FISICA);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmpleador() {
		Assert.assertEquals("El constructor fallo, el username no es el correcto:", "Lio", empleador.getUsserName());
		Assert.assertEquals("El constructor fallo, la password es incorrecta:", "123", empleador.getPassword());
		Assert.assertEquals("El constructor fallo, el nombre real no es el correcto:", "Lionel", empleador.getRealName());
		Assert.assertEquals("El constructor fallo, el numero de telefono no es el correcto:", "5492236", empleador.getTelefono());
		Assert.assertEquals("El constructor fallo, el apellido no es el correcto:",Constantes.SALUD, empleador.getRubro());
		Assert.assertEquals("El constructor fallo, la edad no es la correcta:", Constantes.FISICA, empleador.getTipoPersona());
	}
	
	//Como el constructor no anda, verificamos si es problema de los getters / setters (no podemos saber cual falla)
	
	@Test
	public void testSetUssername() {
		empleador.setUsserName("aprobemos");
		Assert.assertEquals("El setter o getter de ussername fallo", "aprobemos", empleador.getUsserName());		
	}
	
	@Test
	public void testSetPassword() {
		empleador.setPassword("asdfg");
		Assert.assertEquals("El setter o getter de password fallo", "asdfg", empleador.getPassword());	
	}
	
	@Test
	public void testSetRealName() {
		empleador.setRealName("Lautaro");
		Assert.assertEquals("El setter o getter de realName fallo", "Lautaro", empleador.getRealName());	
	}
	
	@Test
	public void testSetTelefono() {
		empleador.setTelefono("123456");
		Assert.assertEquals("El setter o getter de telefono fallo", "123456", empleador.getTelefono());	
	}
	
	@Test
	public void testSetRubro() {
		empleador.setRubro(Constantes.COMERCIO_LOCAL);
		Assert.assertEquals("El setter o getter de rubro fallo",Constantes.COMERCIO_LOCAL, empleador.getRubro());	
	}
	
	@Test
	public void testSetTipoPersona() {
		empleador.setTipoPersona(Constantes.JURIDICA);;
		Assert.assertEquals("El setter o getter de tipoPersona fallo", Constantes.JURIDICA, empleador.getTipoPersona());	
	}

}
