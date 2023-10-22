package br.com.rocha.CAR.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class TravelRequest {

    @Id
    @GeneratedValue
    Long Id;
    @ManyToOne
    Passenger passenger;
    String origin;
    String destination;
    @Enumerated(EnumType.STRING)
    TravelRequestStatus status;
    Date creationDate;



}
