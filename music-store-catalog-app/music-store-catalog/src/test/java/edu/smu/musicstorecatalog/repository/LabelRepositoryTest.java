package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Label;
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
public class LabelRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    ArtistRepository artistRepository;

    Label label1;
    Label label2;
    private List<Label> expectedLabelList = new ArrayList<>();


    @Before
    public void setUp() {
        trackRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        labelRepository.deleteAll();

        label1 = labelRepository.save(new Label(1, "Sony Music Entertainment", "https://www.sonymusic.com/"));
        label2 = labelRepository.save(new Label(2, "Warner Music Group.", "https://www.wmg.com/"));

        expectedLabelList.clear();
    }


    @Test
    public void addGetDeleteLabel() {
        Optional<Label> label = labelRepository.findById(label1.getLabelId());

        assertEquals(label1, label.get());

        labelRepository.deleteById(label1.getLabelId());
        label = labelRepository.findById(label1.getLabelId());

        assertFalse(label.isPresent());
    }

    @Test
    public void updateLabel() {
        label1.setName("New Name");
        labelRepository.save(label1);
        Optional<Label> invoice = labelRepository.findById(label1.getLabelId());

        assertEquals(invoice.get(), label1);
    }

    @Test
    public void getAllLabels() {
        expectedLabelList.add(label1);
        expectedLabelList.add(label2);

        List<Label> actualLabelList = labelRepository.findAll();

        assertEquals(actualLabelList.size(), 2);
        assertEquals(expectedLabelList, actualLabelList);
    }

}