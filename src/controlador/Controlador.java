package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ficheros.FicheroXML;
import ficheros.FicherosPadre;
import modelo.AlumnosDAO;
import vista.IVista;

public class Controlador {

	AlumnosDAO modelo;
	IVista vista;
	
	static Logger logger= LogManager.getLogger(Controlador.class);
	FicherosPadre fichero;

	public void ejecutar(AlumnosDAO modelo, IVista vista) {

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
		vista.mostrarAlumnos(modelo.recogerAlumnos());

	}
	
	public void mostrarAlumnosPorPK() {
		vista.mostrarAlumnos(modelo.recogerAlumnosPK(vista.pedirInt()));
		
	}
	
	public void cambiarGrupo() {
		modelo.cambiarGrupoPK(vista.pedirInt(), vista.pedirString());
		vista.mostrarAlumnos(modelo.recogerAlumnos());
		
	}
	
	public void guardarGrupo() {
		fichero= new FicheroXML();
		fichero.GrupoToFichero(vista.pedirString(), modelo.recogerGrupo());
		
	}
	
	
	
	

}
