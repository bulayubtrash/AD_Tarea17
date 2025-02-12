package vista;

import java.util.ArrayList;

import objetos.Alumno;
import objetos.Grupo;

public interface IVista {
	
	void menu();
	
	Alumno pedirAlumno();
	
	Grupo pedirGrupo();
	
	int pedirInt();
	
	String pedirString();
	
	void mostrarAlumnos(ArrayList<Alumno> aLista);

	void mostrarGrupos(ArrayList<Grupo> aLista);
	


}
