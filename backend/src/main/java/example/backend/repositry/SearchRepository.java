package example.backend.repositry;


import example.backend.model.Post;

import java.util.List;

public interface SearchRepository {
List<Post> finByText(String text);
}
