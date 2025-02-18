package ficheros;

import java.util.ArrayList;

import objetos.Alumno;
import objetos.Grupo;

public interface FicherosPadre {
	
	void AlumnoToFichero(String ruta, ArrayList<Alumno> object);
	
	ArrayList<Alumno> FicheroToAlumno(String ruta);
	
	void GrupoToFichero(String ruta, ArrayList<Grupo> object);
	
	ArrayList<Grupo> FicheroToGrupo(String ruta);
	
	

}
