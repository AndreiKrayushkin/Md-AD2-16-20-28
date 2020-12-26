package by.andrei.firstproject.task4_1draw;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomView customView = findViewById(R.id.customViewMain);

        customView.setOnCustomViewActionListener(new CustomView.OnCustomViewActionListener() {
            @Override
            public void onActionDown(float x, float y , int color) {
                Snackbar.make(customView, "Нажаты координаты [" + (int) x + "; "+ (int) y + "]", Snackbar.LENGTH_LONG)
                        .setTextColor(color)
                        .setBackgroundTint(Color.GRAY)
                        .show();
            }
        });
    }
}