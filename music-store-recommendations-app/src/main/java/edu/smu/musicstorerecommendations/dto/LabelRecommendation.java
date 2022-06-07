package edu.smu.musicstorerecommendations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "label_recommendation")
public class LabelRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_recommendation_id")
    private int labelRecommendationId;

    @Column(name = "label_id", nullable = false)
    @Range(min = 1, message = "please enter a valid labelId")
    @NotNull(message = "please enter a valid labelId")
    private int labelId;

    @Column(name = "user_id", nullable = false)
    @Range(min = 1, message = "please enter a valid userId")
    @NotNull(message = "please enter a valid userId")
    private int userId;

    @NotNull(message = "please enter valid boolean for liked status")
    private boolean liked;


    public LabelRecommendation() {
    }

    public LabelRecommendation(int labelRecommendationId, int labelId, int userId, boolean liked) {
        this.labelRecommendationId = labelRecommendationId;
        this.labelId = labelId;
        this.userId = userId;
        this.liked = liked;
    }

    public LabelRecommendation(int labelId, int userId, boolean liked) {
        this.labelId = labelId;
        this.userId = userId;
        this.liked = liked;
    }

    public int getLabelRecommendationId() {
        return labelRecommendationId;
    }

    public void setLabelRecommendationId(int labelRecommendationId) {
        this.labelRecommendationId = labelRecommendationId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
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
        LabelRecommendation that = (LabelRecommendation) o;
        return labelRecommendationId == that.labelRecommendationId && labelId == that.labelId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelRecommendationId, labelId, userId, liked);
    }

    @Override
    public String toString() {
        return "LabelRecommendation{" +
                "labelRecommendationId=" + labelRecommendationId +
                ", labelId=" + labelId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
