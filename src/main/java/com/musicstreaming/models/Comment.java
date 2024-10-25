package com.musicstreaming.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nhiều comment có thể thuộc về một bài hát (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "songID", nullable = false) // Sửa thành "songID" theo cột trong bảng database
    private Song song;

    // Nhiều comment có thể thuộc về một người dùng (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    // Nội dung của comment
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // Thời gian tạo comment
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    // Thời gian cập nhật comment
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;


    // Lưu lại thời gian tạo và cập nhật trước khi lưu vào database
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}