package com.Spring.BillHub.model;
import java.lang.*;
public class Map_Data_Point{
    private double av_Rent;
    private double av_Cost;
    private double av_Income;
    private int num_datapoints;

    public Map_Data_Point(){
        num_datapoints = 0;
    }

    public Map_Data_Point(int i){
        av_Rent = Math.random()*3000;
        av_Rent = (Math.round(av_Rent*100))/100;
        av_Cost = Math.random()*3000;
        av_Cost = (Math.round(av_Cost*100))/100;
        av_Income = Math.random()*200000;
        av_Income = (Math.round(av_Income*100))/100;
        num_datapoints = (int)Math.floor(Math.random()*15);

    }

    public void add_new(User_Data new_data){
        double tot_rent = av_Rent*num_datapoints;
        tot_rent = tot_rent + new_data.getRent();

        double tot_cost = av_Cost*num_datapoints;
        tot_cost = tot_cost + new_data.getCost();

        double tot_income = av_Income *num_datapoints;
        tot_income = tot_income + new_data.getIncome();

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