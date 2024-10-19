package services;

import models.Athlete;

public class CostCalculatorService {

    // Method to calculate monthly training cost
    public double calculateMonthlyTrainingCost(Athlete athlete) {
        return athlete.getTrainingPlan().getWeeklyFee() * 4; // 4 weeks in a month
    }

    // Method to calculate private coaching cost
    public double calculatePrivateCoachingCost(Athlete athlete) {
        double coachingRate = 9.00;
        return athlete.getPrivateCoachingHours() * coachingRate * 4; // Assuming 4 weeks
    }

    // Method to calculate competition cost
    public double calculateCompetitionCost(Athlete athlete) {
        double competitionFee = 22.00;
        return athlete.getCompetitionsEntered() * competitionFee;
    }

    // Method to calculate total monthly cost
    public double calculateTotalCost(Athlete athlete) {
        double totalCost = calculateMonthlyTrainingCost(athlete);
        totalCost += calculatePrivateCoachingCost(athlete);
        totalCost += calculateCompetitionCost(athlete);
        return totalCost;
    }

    // Method to compare athlete's weight to competition category limit
    public String compareWeight(Athlete athlete) {
        double currentWeight = athlete.getCurrentWeight();
        double categoryLimit = athlete.getCompetitionCategory().getUpperWeightLimit();
        if (currentWeight > categoryLimit) {
            return "Athlete is over the competition weight limit.";
        } else if (currentWeight == categoryLimit) {
            return "Athlete is at the exact competition weight limit.";
        } else {
            return "Athlete is under the competition weight limit.";
        }
    }
}
