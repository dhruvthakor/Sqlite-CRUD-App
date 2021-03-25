package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_name, editText_email,editText_id;
    Button button_add, button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.et_name);
        editText_email = findViewById(R.id.et_email);
        editText_id = findViewById(R.id.et_id);
        button_add = findViewById(R.id.btn_add);
        button_view = findViewById(R.id.btn_view);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringEmail = editText_email.getText().toString();
                int stringId = Integer.parseInt(editText_id.getText().toString());

                if(editText_name.length() == 0)
                    editText_name.setError("Enter your name");
                else if(editText_email.length() == 0)
                    editText_email.setError("Enter your email");
                else if(editText_id.length() == 0)
                    editText_id.setError("Enter your ID");
                else{
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    StudentsModel studentsModel = new StudentsModel(stringId, stringName, stringEmail);
                    databaseHelper.addStudent(studentsModel);
                    Toast.makeText(MainActivity.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAllStudents.class);
                startActivity(intent);
            }
        });

    }
}