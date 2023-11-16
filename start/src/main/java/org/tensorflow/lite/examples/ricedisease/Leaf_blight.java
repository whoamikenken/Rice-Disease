package org.tensorflow.lite.examples.ricedisease;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

import okhttp3.OkHttpClient;

public class Leaf_blight extends AppCompatActivity {

    Button  solution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaf_blight);

        solution = findViewById(R.id.solution_btn);

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 View dialogView = getLayoutInflater().inflate(R.layout.leaf_blight_solution, null);
                final DialogPlus dialogPlus=DialogPlus.newDialog(Leaf_blight.this)
                        .setContentHolder(new ViewHolder(dialogView))
                        .setExpanded(true,1400)
                        .create();

                dialogPlus.show();
            }
        });
    }
}
