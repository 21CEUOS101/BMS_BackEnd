package com.ashish.bms.Services;
import org.springframework.stereotype.Service;

import com.ashish.bms.Models.Blogger;

@Service
public interface BloggerService {
    
    public Blogger addBlogger(Blogger blogger);
    
    public Blogger getBloggerById(String idString);

    public Blogger getBloggerByEmail(String email);

    public Blogger updateBlogger(Blogger blogger);

    public void deleteBlogger(String idString);

    public Blogger loginBlogger(String email, String password);

    public Blogger addBlogtoBlogger(String idString , String blogIdString);
}
