package com.musicstreaming.services.impl;


import com.musicstreaming.models.Comment;
import com.musicstreaming.repositories.CommentRepository;
import com.musicstreaming.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentImplService implements CommentService {

    public CommentRepository commentRepository;


    public List<Comment>getAllComments() throws Exception {

       try{
           List<Comment> comments = commentRepository.findAll();
           return comments;

       }catch(Exception e){
           throw new Exception(e.getMessage());
       }

    }

    @Override
    public List<Comment> getAllCommentsByUser(Long userId) {
        return null;
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

    @Override
    public Comment updateComment(String newContent, Long commentId) {
        return null;
    }

    @Override
    public Comment deleteComment(Long commentId) {
        return null;
    }

    @Override
    public Comment postComment(String content, Long songId, Long userId) {
        return null;
    }

    @Override
    public Comment replyComment(String content, Long songId, Long userId, Long parentCommentId) {
        return null;
    }

}
