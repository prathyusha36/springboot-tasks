package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;


public class TrackServiceTest {
    Track track;
    @Mock
    TrackRepository trackRepository;
    @InjectMocks
    TrackSerciceImpl trackSercice;
    List<Track> list=null;
    @Before
    public void setup()
    {
        //mockito initialization//
        MockitoAnnotations.initMocks(this);
        track=new Track();
        track.setId(2);
        track.setName("maharshi");
        track.setComment("good");
        list=new ArrayList<>();
        list.add(track);
    }

    @Test
    public void saveMuzixTest()  throws UserAlreadyExistsException
    {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedMuzix=trackSercice.saveTrack(track);
        Assert.assertEquals(track,savedMuzix);
        verify(trackRepository,times(1)).save(track);
    }
     @Test(expected =UserAlreadyExistsException.class)
    public void saveMuzixTestFailure() throws UserAlreadyExistsException
    {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedUser = trackSercice.saveTrack(track);
        System.out.println("savedUser" + savedUser);
        Assert.assertEquals(track,savedUser);
    }
    @Test
    public void getAllMuzix()
    {
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> muzixlist=trackSercice.getAllTracks();
        Assert.assertEquals(list,muzixlist);
    }
    @Test
   public void updateMuzixTest()
    {
       when(trackRepository.save(any())).thenReturn(track);
       Track updateMuzix=trackSercice.updateUser(track);
        Assert.assertEquals(track,updateMuzix);
        verify(trackRepository,times(1)).save(track);
    }
    @Test
      public void deleteMuzixTest()
    {
        doNothing().when(trackRepository).deleteById(any());
        boolean deleteMuzix=trackSercice.deleteTrack(track.getId());
        Assert.assertEquals(deleteMuzix,true);
        verify(trackRepository,times(1)).deleteById(track.getId());
    }

}
