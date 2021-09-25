package service;

public enum Groceries {
    APPLE("Apples"), ORANGE("Oranges");

    public final String label;

    Groceries(String label) {
        this.label = label;
    }
}
