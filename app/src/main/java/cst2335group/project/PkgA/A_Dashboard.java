package cst2335group.project.PkgA;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

import cst2335group.project.R;

public class A_Dashboard extends Activity {

    private String year, month;
    private A_DatabaseHelper databaseHelper;
    private TextView lastAvgPrice, lastTotalLiters;
    private ProgressBar progressBar;
    private LinearLayout layout_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_dashboard);

        lastAvgPrice=findViewById(R.id.last_avg_price);
        lastTotalLiters=findViewById(R.id.last_total_liters);

        progressBar = findViewById(R.id.progressBar);
        layout_progressBar = findViewById(R.id.layout_progressBar);

        databaseHelper = new A_DatabaseHelper(this);
        databaseHelper.openDatabase();

        ReadAuto readAuto = new ReadAuto();
        readAuto.execute();
    }

    private void setCurrentMonth() {
        final Calendar cal = Calendar.getInstance();
        int x_year = cal.get(Calendar.YEAR);
        int x_month = cal.get(Calendar.MONTH);
        year = Integer.toString(x_year);
        month = Integer.toString((x_month + 1));
        if (x_month < 9) {

            month = "0" + (x_month + 1);
        }
    }

    private String getLastMonth() {
        setCurrentMonth();

        String date;
        if (!month.equals("01")) {
            int lastMonth = Integer.parseInt(month) - 1;
            if (lastMonth < 10) {
                month = "0" + lastMonth;
                date = (year + "-" + month);
            } else {
                date = (year + "-" + lastMonth);
            }
        } else {
            date = ((Integer.parseInt(year) - 1) + "-" + "12");
        }

        return date;
    }

    private String getLastAvgPrice() {
        String string = databaseHelper.getLastAvgPrice(getLastMonth());
        return string;
    }

    private String getLastTotalLitres() {
        String string = databaseHelper.getLastTotalLitres(getLastMonth());
        return string;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }

    public class ReadAuto extends AsyncTask<String, Integer,String> {

        private String item1, item2;

        @Override
        protected String doInBackground(String[] strings) {

            // Use try catch to avoid crash when rotation
            try {
                publishProgress(0);
                item1 = getLastAvgPrice() + " CAD";
                SystemClock.sleep(500);
                publishProgress(50);
                item2 = getLastTotalLitres() + " L";
                SystemClock.sleep(500);
                publishProgress(100);
            } catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            layout_progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String string) {
            layout_progressBar.setVisibility(View.INVISIBLE);

            lastAvgPrice.setText(item1);
            lastTotalLiters.setText(item2);
        }
    }
}
