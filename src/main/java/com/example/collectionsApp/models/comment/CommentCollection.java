package com.example.collectionsApp.models.comment;

import com.example.collectionsApp.models.collection.CollectionItem;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments_collection")
public class CommentCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private Date date;

    @ManyToOne
    @JoinColumn
    private CollectionItem collection;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CollectionItem getCollection() {
        return collection;
    }

    public void setCollection(CollectionItem collection) {
        this.collection = collection;
    }
}
