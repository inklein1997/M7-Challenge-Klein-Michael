package edu.smu.musicstorerecommendations.repository;

import edu.smu.musicstorerecommendations.dto.LabelRecommendation;
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
public class LabelRecommendationRepositoryTest {

    @Autowired
    LabelRecommendationRepository labelRecommendationRepository;

    LabelRecommendation labelRecommendation1;
    LabelRecommendation labelRecommendation2;
    private List<LabelRecommendation> expectedLabelRecommendationList = new ArrayList<>();


    @Before
    public void setUp() {
        labelRecommendationRepository.deleteAll();

        labelRecommendation1 = labelRecommendationRepository.save(new LabelRecommendation());
        labelRecommendation2 = labelRecommendationRepository.save(new LabelRecommendation());

        expectedLabelRecommendationList.clear();
    }


    @Test
    public void addGetDeleteLabelRecommendation() {
        Optional<LabelRecommendation> label = labelRecommendationRepository.findById(labelRecommendation1.getLabelId());

        assertEquals(labelRecommendation1, label.get());

        labelRecommendationRepository.deleteById(labelRecommendation1.getLabelId());
        label = labelRecommendationRepository.findById(labelRecommendation1.getLabelId());

        assertFalse(label.isPresent());
    }

    @Test
    public void updateLabelRecommendation() {
        labelRecommendation1.setLiked(false);
        labelRecommendationRepository.save(labelRecommendation1);
        Optional<LabelRecommendation> invoice = labelRecommendationRepository.findById(labelRecommendation1.getLabelId());

        assertEquals(invoice.get(), labelRecommendation1);
    }

    @Test
    public void getAllLabelRecommendations() {
        expectedLabelRecommendationList.add(labelRecommendation1);
        expectedLabelRecommendationList.add(labelRecommendation2);

        List<LabelRecommendation> actualLabelList = labelRecommendationRepository.findAll();

        assertEquals(actualLabelList.size(), 2);
        assertEquals(expectedLabelRecommendationList, actualLabelList);
    }

}