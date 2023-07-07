package com.ashish.bms.Services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ashish.bms.Models.Blog;

@Service
public interface BlogService {
    
    public List<Blog> getAllBlogs();
    
    public Blog getBlogById(String idString);

    public Blog addBlog(Blog blog);

    public Blog updateBlog(Blog blog);

    public void deleteBlog(String idString);

    public List<Blog> getBlogsByBloggerId(String bloggerIdString);
}
