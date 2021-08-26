package com.example.collectionsApp.service.collection;

import com.example.collectionsApp.models.collection.CollectionItem;
import com.example.collectionsApp.models.collection.Item;
import com.example.collectionsApp.helperСlasses.comparators.ComparatorSortByDataItems;
import com.example.collectionsApp.repositories.collection.ItemRepository;
import com.example.collectionsApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    public boolean add(Item item, CollectionItem collection){
        try {
            item.setCollection(collection);
            item.setDate(new Date());
            if(item.getUrl().isEmpty())
                item.setUrl("https://socialvk.ru/wp-content/uploads/avatarka-pustaya-vk_21.jpg");
            itemRepository.save(item);
            return true;
        }catch (Exception e){
            System.out.println("Error: (add item). " + e.getMessage());
            System.out.println("Item: " + item);
            return false;
        }
    }

    public boolean changeItem(Item item){
        try {
            if(findById(item.getId()) != null){
                itemRepository.save(item);
                return true;
            }
            return false;
        }
        catch (Exception e){
            System.out.println("Error: (change item). " + e.getMessage());
            return false;
        }
    }

    public boolean deleteById(Long id){
        if (findById(id) != null) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Item findById(Long id){
        Optional<Item> userOptional = itemRepository.findById(id);
        return userOptional.orElse(null);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public List<Item> findAllByCollectionId(Long id){
        return itemRepository.findByCollection_id(id);
    }

    public List<Item> findAllSortByDate(){
        List<Item> items = itemRepository.findAll();
        Collections.sort(items, new ComparatorSortByDataItems());
        return items;
    }




}
