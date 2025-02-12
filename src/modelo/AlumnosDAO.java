package modelo;

import java.util.ArrayList;

import objetos.Alumno;
import objetos.Grupo;

public interface AlumnosDAO {
	
	void insertarAlumno(Alumno a1);
	
	void instertarGrupo(Grupo g1);
	
	ArrayList<Alumno> recogerAlumnos();
	
	ArrayList<Grupo> recogerGrupo();
	
	ArrayList<Alumno> recogerAlumnosPK(int nia);
	
	ArrayList<Alumno> cambiarGrupoPK(int nia, String nombre);
	
	

}
