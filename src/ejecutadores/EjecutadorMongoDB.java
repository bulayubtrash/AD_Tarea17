package ejecutadores;

import controlador.Controlador;
import modelo.AlumnosDAO;
import modelo.AlumnosMongoDB;
import vista.IVista;
import vista.VistaConsola;

public class EjecutadorMongoDB {
	public static void main(String[] args) {
		AlumnosDAO modelo= new AlumnosMongoDB();
		IVista vista = new VistaConsola();
		new Controlador().ejecutar(modelo,vista);
	} 

}
