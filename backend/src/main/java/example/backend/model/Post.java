package example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "jobpost")
public class Post {
    private  String
            profile,
            desc, techs[];
    private  int exp;
}
