package com.example.collectionsApp.service.comment;

import com.example.collectionsApp.models.collection.CollectionItem;
import com.example.collectionsApp.models.comment.CommentCollection;
import com.example.collectionsApp.repositories.comment.CommentsCollectionRepository;
import com.example.collectionsApp.service.collection.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsCollectionRepository commentsRepository;

    @Autowired
    CollectionService collectionService;

    public boolean add(CommentCollection comment, Long collectionId){
        try {
            CollectionItem collection = collectionService.findById(collectionId);
            if(collection != null){
                comment.setCollection(collection);
                commentsRepository.save(comment);
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean delete(Long id){
        try {
            commentsRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<CommentCollection> findAllByCollectionId(Long collection_id){
        return commentsRepository.findAllByCollection_Id(collection_id);
    }

}
