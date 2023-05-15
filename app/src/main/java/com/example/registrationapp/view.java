package com.example.registrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class view extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lst1 = findViewById(R.id.lst1);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud = new ArrayList<>();

        try {
            String id , name , course , fee;
            student stu;
            titles.clear();
            String str = Server.request("read");
            // Split the string into an array of lines
            String[] lines = str.split("\\|\\|");
            for (String line : lines) {
                // Split the line into separate pieces of data
                String[] data = line.split(",");
                // Extract the values for each field
                id = data[0];
                name = data[1];
                course = data[2];
                fee = data[3];
                // Create a new student object and add it to the ArrayList
                stu = new student(id, name, course, fee);
                titles.add(stu.id + " \t " + stu.name + " \t " + stu.course + " \t " + stu.fee);
                stud.add(stu);
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        arrayAdapter.notifyDataSetChanged();
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(), edit.class);
                i.putExtra("id", stu.id);
                i.putExtra("name", stu.name);
                i.putExtra("course", stu.course);
                i.putExtra("fee", stu.fee);
                startActivity(i);
            }
        });
    }
}
