package edu.smu.musicstorecatalog.service;

import edu.smu.musicstorecatalog.dto.Album;
import edu.smu.musicstorecatalog.dto.Artist;
import edu.smu.musicstorecatalog.dto.Label;
import edu.smu.musicstorecatalog.dto.Track;
import edu.smu.musicstorecatalog.repository.AlbumRepository;
import edu.smu.musicstorecatalog.repository.ArtistRepository;
import edu.smu.musicstorecatalog.repository.LabelRepository;
import edu.smu.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private LabelRepository labelRepository;
    private TrackRepository trackRepository;

    @Autowired
    public ServiceLayer(AlbumRepository albumRepository, ArtistRepository artistRepository, LabelRepository labelRepository, TrackRepository trackRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.labelRepository = labelRepository;
        this.trackRepository = trackRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Optional<Album> getAlbumById(int id) {
        return albumRepository.findById(id);
    }
    public Optional<Artist> getArtistById(int id) {
        return artistRepository.findById(id);
    }
    public Optional<Label> getLabelById(int id) {
        return labelRepository.findById(id);
    }
    public Optional<Track> getTrackById(int id) {
        return trackRepository.findById(id);
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }
    public Artist createArtist(Artist album) {
        return artistRepository.save(album);
    }
    public Label createLabel(Label label) {
        return labelRepository.save(label);
    }
    public Track createTrack(Track track) {
        return trackRepository.save(track);
    }

    public void updateAlbum(Album album) {
        albumRepository.save(album);
    }
    public void updateArtist(Artist artist) {
        artistRepository.save(artist);
    }
    public void updateLabel(Label label) {
        labelRepository.save(label);
    }
    public void updateTrack(Track track) {
        trackRepository.save(track);
    }

    public void deleteAlbum(int id) {
        albumRepository.deleteById(id);
    }
    public void deleteArtist(int id) {
        artistRepository.deleteById(id);
    }
    public void deleteLabel(int id) {
        labelRepository.deleteById(id);
    }
    public void deleteTrack(int id) {
        trackRepository.deleteById(id);
    }

}
