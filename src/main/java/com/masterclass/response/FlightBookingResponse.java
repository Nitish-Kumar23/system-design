package com.masterclass.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FlightBookingResponse implements Serializable {

    private Integer tripId;

    private String seatLabel;

    public FlightBookingResponse() {
    }

    public FlightBookingResponse(Integer tripId, String seatLabel) {
        this.tripId = tripId;
        this.seatLabel = seatLabel;
    }
}
