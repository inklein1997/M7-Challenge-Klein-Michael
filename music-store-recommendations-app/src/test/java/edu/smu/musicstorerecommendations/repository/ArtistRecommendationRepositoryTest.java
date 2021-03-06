package edu.smu.musicstorerecommendations.repository;

import edu.smu.musicstorerecommendations.dto.ArtistRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistRecommendationRepositoryTest {

    @Autowired
    ArtistRecommendationRepository artistRecommendationRepository;

    ArtistRecommendation artistRecommendation1;
    ArtistRecommendation artistRecommendation2;
    private List<ArtistRecommendation> expectedArtistRecommendationList = new ArrayList<>();


    @Before
    public void setUp() {
        artistRecommendationRepository.deleteAll();

        artistRecommendation1 = artistRecommendationRepository.save(new ArtistRecommendation(1,1,1,true));
        artistRecommendation2 = artistRecommendationRepository.save(new ArtistRecommendation(2,1,2,true));

        expectedArtistRecommendationList.clear();
    }


    @Test
    public void addGetDeleteArtist() {
        Optional<ArtistRecommendation> artistRecommendation = artistRecommendationRepository.findById(artistRecommendation1.getArtistRecommendationId());

        assertEquals(artistRecommendation1, artistRecommendation.get());

        artistRecommendationRepository.deleteById(artistRecommendation1.getArtistRecommendationId());
        artistRecommendation = artistRecommendationRepository.findById(artistRecommendation1.getArtistRecommendationId());

        assertFalse(artistRecommendation.isPresent());
    }

    @Test
    public void updateArtist() {
        artistRecommendation1.setLiked(false);
        artistRecommendationRepository.save(artistRecommendation1);
        Optional<ArtistRecommendation> invoice = artistRecommendationRepository.findById(artistRecommendation1.getArtistRecommendationId());

        assertEquals(invoice.get(), artistRecommendation1);
    }

    @Test
    public void getAllArtists() {
        expectedArtistRecommendationList.add(artistRecommendation1);
        expectedArtistRecommendationList.add(artistRecommendation2);

        List<ArtistRecommendation> actualArtistList = artistRecommendationRepository.findAll();

        assertEquals(actualArtistList.size(), 2);
        assertEquals(expectedArtistRecommendationList, actualArtistList);
    }

}