package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.TrackAlreadyExistsException;
import com.stackroute.muzixApp.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks() ;
    public void deleteTrack(int trackId) ;
    public Track getTrackById(int trackId) throws TrackNotFoundException;
    public Track updateTrack(Track track);
    public List<Track> findByName(String trackName);
}
