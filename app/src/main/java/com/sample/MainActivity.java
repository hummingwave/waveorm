package com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hummingwave.Custom.WaveORMException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            save();
            update();
            updateWhereClause();
            getFirst();
            getLast();
            listAll();
            getCount();
            getCountWithCond();
            delete();
            fetch();
        } catch (WaveORMException e) {
            Log.d("EXCEPTION_CODE", e.getCode() + "");
            e.printStackTrace();
        }
    }

    private void save() throws WaveORMException {
        Employee employee = new Employee();
        employee.setEmpNo("027");
        employee.setName("Chaitra");
        employee.save();
    }

    private void fetch() throws WaveORMException {
        Employee employee = new Employee();
        List object = employee.fetchRecords(null, null, null, null, null, null);
        Log.e("RESULT",  object + "");
    }

    private void updateWhereClause() {
        try {
            Employee employee = new Employee();
            employee.setEmpNo("027");
            employee.setName("Chaitra T");
            employee.update("empNo = ?", new String[]{"027"});
        } catch (WaveORMException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        try {
            Employee employee = new Employee();
            employee.setEmpNo("027");
            employee.setName("Chaitra");
            employee.update();
        } catch (WaveORMException e) {
            e.printStackTrace();
        }
    }

    private void delete() throws WaveORMException {
        Employee employee = new Employee();
        employee.setEmpNo("027");
        employee.delete();
    }

    private void getFirst() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getFirstRecord();
        Log.e("RESULT", object + "");
    }

    private void getLast() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getLastRecord();
        Log.e("RESULT",  object + "");
    }

    private void listAll() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.listAll();
        Log.e("RESULT",  object + "");
    }

    private void getCount() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getCount();
        Log.e("RESULT",  object + "");
    }

    private void getCountWithCond() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getCount("empNo = ?", new String[]{"027"});
        Log.e("RESULT",  object + "");
    }
}