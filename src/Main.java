import models.*;
import services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CostCalculatorService calculator = new CostCalculatorService();
        InputValidatorService validator = new InputValidatorService();

        List<Athlete> athletes = new ArrayList<>();

        while (true) {
            System.out.println("Register a new athlete (or type 'exit' to quit):");

            System.out.print("Enter athlete name: ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            // Select training plan with input validation
            TrainingPlan selectedPlan = null;
            while (selectedPlan == null) {
                System.out.println("Choose training plan: 1. Beginner 2. Intermediate 3. Elite");
                int planOption = scanner.nextInt();
                switch (planOption) {
                    case 1:
                        selectedPlan = TrainingPlan.BEGINNER;
                        break;
                    case 2:
                        selectedPlan = TrainingPlan.INTERMEDIATE;
                        break;
                    case 3:
                        selectedPlan = TrainingPlan.ELITE;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid training plan.");
                }
            }

            // Get current weight with input validation
            double currentWeight = 0;
            while (true) {
                System.out.print("Enter current weight (kg): ");
                currentWeight = scanner.nextDouble();
                if (validator.validateWeight(currentWeight)) {
                    break;
                }
                System.out.println("Invalid weight. Please enter a positive number.");
            }

            // Select weight category with input validation
            WeightCategory selectedCategory = null;
            while (selectedCategory == null) {
                System.out.println("Choose weight category: 1. Heavyweight 2. Light-Heavyweight 3. Middleweight 4. Light-Middleweight 5. Lightweight 6. Flyweight");
                int categoryOption = scanner.nextInt();
                switch (categoryOption) {
                    case 1:
                        selectedCategory = WeightCategory.HEAVYWEIGHT;
                        break;
                    case 2:
                        selectedCategory = WeightCategory.LIGHT_HEAVYWEIGHT;
                        break;
                    case 3:
                        selectedCategory = WeightCategory.MIDDLEWEIGHT;
                        break;
                    case 4:
                        selectedCategory = WeightCategory.LIGHT_MIDDLEWEIGHT;
                        break;
                    case 5:
                        selectedCategory = WeightCategory.LIGHTWEIGHT;
                        break;
                    case 6:
                        selectedCategory = WeightCategory.FLYWEIGHT;
                        break;
                    default:
                        System.out.println("Invalid category. Please choose a valid weight category.");
                }
            }

            // Get number of competitions entered with eligibility validation
            int competitionsEntered = 0;
            if (selectedPlan != TrainingPlan.BEGINNER) {
                System.out.print("Enter number of competitions entered this month: ");
                competitionsEntered = scanner.nextInt();
            } else {
                System.out.println("Beginners cannot enter competitions.");
            }

            // Get private coaching hours with input validation
            int privateHours = 0;
            while (true) {
                System.out.print("Enter number of hours of private coaching (max 5): ");
                privateHours = scanner.nextInt();
                if (validator.validatePrivateCoachingHours(privateHours)) {
                    break;
                }
                System.out.println("Invalid number of coaching hours. Please enter between 0 and 5.");
            }

            // Create an athlete and add to the list
            Athlete athlete = new Athlete(name, selectedPlan, currentWeight, selectedCategory, competitionsEntered, privateHours);
            athletes.add(athlete);

            // Clear the buffer
            scanner.nextLine();
        }

        // Calculate and display costs for all registered athletes
        for (Athlete athlete : athletes) {
            double totalCost = calculator.calculateTotalCost(athlete);
            String weightComparison = calculator.compareWeight(athlete);

            System.out.println("Athlete: " + athlete.getName());
            System.out.printf("Total monthly cost: $%.2f\n", totalCost);
            System.out.println(weightComparison);
            System.out.println();
        }

        scanner.close();
    }
}
