package cst2335group.project.PkgM;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cst2335group.project.PkgA.A_Main;
import cst2335group.project.PkgF.F_Main;
import cst2335group.project.PkgH.H_Main;
import cst2335group.project.PkgT.T_Main;
import cst2335group.project.R;

public class Main_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tracker) {
            Intent intent = new Intent(Main_Page.this, T_Main.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.action_food) {
            Intent intent = new Intent(Main_Page.this, F_Main.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.action_house) {
            Intent intent = new Intent(Main_Page.this, H_Main.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.action_auto) {
            Intent intent = new Intent(Main_Page.this, A_Main.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
