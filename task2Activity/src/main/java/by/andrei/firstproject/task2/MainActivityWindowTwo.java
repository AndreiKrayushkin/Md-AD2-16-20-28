package by.andrei.firstproject.task2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivityWindowTwo extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actirivy_main_two);

        Intent intentMain = new Intent(MainActivityWindowTwo.this, MainActivity2.class);
        ArrayList <Integer> integ = getIntent().getIntegerArrayListExtra("Main_ArrayList");

        Summator sum = new Summator();
        try {
            sum.sumCount(integ);
        } catch (Exception ex) {
            intentMain.putExtra("Summator_ERROR", ex.getMessage());
        }
        Average average = new Average();
        try {
            average.averageCount(integ);
        } catch (Exception ex) {
            intentMain.putExtra("Average_ERROR", ex.getMessage());
        }
        Division division = new Division();
        try {
            division.divisionCount(integ);
        } catch (Exception ex) {
            intentMain.putExtra("Division_ERROR", ex.getMessage());
        }

        String strSummator = Integer.toString(sum.getSumResultat());
        String strAverage = Double.toString(average.getAverageResultat());
        String strDivision = Double.toString(division.getDivisionResultat());
        String strTextView = "View LOGS";

        intentMain.putExtra("TextView_Name", strTextView);
        intentMain.putExtra("Summator", strSummator);
        intentMain.putExtra("Average", strAverage);
        intentMain.putExtra("Division", strDivision);

        startActivity(intentMain);

    }
}
