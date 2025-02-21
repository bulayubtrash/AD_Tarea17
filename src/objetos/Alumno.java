package objetos;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "Empleados")
public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nia;
	
	@Column(length=50, nullable= false)
	private String nombre;
	
	@Column(length=50, nullable= false)
	private String apellidos;
	
    @Column(name = "genero")
	private char genero;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
	private Date fechaNac;
	
	@Column(length=50, nullable= false)
	private String ciclo;
	
	@Column(length=50, nullable= false)
	private String curso;
	
	@Column(length=50, nullable= false)
	private String grupo;

	public Alumno() {
		super();
	}

	public Alumno(int nia, String nombre, String apellidos, char genero, Date fechaNac, String ciclo, String curso,
			String grupo) {
		super();
		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechaNac = fechaNac;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
	}

	public int getNia() {
		return nia;
	}

	public void setNia(int nia) {
		this.nia = nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero
				+ ", fechaNac=" + fechaNac + ", ciclo=" + ciclo + ", curso=" + curso + ", grupo=" + grupo + "]";
	}



}
