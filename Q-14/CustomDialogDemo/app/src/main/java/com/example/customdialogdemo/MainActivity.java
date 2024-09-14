package com.example.customdialogdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
                showRectangleAreaDialog();
            }
        });
    }

    private void showRectangleAreaDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_rectangle_area, null);
        builder.setView(dialogView);

        EditText etLength = dialogView.findViewById(R.id.etLength);
        EditText etWidth = dialogView.findViewById(R.id.etWidth);
        Button btnCalculate = dialogView.findViewById(R.id.btnCalculate);
        TextView tvResult = dialogView.findViewById(R.id.tvResult);

        builder.setTitle("Calculate Rectangle Area")
                .setNegativeButton("Close", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();

        btnCalculate.setOnClickListener(v -> {
            String lengthStr = etLength.getText().toString();
            String widthStr = etWidth.getText().toString();

            if (lengthStr.isEmpty() || widthStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both length and width", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double length = Double.parseDouble(lengthStr);
                double width = Double.parseDouble(widthStr);
                double area = length * width;
                tvResult.setText("Area: " + area);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}