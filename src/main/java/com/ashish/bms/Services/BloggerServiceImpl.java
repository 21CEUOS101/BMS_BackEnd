package com.ashish.bms.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashish.bms.Models.Blogger;
import com.ashish.bms.Repository.BlogRepo;
import com.ashish.bms.Repository.BloggerRepo;

@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    BloggerRepo bloggerRepo;
    @Autowired
    BlogRepo blogRepo;

    @Override
    public Blogger getBloggerById(String idString) {
        return bloggerRepo.findByIdString(idString).orElse(null);
    }

    @Override
    public Blogger getBloggerByEmail(String email) {
        return bloggerRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Blogger addBlogger(Blogger blogger) {
        
        bloggerRepo.save(blogger);
        return bloggerRepo.findById(blogger.getId()).orElse(null);
    }

    @Override
    public Blogger updateBlogger(Blogger blogger) {
        
        bloggerRepo.save(blogger);
        return bloggerRepo.findById(blogger.getId()).orElse(null);
    }

    @Override
    public void deleteBlogger(String idString) {
        Blogger blogger = bloggerRepo.findByIdString(idString).orElse(null);
        blogger.getBlogs().forEach(blog -> {
            blogRepo.deleteById(blog.getId());
        });
        bloggerRepo.deleteByIdString(idString);
    }
    
    @Override
    public Blogger loginBlogger(String email, String password) {

        Blogger blogger = bloggerRepo.findByEmail(email).orElse(null);
        if (blogger != null) {
            if (blogger.getPassword().equals(password)) {
                return blogger;
            }
        }
        return null;
    }
    
    @Override
    public Blogger addBlogtoBlogger(String id, String blogId) {
        
        Blogger blogger = bloggerRepo.findByIdString(id).orElse(null);
        blogger.getBlogs().add(blogRepo.findByIdString(blogId).orElse(null));
        System.out.println(blogger.getBlogs().size());
        bloggerRepo.save(blogger);
        return bloggerRepo.findByIdString(id).orElse(null);
    }

}

