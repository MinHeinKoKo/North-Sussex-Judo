import models.*;
import services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE_BOLD = "\u001B[1;37m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CostCalculatorService calculator = new CostCalculatorService();
        InputValidatorService validator = new InputValidatorService();

        List<Athlete> athletes = new ArrayList<>();


        System.out.println();
        // Print each line in a different color
        System.out.println(GREEN + "/ \\  /|/  _ \\/  __\\/__ __\\/ \\ /|  / ___\\/ \\ /\\/ ___\\/ ___\\/  __/\\  \\//     / |/ \\ /\\/  _ \\/  _ \\" + RESET);
        System.out.println(RED + "| |\\ ||| / \\||  \\/|  / \\  | |_||  |    \\| | |||    \\|    \\|  \\   \\  /      | || | ||| | \\|| / \\|" + RESET);
        System.out.println(BLUE + "| | \\||| \\_/||    /  | |  | | ||  \\___ || \\_/|\\___ |\\___ ||  /_  /  \\   /\\_| || \\_/|| |_/|| \\_/|" + RESET);
        System.out.println(YELLOW + "\\_/  \\|\\____/\\_/\\_\\  \\_/  \\_/ \\|  \\____/\\____/\\____/\\____/\\____\\/__/\\\\  \\____/\\____/\\____/\\____/" + RESET);

        System.out.println();

        // Display program rules
        System.out.println(BLUE);
        System.out.println("Welcome to the Athlete Registration Program!");
        System.out.println("Please follow the prompts to register athletes.");
        System.out.println("Athletes can choose between Beginner, Intermediate, and Elite training plans.");
        System.out.println("Beginners cannot enter competitions but can receive private coaching (up to 5 hours).");
        System.out.println("Intermediate and Elite athletes can enter competitions and receive coaching.");
        System.out.println(RESET);

        while (true) {
            System.out.println("\nRegister a new athlete (or type "+RED +"exit"+RESET+" to quit):");

            // Asking Athlete name
            System.out.print("Enter athlete name: ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            // Get current weight with input validation
            double currentWeight = 0;
            while (true) {
                System.out.print("Enter current weight (kg): ");
                if (scanner.hasNextDouble()) {
                    currentWeight = scanner.nextDouble();
                    if (validator.validateWeight(currentWeight)) {
                        break;
                    }
                } else {
                    scanner.next(); // discard invalid input
                }
                System.out.println("Invalid weight. Please enter a positive number.");
            }

            // Select weight category with input validation
            WeightCategory selectedCategory = null;
            while (selectedCategory == null) {
                System.out.println("Choose weight category: 1. Heavyweight 2. Light-Heavyweight 3. Middleweight 4. Light-Middleweight 5. Lightweight 6. Flyweight");
                if (scanner.hasNextInt()) {
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
                } else {
                    scanner.next(); // discard invalid input
                }
            }

            // Select training plan with input validation
            TrainingPlan selectedPlan = null;
            while (selectedPlan == null) {
                System.out.println("Choose training plan: 1. Beginner 2. Intermediate 3. Elite");
                if (scanner.hasNextInt()) {
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
                } else {
                    scanner.next(); // discard invalid input
                }
            }

            // Competitions entry for non-beginners
            int competitionsEntered = 0;
            if (selectedPlan != TrainingPlan.BEGINNER) {
                while (true) {
                    System.out.print("Enter number of competitions entered this month (max 5): ");
                    if (scanner.hasNextInt()) {
                        competitionsEntered = scanner.nextInt();
                        if (competitionsEntered <= 5 && competitionsEntered >= 0) {
                            break;
                        }
                    } else {
                        scanner.next(); // discard invalid input
                    }
                    System.out.println("Invalid number. Please enter a value between 0 and 5.");
                }
            } else {
                System.out.println("Beginners cannot enter competitions.");
            }

            // Get private coaching hours with input validation
            int privateHours = 0;
            while (true) {
                System.out.print("Enter number of hours of private coaching (max 5): ");
                if (scanner.hasNextInt()) {
                    privateHours = scanner.nextInt();
                    if (validator.validatePrivateCoachingHours(privateHours)) {
                        break;
                    }
                } else {
                    scanner.next(); // discard invalid input
                }
                System.out.println("Invalid number of coaching hours. Please enter between 0 and 5.");
            }

            // Create an athlete and add to the list
            Athlete athlete = new Athlete(name, selectedPlan, currentWeight, selectedCategory, competitionsEntered, privateHours);
            athletes.add(athlete);

            // Clear the buffer
            scanner.nextLine();

            // Display cost for the current athlete
            double totalCost = calculator.calculateTotalCost(athlete);
            String weightComparison = calculator.compareWeight(athlete);
            CostCalculatorService costCalculatorService = new CostCalculatorService();
            double weeklyFee = athlete.getTrainingPlan().getWeeklyFee();
            double monthlyTrainingCost = costCalculatorService.calculateMonthlyTrainingCost(athlete);  // Use service method
            double totalCoachingCost = costCalculatorService.calculatePrivateCoachingCost(athlete);    // Use service method
            double totalCompetitionCost = costCalculatorService.calculateCompetitionCost(athlete);     // Use service method

            // Displaying the formatted receipt with a wider separator to fit all columns
            System.out.println(WHITE_BOLD + "---------------------------------------------------------------" + RESET);
            System.out.printf(WHITE_BOLD + "|           Athlete Receipt - %s                             |\n", athlete.getName() + RESET);
            System.out.println(WHITE_BOLD + "---------------------------------------------------------------" + RESET);
            System.out.printf(WHITE_BOLD + "| %-18s | %-15s | %-10s | %-6s | %-8s |\n", "Field", "Value", "Cost", "Price", "Total" + RESET);
            System.out.println(WHITE_BOLD + "---------------------------------------------------------------" + RESET);

            // Displaying athlete's training plan, coaching, and competition entries using the service methods
            System.out.printf(GREEN + "| %-18s | %-15s | %-10.2f | %-6.2f | %-8.2f |\n",
                    "Plan", athlete.getTrainingPlan().getPlanName(), weeklyFee, weeklyFee, monthlyTrainingCost);
            System.out.printf(GREEN + "| %-18s | %-15s | %-10d | %-6.2f | %-8.2f |\n",
                    "Coaching", "Private Hours", athlete.getPrivateCoachingHours(), 9.00, totalCoachingCost);
            System.out.printf(GREEN + "| %-18s | %-15s | %-10d | %-6.2f | %-8.2f |\n",
                    "Competitions", "Entered", athlete.getCompetitionsEntered(), 22.00, totalCompetitionCost);

            System.out.println(WHITE_BOLD + "---------------------------------------------------------------" + RESET);
            System.out.printf(YELLOW + "| %-43s | %-10.2f |\n", "Total Monthly Cost", totalCost);
            System.out.println(WHITE_BOLD + "---------------------------------------------------------------" + RESET);

            System.out.println();
            System.out.println(BLUE + weightComparison + RESET);  // Assuming weightComparison is set somewhere


            // Ask if the user wants to register another athlete
            while (true) {
                System.out.print("Do you want to register another athlete? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("no")) {
                    // Display final summary and exit
                    System.out.println("Athlete registration complete. Here is a summary of all registered athletes:");
                    for (Athlete ath : athletes) {
                        totalCost = calculator.calculateTotalCost(ath);
                        weightComparison = calculator.compareWeight(ath);

                        System.out.println("Athlete: " + ath.getName());
                        System.out.printf("Total monthly cost: $%.2f\n", totalCost);
                        System.out.println(weightComparison);
                        System.out.println();
                    }
                    scanner.close();
                    return;
                } else if (response.equals("yes")) {
                    break;
                } else {
                    System.out.println("Invalid response. Please type 'yes' or 'no'.");
                }
            }
        }

        scanner.close();
    }
}
