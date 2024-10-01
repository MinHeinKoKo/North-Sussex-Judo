package models;

public class TrainingPlan {
    private String planName;
    private double weeklyFee;

    // Constructor
    public TrainingPlan(String planName, double weeklyFee) {
        this.planName = planName;
        this.weeklyFee = weeklyFee;
    }

    // Static constants for different training plans
    public static final TrainingPlan BEGINNER = new TrainingPlan("Beginner", 25.00);
    public static final TrainingPlan INTERMEDIATE = new TrainingPlan("Intermediate", 30.00);
    public static final TrainingPlan ELITE = new TrainingPlan("Elite", 35.00);

    // Getters
    public String getPlanName() {
        return planName;
    }

    public double getWeeklyFee() {
        return weeklyFee;
    }
}
