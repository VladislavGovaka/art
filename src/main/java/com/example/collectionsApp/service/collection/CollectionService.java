package com.example.collectionsApp.service.collection;

import com.example.collectionsApp.models.collection.CollectionItem;
import com.example.collectionsApp.helperСlasses.comparators.ComparatorCollectionTop;

import com.example.collectionsApp.helperСlasses.comparators.ComparatorCollectionsByAlphabet;
import com.example.collectionsApp.repositories.collection.CollectionRepository;
import com.example.collectionsApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserService userService;

    public boolean add(CollectionItem collection, String nameUser){
        try {
            collection.setUser(userService.findByName(nameUser));
            collection.setDate(new Date());
            if(collection.getUrl() == null)
                collection.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiKeMxWylWa9X7J859YdKx5r6XE1q45o7-jmnZ9p5xhNMRwrk6qICM0FZO8u7JOnR-F3M&usqp=CAU");
            collectionRepository.save(collection);
            return true;
        }
        catch (Exception e){
            System.out.println("Error: (add collection). " + e.getMessage());
            return false;
        }
    }

    public boolean changeCollection(CollectionItem collection){
        try {
            if(findById(collection.getId()) != null){
                collectionRepository.save(collection);
                return true;
            }
            return false;
        }
        catch (Exception e){
            System.out.println("Error: (change collection). " + e.getMessage());
            return false;
        }
    }

    public boolean deleteById(Long id){
        CollectionItem collectionFromDb = findById(id);
        if (collectionFromDb != null) {
            collectionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CollectionItem> findAll() {
        return collectionRepository.findAll();
    }

    public List<CollectionItem> findAllSort() {
        List<CollectionItem> collections = collectionRepository.findAll();
        ComparatorCollectionsByAlphabet comparator = new ComparatorCollectionsByAlphabet();
        Collections.sort(collections, comparator);
        return collections;
    }

    public List<CollectionItem> findTop() {
        List<CollectionItem> collections = collectionRepository.findAll();
        List<CollectionItem> sortCollections = new ArrayList<>();
        Comparator comparator = new ComparatorCollectionTop();

        for (int i = 0; i < collections.size(); i++) {
            if(collections.get(i).sizeItems() != 0) {
                sortCollections.add(collections.get(i));
            }
        }

        Collections.sort(sortCollections, comparator);

        if(sortCollections.size() > 10)
            sortCollections.subList(10, sortCollections.size()).clear();

        return sortCollections;

    }

    public List<CollectionItem> findAllByUserId(long id) {
        return collectionRepository.findByUser_id(id);
    }

    public CollectionItem findById(Long id){
        Optional<CollectionItem> collectionOptional = collectionRepository.findById((long) id);
        return collectionOptional.orElse(null);
    }

    public int sizeAllItems(){
        int size = 0;
        List<CollectionItem> collections = collectionRepository.findAll();
        for(CollectionItem collection : collections){
            size += collection.sizeItems();
        }
        return size;
    }

    public int sizeAllCollections(){
        List<CollectionItem> collections = collectionRepository.findAll();
        return collections.size();
    }


}
