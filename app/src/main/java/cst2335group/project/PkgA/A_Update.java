package cst2335group.project.PkgA;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import cst2335group.project.R;

public class A_Update extends Activity {

    private EditText editText_litres, editText_price, editText_kilometers, editText_date;
    String id, date;
    int x_year, x_month, x_day;
    static final int DIALOG_ID_DATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_update);

        editText_litres = findViewById(R.id.editText_litres);
        editText_price = findViewById(R.id.editText_price);
        editText_kilometers = findViewById(R.id.editText_kilometers);
        editText_date = findViewById(R.id.editText_date);

        id = getIntent().getStringExtra("id");
        editText_litres.setText(getIntent().getStringExtra("litres"));
        editText_price.setText(getIntent().getStringExtra("price"));
        editText_kilometers.setText(getIntent().getStringExtra("kilometers"));

        date = getIntent().getStringExtra("date");

        if (!date.equals("")) {
            String[] dates = date.split("-");
            x_year = Integer.parseInt(dates[0]);
            x_month = Integer.parseInt(dates[1]) - 1;
            x_day = Integer.parseInt(dates[2]);
        }

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
            result.putExtra("id", id);
            result.putExtra("litres", litres);
            result.putExtra("price",price );
            result.putExtra("kilometers", kilometers);
            result.putExtra("date",date);
            setResult(2, result);
            finish();
        }

    }

    public void Delete(View view) {
        final Intent result = new Intent();
        result.putExtra("id", id);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setResult(3, result);
                finish();
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .show();
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
