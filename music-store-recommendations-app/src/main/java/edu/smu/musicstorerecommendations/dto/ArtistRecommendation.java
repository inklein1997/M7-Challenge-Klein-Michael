package edu.smu.musicstorerecommendations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist_recommendation")
public class ArtistRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artist_recommendation_id")
    private int artistRecommendationId;

    @NotNull(message = "please enter valid artist ID")
    @Range(min = 1, message = "please enter a valid artistId")
    @Column(name = "artist_id")
    private int artistId;

    @NotNull(message = "please enter valid user ID")
    @Range(min = 1, message = "please enter a valid userId")
    @Column(name = "user_id")
    private int userId;

    @NotNull(message = "please enter valid boolean for liked status")
    private boolean liked;


    public ArtistRecommendation() {
    }

    public ArtistRecommendation(int artistRecommendationId, int artistId, int userId, boolean liked) {
        this.artistRecommendationId = artistRecommendationId;
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public ArtistRecommendation(int artistId, int userId, boolean liked) {
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public int getArtistRecommendationId() {
        return artistRecommendationId;
    }

    public void setArtistRecommendationId(int artistRecommendationId) {
        this.artistRecommendationId = artistRecommendationId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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
        ArtistRecommendation that = (ArtistRecommendation) o;
        return artistRecommendationId == that.artistRecommendationId && artistId == that.artistId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistRecommendationId, artistId, userId, liked);
    }

    @Override
    public String toString() {
        return "ArtistRecommendation{" +
                "artistRecommendationId=" + artistRecommendationId +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
