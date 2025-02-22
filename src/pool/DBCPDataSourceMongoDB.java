package pool;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DBCPDataSourceMongoDB {
    private static MongoClient mongoClient;
    
    static {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a MongoDB", e);
        }
    }
    
    public static MongoClient getConnection() {
        return mongoClient;
    }
}