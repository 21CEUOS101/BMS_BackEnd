package com.ashish.bms.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "blog")
@Data    
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    private ObjectId id;

    private String idString;

    private String title;

    private String content;

    @DocumentReference
    @JsonIgnore
    private Blogger author;

    private Integer likes;
    
}
