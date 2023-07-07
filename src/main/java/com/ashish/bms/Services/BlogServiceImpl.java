package com.ashish.bms.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.bms.Models.Blog;
import com.ashish.bms.Models.Blogger;
import com.ashish.bms.Repository.BlogRepo;
import com.ashish.bms.Repository.BloggerRepo;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepo blogRepo;
    @Autowired
    BloggerRepo bloggerRepo;
    @Autowired
    BloggerService bloggerService;
    
    @Override
    public List<Blog> getAllBlogs() {
        
        return blogRepo.findAll();
    }

    @Override
    public Blog getBlogById(String id) {
        System.out.println(id);
        return blogRepo.findByIdString(id).orElse(null);
    }

    @Override
    public List<Blog> getBlogsByBloggerId(String bloggerId) {
        
        Blogger author = bloggerRepo.findByIdString(bloggerId).orElse(null);
        return author.getBlogs();
    }

    @Override
    public Blog addBlog(Blog blog) {
        
        blogRepo.save(blog);
        bloggerService.addBlogtoBlogger(blog.getAuthor().getIdString(),blog.getIdString());
        return blogRepo.findById(blog.getId()).orElse(null);
        
    }

    @Override
    public Blog updateBlog(Blog blog) {
        
        blogRepo.save(blog);
        return blogRepo.findById(blog.getId()).orElse(null);
    }

    @Override
    public void deleteBlog(String idString) {
            blogRepo.deleteByIdString(idString);
    }
}
