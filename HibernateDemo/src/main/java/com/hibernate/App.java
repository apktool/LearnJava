package com.hibernate;

import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        try{
            ManageEmployee.factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManageEmployee ME = new ManageEmployee();

      /* Add few employee records in database */
        Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        Integer empID3 = ME.addEmployee("John", "Paul", 10000);

      /* List down all the employees */
        ME.listEmployees();

      /* Update employee's records */
        ME.updateEmployee(empID1, 5000);

      /* Delete an employee from the database */
        ME.deleteEmployee(empID2);

      /* List down new list of the employees */
        ME.listEmployees();
    }
}
