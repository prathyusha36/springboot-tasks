package com.stackroute.muzixApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixApp.MuzixAppApplication;
import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MuzixAppApplication.class)
public class TrackControllerTest {
    @Mock
    private TrackService trackService;
    private Track track;
    @InjectMocks
    private TrackController trackController;
    @Autowired
    MockMvc mvc;
    private List<Track> list = null;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setId(1);
        track.setName("fdhjf");
        track.setComment("dcdwc");
        list = new ArrayList();
        list.add(track);
    }


    @Test
    public void saveTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mvc.perform(post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).saveTrack(Mockito.any(Track.class));
        verifyNoMoreInteractions(trackService);

    }

   @Test
   public void getAllTracks() throws Exception {
       when(trackService.getAllTracks()).thenReturn(list);
       mvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
               .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());

   }
    @Test
    public void updateTrack() throws Exception
    {
        when(trackService.updateUser(track)).thenReturn(track);
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

   @Test
    public void deleteTrack() throws Exception
    {
        when(trackService.deleteTrack(track.getId())).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/{id}",track.getId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
      throw new RuntimeException(e);
        }
    }
}



