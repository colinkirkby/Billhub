package com.billhub.main.mappage.java.model;

public class Map_Data_point{
    private double av_Rent;
    private double av_Cost;
    private double av_Income;
    private int num_datapoints;

    public Map_Data_point(){
        num_datapoints = 0;
    }

    public void add_new(User_Data new_data){
        double tot_rent = av_Rent*num_datapoints;
        tot_rent = tot_rent + new_data.getRent();

        double tot_cost = av_Cost*num_datapoints;
        tot_cost = tot_cost + new_data.getCost();

        double tot_income = av_Income *num_datapoints;
        tot_income = tot_income + new_income.getIncome();

        num_datapoints++;
        av_Cost = tot_cost/num_datapoints;
        av_Income = tot_income/num_datapoints;
        av_Rent = tot_rent/num_datapoints;
    }

    public double getAv_Rent() {
        return av_Rent;
    }

    public double getAv_Cost() {
        return av_Cost;
    }

    public double getAv_Income() {
        return av_Income;
    }

    public int getNum_datapoints() {
        return num_datapoints;
    }
}