package cst2335group.project.PkgF;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import cst2335group.project.R;

public class StatsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Date> dates;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        total = findViewById(R.id.total);
        listView = findViewById(R.id.stats_lv);
        dates = new ArrayList<>();

        List<FoodModel> models = new DatabaseHandler(this).getAllFoods();
        try {
            dates = getAllDates(models);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Double> calories = new ArrayList<>();
        double grandTotal = 0;
        for (Date d : dates) {
            double totalCalories = 0;
            for (FoodModel foodModel : new DatabaseHandler(this).getAllFoods()) {
                Date date;
                try {
                    date = parseDate(foodModel.timeStamp);
                    if (date.compareTo(d) == 0) {
                        totalCalories += foodModel.getCalories();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            totalCalories = totalCalories / 1;
            grandTotal += totalCalories;
            calories.add(totalCalories);
        }

        total.setText("Overall Average = "+String.format("%.2f", grandTotal / calories.size()) );




        ArrayList<CalorieModel> adapterModels = new ArrayList<>();
        for (int i = 0 ; i < dates.size() ; i++) {
            String dateFormat = dates.get(i).toString().substring(0, 10) + " " + dates.get(i).toString().substring(30);
            CalorieModel model = new CalorieModel(dateFormat, calories.get(i)+"");
            adapterModels.add(model);
            Log.d("Total Calories" , model.toString());
        }

        StatsAdapter adapter = new StatsAdapter(this , adapterModels);
        listView.setAdapter(adapter);




    }



    public Date parseDate(String timeStamp) throws ParseException {
        String date = timeStamp.substring(0, Math.min(timeStamp.length(), 10));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(date);
        return startDate;
    }

    public ArrayList<Date> getAllDates(List<FoodModel> models) throws ParseException {
        ArrayList<Date> dates = new ArrayList<>();
        for (FoodModel model : models) {
            String date = model.getTimeStamp().substring(0, Math.min(model.getTimeStamp().length(), 10));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = df.parse(date);
            dates.add(startDate);
        }
        return groupByDistinct(dates);
    }

    public ArrayList<Date> groupByDistinct(ArrayList dates) {
        LinkedHashSet<Date> lhs = new LinkedHashSet<Date>();
        lhs.addAll(dates);
        dates.clear();
        dates.addAll(lhs);
        return dates;
    }


}
