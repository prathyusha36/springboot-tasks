package com.stackroute.muzixApp.controller;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.exception.UserAlreadyExistsException;
import com.stackroute.muzixApp.repository.TrackRepository;
import com.stackroute.muzixApp.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    private TrackRepository trackRepository;
    private TrackService trackService;
    public TrackController(TrackService trackService) {
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }
    @GetMapping("track/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<Track>(trackService.getTrackById(id), HttpStatus.OK);
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
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<?> getTrackByName(@RequestParam(value="name") String name) {
        Track getTrack=(Track)trackService.findByName(name);
       return new ResponseEntity<Track>(getTrack,HttpStatus.OK);


    }

}
