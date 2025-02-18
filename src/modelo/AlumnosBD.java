package modelo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objetos.Alumno;
import objetos.Grupo;
import pool.DBCPDataSource;

public class AlumnosBD implements AlumnosDAO {

	static Logger logger = LogManager.getLogger(AlumnosBD.class);

	@Override
	public void insertarAlumno(Alumno a1) {

		String sql = "INSERT INTO alumnos (nia, nombre, apellido, genero, fechaNac, ciclo, curso, id_grupo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBCPDataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, a1.getNia());
			ps.setString(2, a1.getApellidos());
			ps.setString(3, String.valueOf(a1.getGenero()));

			java.sql.Date fechaSql = new java.sql.Date(a1.getFechaNac().getTime());
			ps.setDate(5, fechaSql);

			ps.setString(6, a1.getCiclo());
			ps.setString(7, a1.getCurso());
			ps.setString(8, a1.getGrupo());

			int filas = ps.executeUpdate();
			if (filas > 0) {
				logger.info("Alumno Insertado");
			} else {
				logger.warn("Alumno no insertado");
			}

		} catch (Exception e) {
			logger.error("Error al instertar alumno");
		}

	}

	@Override
	public void instertarGrupo(Grupo g1) {
		String sql = "INSERT INTO grupos (id_grupo, nombre) VALUES (?, ?)";

		try (Connection conn = DBCPDataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, g1.getId());
			ps.setString(2, g1.getGrupo());

			int filas = ps.executeUpdate();
			if (filas > 0) {
				logger.info("Grupo insertado");
			} else {
				logger.warn("Grupo no insertado");
			}

		} catch (Exception e) {
			logger.error("error grupo no insertado");
		}
	}

	@Override
	public ArrayList<Alumno> recogerAlumnos() {
		ArrayList<Alumno> aLista = new ArrayList<>();
		String sql = "SELECT nia, nombre, apellidos, genero, ciclo, curso, grupo FROM alumno";

		try (Connection conn = DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql);) {

			while (rs.next()) {
				Alumno a1 = new Alumno();

				a1.setNia(rs.getInt("nia"));
				a1.setNombre(rs.getString("nombre"));
				a1.setApellidos(rs.getString("apellidos"));
				a1.setGenero(rs.getString("genero").charAt(0));
				a1.setCiclo(rs.getString("ciclo"));
				a1.setCurso(rs.getString("curso"));
				a1.setGrupo(rs.getString("grupo"));

				aLista.add(a1);

			}
		} catch (Exception e) {
			logger.error("Error al recoger alumno");
		}
		return aLista;
	}

	@Override
	public ArrayList<Grupo> recogerGrupo() {
		Grupo g1;
		ArrayList<Grupo> gLista = new ArrayList<>();

		String sql = "SELECT id_grupo, nombre FROM grupos";

		try (Connection conn = DBCPDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				g1 = new Grupo();

				g1.setId(rs.getInt("id_grupo"));
				g1.setGrupo(rs.getString("nombre"));
				gLista.add(g1);
			}
			for (Grupo grupo : gLista) {
				System.out.println(grupo);
			}
		} catch (Exception e) {
			logger.error("Error al recoger el grupo");
		}
		return gLista;

	}

	@Override
	public ArrayList<Alumno> recogerAlumnosPK(int nia) {
		ArrayList<Alumno> aLista = new ArrayList<>();
		String sql = "SELECT nia, nombre, apellidos, genero, ciclo, curso, grupo" + "FROM alumno" + "WHERE nia = ?";

		try (Connection conn = DBCPDataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			// Establecer el parámetro del grupo
			ps.setInt(1, nia);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Alumno a1 = new Alumno();

					a1.setNia(rs.getInt("nia"));
					a1.setNombre(rs.getString("nombre"));
					a1.setApellidos(rs.getString("apellidos"));
					a1.setGenero(rs.getString("genero").charAt(0));
					a1.setCiclo(rs.getString("ciclo"));
					a1.setCurso(rs.getString("curso"));
					a1.setGrupo(rs.getString("grupo"));

					aLista.add(a1);
				}
			}
		} catch (Exception e) {
			System.err.println("Error al obtener los alumnos: " + e.getMessage());
		}
		return aLista;
	}

	@Override
	public void cambiarGrupoPK(int nia, String nombre) {
		String sql = "UPDATE alumnos SET grupo = ? WHERE nia = ?";
		try (Connection conn = DBCPDataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, nombre);
			ps.setInt(2, nia);

			int filas = ps.executeUpdate();
			if (filas > 0) {
				System.out.println("Alumno modificado");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
