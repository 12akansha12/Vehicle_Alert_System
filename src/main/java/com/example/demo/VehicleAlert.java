package com.example.demo;

import lombok.Getter;

@Getter
public class VehicleAlert {

    private int id;
    private boolean iotConnected;
    private int batteryPercentage;
    private String driveMode;
    private String createdAt;
    private String updatedAt;
    private String imei;
    private String batteryTemp;
    private double co2Saved;
    private double fuelSaved;
    private String lastSeenAt;
    private double latitude;
    private double longitude;
    private String vehicleCondition;
    private int lastSpeed;
    private int maxSpeed;
    private double totalOperationalHours;
    private double distanceTravelledToday;
    private double totalOdometer;
    private double dailyAvgSpeed;
    private double monthlyRuntime;
    private double totalEnergy;
    private double totalChargeCurrent;
    private double totalDischargeCurrent;

    // Constructor
    public VehicleAlert(int id, boolean iotConnected, int batteryPercentage, String driveMode, String createdAt,
                        String updatedAt, String imei, String batteryTemp, double co2Saved, double fuelSaved,
                        String lastSeenAt, double latitude, double longitude, String vehicleCondition,
                        int lastSpeed, int maxSpeed, double totalOperationalHours, double distanceTravelledToday,
                        double totalOdometer, double dailyAvgSpeed, double monthlyRuntime, double totalEnergy,
                        double totalChargeCurrent, double totalDischargeCurrent) {
        this.id = id;
        this.iotConnected = iotConnected;
        this.batteryPercentage = batteryPercentage;
        this.driveMode = driveMode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imei = imei;
        this.batteryTemp = batteryTemp;
        this.co2Saved = co2Saved;
        this.fuelSaved = fuelSaved;
        this.lastSeenAt = lastSeenAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicleCondition = vehicleCondition;
        this.lastSpeed = lastSpeed;
        this.maxSpeed = maxSpeed;
        this.totalOperationalHours = totalOperationalHours;
        this.distanceTravelledToday = distanceTravelledToday;
        this.totalOdometer = totalOdometer;
        this.dailyAvgSpeed = dailyAvgSpeed;
        this.monthlyRuntime = monthlyRuntime;
        this.totalEnergy = totalEnergy;
        this.totalChargeCurrent = totalChargeCurrent;
        this.totalDischargeCurrent = totalDischargeCurrent;
    }
    public VehicleAlert() {}
}
