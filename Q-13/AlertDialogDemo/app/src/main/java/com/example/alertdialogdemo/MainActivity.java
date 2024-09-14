package com.example.alertdialogdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonShowDialog = findViewById(R.id.buttonShowDialog);

        buttonShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the AlertDialog
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Alert Dialog")
                        .setMessage("This is an example of an AlertDialog.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                        })
                        .setNeutralButton("More Info", (dialog, which) -> {
                            Toast.makeText(MainActivity.this, "More Info clicked", Toast.LENGTH_SHORT).show();
                        })
                        .show(); // Display the dialog
            }
        });
    }
}
