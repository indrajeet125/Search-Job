package example.backend.Controller;


import example.backend.model.Post;
import example.backend.repositry.PostRepository;
import example.backend.repositry.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostContoller {
    @Autowired
    PostRepository repo;
    @Autowired
    SearchRepository sRepo;

    @GetMapping("/post")
    public List<Post> getAllPost() {
        return repo.findAll();
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post) {
        System.out.println(post);
        return repo.save(post);
    }

    @GetMapping("/post/{text}")
    public List<Post> search(@PathVariable String text) {
        return sRepo.finByText(text);
    }
}
