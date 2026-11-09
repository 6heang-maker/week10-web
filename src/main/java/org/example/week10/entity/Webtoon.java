
package org.example.week10.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "webtoons")
@EntityListeners(AuditingEntityListener.class)
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//Q1. 관리할 데이터로 정한다. 데이터의 키를 지정. 자동 기능으로 id 생성
//Q2. DB의 테이블과 연결된다.

    @Column(nullable = false)
    private String title;

    private String author;
    private String genre;
    private String platform;
    private Double rating;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected Webtoon() {
    }

    public Webtoon(String title, String author, String genre,
                   String platform, Double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.platform = platform;
        this.rating = rating;
    }

    public void update(String title, String author, String genre,
                       String platform, Double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.platform = platform;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public Double getRating() {
        return rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
