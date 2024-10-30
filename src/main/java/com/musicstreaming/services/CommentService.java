package com.musicstreaming.services;

import com.musicstreaming.models.Comment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CommentService {
        List<Comment> getAllComments() throws Exception;
        List<Comment> getAllCommentsByUser(Long userId);
        Optional<List<Comment>> getAllCommentsBySong(Long songId) throws Exception;
        Comment updateComment(String newContent,Long commentId);
        Comment deleteComment(Long commentId );
        Comment postComment(String content,Long songId,Long userId);
        Comment replyComment(String content,Long songId,Long userId,Long parentCommentId);


}
