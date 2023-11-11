package testGUI;


import java.awt.Component;

import vista.IOptionPane;

public class FalsoOptionPane implements IOptionPane {
    private String mensaje = null;

    public FalsoOptionPane() {
        super();
    }

//    @Override
//    public void ShowMessage(Component parent, String mensaje) {
//        this.mensaje = mensaje;
//    }

    public String getMensaje() {
        return mensaje;
    }

	@Override
	public void ShowMessage(String arg0) {
		this.mensaje=arg0;
	}
}