package com.boot.dudar.controllers;

import com.boot.dudar.models.Post;
import com.boot.dudar.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogController {

    private final PostRepository postRepository;

    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd() {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String fullText) {
        Post post = new Post(title, anons, fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) return "404";

        Optional<Post> post = postRepository.findById(id);
        Post res = post.orElse(null);

        assert res != null;
        res.setViews(res.getViews()+1);
        postRepository.save(res);

        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/remove")
    public String removeArticle(@PathVariable(value = "id") long id) {
        if (!postRepository.existsById(id)) return "404";

        postRepository.deleteById(id);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/edit")
    public String editArticle(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) return "404";

        Optional<Post> post = postRepository.findById(id);
        Post data = post.orElse(null);
        model.addAttribute("data", data);

        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editedArticle(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText) {
        if (!postRepository.existsById(id)) return "404";

        Optional<Post> post = postRepository.findById(id);
        Post data = post.orElse(null);

        assert data != null;
        data.setTitle(title);
        data.setAnons(anons);
        data.setFullText(fullText);
        postRepository.save(data);
        
        return "redirect:/blog/" + id;
    }

    @GetMapping("*")
    public String notFound() {
        return "404";
    }
}
