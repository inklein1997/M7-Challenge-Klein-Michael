package edu.smu.musicstorerecommendations.repository;

import edu.smu.musicstorerecommendations.dto.TrackRecommendation;
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
public class TrackRecommendationRepositoryTest {

    @Autowired
    TrackRecommendationRepository trackRecommendationRepository;

    TrackRecommendation trackRecommendation1;
    TrackRecommendation trackRecommendation2;
    private List<TrackRecommendation> expectedTrackRecommendationList = new ArrayList<>();


    @Before
    public void setUp() {
        trackRecommendationRepository.deleteAll();

        trackRecommendation1 = trackRecommendationRepository.save(new TrackRecommendation());
        trackRecommendation2 = trackRecommendationRepository.save(new TrackRecommendation());

        expectedTrackRecommendationList.clear();
    }


    @Test
    public void addGetDeleteTrackRecommendation() {
        Optional<TrackRecommendation> label = trackRecommendationRepository.findById(trackRecommendation1.getTrackRecommendationId());

        assertEquals(trackRecommendation1, label.get());

        trackRecommendationRepository.deleteById(trackRecommendation1.getTrackRecommendationId());
        label = trackRecommendationRepository.findById(trackRecommendation1.getTrackRecommendationId());

        assertFalse(label.isPresent());
    }

    @Test
    public void updateTrackRecommendation() {
        trackRecommendation1.setLiked(false);
        trackRecommendationRepository.save(trackRecommendation1);
        Optional<TrackRecommendation> trackRecommendation = trackRecommendationRepository.findById(trackRecommendation1.getTrackRecommendationId());

        assertEquals(trackRecommendation.get(), trackRecommendation1);
    }

    @Test
    public void getAllTrackRecommendations() {
        expectedTrackRecommendationList.add(trackRecommendation1);
        expectedTrackRecommendationList.add(trackRecommendation2);

        List<TrackRecommendation> actualTrackRecommendationList = trackRecommendationRepository.findAll();

        assertEquals(actualTrackRecommendationList.size(), 2);
        assertEquals(expectedTrackRecommendationList, actualTrackRecommendationList);
    }

}