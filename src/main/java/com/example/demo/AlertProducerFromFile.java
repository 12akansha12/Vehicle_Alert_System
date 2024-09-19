package com.example.demo;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AlertProducerFromFile {

    private final static String QUEUE_NAME = "vehicle_alert_queue";
    private final static int ALERTS_PER_SECOND = 1000;  // Target rate
    private final static String FILE_PATH = "/home/akansha.singh/alert";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            ObjectMapper objectMapper = new ObjectMapper();

            List<VehicleAlert> alerts = readAlertsFromFile(FILE_PATH);
            if (alerts.isEmpty()) {
                System.err.println("No alerts found in file.");
                return;
            }

            int totalAlerts = alerts.size();
            int index = 0;

            while (true) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < ALERTS_PER_SECOND; i++) {
                    if (index >= totalAlerts) {
                        alerts = readAlertsFromFile(FILE_PATH);
                        totalAlerts = alerts.size();
                        index = 0;
                    }

                    VehicleAlert alert = alerts.get(index);
                    String message = objectMapper.writeValueAsString(alert);
                    System.out.println("Sending alert: " + message);
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

                    index++;
                }

                long elapsed = System.currentTimeMillis() - startTime;
                long sleepTime = 1000 - elapsed;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }
        }
    }

    private static List<VehicleAlert> readAlertsFromFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(fileName)));
            System.out.println("JSON Content Length: " + jsonContent.length());

            List<VehicleAlert> alerts = objectMapper.readValue(jsonContent, new TypeReference<List<VehicleAlert>>() {});
            System.out.println("Number of Alerts Read: " + alerts.size());

            for (VehicleAlert alert : alerts) {
                System.out.println("Alert: " + alert);
            }

            return alerts;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
