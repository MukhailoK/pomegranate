package model;

public class Seed {
    private final double weight = 2;

    public Seed() {
    }

    @Override
    public String toString() {
        return "Seed{" +
                "weight=" + weight +
                '}';
    }

    public double getWeight() {
        return weight;
    }
}
