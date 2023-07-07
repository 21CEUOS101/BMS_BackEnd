package com.ashish.bms.Repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ashish.bms.Models.Blog;
import com.ashish.bms.Models.Blogger;

@Repository
public interface BlogRepo extends MongoRepository<Blog, ObjectId> {
    public Optional<Blog> findByIdString(String idString);
    public Optional<Blog> deleteByIdString(String idString);
}
