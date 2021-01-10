package by.andrei.firstproject.task4_1draw;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomView customView = findViewById(R.id.customViewMain);
        Switch switchEvent = findViewById(R.id.switch1);

        switchEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = switchEvent.isChecked();
                if (checked) {
                    customView.setOnCustomViewActionListener(new CustomView.OnCustomViewActionListener() {
                        @Override
                        public void onActionDown(float x, float y , int color) {
                            Toast.makeText(MainActivity.this, "Нажаты координаты [" + (int) x + "; "+ (int) y + "]", Toast.LENGTH_SHORT)
                                    .show();

                        }
                    });
                } else {
                    customView.setOnCustomViewActionListener(new CustomView.OnCustomViewActionListener() {
                        @Override
                        public void onActionDown(float x, float y, int color) {
                            Snackbar.make(customView, "Нажаты координаты [" + (int) x + "; "+ (int) y + "]", Snackbar.LENGTH_LONG)
                                    .setTextColor(color)
                                    .setBackgroundTint(Color.GRAY)
                                    .show();
                        }
                    });
                }
            }
        });
    }
}
