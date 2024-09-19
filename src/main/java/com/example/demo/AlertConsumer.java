package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.ExceptionHandler;

public class AlertConsumer {

    private final static String QUEUE_NAME = "vehicle_alert_queue";
    private static ConnectionFactory factory = new ConnectionFactory();
    private static int totalAlertCount = 0;

    public static void main(String[] argv) throws Exception {
        factory.setHost("localhost");

        while (true) {
            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);
                System.out.println(" [*] Waiting for messages. To exit press Ctrl+C");

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    try {
                        processAlert(message);
                        // Manually acknowledge only after successful processing
                        if (channel.isOpen()) {
                            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        } else {
                            System.err.println("Channel is closed, unable to ack message");
                        }
                    } catch (Exception e) {
                        System.err.println("Error processing or acknowledging message: " + e.getMessage());
                        e.printStackTrace();
                    }
                };

                channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });

                // Keep the program running to process messages
                Thread.sleep(Long.MAX_VALUE);
            } catch (Exception e) {
                System.err.println("Error with RabbitMQ connection or channel: " + e.getMessage());
                e.printStackTrace();
                // Optionally add a sleep to avoid rapid reconnection attempts
                Thread.sleep(5000);
            }
        }
    }

    private static void processAlert(String message) {
        System.out.println("Received raw message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            VehicleAlert alert = objectMapper.readValue(message, VehicleAlert.class);
            System.out.println("Processed alert: " + alert);
            // Other processing logic...
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
