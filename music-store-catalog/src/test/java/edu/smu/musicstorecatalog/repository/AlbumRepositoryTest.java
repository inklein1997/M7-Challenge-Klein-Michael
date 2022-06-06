package edu.smu.musicstorecatalog.repository;

import edu.smu.musicstorecatalog.dto.Album;
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
public class AlbumRepositoryTest {

    @Autowired
    AlbumRepository albumRepository;

    Album album1;
    Album album2;
    private List<Album> expectedAlbumList = new ArrayList<>();


    @Before
    public void setUp() {
        albumRepository.deleteAll();

        album1 = albumRepository.save(new Album(1, "Night Visions", 2, LocalDate.of(2012,9,12), 2, 3.99));
        album2 = albumRepository.save(new Album(2, "Red", 1, LocalDate.of(2012,10,22), 1, 4.99));

        expectedAlbumList.clear();
    }


    @Test
    public void addGetDeleteAlbum() {
        Optional<Album> album = albumRepository.findById(album1.getAlbumId());

        assertEquals(album1, album.get());

        albumRepository.deleteById(album1.getAlbumId());
        album = albumRepository.findById(album1.getAlbumId());

        assertFalse(album.isPresent());
    }

    @Test
    public void updateAlbum() {
        album1.setTitle("New Title");
        albumRepository.save(album1);
        Optional<Album> invoice = albumRepository.findById(album1.getAlbumId());

        assertEquals(invoice.get(), album1);
    }

    @Test
    public void getAllAlbums() {
        expectedAlbumList.add(album1);
        expectedAlbumList.add(album2);

        List<Album> actualAlbumList = albumRepository.findAll();

        assertEquals(actualAlbumList.size(), 2);
        assertEquals(expectedAlbumList, actualAlbumList);
    }

}