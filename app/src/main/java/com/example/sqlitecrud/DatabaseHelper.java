package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "student_database";

    //Database Table Name
    private static final String DATABASE_TABLE_NAME = "STUDENTS";

    //Table columns
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    private SQLiteDatabase sqLiteDatabase;

    //Creating Table query
    private static final String SQL = "create table " + DATABASE_TABLE_NAME + "("+ ID +
            " INTEGER PRIMARY KEY," + NAME + " TEXT NOT NULL," + EMAIL + " TEXT NOT NULL);";

    //Constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);
    }

    //Add Student's Data to SQLite
    public void addStudent(StudentsModel studentsModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ID, studentsModel.getId());
        contentValues.put(DatabaseHelper.NAME, studentsModel.getName());
        contentValues.put(DatabaseHelper.EMAIL, studentsModel.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.DATABASE_TABLE_NAME, null, contentValues);

    }

    public List<StudentsModel> getStudnetsList(){
        String sql = "Select * from " + DATABASE_TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<StudentsModel> storeStudent = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                storeStudent.add(new StudentsModel(id, name, email));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return storeStudent;
    }

    public void updateEmployee(StudentsModel employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME,employeeModelClass.getName());
        contentValues.put(DatabaseHelper.EMAIL,employeeModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(DATABASE_TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(employeeModelClass.getId())});
    }

    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DATABASE_TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
