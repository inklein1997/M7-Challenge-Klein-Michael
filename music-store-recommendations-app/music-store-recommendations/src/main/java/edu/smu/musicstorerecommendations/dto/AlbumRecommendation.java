package edu.smu.musicstorerecommendations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "album_recommendation")
public class AlbumRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_recommendation_id")
    private int albumRecommendationId;

    @NotNull(message = "please enter valid album ID")
    @Column(name = "album_id", nullable = false)
    @Range(min = 1, message = "please enter a valid albumId")
    private int albumId;

    @NotNull(message = "please enter valid user ID")
    @Column(name = "user_id", nullable = false)
    @Range(min = 1, message = "please enter a valid userId")
    private int userId;

    @Column(nullable = false)
    @NotNull(message = "please enter valid boolean for liked status")
    private boolean liked;

    public AlbumRecommendation() {
    }

    public AlbumRecommendation(int albumRecommendationId, int albumId, int userId, boolean liked) {
        this.albumRecommendationId = albumRecommendationId;
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public AlbumRecommendation(int albumId, int userId, boolean liked) {
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public int getAlbumRecommendationId() {
        return albumRecommendationId;
    }

    public void setAlbumRecommendationId(int albumRecommendationId) {
        this.albumRecommendationId = albumRecommendationId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumRecommendation that = (AlbumRecommendation) o;
        return albumRecommendationId == that.albumRecommendationId && albumId == that.albumId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumRecommendationId, albumId, userId, liked);
    }

    @Override
    public String toString() {
        return "AlbumRecommendation{" +
                "albumRecommendationId=" + albumRecommendationId +
                ", albumId=" + albumId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
