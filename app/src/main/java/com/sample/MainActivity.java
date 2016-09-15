package com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hummingwave.Custom.WaveORMArrayList;
import com.hummingwave.Custom.WaveORMException;
import com.hummingwave.Custom.WaveORMPaginationResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WaveORMPaginationResult waveORMPaginationResult;

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
           /* saveList();
            update();
            updateWhereClause();*/
            fetch();
          /*  fetchWithPrimaryKey();
            fetchWithWhereClause();
            getFirst();
            getLast();
            listAll();
            getCount();
            getCountWithCond();
            delete();*/
           /* deleteAll();
            deleteList();
            deleteListWithWhereClause();*/
          /*  fetch();
            if (waveORMPaginationResult != null) {
                fetchRecordsWithPagination(waveORMPaginationResult.getPaginationOffset());
            }else {
                fetchRecordsWithPagination(0);
            }
            if (waveORMPaginationResult != null) {
                fetchRecordsWithPagination(waveORMPaginationResult.getPaginationOffset());
            }*/
        } catch (WaveORMException e) {
            Log.d("EXCEPTION_CODE", e.getCode() + "");
            e.printStackTrace();
        }
    }

    private void save() throws WaveORMException {
        Employee employee = new Employee();
        employee.setEmpNo("027");
        employee.setName("Chaitra");
         {
            try {
                employee.setBytes("Chaitra".getBytes("ISO-8859-15"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Log.d("BYTES", "Chaitra".getBytes() + "");
        employee.update();
    }

    private void saveList() throws WaveORMException {
        WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();
        for (int i = 0; i < 200; i++) {
            Employee employee = new Employee();
            employee.setEmpNo(i + "");
            employee.setName("Chaitra " + i);
            employeeWaveORMArrayList.add(employee);
        }
        employeeWaveORMArrayList.save();
    }

    private void fetchWithPrimaryKey() throws WaveORMException {
        Employee employee = new Employee();
        Employee object = (Employee) employee.findRecord("027");
        Log.e("RESULT", object + "");
    }

    private void fetchWithWhereClause() throws WaveORMException {
        Employee employee = new Employee();
        List object = employee.fetchRecords("empNo = ?", new String[]{"027"}, null, null, null, "1");
        Log.e("RESULT", object + "");
    }

    private void fetch() throws WaveORMException {
        Employee employee = new Employee();
        List object = employee.fetchRecords(null, null, null, null, null, null);
        employee = (Employee)object.get(0);
         {
            {
                try {
                    Log.e("RESULT",  new String(employee.getBytes(), "ISO-8859-15")+ "");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
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
        try {
            Employee employee = new Employee();
            employee.setEmpNo("027");
            employee.delete();
        } catch (WaveORMException e) {
            e.printStackTrace();
        }
    }

    private void deleteAll() throws WaveORMException {
        try {
            Employee employee = new Employee();
            employee.deleteAll();
        } catch (WaveORMException e) {
            e.printStackTrace();
        }
    }

    private void deleteList() throws WaveORMException {
        try {
            WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();

            Employee employee = new Employee();
            employee.setEmpNo("027");

            employeeWaveORMArrayList.add(employee);

            employeeWaveORMArrayList.delete();
        } catch (WaveORMException e) {
            e.printStackTrace();
        }
    }

    private void deleteListWithWhereClause() throws WaveORMException {
        try {
            WaveORMArrayList<Employee> employeeWaveORMArrayList = new WaveORMArrayList<>();

            Employee employee = new Employee();
            employee.setEmpNo("027");

            employeeWaveORMArrayList.add(employee);

            employeeWaveORMArrayList.delete("empNo = ?", new String[]{"027"});
        } catch (WaveORMException e) {
            e.printStackTrace();
        }
    }

    private void getFirst() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getFirstRecord();
        Log.e("RESULT", object + "");
    }

    private void getLast() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getLastRecord();
        Log.e("RESULT", object + "");
    }

    private void listAll() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.listAll();
        Log.e("RESULT", object + "");
    }

    private void getCount() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getCount();
        Log.e("RESULT", object + "");
    }

    private void getCountWithCond() throws WaveORMException {
        Employee employee = new Employee();
        Object object = employee.getCount("empNo = ?", new String[]{"027"});
        Log.e("RESULT", object + "");
    }

    private void executingRawQuery() throws WaveORMException {
        Employee employee = new Employee();
        employee.executeRawQuery("Insert into Employee(empNo, name) values('029', 'Rahul')");
    }

    private void fetchRecordsWithPagination(int pagination) throws WaveORMException {
        Employee employee = new Employee();
        waveORMPaginationResult = employee.fetchRecordsWithPagination(pagination);
        Log.e("RESULT", waveORMPaginationResult + "");
    }
}