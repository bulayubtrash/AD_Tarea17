package modelo;

import java.sql.*;
import java.util.ArrayList;
import pool.DBCPDataSourceOracle;
import objetos.Alumno;
import objetos.Grupo;

public class AlumnosOracle implements AlumnosDAO {
    
    @Override
    public void insertarAlumno(Alumno a1) {
        String sql = "INSERT INTO ALUMNOS (nia, nombre, apellidos, genero, fecha_nacimiento, ciclo, curso, grupo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBCPDataSourceOracle.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, a1.getNia());
            stmt.setString(2, a1.getNombre());
            stmt.setString(3, a1.getApellidos());
            stmt.setString(4, String.valueOf(a1.getGenero()));
            stmt.setDate(5, new java.sql.Date(a1.getFechaNac().getTime()));
            stmt.setString(6, a1.getCiclo());
            stmt.setString(7, a1.getCurso());
            stmt.setString(8, a1.getGrupo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void instertarGrupo(Grupo g1) {
        String sql = "INSERT INTO Grupos (id, grupo) VALUES (?, ?)";
        try (Connection conn = DBCPDataSourceOracle.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, g1.getId());
            stmt.setString(2, g1.getGrupo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Alumno> recogerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM ALUMNOS";
        try (Connection conn = DBCPDataSourceOracle.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alumnos.add(new Alumno(rs.getInt("nia"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("genero").charAt(0), rs.getDate("fecha_nacimiento"), rs.getString("ciclo"), rs.getString("curso"), rs.getString("grupo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public ArrayList<Grupo> recogerGrupo() {
        ArrayList<Grupo> grupo = new ArrayList<>();
        String sql = "SELECT * FROM Grupos";
        try (Connection conn = DBCPDataSourceOracle.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                grupo.add(new Grupo(rs.getInt("id"),rs.getString("grupo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }

    @Override
    public ArrayList<Alumno> recogerAlumnosPK(int nia) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos WHERE nia = ?";
        try (Connection conn = DBCPDataSourceOracle.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nia);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alumnos.add(new Alumno(rs.getInt("nia"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("genero").charAt(0), rs.getDate("fecha_nacimiento"), rs.getString("ciclo"), rs.getString("curso"), rs.getString("grupo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void cambiarGrupoPK(int nia, String nombre) {
        String sql = "UPDATE alumnos SET grupo = ? WHERE nia = ?";
        try (Connection conn = DBCPDataSourceOracle.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, nia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
