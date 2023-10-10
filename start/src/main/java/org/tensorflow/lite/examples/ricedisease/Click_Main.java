package org.tensorflow.lite.examples.ricedisease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Click_Main extends AppCompatActivity{
    Button detect_c;
    TextView name_to_search_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_main);

        detect_c = findViewById(R.id.detect_c);
        name_to_search_c = findViewById(R.id.name_to_search_c);

        //Open Scanner Camera
        detect_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Click_Main.this, Scanner.class));
            }
        });
    }

}