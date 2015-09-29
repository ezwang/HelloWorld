package com.example.ewang.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class CounterActivity extends AppCompatActivity {
    HashMap<Integer, Integer> counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        counter = new HashMap<Integer, Integer>();

        final CounterActivity a = this;
        final int[] btnIDs = new int[] { R.id.btnRed, R.id.btnBlue, R.id.btnGreen, R.id.btnYellow };

        final SharedPreferences prefs = this.getSharedPreferences("com.example.ewang.helloworld", Context.MODE_PRIVATE);
        for (int i : btnIDs) {
            counter.put(i, prefs.getInt("counter" + i, 0));
            ((Button)a.findViewById(i)).setText(counter.get(i) + " Presses");
        }

        for (int i : btnIDs)
        {
            final Button b = (Button)a.findViewById(i);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter.put(b.getId(), counter.get(b.getId()) + 1);
                    int d = counter.get((b.getId()));
                    prefs.edit().putInt("counter" + b.getId(), d).apply();
                    b.setText(d + " Presses");
                    Toast.makeText(a, "This button was pressed " + d  + " times!", Toast.LENGTH_SHORT).show();
                    Log.i("CounterApp", "This button was pressed " + d + " times!");
                }
            });
        }

        final Button r = (Button)a.findViewById(R.id.btnReset);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i : btnIDs) {
                    Button b = (Button)a.findViewById(i);
                    counter.put(i, 0);
                    prefs.edit().putInt("counter" + b.getId(), 0);
                    b.setText("0 Presses");
                }
                prefs.edit().apply();
                Toast.makeText(a, "All buttons are reset!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
