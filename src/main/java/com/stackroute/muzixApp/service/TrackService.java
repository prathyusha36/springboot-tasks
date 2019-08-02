package com.stackroute.muzixApp.service;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.exception.UserNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws UserAlreadyExistsException;
    public List<Track> getAllTracks() ;
    public boolean deleteTrack(int trackId);
    public Track getTrackById(int trackId) throws UserNotFoundException;
    public Track updateUser(Track track);
    public List<Track> findByName(String trackName);
}
