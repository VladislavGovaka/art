package com.example.collectionsApp.models.comment;


import com.example.collectionsApp.models.collection.Item;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments_item")
public class CommentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private Date date;

    @ManyToOne
    @JoinColumn
    private Item item;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
