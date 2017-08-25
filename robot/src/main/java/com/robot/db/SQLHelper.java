package com.robot.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.robot.info.ClassInfo;
import com.robot.info.Student;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, name, factory, version);

        String CREATE_CLASS = "Create Table If Not Exists Class(classId integer primary key,"
                + "className varchar(100),"
                + "studentId integer References Student(studentId),"
                + "teacherId integer References Teacher(teacherId))";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(CREATE_CLASS);
        db.close();



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStudent(Student student, ClassInfo classInfo) {

        String INSERT_STUDENT_INTO_CLASS = "Insert Into Class(className, studentId, classId) Values('" + classInfo.getName() + "',"
                + student.getStudentId() + "," + classInfo.getId() + ")";
        String INSERT_STUDENT = "Insert Into Student(studentId, name, classId) Values(" + student.getStudentId() + ","
                + "'" + student.getName() + "'," + classInfo.getId() + ")";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(INSERT_STUDENT);
        db.execSQL(INSERT_STUDENT_INTO_CLASS);
        db.close();

    }
}
