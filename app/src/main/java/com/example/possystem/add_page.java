package com.example.possystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class add_page extends AppCompatActivity {
    ConnectionClass connectionClass;

    Connection con;

    ResultSet rs;

    public EditText food, cost;


    String name, str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);
        connectionClass = new ConnectionClass();

        food = findViewById(R.id.edit_FoodName);
        cost = findViewById(R.id.edit_Cost);

        Button btn_Add = (Button)findViewById(R.id.btn_Insert);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Food = food.getText().toString();
                String Cost = cost.getText().toString();
                if (TextUtils.isEmpty(Food) || TextUtils.isEmpty(Cost)) {
                    Toast.makeText(add_page.this, "ALL fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(() -> {
                        try {
                            con = connectionClass.CONN();
                            String query = "INSERT INTO `MenuItems` (`Food Name`, `Cost`) VALUES (" +"'" +Food +"'" +"," +Cost +")" ;
                            //INSERT INTO `MenuItems`(`Food Name`, `Cost`) VALUES ('Celery', 2.99)
                            System.out.println(query);
                            PreparedStatement stmt = con.prepareStatement(query);
                            stmt.executeUpdate();
                            food.getText().clear();
                            cost.getText().clear();



                        }catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        runOnUiThread(() -> {
                            try {
                                Thread.sleep(1000);
                            } catch(InterruptedException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(add_page.this, "Info Submitted", Toast.LENGTH_SHORT).show();



                        });
                    });
                }

            }
        });
    }


}