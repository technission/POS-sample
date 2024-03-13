package com.example.possystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class landing_page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        //this accesses shared preferences set from edit page
        //eventually this would be used to load the data when the app first launches to make things run a bit smoother
//        SharedPreferences sp = getApplicationContext().getSharedPreferences("FoodItems", MODE_PRIVATE);
//        String item = sp.getString("Item", "");
//        System.out.println("item is" +item);



        //declare all 3 buttons
        Button btn_employee = (Button)findViewById(R.id.btn_employee);
        Button btn_manager = (Button)findViewById(R.id.btn_manager);
        Button btn_guest = (Button)findViewById(R.id.btn_guest);

        //set on click listener for each button and send them to a new view upon click
        btn_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, employee_page.class));
            }
        });

        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, guest_page.class));
            }
        });

        btn_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing_page.this, manager_page.class));
            }
        });
    }

}
