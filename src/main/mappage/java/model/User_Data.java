package com.billhub.main.mappage.java.model;
public class User_Data{
    private double Rent;
    private double Cost;
    private double income;

    public User_Data(double rent, double cost, double income) {
        Rent = rent;
        Cost = cost;
        this.income = income;
    }

    public double getRent() {
        return Rent;
    }

    public double getCost() {
        return Cost;
    }

    public double getIncome() {
        return income;
    }
}