package com.sample;

import com.hummingwave.Annotations.PrimaryKey;
import com.hummingwave.Annotations.Table;
import com.hummingwave.Util.WaveORMRecord;

@Table(name = "Tables")
public class Employee extends WaveORMRecord {
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
}
