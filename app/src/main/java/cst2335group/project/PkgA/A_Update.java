package cst2335group.project.PkgA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cst2335group.project.R;

public class A_Update extends Activity {

    private EditText editText_litres, editText_price, editText_kilometers, editText_date;
    String id;

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
        editText_date.setText(getIntent().getStringExtra("date"));
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
        Intent result = new Intent();
        result.putExtra("id", id);
        setResult(3, result);
        finish();
    }

    public void Cancel(View view) {
        finish();
    }
}
