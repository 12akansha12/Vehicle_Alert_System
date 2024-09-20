package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleAlertGenerator {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<VehicleAlert> alerts = generateVehicleAlerts(1000);
        writeAlertsToFile(alerts, "vehicle_alerts.json");
    }

    private static List<VehicleAlert> generateVehicleAlerts(int count) {
        List<VehicleAlert> alerts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int id = i + 1;
            boolean iotConnected = RANDOM.nextBoolean();
            int batteryPercentage = RANDOM.nextInt(101);
            String driveMode = RANDOM.nextBoolean() ? "Eco" : "Sport"; // Random drive mode
            String createdAt = String.valueOf(System.currentTimeMillis());
            String updatedAt = String.valueOf(System.currentTimeMillis());
            String imei = "IMEI_" + (i + 1);
            String batteryTemp = RANDOM.nextInt(100) + "°C"; // Random battery temperature
            double co2Saved = RANDOM.nextDouble() * 100; // Random CO2 saved
            double fuelSaved = RANDOM.nextDouble() * 50; // Random fuel saved
            String lastSeenAt = String.valueOf(System.currentTimeMillis());
            double latitude = -90 + (180 * RANDOM.nextDouble()); // Random latitude
            double longitude = -180 + (360 * RANDOM.nextDouble()); // Random longitude
            String vehicleCondition = RANDOM.nextBoolean() ? "Good" : "Needs Maintenance"; // Random vehicle condition
            int lastSpeed = RANDOM.nextInt(121); // Random speed between 0 and 120
            int maxSpeed = 120; // Assuming max speed is 120
            double totalOperationalHours = RANDOM.nextDouble() * 1000; // Random operational hours
            double distanceTravelledToday = RANDOM.nextDouble() * 100; // Random distance
            double totalOdometer = RANDOM.nextDouble() * 50000; // Random total odometer
            double dailyAvgSpeed = RANDOM.nextDouble() * 80; // Random daily average speed
            double monthlyRuntime = RANDOM.nextDouble() * 720; // Random runtime in hours
            double totalEnergy = RANDOM.nextDouble() * 5000; // Random total energy
            double totalChargeCurrent = RANDOM.nextDouble() * 100; // Random charge current
            double totalDischargeCurrent = RANDOM.nextDouble() * 100; // Random discharge current

            VehicleAlert alert = new VehicleAlert(id, iotConnected, batteryPercentage, driveMode, createdAt, updatedAt,
                    imei, batteryTemp, co2Saved, fuelSaved, lastSeenAt, latitude, longitude, vehicleCondition,
                    lastSpeed, maxSpeed, totalOperationalHours, distanceTravelledToday, totalOdometer,
                    dailyAvgSpeed, monthlyRuntime, totalEnergy, totalChargeCurrent, totalDischargeCurrent);

            alerts.add(alert);
        }

        return alerts;
    }

    private static void writeAlertsToFile(List<VehicleAlert> alerts, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), alerts);
            System.out.println("Alerts written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing alerts to file: " + e.getMessage());
        }
    }
}
