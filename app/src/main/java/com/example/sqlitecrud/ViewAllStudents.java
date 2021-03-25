package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewAllStudents extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_students);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<StudentsModel> studentsModelList = databaseHelper.getStudnetsList();
        
        if(studentsModelList.size() > 0){
            StudentsAdapterClass studentsAdapterClass = new StudentsAdapterClass(studentsModelList, ViewAllStudents.this);
            recyclerView.setAdapter(studentsAdapterClass);
        }else{
            Toast.makeText(this, "There is no students in the database", Toast.LENGTH_SHORT).show();
        }
    }
}