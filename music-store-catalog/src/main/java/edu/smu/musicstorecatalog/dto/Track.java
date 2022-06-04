package edu.smu.musicstorecatalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "track_id")
    private int trackId;

    @NotNull(message = "albumId must not be null")
    @Column(name = "album_id")
    private int albumId;

    @NotNull(message = "title must not be null")
    @Size(max = 50)
    private String title;

    @NotNull(message = "runTime must not be null")
    @Column(name = "run_time")
    private int runTime;

    @NotNull(message = "artistId must not be null")
    @Size(max = 255)
    @Column(name = "artist_id")
    private int artistId;

    @NotNull(message = "releaseDate must not be null")
    @Column(name = "release_date")
    private Date releaseDate;

    public Track(int trackId, int albumId, String title, int runTime, int artistId, Date releaseDate) {
        this.trackId = trackId;
        this.albumId = albumId;
        this.title = title;
        this.runTime = runTime;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
    }

    public Track(int albumId, String title, int runTime, int artistId, Date releaseDate) {
        this.albumId = albumId;
        this.title = title;
        this.runTime = runTime;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
    }

    public Track() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return trackId == track.trackId && albumId == track.albumId && runTime == track.runTime && artistId == track.artistId && Objects.equals(title, track.title) && Objects.equals(releaseDate, track.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, albumId, title, runTime, artistId, releaseDate);
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", runTime=" + runTime +
                ", artistId=" + artistId +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
