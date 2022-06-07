package edu.smu.musicstorecatalog.service;

import edu.smu.musicstorecatalog.dto.Album;
import edu.smu.musicstorecatalog.dto.Artist;
import edu.smu.musicstorecatalog.dto.Label;
import edu.smu.musicstorecatalog.dto.Track;
import edu.smu.musicstorecatalog.repository.AlbumRepository;
import edu.smu.musicstorecatalog.repository.ArtistRepository;
import edu.smu.musicstorecatalog.repository.LabelRepository;
import edu.smu.musicstorecatalog.repository.TrackRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;
    AlbumRepository albumRepository;
    ArtistRepository artistRepository;
    TrackRepository trackRepository;
    LabelRepository labelRepository;

    private Album expectedAlbum;
    private Album actualAlbum;
    private List<Album> actualAlbumList;
    private List<Album> expectedAlbumList;

    private Artist expectedArtist;
    private Artist actualArtist;
    private List<Artist> actualArtistList;
    private List<Artist> expectedArtistList;

    private Track expectedTrack;
    private Track actualTrack;
    private List<Track> actualTrackList;
    private List<Track> expectedTrackList;

    private Label expectedLabel;
    private Label actualLabel;
    private List<Label> actualLabelList;
    private List<Label> expectedLabelList;

    @Before
    public void setUp() {
        setUpAlbumRepositoryMock();
        setUpArtistRepositoryMock();
        setUpTrackRepositoryMock();
        setUpLabelRepositoryMock();
        serviceLayer = new ServiceLayer(albumRepository, artistRepository, labelRepository, trackRepository);
    }

    public void setUpAlbumRepositoryMock() {
        albumRepository = mock(AlbumRepository.class);

        Album album1 = new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99);
        Album album2 = new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99);

        List<Album> albumList = Arrays.asList(album1, album2);

        doReturn(albumList).when(albumRepository).findAll();
        doReturn(Optional.of(album1)).when(albumRepository).findById(1);
        doReturn(album1).when(albumRepository).save(album1);
    }

    public void setUpArtistRepositoryMock() {
        artistRepository = mock(ArtistRepository.class);

        Artist artist1 = new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift");
        Artist artist2 = new Artist(2, "Imagine Dragons", "@imaginedragons", "@imaginedragons");

        List<Artist> artistList = Arrays.asList(artist1, artist2);

        doReturn(artistList).when(artistRepository).findAll();
        doReturn(Optional.of(artist1)).when(artistRepository).findById(1);
        doReturn(artist1).when(artistRepository).save(artist1);
    }

    public void setUpTrackRepositoryMock() {
        trackRepository = mock(TrackRepository.class);

        Track track1 = new Track(1, "Red", 5, 1);
        Track track2 = new Track(2, "22", 3, 1);

        List<Track> trackList = Arrays.asList(track1, track2);

        doReturn(trackList).when(trackRepository).findAll();
        doReturn(Optional.of(track1)).when(trackRepository).findById(1);
        doReturn(track1).when(trackRepository).save(track1);
    }

    public void setUpLabelRepositoryMock() {
        labelRepository = mock(LabelRepository.class);

        Label label1 = new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/");
        Label label2 = new Label(2, "Warner Music Group.", "https://www.wmg.com/");

        List<Label> labelList = Arrays.asList(label1, label2);

        doReturn(labelList).when(labelRepository).findAll();
        doReturn(Optional.of(label1)).when(labelRepository).findById(1);
        doReturn(label1).when(labelRepository).save(label1);
    }

    @Test
    public void shouldReturnAllAlbums() {
        expectedAlbumList = Arrays.asList(
                new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99),
                new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99)
        );
        actualAlbumList = serviceLayer.getAllAlbums();

        assertEquals(expectedAlbumList, actualAlbumList);
    }

    @Test
    public void shouldReturnAllArtists() {
        expectedArtistList = Arrays.asList(
                new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift"),
                new Artist(2, "Imagine Dragons", "@imaginedragons", "@imaginedragons")
        );
        actualArtistList = serviceLayer.getAllArtists();

        assertEquals(expectedArtistList, actualArtistList);
    }

    @Test
    public void shouldReturnAllTracks() {
        expectedTrackList = Arrays.asList(
                new Track(1, "Red", 5, 1),
                new Track(2, "22", 3, 1)
        );
        actualTrackList = serviceLayer.getAllTracks();

        assertEquals(expectedAlbumList, actualAlbumList);
    }

    @Test
    public void shouldReturnAllLabels() {
        expectedLabelList = Arrays.asList(
                new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/"),
                new Label(2, "Warner Music Group.", "https://www.wmg.com/")
        );
        actualLabelList = serviceLayer.getAllLabels();

        assertEquals(expectedLabelList, actualLabelList);
    }

    @Test
    public void shouldReturnAlbumById() {
        expectedAlbum = new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99);
        actualAlbum = serviceLayer.getAlbumById(1).get();

        assertEquals(expectedAlbum, actualAlbum);
    }

    @Test
    public void shouldReturnArtistById() {
        expectedArtist = new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift");
        actualArtist = serviceLayer.getArtistById(1).get();

        assertEquals(expectedArtist, actualArtist);
    }

    @Test
    public void shouldReturnTrackById() {
        expectedTrack = new Track(1, "Red", 5, 1);
        actualTrack = serviceLayer.getTrackById(1).get();

        assertEquals(expectedTrack, actualTrack);
    }

    @Test
    public void shouldReturnLabelById() {
        expectedLabel = new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/");
        actualLabel = serviceLayer.getLabelById(1).get();

        assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void shouldCreateAndReturnAlbum() {
        expectedAlbum = new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99);
        actualAlbum = serviceLayer.createAlbum(new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99));

        assertEquals(expectedAlbum, actualAlbum);
    }

    @Test
    public void shouldCreateAndReturnArtist() {
        expectedArtist = new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift");
        actualArtist = serviceLayer.createArtist(new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift"));

        assertEquals(expectedAlbum, actualAlbum);
    }

    @Test
    public void shouldCreateAndReturnTrack() {
        expectedTrack = new Track(1, "Red", 5, 1);
        actualTrack = serviceLayer.createTrack(new Track(1, "Red", 5, 1));

        assertEquals(expectedAlbum, actualAlbum);
    }

    @Test
    public void shouldCreateAndReturnLabel() {
        expectedLabel = new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/");
        actualLabel = serviceLayer.createLabel(new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/"));

        assertEquals(expectedAlbum, actualAlbum);
    }
}