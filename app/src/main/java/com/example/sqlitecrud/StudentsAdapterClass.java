package com.example.sqlitecrud;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapterClass extends RecyclerView.Adapter<StudentsAdapterClass.ViewHolder>{

    List<StudentsModel> student;
    Context context;
    DatabaseHelper databaseHelper;

    public StudentsAdapterClass(List<StudentsModel> student, Context context) {
        this.student = student;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.students_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final StudentsModel studentsModel = student.get(position);

        holder.textViewId.setText(Integer.toString(studentsModel.getId()));
        holder.editText_Name.setText(studentsModel.getName());
        holder.editText_Email.setText(studentsModel.getEmail());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringEmail = holder.editText_Email.getText().toString();

                databaseHelper.updateEmployee(new StudentsModel(studentsModel.getId(),stringName,stringEmail));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteEmployee(studentsModel.getId());
                student.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return student.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewId;
        EditText editText_Name;
        EditText editText_Email;
        Button button_Edit;
        Button button_Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.text_id);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            button_Edit = itemView.findViewById(R.id.btn_edit);
            button_Delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
