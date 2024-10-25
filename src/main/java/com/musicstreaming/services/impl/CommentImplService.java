package com.musicstreaming.services.impl;


import com.musicstreaming.models.Comment;
import com.musicstreaming.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentImplService {

    public CommentRepository commentRepository;


    public List<Comment>getAllComments() throws Exception {

       try{
           List<Comment> comments = commentRepository.findAll();
           return comments;

       }catch(Exception e){
           throw new Exception(e.getMessage());
       }

    }
    public Optional<List<Comment>>getAllCommentsBySong(Long songId) throws Exception {
        try{
            Optional<List<Comment>>getAllCommentsBySong=commentRepository.getCommentListBySongID(songId);
            return getAllCommentsBySong;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
