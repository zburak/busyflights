package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.validation.ValidateDepartureAndReturnDate;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@ValidateDepartureAndReturnDate
@Data
public class BusyFlightsRequest {

  @NotBlank(message = "Origin must be three letters")
  @Size(min = 3, max = 3)
  private String origin;

  @NotBlank(message = "Destination mustbe three letters")
  @Size(min = 3, max = 3)
  private String destination;

  @NotBlank(message = "Departure date must have value")
  private String departureDate;

  private String returnDate;

  @Range(min = 1, max = 4, message = "Number of passengers ")
  private int numberOfPassengers;
}
