package cst2335group.project.PkgF;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cst2335group.project.R;

/**
 * Created by mac on 31/12/2017.
 */

public class FoodListAdapter extends BaseAdapter {

    Context context;
    List<FoodModel> models;
    LayoutInflater inflater;

    public FoodListAdapter(Context context, List<FoodModel> foodModels) {
        this.context = context;
        this.models = foodModels;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return models.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View convertView;
        if (view == null) {
            convertView = inflater.inflate(R.layout.food_item_layout, null);
        } else {
            convertView = view;
        }

        TextView name = convertView.findViewById(R.id.item_name);
        TextView cal = convertView.findViewById(R.id.item_calories);
        TextView carbs = convertView.findViewById(R.id.item_carbs);
        TextView fats = convertView.findViewById(R.id.item_fats);
        TextView timeStamp = convertView.findViewById(R.id.item_dateTime);


        FoodModel model = models.get(i);
        name.setText(model.foodName.toUpperCase());
        cal.setText("Calories : "+model.calories);
        carbs.setText("Carbs : "+model.totalCarbs);
        fats.setText("Fats : "+model.totalFat);
        timeStamp.setText(model.timeStamp);

        return convertView;
    }
}
