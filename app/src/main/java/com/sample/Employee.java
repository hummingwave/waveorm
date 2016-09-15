package com.sample;

import com.hummingwave.Annotations.PrimaryKey;
import com.hummingwave.Annotations.Table;
import com.hummingwave.Util.WaveORMRecord;

import java.util.Arrays;

@Table(name = "Employee")
public class Employee extends WaveORMRecord {
    public byte[] bytes;
    @PrimaryKey
    public String empNo;
    public String name;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "bytes=" + Arrays.toString(bytes) +
                ", empNo='" + empNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
