package edu.smu.musicstorecatalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id")
    private int albumId;

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Size(max = 255)
    @Column(name = "artist_id")
    private int artistId;

    @NotNull
    @Column(name = "release_date")
    private Date releaseDate;

    @NotNull
    @Column(name = "label_id")
    private int labelId;

    @NotNull
    @Column(name = "list_price")
    private double listPrice;

    public Album(int albumId, String title, int artistId, Date releaseDate, int labelId, double listPrice) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.labelId = labelId;
        this.listPrice = listPrice;
    }

    public Album(String title, int artistId, Date releaseDate, int labelId, double listPrice) {
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.labelId = labelId;
        this.listPrice = listPrice;
    }

    public Album() {
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

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return albumId == album.albumId && artistId == album.artistId && labelId == album.labelId && Double.compare(album.listPrice, listPrice) == 0 && Objects.equals(title, album.title) && Objects.equals(releaseDate, album.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, title, artistId, releaseDate, labelId, listPrice);
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", releaseDate=" + releaseDate +
                ", labelId=" + labelId +
                ", listPrice=" + listPrice +
                '}';
    }
}
