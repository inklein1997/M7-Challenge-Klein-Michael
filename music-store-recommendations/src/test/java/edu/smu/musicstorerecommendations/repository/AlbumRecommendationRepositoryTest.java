package edu.smu.musicstorerecommendations.repository;

import edu.smu.musicstorerecommendations.dto.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {

    @Autowired
    AlbumRecommendationRepository albumRecommendationRepository;

    AlbumRecommendation albumRecommendation1;
    AlbumRecommendation albumRecommendation2;
    private List<AlbumRecommendation> expectedAlbumRecommendationList = new ArrayList<>();

    @Before
    public void setUp() {
        albumRecommendationRepository.deleteAll();

        albumRecommendation1 = albumRecommendationRepository.save(new AlbumRecommendation());
        albumRecommendation2 = albumRecommendationRepository.save(new AlbumRecommendation());

        expectedAlbumRecommendationList.clear();
    }

    @Test
    public void addGetDeleteAlbumRecommendation() {
        Optional<AlbumRecommendation> album = albumRecommendationRepository.findById(albumRecommendation1.getAlbumRecommendationId());

        assertEquals(albumRecommendation1, album.get());

        albumRecommendationRepository.deleteById(albumRecommendation1.getAlbumRecommendationId());
        album = albumRecommendationRepository.findById(albumRecommendation1.getAlbumRecommendationId());

        assertFalse(album.isPresent());
    }

    @Test
    public void updateAlbumRecommendation() {
        albumRecommendation1.setLiked(false);
        albumRecommendationRepository.save(albumRecommendation1);
        Optional<AlbumRecommendation> albumRecommendation = albumRecommendationRepository.findById(albumRecommendation1.getAlbumId());

        assertEquals(albumRecommendation.get(), albumRecommendation1);
    }

    @Test
    public void getAllAlbumRecommendations() {
        expectedAlbumRecommendationList.add(albumRecommendation1);
        expectedAlbumRecommendationList.add(albumRecommendation2);

        List<AlbumRecommendation> actualAlbumList = albumRecommendationRepository.findAll();

        assertEquals(actualAlbumList.size(), 2);
        assertEquals(expectedAlbumRecommendationList, actualAlbumList);
    }

}