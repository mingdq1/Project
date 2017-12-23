package cst2335group.project.PkgA;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import cst2335group.project.R;

public class A_Insert extends Activity {

    private EditText editText_litres, editText_price, editText_kilometers, editText_date;
    int x_year, x_month, x_day;
    static final int DIALOG_ID_DATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_insert);

        editText_litres = findViewById(R.id.editText_litres);
        editText_price = findViewById(R.id.editText_price);
        editText_kilometers = findViewById(R.id.editText_kilometers);
        editText_date = findViewById(R.id.editText_date);

        final Calendar cal = Calendar.getInstance();
        x_year = cal.get(Calendar.YEAR);
        x_month = cal.get(Calendar.MONTH);
        x_day = cal.get(Calendar.DAY_OF_MONTH);

        setDate();

        editText_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID_DATE);
            }
        });
    }

    public void Save(View view) {
        String litres = editText_litres.getText().toString();
        String price = editText_price.getText().toString();
        String kilometers = editText_kilometers.getText().toString();
        String date =  editText_date.getText().toString();

        if(litres.equals("")||price.equals("")||kilometers.equals("")||date.equals("") ){
            Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
        }else{
            Intent result = new Intent();
            result.putExtra("litres", litres);
            result.putExtra("price",price );
            result.putExtra("kilometers", kilometers);
            result.putExtra("date",date);
            setResult(1, result);
            finish();
        }

    }

    public void Cancel(View view) {
        finish();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ID_DATE:
                return new DatePickerDialog(this, dpickerListner, x_year, x_month, x_day);
            default:
                return null;
        }
    }


    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            x_year = year;
            x_month = month;
            x_day = dayOfMonth;

            setDate();
        }
    };

    private void setDate() {
        String year = Integer.toString(x_year);
        String month = Integer.toString((x_month + 1));
        String day = Integer.toString(x_day);
        if (x_month < 9) {

            month = "0" + (x_month + 1);
        }
        if (x_day < 10) {

            day = "0" + x_day;
        }
        editText_date.setText((year + "-" + month + "-" + day));
    }
}
