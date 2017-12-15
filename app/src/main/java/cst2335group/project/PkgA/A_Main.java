package cst2335group.project.PkgA;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cst2335group.project.R;

public class A_Main extends Activity {

    private ListView listView;
    private ArrayList<AutoInfo> arrayList;
    private AutoAdapter adapter;

    private class AutoInfo {
        private String litres, price, kilometers, date;

        public AutoInfo(String litres, String price, String kilometers, String date) {
            this.litres = litres;
            this.price = price;
            this.kilometers = kilometers;
            this.date = date;
        }

        public String getLitres() {
            return litres;
        }

        public String getPrice() {
            return price;
        }

        public String getKilometers() {
            return kilometers;
        }

        public String getDate() {
            return date;
        }
    }

    class AutoAdapter extends ArrayAdapter<AutoInfo> {

        private TextView textView_litres, textView_price, textView_kilometers, textView_date;

        public AutoAdapter(Context context, ArrayList<AutoInfo> info) {
            super(context, R.layout.auto_info, info);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.auto_info, parent, false);
            textView_litres = customView.findViewById(R.id.auto_litres);
            textView_price = customView.findViewById(R.id.auto_price);
            textView_kilometers = customView.findViewById(R.id.auto_kilometers);
            textView_date = customView.findViewById(R.id.auto_date);

            textView_litres.setText(getItem(position).getLitres() + " " + getResources().getString(R.string.litres_unit));
            textView_price.setText(getItem(position).getPrice() + " " + getResources().getString(R.string.money));
            textView_kilometers.setText(getItem(position).getKilometers() + " " + getResources().getString(R.string.km));
            textView_date.setText(getItem(position).getDate());

            return customView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_main);

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.auto_listView);
        adapter = new AutoAdapter(this,arrayList);
        listView.setAdapter(adapter);
        
    }

    public void auto_add(View view) {
        Intent intent = new Intent(A_Main.this, A_Insert.class);
        startActivityForResult(intent, 1);
    }
}
