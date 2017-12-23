package cst2335group.project.PkgA;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cst2335group.project.R;

public class A_Main extends Activity {

    private ListView listView;
    private ArrayList<AutoInfo> arrayList;
    private AutoAdapter adapter;
    private A_DatabaseHelper databaseHelper;
    private ReadAuto readAuto;

    public void dashboard(View view) {
        Intent intent = new Intent(A_Main.this,A_Dashboard.class);
        startActivity(intent);
    }
//    private ProgressBar progressBar;
//    private LinearLayout layout_progressBar;


    private class AutoInfo {
        private String litres, price, kilometers, date;
        private int id;

        public AutoInfo(int id, String litres, String price, String kilometers, String date) {
            this.id = id;
            this.litres = litres;
            this.price = price;
            this.kilometers = kilometers;
            this.date = date;
        }

        public int getId() {
            return id;
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

//        progressBar = findViewById(R.id.progressBar);
//        layout_progressBar = findViewById(R.id.layout_progressBar);
        arrayList = new ArrayList<>();
        listView = findViewById(R.id.auto_listView);
        adapter = new AutoAdapter(this, arrayList);
        listView.setAdapter(adapter);
        databaseHelper = new A_DatabaseHelper(this);

        databaseHelper.openDatabase();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(A_Main.this, A_Update.class);
                AutoInfo autoInfo = (AutoInfo) adapterView.getItemAtPosition(i);
                String id = autoInfo.getId() + "";
                String litres = autoInfo.getLitres();
                String price = autoInfo.getPrice();
                String kilometers = autoInfo.getKilometers();
                String date = autoInfo.getDate();

                intent.putExtra("id", id);
                intent.putExtra("litres", litres);
                intent.putExtra("price", price);
                intent.putExtra("kilometers", kilometers);
                intent.putExtra("date", date);

                startActivityForResult(intent, 2);
            }
        });
    }

    public void auto_add(View view) {
        Intent intent = new Intent(A_Main.this, A_Insert.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        readDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }

    private void readDatabase() {
        readAuto = new ReadAuto();
        readAuto.execute(arrayList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            String litres = data.getStringExtra("litres");
            String price = data.getStringExtra("price");
            String kilometers = data.getStringExtra("kilometers");
            String date = data.getStringExtra("date");
            databaseHelper.insert(litres, price, kilometers, date);
            Snackbar.make(this.findViewById(R.id.auto_listView), R.string.insert_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }

        if (resultCode == 2) {
            String litres = data.getStringExtra("litres");
            String price = data.getStringExtra("price");
            String kilometers = data.getStringExtra("kilometers");
            String date = data.getStringExtra("date");
            String id = data.getStringExtra("id");
            databaseHelper.update(id, litres, price, kilometers, date);
            Snackbar.make(this.findViewById(R.id.auto_listView), R.string.update_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }

        if (resultCode == 3) {
            String id = data.getStringExtra("id");
            databaseHelper.delete(id);
            Snackbar.make(this.findViewById(R.id.auto_listView), R.string.delete_done, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }

    }

    public class ReadAuto extends AsyncTask<ArrayList<AutoInfo>, Integer, ArrayList<AutoInfo>> {

        @Override
        protected ArrayList<AutoInfo> doInBackground(ArrayList<AutoInfo>[] arrayLists) {

            // Use try catch to avoid crash when rotation
            try {
                Cursor cursor = databaseHelper.read();
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    arrayLists[0].add(new AutoInfo(cursor.getInt(cursor.getColumnIndex(databaseHelper.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_LITRES)),
                            cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_PRICE)),
                            cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_KM)),
                            cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_DATE))));
                }
            } catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            arrayList.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<AutoInfo> autoInfos) {
            adapter.notifyDataSetChanged();
        }
    }
}
