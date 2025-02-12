package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AlumnosBD;
import modelo.AlumnosDAO;
import vista.IVista;
import vista.VistaConsola;

public class Controlador {

	AlumnosDAO modelo;
	IVista vista;
	
	static Logger logger= LogManager.getLogger(Controlador.class);

	public void ejecutar(AlumnosDAO modelo, IVista vista) {
		vista = new VistaConsola();

		int opcion;
		do {
			vista.menu();
			
			opcion = vista.pedirInt();

			
			switch (opcion) {
			case 1:
				mostrarAlumnosPorGrupo();
				break;
			case 2:
				mostrarAlumnosPorPK();
				break;
			case 3:
				cambiarGrupo();
				break;
			case 4:
				guardarGrupo();
				break;
			case 0:

				break;

			default:
				logger.error("Opcion incorrecta");
				break;
			}
		} while (opcion != 0);

	}

	public void mostrarAlumnosPorGrupo() {
		modelo = new AlumnosBD();
		vista = new VistaConsola();

	}
	
	public void mostrarAlumnosPorPK() {
		
	}
	
	public void cambiarGrupo() {
		
	}
	
	public void guardarGrupo() {
		
	}
	
	
	
	

}
