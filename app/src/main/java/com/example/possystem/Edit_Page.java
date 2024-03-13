package com.example.possystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Edit_Page extends AppCompatActivity {
    ConnectionClass connectionClass;

    Connection con;

    ResultSet rs;

    String name, str;
    ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        connectionClass = new ConnectionClass();


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                String query = "SELECT * FROM MenuItems";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                StringBuilder bStr = new StringBuilder("Menu Items" + "   Cost\n");


                while (rs.next()) {
                    ArrayList<String> inner = new ArrayList<String>();
                    inner.add(rs.getString("Food Name") + "     " + "$" + rs.getString("Cost"));
                    System.out.println(inner);
                    outer.add(inner);
                    bStr.append(rs.getString("Food Name")).append("   ").append(rs.getString("Cost")).append("\n");

                }
                name = bStr.toString();
                System.out.println(outer.get(0));

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(() -> {
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                //this adds each item into the textVIEW
                TextView txtList = findViewById(R.id.txt_Items);
                txtList.setText(name);


            });
        });
    }

    //this currently saves the data from the database into shared data but not as an array....
    public void saveDataToShared() {
        SharedPreferences sharedPreferences = getSharedPreferences("FoodItems", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("Item", String.valueOf(outer));
        myEdit.commit();
        System.out.println(outer);
    };

    //this reads the data from shared preferences but not as an array which will be my end goal
    public void readDataFromShared() {
        SharedPreferences sh = getApplicationContext().getSharedPreferences("FoodItems", MODE_PRIVATE);
        String item = sh.getString("Item", "");
        System.out.println("item is" +item);

    };
    //this was used for testing the connection to the database
    public void connect() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                if (con == null) {
                    str = "Error in connection with MySql server";
                }else {
                    str = "Connected with MySql server";
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(() -> {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            });
        });
    }
}