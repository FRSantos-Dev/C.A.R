package br.com.rocha.CAR.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Component
public class TravelService {

    @Autowired
    TravelRequestRepository travelRequestRepository;

    @PostMapping
    public TravelRequest saveTravelRequest(TravelRequest travelRequest){
        travelRequest.setStatus(TravelRequestStatus.CREATED);
        travelRequest.setCreationDate(new Date());
        return travelRequestRepository.save(travelRequest);
    }
}

