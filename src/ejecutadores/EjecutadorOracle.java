package ejecutadores;

import controlador.Controlador;
import modelo.AlumnosDAO;
import modelo.AlumnosOracle;
import vista.IVista;
import vista.VistaConsola;

public class EjecutadorOracle {
	public static void main(String[] args) {
		AlumnosDAO modelo = new AlumnosOracle();
		IVista vista = new VistaConsola();
		new Controlador().ejecutar(modelo, vista);
	}

}
