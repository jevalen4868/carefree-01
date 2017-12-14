package org.sp.j01.carefree.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sp.j01.carefree.api.db.FamilyRepository;
import org.sp.j01.carefree.api.domain.Family;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.sp.j01.carefree.api.TestData.FAMILIES;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class FamilyControllerTest {
    private static final Logger logger = LogManager.getLogger(FamilyControllerTest.class);

    @Before
    public void setUp() throws Exception {
        Family family = FAMILIES[0];
    }

    @Test
    public void testHelloWorld() throws Exception {
        FamilyRepository familyRepository = mock(FamilyRepository.class);
        FamilyController controller = new FamilyController(familyRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/family/helloWorld/John"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello World, and John, if you're there."));
    }

    @Test
    public void testGetByFamilyId() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Family expectedFamily = FAMILIES[0];
        String expectedFamilyAsJson = mapper.writeValueAsString(expectedFamily);
        logger.info("Expected content:" + expectedFamilyAsJson);
        FamilyRepository mockRepository = mock(FamilyRepository.class);
        when(mockRepository.getOne(1l)).thenReturn(expectedFamily);

        FamilyController controller = new FamilyController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/family/get/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expectedFamilyAsJson, true));
    }

    @Test
    public void testGetAllFamilies() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Family> expectedFamilies = Arrays.asList(FAMILIES);
        String expectedFamiliesAsJson = mapper.writeValueAsString(expectedFamilies);
        logger.info("Expected content:" + expectedFamiliesAsJson);
        FamilyRepository mockRepository = mock(FamilyRepository.class);
        when(mockRepository.getAll()).thenReturn(expectedFamilies);

        FamilyController controller = new FamilyController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/family/getAll"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expectedFamiliesAsJson, true));
    }

    //  @Test
//  public void testSpittle() throws Exception {
//    Spittle expectedSpittle = new Spittle("Hello", new Date());
//    SpittleRepository mockRepository = mock(SpittleRepository.class);
//    when(mockRepository.getOne(12345)).thenReturn(expectedSpittle);
//
//    SpittleController controller = new SpittleController(mockRepository);
//    MockMvc mockMvc = standaloneSetup(controller).build();
//
//    mockMvc.perform(get("/spittles/12345"))
//      .andExpect(view().name("spittle"))
//      .andExpect(model().attributeExists("spittle"))
//      .andExpect(model().attribute("spittle", expectedSpittle));
//  }

    @After
    public void tearDown() throws Exception {
    }
}