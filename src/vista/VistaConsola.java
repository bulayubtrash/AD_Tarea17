package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import objetos.Alumno;
import objetos.Grupo;

public class VistaConsola implements IVista {
	Scanner sc = new Scanner(System.in);

	@Override
	public void menu() {
		System.out.println(
				"Menu\n" + "1. Insertar alumno\n" + "2. Insertar grupo\n" + "3. Mostrar todos los alumnos por grupo\n"
						+ "4. Mostrar todos los alumnos por PK\n" + "5. Modificar el grupo del alumno\n"
						+ "6. Guardar el grupo elegido\n" + "0. Salir\n" + "Elige una opción: ");
	}

	@Override
	public Alumno pedirAlumno() {
		Alumno a1 = new Alumno();

		System.out.println("Introduzca el nia");
		a1.setNia(sc.nextInt());

		sc.nextLine();

		System.out.println("Introduzca el nombre");
		a1.setNombre(sc.nextLine());

		System.out.println("Introduzca el apellido");
		a1.setApellidos(sc.nextLine());

		System.out.println("Introduzca el genero");
		String genero = sc.nextLine();
		a1.setGenero(genero.charAt(0));

		System.out.println("Introduzca la fecha de nacimiento");
		String fecha = sc.nextLine();
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		try {
			a1.setFechaNac(format.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Introduzca el ciclo");
		a1.setCiclo(sc.nextLine());

		System.out.println("Introduzca el curso");
		a1.setCurso(sc.nextLine());

		return a1;
	}

	@Override
	public Grupo pedirGrupo() {
		Grupo g1 = new Grupo();
		g1 = new Grupo();
		System.out.println("Introduzca el id del grupo");
		g1.setId(sc.nextInt());
		sc.nextLine();
		System.out.println("Introduzca el nombre del grupo");
		g1.setGrupo(sc.nextLine());
		return g1;
	}

	@Override
	public int pedirInt() {
		System.out.println("Introduce Int");
		int numero = sc.nextInt();
		sc.nextLine();
		return numero;
	}

	@Override
	public String pedirString() {
		System.out.println("Introduce String");
		String palabra = sc.nextLine();
		return palabra;
	}

	@Override
	public void mostrarAlumnos(ArrayList<Alumno> aLista) {
		for (Alumno alumno : aLista) {
			System.out.println(alumno);
		}

	}

	@Override
	public void mostrarGrupos(ArrayList<Grupo> aLista) {
		for (Grupo grupo : aLista) {
			System.out.println(grupo);
		}

	}

}
