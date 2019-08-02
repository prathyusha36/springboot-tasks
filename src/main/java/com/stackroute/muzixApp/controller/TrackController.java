package com.stackroute.muzixApp.controller;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.exception.UserNotFoundException;
import com.stackroute.muzixApp.service.TrackSerciceImpl;
import com.stackroute.muzixApp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService) {

        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Track track) throws UserAlreadyExistsException{
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("tracks")
    public ResponseEntity<?> saveUser1(@RequestBody List<Track> track) throws UserAlreadyExistsException{
        ResponseEntity responseEntity;
       for(Track t:track) {
           trackService.saveTrack(t);
       }
        responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id)throws UserNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<Track>(trackService.getTrackById(id), HttpStatus.CREATED);
        return responseEntity;
        }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        trackService.deleteTrack(id);
        return new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateUser(@RequestBody Track track) {
        trackService.updateUser(track);
        return new ResponseEntity<Track>(track, HttpStatus.OK);
    }

    @GetMapping("track/name/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable(value="name") String name) {
        List<Track> getTrack=trackService.findByName(name);
        return new ResponseEntity<List<Track>>(getTrack,HttpStatus.OK);
    }
}
