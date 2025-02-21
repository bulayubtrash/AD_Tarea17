package ejecutadores;

import controlador.Controlador;
import modelo.AlumnosDAO;
import modelo.AlumnosHibernate;
import vista.IVista;
import vista.VistaConsola;

public class EjecutadorHibernate {
	public static void main(String[] args) {
		AlumnosDAO modelo= new AlumnosHibernate();
		IVista vista = new VistaConsola();
		new Controlador().ejecutar(modelo,vista);
	} 

}
