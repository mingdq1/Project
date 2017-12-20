package cst2335group.project.PkgF;

import java.util.Date;

/**
 * Created by mingd on 2017-12-20.
 */

public class Food {
    int id;
    String name;
    double  calories, fat, carbohydrate;
    Date date;

    public Food(int id, String name, double calories, double fat, double carbohydrate, Date date) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public Date getDate() {
        return date;
    }
}
