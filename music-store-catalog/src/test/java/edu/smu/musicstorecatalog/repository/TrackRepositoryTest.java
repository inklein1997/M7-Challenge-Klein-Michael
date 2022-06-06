package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Album;
import edu.smu.musicstorecatalog.dto.Artist;
import edu.smu.musicstorecatalog.dto.Label;
import edu.smu.musicstorecatalog.dto.Track;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    ArtistRepository artistRepository;

    Track track1;
    Track track2;
    Label label1;
    Label label2;
    Album album1;
    Album album2;
    Artist artist1;
    Artist artist2;
    private List<Track> expectedTrackList = new ArrayList<>();

    @Before
    public void setUp() {
        trackRepository.deleteAll();
        labelRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();

        artist1 = artistRepository.save(new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift"));
        artist2 = artistRepository.save(new Artist(2, "Imagine Dragons", "@imaginedragons", "@imaginedragons"));

        label1 = labelRepository.save(new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/"));
        label2 = labelRepository.save(new Label(2, "Warner Music Group.", "https://www.wmg.com/"));

        album1 = albumRepository.save(new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99));
        album2 = albumRepository.save(new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99));

        track1 = trackRepository.save(new Track(1, 1, "All Too Well", 5,1, LocalDate.of(2012,10,12)));
        track2 = trackRepository.save(new Track(2, 1, "22", 3,1, LocalDate.of(2012,10,12)));

        expectedTrackList.clear();
    }


    @Test
    public void addGetDeleteLabel() {
        Optional<Track> track = trackRepository.findById(track1.getTrackId());

        assertEquals(track1, track.get());

        trackRepository.deleteById(track1.getTrackId());
        track = trackRepository.findById(track1.getTrackId());

        assertFalse(track.isPresent());
    }

    @Test
    public void updateLabel() {
        track1.setTitle("New Name");
        trackRepository.save(track1);
        Optional<Track> track = trackRepository.findById(track1.getTrackId());

        assertEquals(track.get(), track1);
    }

    @Test
    public void getAllLabels() {
        expectedTrackList.add(track1);
        expectedTrackList.add(track2);

        List<Track> actualTrackList = trackRepository.findAll();

        assertEquals(actualTrackList.size(), 2);
        assertEquals(expectedTrackList, actualTrackList);
    }
}