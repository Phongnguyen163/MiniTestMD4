package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.IPostService;
import service.impl.PostService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    IPostService postService;

    @GetMapping("/posts")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("posts",postService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-post")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("post" , new Post());
        return modelAndView;
    }

    @PostMapping("/create-post")
    public ModelAndView savePost(@ModelAttribute("post") Post post) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timePost = time.format(fmt);
        post.setCreateAt(timePost);
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "Create successfully");
        return modelAndView;
    }

    @PostMapping("/search")
    public String search(@RequestParam String title , RedirectAttributes redirect) {
        if (title != null) {
            Iterable<Post> postList = postService.findAllByTitle(title);
            redirect.addFlashAttribute("postList", postList);
        }
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Post post = postService.findById(id).get();
        modelAndView.addObject("posts", post);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Integer id, Post post) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timePost = time.format(fmt);
        post.setCreateAt(timePost);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }

    @PostMapping("/sort")
    public String sort(RedirectAttributes redirect) {
        Iterable<Post> postList = postService.sortByLikes();
        redirect.addFlashAttribute("postList", postList);
        return "redirect:/posts";
    }

    @PostMapping("/lastest")
    public String lastest(RedirectAttributes redirect) {
        Iterable<Post> postList =postService.findByCreateAt();
        redirect.addFlashAttribute("postList",postList);
        return "redirect:/posts";
    }
}
