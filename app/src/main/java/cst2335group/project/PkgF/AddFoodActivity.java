package cst2335group.project.PkgF;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cst2335group.project.R;

public class AddFoodActivity extends AppCompatActivity {

    EditText name, cal, fat, carbs;
    String nameStr, calStr, fatStr, carbsStr;
    boolean updateFlag = false;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        name = findViewById(R.id.food_name);
        cal = findViewById(R.id.food_calories);
        fat = findViewById(R.id.food_fats);
        carbs = findViewById(R.id.food_carbs);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            Id = extras.getInt("Id");
            updateFlag = true;
            DatabaseHandler handler = new DatabaseHandler(this);
            FoodModel model = handler.getFoodItem(Id);
           // showToast(model.toString());

            name.setText(model.getFoodName());
            cal.setText(model.getCalories() + "");
            fat.setText(model.getTotalFat() + "");
            carbs.setText(model.getTotalCarbs() + "");
        }


    }


    public boolean checkData() {
        nameStr = name.getText().toString();
        calStr = cal.getText().toString();
        fatStr = fat.getText().toString();
        carbsStr = carbs.getText().toString();

        if (nameStr.isEmpty() && calStr.isEmpty() && fatStr.isEmpty() && carbsStr.isEmpty()) {
            showToast("Fields are Empty");
            return false;
        } else if (nameStr.isEmpty() || calStr.isEmpty() || fatStr.isEmpty() || carbsStr.isEmpty()) {

            if (nameStr.isEmpty()) {
                showToast("Please! Enter Name");
                return false;
            }

            if (calStr.isEmpty()) {
                showToast("Please! Enter Calories");
                return false;
            }

            if (fatStr.isEmpty()) {
                showToast("Please! Enter Fats");
                return false;
            }

            if (carbsStr.isEmpty()) {
                showToast("Please! Enter Carbohydrates");

            }

        } else {
            return true;
        }
        return false;
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void addFoodClick(View view) {
        if (checkData()) {


            FoodModel model = new FoodModel();


            model.setFoodName(nameStr);
            model.setCalories(Double.parseDouble(calStr));
            model.setTotalFat(Double.parseDouble(fatStr));
            model.setTotalCarbs(Double.parseDouble(carbsStr));

            model.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));

            DatabaseHandler handler = new DatabaseHandler(this);

            if (updateFlag) {
                model.setId(Id);
                handler.updateFoodItem(model);
                showToast("Food Item Updated.");
                finish();
            } else {
                handler.addFoodItem(model);
                showToast("Food Item Added.");
                finish();
            }


        }
    }

}
