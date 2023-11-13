package com.ashish.bms.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.bms.Models.Blog;
import com.ashish.bms.Models.Blogger;
import com.ashish.bms.Services.BlogService;
import com.ashish.bms.Services.BloggerService;

import java.sql.Date;
import java.time.LocalDate;
import jakarta.websocket.server.PathParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @Autowired
    BlogService blogService;
    @Autowired
    BloggerService bloggerService;

    @GetMapping("/getAllBlogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/getBlogById/{idString}")
    public Blog getBlogById(@PathVariable String idString) {
        Blog blog = blogService.getBlogById(idString);
        if (blog == null) {
            System.out.println("Blog not found");
            return null;
        }
        return blog;
    }

    @GetMapping("/getBloggerById/{id}")
    public Blogger getBloggerById(@PathVariable String idString) {
        Blogger blogger = bloggerService.getBloggerById(idString);
        if (blogger == null) {
            System.out.println("Blogger not Found");
            return null;
        }
        return blogger;
    }

    @GetMapping("/getBlogsByBloggerId/{bloggerIdString}")
    public List<Blog> getBlogsByBloggerId(@PathVariable String bloggerIdString) {
        List<Blog> blogs = blogService.getBlogsByBloggerId(bloggerIdString);
        if (blogs == null) {
            System.out.println("Blogs not found");
            return null;
        }
        return blogs;
    }

    @PostMapping("/addBlog")
    public Blog addBlog(@RequestParam("title") String title, @RequestParam("content") String content,
            @RequestParam("email") String email) {
        Blogger blogger = bloggerService.getBloggerByEmail(email);
        if (blogger == null) {
            System.out.println("Blogger not found");
            return null;
        }
        System.out.println("Blogger found for adding blog");
        Blog blog = new Blog();
        blog.setIdString(generateIdString());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setAuthor(blogger);
        blog.setLikes(0);
        blogService.addBlog(blog);
        return blog;
    }

    private String generateIdString() {
        String idString = "";
        LocalDate date = LocalDate.now();
        idString += date.toString();
        idString += "-";
        idString += System.currentTimeMillis();
        return idString;
    }

    @PostMapping("/updateBlog/{idString}")
    public Blog updateBlog(@PathVariable String idString, @RequestParam("title") String title,
            @RequestParam("content") String content) {
        Blog blog = blogService.getBlogById(idString);
        if (blog == null) {
            System.out.println("Blog not found");
            return null;
        }
        blog.setTitle(title);
        blog.setContent(content);
        blogService.updateBlog(blog);
        return blog;
    }

    @PostMapping("/deleteBlog/{idString}")
    public void deleteBlog(@PathVariable String idString) {
        
        Blog blog = blogService.getBlogById(idString);
        if (blog == null) {
            System.out.println("Blog not found");
            return;
        }
        blogService.deleteBlog(idString);
        System.out.println("Blog deleted");
    }

    @PostMapping("/addBlogger")
    public Blogger addBlogger(@RequestParam("name") String name, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("role") String role) {
                
                LocalDate localDate = LocalDate.now();
                Date date = java.sql.Date.valueOf(localDate);
        
                Blogger blogger = new Blogger();
        blogger.setIdString(generateIdForBlogger());
        blogger.setName(name);
        blogger.setEmail(email);
        blogger.setPassword(password);
        blogger.setRole(role);
        blogger.setCreated_at(date.toString());
        bloggerService.addBlogger(blogger);
        return blogger;
    }

    private String generateIdForBlogger() {
        String idString = "";
        UUID uuid = UUID.randomUUID();
        idString += uuid.toString();
        return idString;
    }

    @PostMapping("/updateBlogger")
    public Blogger updateBlogger(@RequestParam("id") String id, @RequestParam("name") String name,
            @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role) {
        Blogger blogger = bloggerService.getBloggerById(id);
        if (blogger == null) {
            System.out.println("Blogger not found");
            return null;
        }
        blogger.setName(name);
        blogger.setEmail(email);
        blogger.setPassword(password);
        blogger.setRole(role);
        bloggerService.updateBlogger(blogger);
        System.out.println("Blogger updated");
        return blogger;
    }

    @PostMapping("/deleteBlogger")
    public void deleteBlogger(@RequestParam("id") String id) {
        System.out.println(id);
        Blogger blogger = bloggerService.getBloggerById(id);
        if (blogger == null) {
            System.out.println("Blogger not found");
            return;
        }
        bloggerService.deleteBlogger(id);
    }

    @PostMapping("/likeBlog/{idString}/{like}")
    public Blog likeBlog(@PathVariable String idString , @PathVariable int like) {
        Blog blog = blogService.getBlogById(idString);
        if (blog == null) {
            System.out.println("Blog not found");
            return null;
        }
        blog.setLikes(blog.getLikes() + like);
        blogService.updateBlog(blog);
        return blog;
    }

    @PostMapping("/getBloggerByEmail/{email}")
    public Blogger getBloggerByEmail(@PathVariable String email) {
        Blogger blogger = bloggerService.getBloggerByEmail(email);
        if (blogger == null) {
            System.out.println("Blogger not found");
            return null;
        }
        System.out.println("Blogger found");
        return blogger;
    }
}
