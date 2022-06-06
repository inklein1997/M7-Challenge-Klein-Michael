package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Album;
import edu.smu.musicstorecatalog.dto.Artist;
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
public class ArtistRepositoryTest {

    @Autowired
    ArtistRepository artistRepository;

    Artist artist1;
    Artist artist2;
    private List<Artist> expectedArtistList = new ArrayList<>();


    @Before
    public void setUp() {
        artistRepository.deleteAll();

        artist1 = artistRepository.save(new Artist(1, "Taylor Swift", "@taylorswift", "@taylorswift"));
        artist2 = artistRepository.save(new Artist(2, "Imagine Dragons", "@imaginedragons", "@imaginedragons"));

        expectedArtistList.clear();
    }


    @Test
    public void addGetDeleteArtist() {
        Optional<Artist> artist = artistRepository.findById(artist1.getArtistId());

        assertEquals(artist1, artist.get());

        artistRepository.deleteById(artist1.getArtistId());
        artist = artistRepository.findById(artist1.getArtistId());

        assertFalse(artist.isPresent());
    }

    @Test
    public void updateArtist() {
        artist1.setInstagram("@NewHandle");
        artistRepository.save(artist1);
        Optional<Artist> invoice = artistRepository.findById(artist1.getArtistId());

        assertEquals(invoice.get(), artist1);
    }

    @Test
    public void getAllArtists() {
        expectedArtistList.add(artist1);
        expectedArtistList.add(artist2);

        List<Artist> actualArtistList = artistRepository.findAll();

        assertEquals(actualArtistList.size(), 2);
        assertEquals(expectedArtistList, actualArtistList);
    }

}