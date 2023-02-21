package com.bee.beeWatching.Service.Impl;

import com.bee.beeWatching.Exception.FileStorageException;
import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Participant;
import com.bee.beeWatching.Repository.ParticipantRepository;
import com.bee.beeWatching.Service.FileStorageService;
import com.bee.beeWatching.Service.ParticipantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    FileStorageService fileStorageService;

    private static final Logger logger = LogManager.getLogger(ParticipantServiceImpl.class);

    @Override
    public Participant saveParticipant(Participant entity) {
        try {
            if (entity.getGuuid() == null) {
                entity.setGuuid(UUID.randomUUID());
            }
            if (entity.getCreatedAt() == null) {
                entity.setCreatedAt(new Date());
            }
            entity.setUpdatedAt(new Date());
            return participantRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error saving participant: " + e.getMessage(), e);
            throw new RuntimeException("Error saving participant", e);
        }
    }

    @Override
    public Page<Participant> findAll(Pageable pageable) {
        return participantRepository.findAll(pageable);
    }


    @Override
    public Participant updateParticipant(int id, Participant participant) {
        try{
            Participant existingParticipant = participantRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

            existingParticipant.setDiscordName(participant.getDiscordName());
            existingParticipant.setName(participant.getName());
            existingParticipant.setUpdatedAt(new Date());
            return saveParticipant(existingParticipant);
        }catch (ResourceNotFoundException e){
            logger.error("User not found with id: " + id);
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        participantRepository.deleteById(id);
    }

    @Override
    public List<Participant> findByName(String name) {
        return participantRepository.findByName(name);
    }

    public Participant setParticipantAvatar(int id, String base64Image) {
        Optional<Participant> existingParticipant = participantRepository.findById(id);
        if (existingParticipant.isPresent()){
            existingParticipant.get().setAvatarFileUpload(base64Image);
            logger.info(base64Image);
            return saveParticipant(existingParticipant.get());
        }else {
            return null;
        }
    }


    @Override
    public Participant setAvatar(int id, MultipartFile file) throws ResourceNotFoundException {
        Optional<Participant> existingParticipant = participantRepository.findById(id);
        String regex = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$";
        Pattern p = Pattern.compile(regex);
        if (existingParticipant.isPresent())
        {
            String fileName = fileStorageService.storeFile(file);
            existingParticipant.get().setAvatarFileUpload(fileName);
            Matcher m = p.matcher(fileName);
            if (m.matches())
            {
                existingParticipant.get().setAvatarFileUpload(fileName);
                logger.info("Participant with id " + id + " updated their avatar.");
                return saveParticipant(existingParticipant.get());
            }else {
                throw new FileStorageException("File pattern invalid");
            }
        }else{
            throw new ResourceNotFoundException("User not found : " + id);
        }
    }

    @Override
    public Resource getParticipantAvatar(int id) throws ResourceNotFoundException {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isPresent())
        {
            String fileName = participant.get().getAvatarFileUpload();
            if (fileName != null)
            {
                Resource resource = fileStorageService.loadFileAsResource(fileName);
                if (resource != null) {
                    return resource;
                } else {
                    throw new ResourceNotFoundException("Avatar file not found");
                }
            }
        }
        throw new ResourceNotFoundException("Participant not found : " + id);
    }
    @Override
    public Participant findById(int id) throws ResourceNotFoundException {
        Optional<Participant> optionalEntity = participantRepository.findById(id);
        if (optionalEntity.isPresent()) {
            logger.info(String.format("Participant with id %d found!", id));
            return optionalEntity.get();
        } else {
            logger.error(String.format("Participant with id %d not found!", id));
            throw new ResourceNotFoundException(String.format("Participant with id %d not found!", id));
        }
    }
}
