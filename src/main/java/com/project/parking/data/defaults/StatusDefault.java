package com.project.parking.data.defaults;

import com.project.parking.data.entity.Status;
import com.project.parking.data.repository.StatusRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusDefault {

    @NonNull
    private final StatusRepository statusRepository;

        public Status getEnabled(){
        return statusRepository.findById(StatusValue.ENABLED.getValue())
                .orElseGet(Status::new);
    }

    public Status getDisabled(){
        return statusRepository.findById(StatusValue.DISABLED.getValue())
                .orElseGet(Status::new);
    }

    public Status getStatus(Long id){
        return statusRepository.findById(id)
                .orElseGet(Status::new);
    }

}
