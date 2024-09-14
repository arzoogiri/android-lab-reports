package com.example.popupmenudemo;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonShowPopup = findViewById(R.id.buttonShowPopup);

        buttonShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.action_option_one) {
                            Toast.makeText(MainActivity.this, "Copy selected", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (id == R.id.action_option_two) {
                            Toast.makeText(MainActivity.this, "Share selected", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (id == R.id.action_option_three) {
                            Toast.makeText(MainActivity.this, "Delete selected", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }
}
