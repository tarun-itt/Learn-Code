package model;

import java.time.LocalDate;

public class DataRecord {
    private final String id;
    private String name;
    private final double value;
    private LocalDate date;
    private Double doubledValue;
    private Double squaredValue;

    public DataRecord(String id, String name, double value, LocalDate date) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public DataRecord(String id, String name, double value) {
        this(id, name, value, null);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getDoubledValue() {
        return doubledValue;
    }

    public Double getSquaredValue() {
        return squaredValue;
    }

    public DataRecord withTransformedName(String transformedName) {
        DataRecord copy = copy();
        copy.name = transformedName;
        return copy;
    }

    public DataRecord withFormattedDate(LocalDate formattedDate) {
        DataRecord copy = copy();
        copy.date = formattedDate;
        return copy;
    }

    public DataRecord withDerivedValues(double doubled, double squared) {
        DataRecord copy = copy();
        copy.doubledValue = doubled;
        copy.squaredValue = squared;
        return copy;
    }

    private DataRecord copy() {
        DataRecord copy = new DataRecord(id, name, value, date);
        copy.doubledValue = doubledValue;
        copy.squaredValue = squaredValue;
        return copy;
    }
}
