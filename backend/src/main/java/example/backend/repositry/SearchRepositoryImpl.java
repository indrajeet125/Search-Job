package example.backend.repositry;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import example.backend.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> finByText(String text) {

        final List<Post> posts = new ArrayList<>();
        MongoDatabase database = client.getDatabase("jobdb");
        MongoCollection<Document> collection = database.getCollection("jobpost");
        AggregateIterable<Document> result = collection
                .aggregate(Arrays.asList(
                        new Document("$search",
                        new Document("text",
                        new Document("query", text)
                        .append("path",
                        Arrays.asList("desc", "profile", "techs")))),
                        new Document("$limit", 5L),
                        new Document("$sort",
                        new Document("exp", 1L))));
result.forEach(doc -> posts.add(converter.read(Post.class, doc)));


        return posts;
    }
}
