package edu.smu.musicstorecatalog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Integer trackId;

    @NotNull(message = "title must not be null")
    @Size(max = 50)
    private String title;

    @NotNull(message = "runTime must not be null")
    @Column(name = "run_time")
    private Integer runTime;

    @NotNull
    @Column(name = "album_id")
    private Integer albumId;

    public Track() {
    }

    public Track(Integer trackId, String title, Integer runTime, Integer albumId) {
        this.trackId = trackId;
        this.title = title;
        this.runTime = runTime;
        this.albumId = albumId;
    }

    public Track(String title, Integer runTime, Integer albumId) {
        this.title = title;
        this.runTime = runTime;
        this.albumId = albumId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(trackId, track.trackId) && Objects.equals(title, track.title) && Objects.equals(runTime, track.runTime) && Objects.equals(albumId, track.albumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, title, runTime, albumId);
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", title='" + title + '\'' +
                ", runTime=" + runTime +
                ", albumId=" + albumId +
                '}';
    }
}
