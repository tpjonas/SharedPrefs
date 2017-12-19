package cimdata.android.dez2017.sharedprefs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STRING_COLOR = "extra.string.color";

    EditText colorInput;
    Button showColorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorInput = findViewById(R.id.etxt_acmain_color);
        showColorButton = findViewById(R.id.btn_acmain_show_color);

        showColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColor();
            }
        });

    }

    private void showColor() {

        String color = String.valueOf(colorInput.getText());

        Intent intent = new Intent(this, ColorActivity.class);

        intent.putExtra(EXTRA_STRING_COLOR, color);

        startActivity(intent);

    }
}
