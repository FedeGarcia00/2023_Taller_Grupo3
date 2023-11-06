package escenarios;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.NewRegisterException;
import modeloNegocio.Agencia;


public class SistemaConClientes {
	
	 
	public SistemaConClientes(){
		modeloNegocio.Agencia agencia = bin.modeloNegocio.Agencia.getInstance();
		try {
			agencia.registroEmpleado("fede", "123", "Federico", "Garcia", "223456", 23);
			agencia.registroEmpleado("santi", "121", "Santiago", "Carmenes", "2231256", 23);
			try {
				agencia.registroEmpleador("Baucho", "121", "Bautista", "223131256", "tipoPersona?", "rubro");
			} catch (ImposibleCrearEmpleadorException e) {
				e.printStackTrace();
			}
		} catch (NewRegisterException e) {
			e.printStackTrace();
		} catch (ImposibleCrearEmpleadoException e) {
			e.printStackTrace();
		}
	}
		 
 

}