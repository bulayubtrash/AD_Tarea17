package ejecutadores;

import controlador.Controlador;
import modelo.AlumnosBD;
import modelo.AlumnosDAO;
import vista.IVista;
import vista.VistaConsola;

public class EjecutadorBD {
	public static void main(String[] args) {
		AlumnosDAO modelo= new AlumnosBD();
		IVista vista = new VistaConsola();
		new Controlador().ejecutar(modelo,vista);
		
	}

}
