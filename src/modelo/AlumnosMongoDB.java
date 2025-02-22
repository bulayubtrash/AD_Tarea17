package modelo;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import objetos.Alumno;
import objetos.Grupo;
import org.bson.Document;
import pool.DBCPDataSourceMongoDB;
import java.util.ArrayList;

public class AlumnosMongoDB implements AlumnosDAO {
    private MongoDatabase database;
    private MongoCollection<Document> alumnosCollection;
    private MongoCollection<Document> gruposCollection;

    public AlumnosMongoDB() {
        MongoClient mongoClient = DBCPDataSourceMongoDB.getConnection();
        this.database = mongoClient.getDatabase("instituto");
        this.alumnosCollection = database.getCollection("alumnos");
        this.gruposCollection = database.getCollection("grupos");
    }

    @Override
    public void insertarAlumno(Alumno a1) {
        Document doc = new Document("nia", a1.getNia())
                .append("nombre", a1.getNombre())
                .append("apellidos", a1.getApellidos())
                .append("genero", String.valueOf(a1.getGenero()))
                .append("fechaNac", a1.getFechaNac().toString())
                .append("ciclo", a1.getCiclo())
                .append("curso", a1.getCurso())
                .append("grupo", a1.getGrupo());
        alumnosCollection.insertOne(doc);
    }

    @Override
    public void instertarGrupo(Grupo g1) {
        Document doc = new Document("id", g1.getId())
                .append("grupo", g1.getGrupo());
        gruposCollection.insertOne(doc);
    }

    @Override
    public ArrayList<Alumno> recogerAlumnos() {
        ArrayList<Alumno> lista = new ArrayList<>();
        for (Document doc : alumnosCollection.find()) {
            lista.add(new Alumno(doc.getInteger("nia"), doc.getString("nombre"),
                    doc.getString("apellidos"), doc.getString("genero").charAt(0),
                    null, doc.getString("ciclo"), doc.getString("curso"),
                    doc.getString("grupo")));
        }
        return lista;
    }

    @Override
    public ArrayList<Grupo> recogerGrupo() {
        ArrayList<Grupo> lista = new ArrayList<>();
        for (Document doc : gruposCollection.find()) {
            lista.add(new Grupo(doc.getInteger("id"), doc.getString("grupo")));
        }
        return lista;
    }

    @Override
    public ArrayList<Alumno> recogerAlumnosPK(int nia) {
        ArrayList<Alumno> lista = new ArrayList<>();
        for (Document doc : alumnosCollection.find(Filters.eq("nia", nia))) {
            lista.add(new Alumno(doc.getInteger("nia"), doc.getString("nombre"),
                    doc.getString("apellidos"), doc.getString("genero").charAt(0),
                    null, doc.getString("ciclo"), doc.getString("curso"),
                    doc.getString("grupo")));
        }
        return lista;
    }

    @Override
    public void cambiarGrupoPK(int nia, String nombre) {
        alumnosCollection.updateOne(Filters.eq("nia", nia),
                new Document("$set", new Document("grupo", nombre)));
    }
}
