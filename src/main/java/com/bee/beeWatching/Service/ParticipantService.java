package com.bee.beeWatching.Service;


import com.bee.beeWatching.Exception.ResourceNotFoundException;
import com.bee.beeWatching.Model.Participant;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface ParticipantService {

    Participant saveParticipant(Participant participant);

    Page<Participant> findAll(Pageable pageable);

    Participant updateParticipant(int id, Participant participant);

    void deleteById(int id);

    public List<Participant> findByName(String name);

    public Participant findById(int id) throws ResourceNotFoundException;

    public Participant setParticipantAvatar(int id, String base64Image);

    Participant setAvatar(int id, MultipartFile file) throws ResourceNotFoundException;

    Resource getParticipantAvatar(int id) throws ResourceNotFoundException;
}
