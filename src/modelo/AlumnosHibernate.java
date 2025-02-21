package modelo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import objetos.Alumno;
import objetos.Grupo;
import util.HibernateUtil;

public class AlumnosHibernate implements AlumnosDAO {

    @Override
    public void insertarAlumno(Alumno a1) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(a1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Alumno> recogerAlumnos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Alumno> alumnos = session.createQuery("FROM Alumno", Alumno.class).list();
            return new ArrayList<>(alumnos);
        }
    }

    @Override
    public ArrayList<Alumno> recogerAlumnosPK(int nia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Alumno alumno = session.get(Alumno.class, nia);
            ArrayList<Alumno> lista = new ArrayList<>();
            if (alumno != null) lista.add(alumno);
            return lista;
        }
    }

    @Override
    public void cambiarGrupoPK(int nia, String nombre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, nia);
            if (alumno != null) {
                alumno.setGrupo(nombre);
                session.merge(alumno);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void instertarGrupo(Grupo g1) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(g1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Grupo> recogerGrupo() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Grupo> grupos = session.createQuery("FROM Grupo", Grupo.class).list();
            return new ArrayList<>(grupos);
        }
    }
}
