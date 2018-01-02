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

public class StatsAdapter extends BaseAdapter {

    Context context;
    List<CalorieModel> models;
    LayoutInflater inflater;

    public StatsAdapter(Context context, List<CalorieModel> foodModels) {
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View convertView;
        if (view == null) {
            convertView = inflater.inflate(R.layout.stats_list_item_layout, null);
        } else {
            convertView = view;
        }

        TextView date = convertView.findViewById(R.id.stat_date);
        TextView cal = convertView.findViewById(R.id.totalCalories);

        CalorieModel model = models.get(i);
        date.setText(model.getDate());
        cal.setText(model.getTotalCalories());
        return convertView;
    }
}
