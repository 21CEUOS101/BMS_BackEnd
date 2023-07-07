package com.ashish.bms.Models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "blogger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogger {

    @Id
    private ObjectId id;
    private String idString;
    private String name;
    private String email;
    private String password;
    private String role;
    private String created_at;

    @DocumentReference
    private List<Blog> blogs;
}
