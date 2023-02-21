package com.bee.beeWatching.Controller;


import com.bee.beeWatching.Exception.FileStorageException;
import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Participant;
import com.bee.beeWatching.Service.ParticipantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/participants")
@CrossOrigin
public class ParticipantController {

    @Autowired
    ParticipantService participantService;


    private static final Logger logger = LogManager.getLogger(ParticipantController.class);



    @GetMapping("/getAll")
    public ResponseEntity<Page<Participant>> getAllParticipants(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "id") String sortBy) {
        try{
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<Participant> participants = participantService.findAll(pageable);
            logger.info(String.format("Users: %s", participants.getContent()));
            return new ResponseEntity<>(participants, HttpStatus.OK);
        } catch (Exception e){
            logger.error(String.format("Error: %s",e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Participant>> getUsersByName(@PathVariable String name)
    {
        try {
            List<Participant> participants = participantService.findByName(name);
            if (participants.isEmpty()) {
                logger.debug("No participant found with name {}", name);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                logger.debug("Found participants with name {}", name);
                return new ResponseEntity<>(participants, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error occurred while trying to get participants with name {}", name, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Participant> createParticipant(@RequestBody @Validated Participant newParticipant)
    {
        try {
            Participant savedParticipant = participantService.saveParticipant(newParticipant);
            logger.info("New Participant with name " + savedParticipant.getName() + " is registered.");
            return new ResponseEntity<>(savedParticipant, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occured while registering Participant", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Participant> updateUser(@PathVariable int id, @Validated @RequestBody Participant participant)
    {
        try {
            Participant updatedParticipant = participantService.updateParticipant(id, participant);
            if (updatedParticipant != null) {
                logger.info("Participant with id {} updated successfully", id);
                return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
            } else {
                logger.error("Participant with id {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while updating user with id {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            participantService.deleteById(id);
            logger.info("Participant with id {} was successfully deleted.", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while deleting participant with id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/setAvatar/{id}")
    public ResponseEntity<Participant> setAvatar(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            Participant user = participantService.setAvatar(id, file);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            logger.error("Participant not found: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (FileStorageException e) {
            logger.error("File storage error: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAvatar/{id}")
    public ResponseEntity<Resource> getAvatarByUser(@PathVariable int id, HttpServletRequest request) throws IOException
    {
        try {
            Resource resource = participantService.getParticipantAvatar(id);
            if (resource != null) {
                String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (ResourceNotFoundException e) {
            logger.error("Error getting participant avatar: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
