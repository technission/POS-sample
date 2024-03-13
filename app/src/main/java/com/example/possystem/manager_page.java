package com.example.possystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class manager_page extends AppCompatActivity {

    ConnectionClass connectionClass;

    Connection con;

    ResultSet rs;

    String name, cost, str;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_page);


    }

    public void btnEdit(View view) {
        startActivity(new Intent(manager_page.this, Edit_Page.class));


    }


    public void btn_Add(View view) {
        startActivity(new Intent(manager_page.this, add_page.class));
    }
}