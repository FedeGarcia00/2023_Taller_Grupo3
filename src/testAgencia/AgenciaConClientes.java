package testAgencia;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;
import java.util.HashMap;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

public class AgenciaConClientes {

  Agencia agencia;
  Cliente empleado1;
  Cliente empleado2;

  @Before
  public void setUp() throws Exception {
    //Preparación del escenario, ya existen empleados en el sistema
    agencia = Agencia.getInstance();
    HashMap<String, EmpleadoPretenso> empleados = new HashMap<>();
    HashMap<String, Empleador> empleadores = new HashMap<>();
    agencia.setEmpleados(empleados);
    agencia.setEmpleadores(empleadores);
    agencia.registroEmpleador(
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
        "baucho",
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
  public void tearDown() throws Exception {
    agencia.cerrarSesion();
  }

  //
  //	@Test
  //	public void testaplicarPromo1() {
  //		fail("Not yet implemented");
  //	}
  //	@Test
  //	public void testaplicarPromo2() {
  //		fail("Not yet implemented");
  //	}
  //
  //	@Test
  //	public void  testcalculaPremiosCastigosAsignaciones() {
  //		fail("Not yet implemented");
  //	}

  @Test
  public void testlogin1() {
    //NO ANDA
    try {
      agencia.login("baucho", "123");
    } catch (ContraException | NombreUsuarioException e) {
      //sacar multicatch y ver que excepcion tira
      Assert.fail("No deberia entrar aqui, usuario y contrasena correctos");
    }
    Assert.assertEquals(
      "El tipo de usuario deberia ser 0",
      0,
      agencia.getTipoUsuario()
    );
  }

  @Test
  public void testlogin2() {
    //ANDA
    try {
      agencia.login("santi", "456");
    } catch (ContraException | NombreUsuarioException e) {
      Assert.fail("No deberia entrar aqui, usuario y contrasena correctos");
    }
    Assert.assertEquals(
      "El tipo de usuario deberia ser 1",
      1,
      agencia.getTipoUsuario()
    );
  }

  @Test
  public void testlogin3() {
    //ANDA
    try {
      agencia.login("admin", "admin");
    } catch (ContraException | NombreUsuarioException e) {
      Assert.fail("No deberia entrar aqui, usuario y contrasena correctos");
    }
    Assert.assertEquals(
      "El tipo de usuario deberia ser 2",
      2,
      agencia.getTipoUsuario()
    );
  }

  @Test
  public void testlogin4() {
    //NO ANDA
    try {
      agencia.login("baucho", "789");
      Assert.fail("Deberia haber tirado excepcion ContraException");
    } catch (ContraException e) {
      // deberia entrar aqui, contrasena incorrecta;
    } catch (NombreUsuarioException e) {
      Assert.fail("No deberia entrar aqui, usuario correctos");
    }
  }

  @Test
  public void testlogin5() {
    //ANDA PERO SI PONGO EL ASSERT.FAIL AL FINAL NO ANDA
    try {
      agencia.login("mati", "789");
      Assert.fail("Deberia haber tirado excepcion NombreUsuarioException");
    } catch (ContraException e) {
      Assert.fail(
        "No deberia entrar aqui, deberia entrar en excepcion NombreUsuarioException "
      );
    } catch (NombreUsuarioException e) {
      // deberia entrar aqui, no existe un usuario con ese username;
    }
  }

  @Test
  public void testcerrarSesion() {
    // ANDA
    // para testear el cierre de sesion hacemos un login, que ya tiene su propio test
    try {
      agencia.login("fede", "345");
    } catch (ContraException | NombreUsuarioException e) {
      // no deberia entrar aca, testeado en su propio metodo
    }
    agencia.cerrarSesion();
    Assert.assertEquals(
      "El tipo de usuario al cerrar sesion deberia ser -1",
      -1,
      agencia.getTipoUsuario()
    );
  }

  @Test
  public void testregistroEmpleado1() {
    //NO ANDA
    EmpleadoPretenso clienteCreado = null;
    try {
      clienteCreado =
        (EmpleadoPretenso) agencia.registroEmpleado(
          "pepito",
          "789",
          "Jose",
          "Perez",
          "+5492234562170",
          45
        );
    } catch (NewRegisterException | ImposibleCrearEmpleadoException e) {
      Assert.fail("No deberia entrar aqui");
    }
    Assert.assertEquals("pepito", clienteCreado.getUsserName());
    Assert.assertEquals("789", clienteCreado.getPassword());
    Assert.assertEquals("Jose", clienteCreado.getRealName());
    Assert.assertEquals("+5492234562170", clienteCreado.getTelefono());
    Assert.assertEquals(45, clienteCreado.getEdad());
  }

  @Test
  public void testregistroEmpleado2() {
    //ANDA PERO SI PONGO EL ASSERT.FAIL AL FINAL NO ANDA
    try {
      agencia.registroEmpleado(
        "fede",
        "345",
        "Federico",
        "Garcia",
        "22321",
        23
      );
      Assert.fail("Deberia haber tirado NewRegisterException");
    } catch (NewRegisterException e) {
      //deberia entrar aqui
    } catch (ImposibleCrearEmpleadoException e) {
      // no deberia entrar aqui
      Assert.fail("Deberia haber tirado NewRegisterException");
    }
  }

  @Test
  public void testregistroEmpleado3() {
    //ANDA
    try {
      agencia.registroEmpleado(
        null,
        "789",
        "Jose",
        "Perez",
        "+5492234562170",
        45
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (ImposibleCrearEmpleadoException e) {
      // deberia entrar aqui

    }
  }

  @Test
  public void testregistroEmpleado4() {
    //ANDA PERO SI PONGO EL ASSERT.FAIL AL FINAL NO ANDA
    try {
      agencia.registroEmpleado(
        "pepito",
        null,
        "Jose",
        "Perez",
        "+5492234562170",
        45
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (ImposibleCrearEmpleadoException e) {
      // deberia entrar aqui

    }
    //Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
  }

  @Test
  public void testregistroEmpleado5() {
    //ANDA PERO SI PONGO EL ASSERT.FAIL AL FINAL NO ANDA
    try {
      agencia.registroEmpleado(
        "pepito",
        "789",
        null,
        "Perez",
        "+5492234562170",
        45
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (ImposibleCrearEmpleadoException e) {
      // deberia entrar aqui

    }
    //Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
  }

  @Test
  public void testregistroEmpleado6() {
    //ANDA PERO SI PONGO EL ASSERT.FAIL AL FINAL NO ANDA
    try {
      agencia.registroEmpleado(
        "pepito",
        "789",
        "Jose",
        null,
        "+5492234562170",
        45
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (ImposibleCrearEmpleadoException e) {
      // deberia entrar aqui

    }
    //Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
  }

  @Test
  public void testregistroEmpleado7() {
    //ANDA PERO SI PONGO EL ASSERT.FAIL AL FINAL NO ANDA
    try {
      agencia.registroEmpleado("pepito", "789", "Jose", "Perez", null, 45);
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (ImposibleCrearEmpleadoException e) {
      // deberia entrar aqui

    }
    //Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
  }

  @Test
  public void testregistroEmpleador1() {
    //ANDA
    Empleador clienteCreado = null;
    try {
      clienteCreado =
        (Empleador) agencia.registroEmpleador(
          "pepito",
          "789",
          "Jose",
          "+5492234562170",
          Constantes.FISICA,
          Constantes.SALUD
        );
    } catch (NewRegisterException | ImposibleCrearEmpleadorException e) {
    	Assert.fail("No debería entrar aca");
    }
    Assert.assertEquals("pepito", clienteCreado.getUsserName());
    Assert.assertEquals("789", clienteCreado.getPassword());
    Assert.assertEquals("Jose", clienteCreado.getRealName());
    Assert.assertEquals("+5492234562170", clienteCreado.getTelefono());
    Assert.assertEquals(Constantes.FISICA, clienteCreado.getTipoPersona());
    Assert.assertEquals(Constantes.SALUD, clienteCreado.getRubro());
  }

  @Test
  public void testregistroEmpleador2() {
    // no anda
    System.out.println(agencia.getEmpleadores());
    try {
      agencia.registroEmpleador(
        "santi",
        "456",
        "Santiago",
        "43234",
        Constantes.JURIDICA,
        Constantes.SALUD
      );
      Assert.fail("Deberia haber lanzado NewRegisterException");
    } catch (NewRegisterException e) {
      //deberia entrar aqui
    } catch (ImposibleCrearEmpleadorException e) {
      Assert.fail("Deberia haber lanzado NewRegisterException");
    }
  }

  @Test
  public void testregistroEmpleador3() {
    try {
      agencia.registroEmpleador(
        null,
        "789",
        "Jose",
        "+5492234562170",
        Constantes.FISICA,
        Constantes.SALUD
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (ImposibleCrearEmpleadorException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testregistroEmpleador4() {
    try {
      agencia.registroEmpleador(
        "pepito",
        null,
        "Jose",
        "+5492234562170",
        Constantes.FISICA,
        Constantes.SALUD
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (ImposibleCrearEmpleadorException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testregistroEmpleador5() {
    try {
      agencia.registroEmpleador(
        "pepito",
        "789",
        null,
        "+5492234562170",
        Constantes.FISICA,
        Constantes.SALUD
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (ImposibleCrearEmpleadorException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testregistroEmpleador6() {
    try {
      agencia.registroEmpleador(
        "pepito",
        "789",
        "Jose",
        null,
        Constantes.FISICA,
        Constantes.SALUD
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (ImposibleCrearEmpleadorException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testregistroEmpleador7() {
    try {
      agencia.registroEmpleador(
        "pepito",
        "789",
        "Jose",
        "+5492234562170",
        null,
        Constantes.SALUD
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (ImposibleCrearEmpleadorException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testregistroEmpleador8() {
    try {
      agencia.registroEmpleador(
        "pepito",
        "789",
        "Jose",
        "+5492234562170",
        Constantes.FISICA,
        null
      );
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadorException");
    } catch (ImposibleCrearEmpleadorException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testsetLimitesRemuneracion1() {
    //ANDA
    try {
      agencia.setLimitesRemuneracion(-2000, 5000);
      Assert.fail("Deberia lanzar LimiteInferiorRemuneracionInvalidaException");
    } catch (LimiteSuperiorRemuneracionInvalidaException e) {
      Assert.fail("Deberia lanzar LimiteInferiorRemuneracionInvalidaException");
    } catch (LimiteInferiorRemuneracionInvalidaException e) {
      //deberia entrar aqui
    }
  }

  @Test
  public void testsetLimitesRemuneracion2() {
    // NO ANDA
    try {
      agencia.setLimitesRemuneracion(6000, 2000);
      Assert.fail("Deberia lanzar LimiteSuperiorRemuneracionInvalidaException");
    } catch (LimiteSuperiorRemuneracionInvalidaException e) {
      //deberia entrar aqui
    } catch (LimiteInferiorRemuneracionInvalidaException e) {
      Assert.fail("Deberia lanzar LimiteSuperiorRemuneracionInvalidaException");
    }
  }

  @Test
  public void testsetLimitesRemuneracion3() {
    // ANDA
    try {
      agencia.setLimitesRemuneracion(2000, 6000);
      Assert.assertEquals(
        "No se establecio bien el limite inferior",
        agencia.getLimiteInferior(),
        2000
      );
      Assert.assertEquals(
        "No se establecio bien el limite superior",
        agencia.getLimiteSuperior(),
        6000
      );
    } catch (LimiteSuperiorRemuneracionInvalidaException e) {
      //no deberia lanzar excepcion
    } catch (LimiteInferiorRemuneracionInvalidaException e) {
      //no deberia lanzar excepcion
    }
  }
}
