package edu.smu.musicstorerecommendations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "track_recommendation")
public class TrackRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "track_recommendation_id")
    private int trackRecommendationId;

    @Column(name = "track_id", nullable = false)
    @Range(min = 1, message = "please enter a valid trackId")
    @NotNull(message = "please enter valid boolean for liked status")
    private int trackId;

    @Column(name = "user_id", nullable = false)
    @Range(min = 1, message = "please enter a valid userId")
    @NotNull(message = "please enter valid boolean for liked status")
    private int userId;

    @NotNull(message = "please enter valid boolean for liked status")
    private boolean liked;

    public TrackRecommendation() {
    }

    public TrackRecommendation(int trackRecommendationId, int trackId, int userId, boolean liked) {
        this.trackRecommendationId = trackRecommendationId;
        this.trackId = trackId;
        this.userId = userId;
        this.liked = liked;
    }

    public TrackRecommendation(int trackId, int userId, boolean liked) {
        this.trackId = trackId;
        this.userId = userId;
        this.liked = liked;
    }

    public int getTrackRecommendationId() {
        return trackRecommendationId;
    }

    public void setTrackRecommendationId(int trackRecommendationId) {
        this.trackRecommendationId = trackRecommendationId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
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
        TrackRecommendation that = (TrackRecommendation) o;
        return trackRecommendationId == that.trackRecommendationId && trackId == that.trackId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackRecommendationId, trackId, userId, liked);
    }

    @Override
    public String toString() {
        return "TrackRecommendation{" +
                "trackRecommendationId=" + trackRecommendationId +
                ", trackId=" + trackId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
