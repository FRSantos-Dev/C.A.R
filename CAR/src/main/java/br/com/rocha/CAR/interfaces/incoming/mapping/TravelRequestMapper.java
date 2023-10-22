package br.com.rocha.CAR.interfaces.incoming.mapping;

import br.com.rocha.CAR.domain.Passenger;
import br.com.rocha.CAR.domain.PassengerRepository;
import br.com.rocha.CAR.domain.TravelRequest;
import br.com.rocha.CAR.interfaces.PassengerAPI;
import br.com.rocha.CAR.interfaces.incoming.input.TravelRequestInput;
import br.com.rocha.CAR.interfaces.incoming.output.TravelRequestOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class TravelRequestMapper {

    @Autowired
    PassengerRepository passengerRepository;

    public TravelRequest map(TravelRequestInput input){

        Passenger passenger = passengerRepository.findById(input.getPassengerId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TravelRequest travelRequest = new TravelRequest();
        travelRequest.setOrigin(input.getOrigin());
        travelRequest.setDestination(input.getDestination());
        travelRequest.setPassenger(passenger);

        return travelRequest;
    }

    public TravelRequestOutput map(TravelRequest travelRequest){
        TravelRequestOutput travelRequestOutput = new TravelRequestOutput();
        travelRequestOutput.setCreationDate(travelRequest.getCreationDate());
        travelRequestOutput.setDestination(travelRequest.getDestination());
        travelRequestOutput.setId(travelRequestOutput.getId());
        travelRequestOutput.setOrigin(travelRequestOutput.getOrigin());
        travelRequestOutput.setStatus(travelRequestOutput.getStatus());

        return travelRequestOutput;
    }

    public EntityModel<TravelRequestOutput> buildOutputModel(TravelRequest travelRequest,
                                                        TravelRequestOutput output){
        EntityModel<TravelRequestOutput> model = new EntityModel<>(output);

        Link passengerLink = WebMvcLinkBuilder
                .linkTo(PassengerAPI.class)
                .slash(travelRequest.getPassenger().getId())//helps return complete link with specific passenger"id"
                .withRel("passenger")
                .withTitle(travelRequest.getPassenger().getName());
        model.add(passengerLink);
        return model;
    }
}
