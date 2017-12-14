package org.sp.j01.carefree.api.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.domain.Family;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.sp.j01.carefree.api.TestData.FAMILIES;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class JdbcFamilyRepositoryTest {
    private static final Logger logger = LogManager.getLogger(JdbcFamilyRepositoryTest.class);

    @Inject
    FamilyRepository familyRepository;

    private static void assertFamily(int expectedFamilyIndex, Family actual) {
        Family expected = FAMILIES[expectedFamilyIndex];
        assertEquals("family id", expected.getFamilyId(), actual.getFamilyId());
        assertEquals("family name", expected.getFamilyName(), actual.getFamilyName());
        assertEquals("thumbnail photo", expected.getThumbnailPhotoUrl(), actual.getThumbnailPhotoUrl());
        assertEquals("standard photo", expected.getStandardPhotoUrl(), actual.getStandardPhotoUrl());
        assertEquals("description", expected.getDescription(), actual.getDescription());
        assertEquals("leaves behind", expected.getLeavesBehind(), actual.getLeavesBehind());
        assertEquals("incarceratedDate", expected.getIncarceratedDate(), actual.getIncarceratedDate());
    }

    @BeforeClass
    public static void before() {

    }

    @Test
    public void count() {
        assertEquals(4, (long) familyRepository.count());
    }

    @Test
    @Transactional
    public void findAll() {
        List<Family> families = familyRepository.getAll();
        assertEquals(4, families.size());
        assertFamily(0, families.get(0));
        assertFamily(1, families.get(1));
        assertFamily(2, families.get(2));
        assertFamily(3, families.get(3));
    }

    @Test
    @Transactional
    public void findByFamilyName() {
        assertFamily(0, familyRepository.getBy("Valenzuela"));
        assertFamily(1, familyRepository.getBy("Garcia"));
        assertFamily(2, familyRepository.getBy("Tupac"));
        assertFamily(3, familyRepository.getBy("Biggie"));
    }

    @Test
    @Transactional
    public void findOne() {
        assertFamily(0, familyRepository.getOne(1L));
        assertFamily(1, familyRepository.getOne(2L));
        assertFamily(2, familyRepository.getOne(3L));
        assertFamily(3, familyRepository.getOne(4L));
    }

    @Test
    @Transactional
    public void save_newFamily() {
        assertEquals(4, (long) familyRepository.count());
        Family family = new Family("newbee", "http://sampleurl.com/thumbnail5", "http://sampleurl.com/standard5", "Triple Homicide5",
                5, LocalDate.of(1989, 01, 05));
        Family saved = familyRepository.save(family);
        assertEquals(5, (long) familyRepository.count());
        assertFamily(4, saved);
        assertFamily(4, familyRepository.getOne(5L));
    }

    @Test
    @Transactional
    public void save_existingFamily() {
        assertEquals(4, (long) familyRepository.count());
        Family family = new Family("arthur", "http://sampleurl.com/thumbnail10", "http://sampleurl.com/standard10",
                "Triple Homicide10", 10, LocalDate.of(1989, 01, 10));
        family.setFamilyId(4L);
        Family saved = familyRepository.save(family);
        FAMILIES[3] = saved;
        assertFamily(3, saved);
        assertEquals(4, (long) familyRepository.count());
        Family updated = familyRepository.getOne(4L);
        assertFamily(3, updated);
    }

}
