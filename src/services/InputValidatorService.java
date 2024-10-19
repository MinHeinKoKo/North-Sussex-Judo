package services;

import models.Athlete;
import models.TrainingPlan;

public class InputValidatorService {

    // Validate that the athlete's weight is within a reasonable range
    public boolean validateWeight(double weight) {
        return weight > 0 && weight < 500; // Weight should be a positive number
    }

    // Validate that the private coaching hours are within limits
    public boolean validatePrivateCoachingHours(int hours) {
        return hours >= 0 && hours <= 5; // Maximum of 5 hours
    }

    // Validate that the athlete is eligible for competitions (only Intermediate and Elite)
    public boolean validateTrainingPlanEligibility(Athlete athlete) {
        TrainingPlan plan = athlete.getTrainingPlan();
        return plan == TrainingPlan.INTERMEDIATE || plan == TrainingPlan.ELITE;
    }
}
