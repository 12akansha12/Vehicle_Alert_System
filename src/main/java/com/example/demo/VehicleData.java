package com.example.demo;


import lombok.Getter;

public class VehicleData {
    @Getter
    private String vehicleId;
    @Getter
    private int batteryPercentage;
    private long lastDataReceivedTimestamp;

    public boolean isOfflineForLast20Minutes(int offlineThresholdMinutes) {
        return System.currentTimeMillis() - lastDataReceivedTimestamp > 20 * 60 * 1000;
    }

    public boolean isOfflineForMoreThan10Minutes() {
        return System.currentTimeMillis() - lastDataReceivedTimestamp > 10 * 60 * 1000;
    }
}
