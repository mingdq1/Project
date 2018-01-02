package cst2335group.project.PkgF;

import java.sql.Time;
import java.util.Date;

/**
 * Created by mac on 31/12/2017.
 */

public class FoodModel {

    public int Id;
    public String foodName;
    public double calories , totalFat , totalCarbs;
    public String timeStamp;


    public FoodModel(){}

    public FoodModel(int Id){
        this.Id = Id;
    }

    public FoodModel(int Id , String foodName , double calories , double totalFat , double totalCarbs , String timeStamp)
    {
        this.Id = Id;
        this.foodName = foodName;
        this.calories = calories;
        this.totalFat = totalFat;
        this.totalCarbs = totalCarbs;
        this.timeStamp = timeStamp;
    }


    public void setId(int id) {
        Id = id;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getCalories() {
        return calories;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        return foodName;
    }
}
