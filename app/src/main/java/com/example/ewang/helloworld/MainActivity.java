package com.example.ewang.helloworld;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = (TextView)this.findViewById(R.id.textView);
        t.setText("Happy " + Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US) + "!");

        Button b = (Button)this.findViewById(R.id.button1);
        final MainActivity m = this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView)m.findViewById(R.id.textView);
                Switch s = (Switch)m.findViewById(R.id.switch1);
                if (t == null) {
                    Log.e("HelloWorld", "Null pointer exception!");
                    return;
                }
                if (t.getCurrentTextColor() == Color.RED) {
                    t.setTextColor(Color.YELLOW);
                    if (s.isChecked())
                        t.setTextSize(75);
                }
                else if (t.getCurrentTextColor() == Color.YELLOW) {
                    t.setTextColor(Color.GREEN);
                    if (s.isChecked())
                        t.setTextSize(100);
                }
                else {
                    if (s.isChecked())
                        t.setTextSize(50);
                    t.setTextColor(Color.RED);
                }
                Log.v("HelloWorld", "Changed text properties!");
            }
        });

        Button nameButton = (Button)this.findViewById(R.id.btnName);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView) m.findViewById(R.id.txtHello);
                String name = ((EditText) m.findViewById(R.id.editText)).getText().toString();
                t.setText("Hello " + name + "!");
            }
        });

        ValueAnimator anim = ValueAnimator.ofObject(new ArgbEvaluator(), Color.RED, Color.BLUE);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                TextView t = (TextView)m.findViewById(R.id.txtHello);
                t.setTextColor((Integer)animation.getAnimatedValue());
            }
        });

        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id == R.id.action_about) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("This app was created by Eric Wang.");
            alert.setTitle("About");
            alert.setPositiveButton("OK", null);
            alert.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
