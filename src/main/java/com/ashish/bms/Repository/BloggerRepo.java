package com.ashish.bms.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ashish.bms.Models.Blogger;

@Repository
public interface BloggerRepo extends MongoRepository<Blogger, ObjectId>{
    public Optional<Blogger> findByEmail(String email);

    public Optional<Blogger> findByIdString(String idString);
    public Optional<Blogger> deleteByIdString(String idString);
}
