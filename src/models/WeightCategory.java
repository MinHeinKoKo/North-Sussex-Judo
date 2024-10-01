package models;

public class WeightCategory {
    private String categoryName;
    private double upperWeightLimit;

    // Constructor
    public WeightCategory(String categoryName, double upperWeightLimit) {
        this.categoryName = categoryName;
        this.upperWeightLimit = upperWeightLimit;
    }

    // Static constants for weight categories
    public static final WeightCategory HEAVYWEIGHT = new WeightCategory("Heavyweight", Double.MAX_VALUE);
    public static final WeightCategory LIGHT_HEAVYWEIGHT = new WeightCategory("Light-Heavyweight", 100);
    public static final WeightCategory MIDDLEWEIGHT = new WeightCategory("Middleweight", 90);
    public static final WeightCategory LIGHT_MIDDLEWEIGHT = new WeightCategory("Light-Middleweight", 81);
    public static final WeightCategory LIGHTWEIGHT = new WeightCategory("Lightweight", 73);
    public static final WeightCategory FLYWEIGHT = new WeightCategory("Flyweight", 66);

    // Getters
    public String getCategoryName() {
        return categoryName;
    }

    public double getUpperWeightLimit() {
        return upperWeightLimit;
    }
}
