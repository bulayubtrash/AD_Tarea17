package vista;

import java.util.ArrayList;

import objetos.Alumno;
import objetos.Grupo;

public class VistaConsola implements IVista {

	@Override
	public void menu() {
       	System.out.println(
    		    "Menu\n" +
    		    "1. Insertar alumno\n" +
    		    "2. Insertar grupo\n" +
    		    "3. Mostrar todos los alumnos por grupo\n" +
    		    "4. Mostrar todos los alumnos por PK\n" +
    		    "5. Modificar el grupo del alumno\n" +
    		    "6. Guardar el grupo elegido\n" +
    		    "0. Salir\n" +
    		    "Elige una opción: "
    		);		
	}

	@Override
	public Alumno pedirAlumno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grupo pedirGrupo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pedirInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String pedirString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarAlumnos(ArrayList<Alumno> aLista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarGrupos(ArrayList<Grupo> aLista) {
		// TODO Auto-generated method stub
		
	}

}
