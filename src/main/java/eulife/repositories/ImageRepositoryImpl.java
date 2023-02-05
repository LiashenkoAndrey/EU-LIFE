package eulife.repositories;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import eulife.domain.Image;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageRepositoryImpl implements ImageRepository {

    private final Logger log = LogManager.getLogger(ImageRepositoryImpl.class);
    private final MongoDatabase mongoDatabase;



    public ImageRepositoryImpl(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public String saveImage(Image image) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("newCollection");
        log.info("Inserting: " + image);
        Document document = new Document();
        document.append("filename", image.getFilename());
        document.append("binaryImage", image.getBinary_image());
        collection.insertOne(document);
        ObjectId id = (ObjectId) collection.find(document).iterator().tryNext().get("_id");
        log.info("Saving image was successful!");
        return id.toString();
    }

    @Override
    public byte[] getImageById(String id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("newCollection");
        log.info("Getting image: " + id);
        FindIterable<Document> iterable = collection.find(new Document("_id", new ObjectId(id)));
        Document document = Optional.ofNullable(iterable.iterator().tryNext()).orElseThrow(EntityNotFoundException::new);
        Binary binary = (Binary) document.get("binaryImage");
        return binary.getData();
    }
}
