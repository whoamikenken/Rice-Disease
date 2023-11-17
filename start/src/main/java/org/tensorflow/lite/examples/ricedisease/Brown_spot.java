package org.tensorflow.lite.examples.ricedisease;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Brown_spot extends AppCompatActivity {

    Button  solution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brown_spot);

        solution = findViewById(R.id.solution_btn);

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 View dialogView = getLayoutInflater().inflate(R.layout.brown_spot_solution, null);
                final DialogPlus dialogPlus=DialogPlus.newDialog(Brown_spot.this)
                        .setContentHolder(new ViewHolder(dialogView))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();
            }
        });
    }
}
