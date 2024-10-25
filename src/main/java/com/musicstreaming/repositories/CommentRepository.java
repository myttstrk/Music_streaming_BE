package com.musicstreaming.repositories;

import com.musicstreaming.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

     @Query("SELECT c FROM Comment c")
     Optional<List<Comment>> getAllComments();
     @Query("SELECT c FROM Comment c WHERE c.song.id = :songId")
     Optional<List<Comment>> getCommentListBySongID(@Param("songId") Long songId);

     @Query("SELECT c FROM Comment c WHERE c.user.id = :userId")
     Optional<List<Comment>> getCommentListByUserID(@Param("userId") Long userId);

}
