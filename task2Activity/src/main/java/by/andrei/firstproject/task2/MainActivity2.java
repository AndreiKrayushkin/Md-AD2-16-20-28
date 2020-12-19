package by.andrei.firstproject.task2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private final static String LOG_KEY = "LOG_KEY";
    private final static String LOG_KEY_ERROR = "LOG_KEY_ERROR";
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivityWindowTwo.class);

                Observer obs = new Observer();
                Random rand = new Random();
                int sizeArrayList = rand.nextInt(100);
                try {
                    obs.generateSetListAndAddToArrayList(sizeArrayList);
                } catch (Exception x) {
                    System.out.println(Log.i(LOG_KEY_ERROR, x.getMessage()));
                }


                intent.putIntegerArrayListExtra("Main_ArrayList", obs.getListNumbers());
                startActivity(intent);
            }
        });

        TextView tv = (TextView) findViewById(R.id.text);
        String getTextViewAfter = getIntent().getStringExtra("TextView_Name");
        if (getTextViewAfter != null) {
            tv.setText(getTextViewAfter);
        }

        String getSummator = getIntent().getStringExtra("Summator");
        String getAverage = getIntent().getStringExtra("Average");
        String getDivision = getIntent().getStringExtra("Division");
        String getSummatorError = getIntent().getStringExtra("Summator_ERROR");
        String getAverageError = getIntent().getStringExtra("Average_ERROR");
        String getDivisionError = getIntent().getStringExtra("Division_ERROR");
        if (getSummator != null) {
            System.out.println(Log.i(LOG_KEY, "Summ = " + getSummator));
        }
        if (getAverage != null) {
            System.out.println(Log.i(LOG_KEY, "Average = " + getAverage));
        }
        if (getDivision != null) {
            System.out.println(Log.i(LOG_KEY, "Division = " + getDivision));
        }
        if (getSummatorError != null) {
            System.out.println(Log.i(LOG_KEY_ERROR, "Summator ERROR: " + getSummatorError));
        }
        if (getAverageError != null) {
            System.out.println(Log.i(LOG_KEY_ERROR, "Average ERROR: " + getAverageError));
        }
        if (getDivisionError != null) {
            System.out.println(Log.i(LOG_KEY_ERROR, "Division ERROR: " + getDivisionError));
        }
    }
}