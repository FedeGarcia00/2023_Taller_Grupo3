package testModeloNegocio;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;
import java.util.HashMap;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;

public class TestAgenciaSinClientes {

  Agencia agencia;

  @Before
  public void setUp() throws Exception {
    agencia = Agencia.getInstance();
    HashMap<String, EmpleadoPretenso> empleados = new HashMap<>();
    HashMap<String, Empleador> empleadores = new HashMap<>();
    agencia.setEmpleados(empleados);
    agencia.setEmpleadores(empleadores);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testlogin1() {
    try {
      agencia.login("admin", "admin");
    } catch (ContraException | NombreUsuarioException e) {
      Assert.fail("No deberia entrar aqui, usuario y contrasena correctos");
    }
    Assert.assertEquals("El tipo de usuario deberia ser 2",2,agencia.getTipoUsuario());
  }

  @Test
  public void testlogin2() {
    try {
      agencia.login("admin", "123");
      Assert.fail("Deberia haber lanzado excepcion ContraException");
    } catch (ContraException e) {
      // deberia entrar aqui
    } catch (NombreUsuarioException e) {
      Assert.fail("Deberia haber lanzado excepcion ContraException");
    }
  }

  @Test
  public void testlogin3() {
    try {
      agencia.login("santi", "123");
      Assert.fail("Deberia haber lanzado excepcion NombreUsuarioException");
    } catch (ContraException e) {
      Assert.fail("Deberia haber lanzado excepcion NombreUsuarioException");
    } catch (NombreUsuarioException e) {
      //deberia entrar aqui
    }
  }
  
  @Test
  public void testcerrarSesion() {
    try {
      agencia.login("admin", "admin");
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
    Assert.assertEquals("No coincide el username", "pepito", clienteCreado.getUsserName());
    Assert.assertEquals("No coincide la password","789", clienteCreado.getPassword());
    Assert.assertEquals("No coincide el realname","Jose", clienteCreado.getRealName());
    Assert.assertEquals("No coincide el numero de telefono","+5492234562170", clienteCreado.getTelefono());
    Assert.assertEquals("No coincide la edad",45, clienteCreado.getEdad());
  }

  @Test
  public void testregistroEmpleado2() {
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
  public void testregistroEmpleado3() {
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
  }

  @Test
  public void testregistroEmpleado4() {
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
  }

  @Test
  public void testregistroEmpleado5() {
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
  }

  @Test
  public void testregistroEmpleado6() {
    try {
      agencia.registroEmpleado("pepito", "789", "Jose", "Perez", null, 45);
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (NewRegisterException e) {
      Assert.fail("Deberia haber tirado ImposibleCrearEmpleadoException");
    } catch (ImposibleCrearEmpleadoException e) {
      // deberia entrar aqui
    }
  }

  @Test
  public void testregistroEmpleador1() {
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
      e.printStackTrace();
    }
    Assert.assertEquals("No coincide el username","pepito", clienteCreado.getUsserName());
    Assert.assertEquals("No coincide la password","789", clienteCreado.getPassword());
    Assert.assertEquals("No coincide el realname","Jose", clienteCreado.getRealName());
    Assert.assertEquals("No coincide el telefono", "+5492234562170", clienteCreado.getTelefono());
    Assert.assertEquals("No coincide el tipo de persona", Constantes.FISICA, clienteCreado.getTipoPersona());
    Assert.assertEquals("No coincide el rubro",Constantes.SALUD, clienteCreado.getRubro());
  }

  @Test
  public void testregistroEmpleador2() {
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
  public void testregistroEmpleador3() {
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
  public void testregistroEmpleador4() {
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
  public void testregistroEmpleador5() {
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
  public void testregistroEmpleador6() {
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
  public void testregistroEmpleador7() {
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
  public void testregistroEmpleador8() {
    try {
      agencia.registroEmpleador(
        "pepito",
        "789",
        "Jose",
        "+5492234562170",
        Constantes.FISICA,
        "cualquierCosa"
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
