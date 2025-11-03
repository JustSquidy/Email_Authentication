import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class UserEmailService {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public UserEmailService() {
        // Replace with your MongoDB connection string if different
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("USER_DATA");
        this.userCollection = database.getCollection("users");
    }

    // Get all user emails
    public List<String> getAllUserEmails() {
        List<String> emails = new ArrayList<>();
        userCollection.find()
                     .projection(new Document("email", 1).append("_id", 0))
                     .forEach(doc -> {
                         String email = doc.getString("email");
                         if (email != null) {
                             emails.add(email);
                         }
                     });
        return emails;
    }

    // Get emails of unverified users
    public List<String> getUnverifiedUserEmails() {
        List<String> emails = new ArrayList<>();
        userCollection.find(new Document("email_verified", false))
                     .projection(new Document("email", 1).append("_id", 0))
                     .forEach(doc -> {
                         String email = doc.getString("email");
                         if (email != null) {
                             emails.add(email);
                         }
                     });
        return emails;
    }

    // Get a single user's email by username
    public String getUserEmail(String username) {
        Document user = userCollection.find(new Document("username", username))
                                    .first();
        return user != null ? user.getString("email") : null;
    }

    // Close the MongoDB connection when done
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}