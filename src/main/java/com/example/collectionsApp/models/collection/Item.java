package com.example.collectionsApp.models.collection;


import com.example.collectionsApp.models.collection.CollectionItem;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Column(length = 2000)
    private String description;
    private Date date;
    private String url;

    @ManyToOne
    @JoinColumn
    private CollectionItem collection;


    public String getFormatDate(){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        return formater.format(getDate());
    }

    public String getFormatTime(){
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        return formater.format(getDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CollectionItem getCollection() {
        return collection;
    }

    public void setCollection(CollectionItem collection) {
        this.collection = collection;
    }
}
