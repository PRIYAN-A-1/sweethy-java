package model;

public enum ModelType {
    MACHINE_LEARNING("Machine Learning"),
    DEEP_LEARNING("Deep Learning"),
    COMPUTER_VISION("Computer Vision"),
    NATURAL_LANGUAGE_PROCESSING("Natural Language Processing"),
    GENERATIVE_AI("Generative AI"),
    RECOMMENDATION_SYSTEM("Recommendation System"),
    TIME_SERIES("Time Series"),
    REINFORCEMENT_LEARNING("Reinforcement Learning");

    private final String displayName;

    ModelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
