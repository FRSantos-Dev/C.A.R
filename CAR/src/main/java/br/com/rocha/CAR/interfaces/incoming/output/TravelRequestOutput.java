package br.com.rocha.CAR.interfaces.incoming.output;

import br.com.rocha.CAR.domain.TravelRequestStatus;
import lombok.Data;
import java.util.Date;

@Data
public class TravelRequestOutput {

    Long id;
    String origin;
    String destination;
    TravelRequestStatus status;
    Date creationDate;
}
