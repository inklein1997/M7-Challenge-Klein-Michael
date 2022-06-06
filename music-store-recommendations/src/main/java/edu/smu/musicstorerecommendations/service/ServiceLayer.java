package edu.smu.musicstorerecommendations.service;

import edu.smu.musicstorerecommendations.dto.AlbumRecommendation;
import edu.smu.musicstorerecommendations.dto.ArtistRecommendation;
import edu.smu.musicstorerecommendations.dto.LabelRecommendation;
import edu.smu.musicstorerecommendations.dto.TrackRecommendation;
import edu.smu.musicstorerecommendations.repository.AlbumRecommendationRepository;
import edu.smu.musicstorerecommendations.repository.ArtistRecommendationRepository;
import edu.smu.musicstorerecommendations.repository.LabelRecommendationRepository;
import edu.smu.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {
    private AlbumRecommendationRepository albumRecommendationRepository;
    private ArtistRecommendationRepository artistRecommendationRepository;
    private LabelRecommendationRepository labelRecommendationRepository;
    private TrackRecommendationRepository trackRecommendationRepository;

    @Autowired
    public ServiceLayer(AlbumRecommendationRepository albumRecommendationRepository, ArtistRecommendationRepository artistRecommendationRepository, LabelRecommendationRepository labelRecommendationRepository, TrackRecommendationRepository trackRecommendationRepository) {
        this.albumRecommendationRepository = albumRecommendationRepository;
        this.artistRecommendationRepository = artistRecommendationRepository;
        this.labelRecommendationRepository = labelRecommendationRepository;
        this.trackRecommendationRepository = trackRecommendationRepository;
    }

    public List<AlbumRecommendation> getAllAlbumRecommendations() {
        return albumRecommendationRepository.findAll();
    }
    public List<ArtistRecommendation> getAllArtistRecommendations() {
        return artistRecommendationRepository.findAll();
    }
    public List<LabelRecommendation> getAllLabelRecommendations() {
        return labelRecommendationRepository.findAll();
    }
    public List<TrackRecommendation> getAllTrackRecommendations() {
        return trackRecommendationRepository.findAll();
    }

    public Optional<AlbumRecommendation> getAlbumRecommendationById(int id) {
        return albumRecommendationRepository.findById(id);
    }
    public Optional<ArtistRecommendation> getArtistRecommendationById(int id) {
        return artistRecommendationRepository.findById(id);
    }
    public Optional<LabelRecommendation> getLabelRecommendationById(int id) {
        return labelRecommendationRepository.findById(id);
    }
    public Optional<TrackRecommendation> getTrackRecommendationById(int id) {
        return trackRecommendationRepository.findById(id);
    }

    public AlbumRecommendation createAlbumRecommendation(AlbumRecommendation albumRecommendation) {
        return albumRecommendationRepository.save(albumRecommendation);
    }
    public ArtistRecommendation createArtistRecommendation(ArtistRecommendation artistRecommendation) {
        return artistRecommendationRepository.save(artistRecommendation);
    }
    public LabelRecommendation createLabelRecommendation(LabelRecommendation labelRecommendation) {
        return labelRecommendationRepository.save(labelRecommendation);
    }
    public TrackRecommendation createTrackRecommendation(TrackRecommendation trackRecommendation) {
        return trackRecommendationRepository.save(trackRecommendation);
    }

    public void updateAlbumRecommendation(AlbumRecommendation album) {
        albumRecommendationRepository.save(album);
    }
    public void updateArtistRecommendation(ArtistRecommendation artist) {
        artistRecommendationRepository.save(artist);
    }
    public void updateLabelRecommendation(LabelRecommendation label) {
        labelRecommendationRepository.save(label);
    }
    public void updateTrackRecommendation(TrackRecommendation track) {
        trackRecommendationRepository.save(track);
    }

    public void deleteAlbumRecommendation(int id) {
        albumRecommendationRepository.deleteById(id);
    }
    public void deleteArtistRecommendation(int id) {
        artistRecommendationRepository.deleteById(id);
    }
    public void deleteLabelRecommendation(int id) {
        labelRecommendationRepository.deleteById(id);
    }
    public void deleteTrackRecommendation(int id) {
        trackRecommendationRepository.deleteById(id);
    }

}
