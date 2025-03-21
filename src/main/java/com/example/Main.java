package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfRoads = -1;
        int inputInterval = -1;
        int selectedOption;

        System.out.println("Welcome to the traffic management system!");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.print("Input the number of roads: > ");
                    numberOfRoads = scanner.nextInt();
                    System.out.print("Input the interval of time: > ");
                    inputInterval = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }

            TrafficLights trafficLights = new TrafficLights(numberOfRoads, inputInterval);

            // TODO
            // Thread queueThread = new Thread();
            // queueThread.setName("QueueThread");

            while (true) {
                trafficLights.printMenu();
                System.out.print("> ");
                try {
                    selectedOption = scanner.nextInt();
                    switch (selectedOption) {
                        case 1 -> System.out.println("Road added");
                        case 2 -> System.out.println("Road deleted");
                        case 3 -> System.out.println("System opened");
                        case 0 -> {
                            System.out.println("Bye!");
                            return;
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }
        }
    }
}