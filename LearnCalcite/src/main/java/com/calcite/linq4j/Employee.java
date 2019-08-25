package com.calcite.linq4j;

public class Employee {
    private int empno;
    private final String name;
    private final int deptno;

    public Employee(int empno, String name, int deptno) {
        this.empno = empno;
        this.name = name;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public String getName() {
        return name;
    }

    public int getDeptno() {
        return deptno;
    }

    @Override
    public String toString() {
        return "Employee(name: " + name + ", deptno:" + deptno + ")";
    }
}
