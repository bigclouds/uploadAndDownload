import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import java.net.*;
import java.util.*;
import org.bson.Document;

/**
 * Created by Suwadith on 7/13/2017.
 */
public class ConnectionDemo {

    public static void main(String[] args){


        //Alternative code to make new connection
        /*MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);*/

	//MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
	//MongoCredential credential = MongoCredential.createScramSha1Credential("admin", "test", "admin".toCharArray());  
	MongoCredential credential = MongoCredential.createCredential("admin", "test", "admin".toCharArray());  
	List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	credentials.add(credential);  

	ServerAddress serverAddress = new ServerAddress("127.0.0.1",27017);
	List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
        addrs.add(serverAddress); 
        //Connects to your local mongoDB server
        MongoClient mongoClient = new MongoClient(addrs, credentials);

        //Retrieves the Database named: "TestDB"
        //If it's not there It';ll create one
        MongoDatabase database = mongoClient.getDatabase("test");

        //Creates a new Collection inside the DB that you just created or retrieved
        MongoCollection<Document> collection = database.getCollection("test");

        //Creates a new Document named "doc1"
        //Since mongoDB is schema less you don't have to worry about extra fields or missing fields.
        //Everything will be considered as key, value pairs
        Document doc1 = new Document("Name: ", "Suwadith")
                .append("Age", "21")
                .append("Gender", "Male")
                .append("Address", new Document("No", "221B").append("Street", "Baker").append("City", "London"));

        //Inserts the newly created document on to the Collection that we created
        collection.insertOne((Document)doc1);

        //Stores the first document that's inside the collection to the variable named myDoc
        Document myDoc = (Document) collection.find().first();

        //Prints the document in JSON format
        System.out.println(myDoc.toJson());
	
	//Print all
	FindIterable<Document> findIterable = collection.find();  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        while(mongoCursor.hasNext()){  
            System.out.println((Document)mongoCursor.next());  
        }  
    }
}
