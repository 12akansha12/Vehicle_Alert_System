package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleAlert {
    private String id;
    private String type;
    private String message;
    private String severity;
    private String timestamp;
    private String vehicleId;
    private Location location;

    public VehicleAlert() {
    }

    public VehicleAlert(String id, String type, String message, String severity, String timestamp, String vehicleId, Location location) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.severity = severity;
        this.timestamp = timestamp;
        this.vehicleId = vehicleId;
        this.location = location;
    }

    @Override
    public String toString() {
        return "VehicleAlert{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", severity='" + severity + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", location=" + location +
                '}';
    }
}
