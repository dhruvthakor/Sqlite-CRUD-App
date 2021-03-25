package com.example.sqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentsData extends AppCompatActivity {

    EditText etName, etEmail;
    Button btnEdit, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_item_list);

        etName = findViewById(R.id.edittext_name);
        etEmail = findViewById(R.id.edittext_email);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);

        etName.setFocusable(false);
        etEmail.setFocusable(false);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setEnabled(true);
                etEmail.setEnabled(true);
                Toast.makeText(StudentsData.this, "Edited Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentsData.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}