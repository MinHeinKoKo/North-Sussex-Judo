package models;

public class Athlete {
    private String name;
    private TrainingPlan trainingPlan;
    private double currentWeight;
    private WeightCategory competitionCategory;
    private int competitionsEntered;
    private int privateCoachingHours;

    // Constructor
    public Athlete(String name, TrainingPlan trainingPlan, double currentWeight, WeightCategory competitionCategory,
                   int competitionsEntered, int privateCoachingHours) {
        this.name = name;
        this.trainingPlan = trainingPlan;
        this.currentWeight = currentWeight;
        this.competitionCategory = competitionCategory;
        this.competitionsEntered = competitionsEntered;
        this.privateCoachingHours = privateCoachingHours;
    }

    // Getters
    public String getName() {
        return name;
    }

    public TrainingPlan getTrainingPlan() {
        return trainingPlan;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public WeightCategory getCompetitionCategory() {
        return competitionCategory;
    }

    public int getCompetitionsEntered() {
        return competitionsEntered;
    }

    public int getPrivateCoachingHours() {
        return privateCoachingHours;
    }
}
