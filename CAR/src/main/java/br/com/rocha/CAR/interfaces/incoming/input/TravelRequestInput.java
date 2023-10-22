package br.com.rocha.CAR.interfaces.incoming.input;

import lombok.Data;

@Data
public class TravelRequestInput {

    long passengerId;
    String origin;
    String destination;
}
