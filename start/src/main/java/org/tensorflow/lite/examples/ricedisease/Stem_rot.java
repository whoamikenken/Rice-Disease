package org.tensorflow.lite.examples.ricedisease;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Stem_rot extends AppCompatActivity {

    Button  solution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stem_rot);

        solution = findViewById(R.id.solution_btn);

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 View dialogView = getLayoutInflater().inflate(R.layout.stem_rot_solution, null);
                final DialogPlus dialogPlus=DialogPlus.newDialog(Stem_rot.this)
                        .setContentHolder(new ViewHolder(dialogView))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();
            }
        });
    }
}
