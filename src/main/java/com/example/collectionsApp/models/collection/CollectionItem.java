package com.example.collectionsApp.models.collection;


import com.example.collectionsApp.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "collections")
public class CollectionItem {

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
    private User user;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.REMOVE)
    private List<Item> items;

    public String getFormatDate(){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        return formater.format(getDate());
    }

    public String getFormatTime(){
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        return formater.format(getDate());
    }

    public int sizeItems (){
        return getItems().size();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CollectionItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user.getUsername() +
                ", items size=" + items.size() +
                ", date=" + date +
                '}';
    }
}


