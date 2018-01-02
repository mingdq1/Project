package cst2335group.project.PkgF;

/**
 * Created by mac on 02/01/2018.
 */

public class CalorieModel {
    public String date;
    public String totalCalories;

    public CalorieModel(){}


    public CalorieModel(String date  , String totalCalories)
    {
        this.date = date;
        this.totalCalories = totalCalories;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalCalories(String totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getDate() {
        return date;
    }

    public String getTotalCalories() {
        return totalCalories;
    }

    @Override
    public String toString() {
        return date +" "+totalCalories;
    }
}
