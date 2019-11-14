package com.kishannareshpal;

import java.io.Serializable;

// POJO
public class Employee implements Serializable {
    String id;
    String title;
    String typeOfEmployee;
    String first_name;
    String last_name;
    String dob;
    String nino;
    String jobTitle;
    String jobDepartment;
    String contractType;
    String annualSalary;
    String hourlyPayRate;
    String annualGrossSalary;
    String commissionRate;

    // types of employees
    public static final String EMPLOYEE_TYPE_SALARIED   = "Salaried Employee";
    public static final String EMPLOYEE_TYPE_HOURLY     = "Hourly Employee";
    public static final String EMPLOYEE_TYPE_COMMISSION = "Commission Employee";
    // job titles (allows custom titles)
    public static final String EMPLOYEE_JOBTITLE_OFFICE_WORKER   = "Office Worker";
    public static final String EMPLOYEE_JOBTITLE_OFFICER   = "Officer";
    public static final String EMPLOYEE_JOBTITLE_SUPERVISOR   = "Supervisor";
    public static final String EMPLOYEE_JOBTITLE_TEAM_LEADER   = "Team Leader";
    public static final String EMPLOYEE_JOBTITLE_MANAGER   = "Manager";
    public static final String EMPLOYEE_JOBTITLE_CEO   = "CEO";
    public static final String EMPLOYEE_JOBTITLE_DIRECTOR   = "Director";
    // job dept (todo: allows custom job dept name)
    public static final String EMPLOYEE_JOBDEPARTMENT_PRODUCTION = "Production";
    public static final String EMPLOYEE_JOBDEPARTMENT_SALES = "Sales";
    public static final String EMPLOYEE_JOBDEPARTMENT_MARKETING = "Marketing";
    // contract type
    public static final String EMPLOYEE_CONTRACTTYPE_PARTTIME = "Part Time";
    public static final String EMPLOYEE_CONTRACTTYPE_FULLTIME = "Full Time";


    public Employee() {
        // No-args contructor
    };

    // Contructor
    public Employee(String id, String typeOfEmployee, String title, String first_name, String last_name, String dob, String nino, String jobTitle, String jobDepartment, String contractType, String annualSalary, String hourlyPayRate, String annualGrossSalary, String commissionRate) {
        this.id = nino + last_name;
        this.typeOfEmployee = typeOfEmployee;
        this.title = title;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.nino = nino;
        this.jobTitle = jobTitle;
        this.jobDepartment = jobDepartment;
        this.contractType = contractType;
        this.annualSalary = annualSalary;
        this.hourlyPayRate = hourlyPayRate;
        this.annualGrossSalary = annualGrossSalary;
        this.commissionRate = commissionRate;
    }

    public static String determineTitle(String title) {
        switch (title) {
            case "1":
                return "Mr.";

            case "2":
                return "Ms.";

            default:
                return title;
        }
    }

    public static String determineJobTitle(String jobTitle) {
        switch (jobTitle) {
            case "1":
                return EMPLOYEE_JOBTITLE_OFFICE_WORKER;

            case "2":
                return EMPLOYEE_JOBTITLE_OFFICER;

            case "3":
                return EMPLOYEE_JOBTITLE_SUPERVISOR;

            case "4":
                return EMPLOYEE_JOBTITLE_TEAM_LEADER;

            case "5":
                return EMPLOYEE_JOBTITLE_MANAGER;

            case "6":
                return EMPLOYEE_JOBTITLE_CEO;

            case "7":
                return EMPLOYEE_JOBTITLE_DIRECTOR;

            default:
                return jobTitle;
        }
    }

    public static final String determineTypeOfEmployee(String typeOfEmployee){
        switch (typeOfEmployee) {
            case "1":
                return EMPLOYEE_TYPE_SALARIED;

            case "2":
                return EMPLOYEE_TYPE_HOURLY;

            case "3":
                return EMPLOYEE_TYPE_COMMISSION;

            default:
                return null;
        }
    }

    public static final String determineJobDepartment(String jobDepartment) {
        switch (jobDepartment) {
            case "1":
                return EMPLOYEE_JOBDEPARTMENT_PRODUCTION;

            case "2":
                return EMPLOYEE_JOBDEPARTMENT_SALES;

            case "3":
                return EMPLOYEE_JOBDEPARTMENT_MARKETING;

            default:
                return jobDepartment;
        }
    }

    public static final String determineContractType(String contractType) {
        switch (contractType) {
            case "1":
                return EMPLOYEE_CONTRACTTYPE_PARTTIME;

            case "2":
                return EMPLOYEE_CONTRACTTYPE_FULLTIME;

            default:
                return contractType;
        }
    }

    // Getters and Setters


    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNino() {
        return nino;
    }

    public void setNino(String nino) {
        this.nino = nino;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(String annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(String hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public String getAnnualGrossSalary() {
        return annualGrossSalary;
    }

    public void setAnnualGrossSalary(String annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }


    public String toCSV(){
        return id + "," + title + "," + first_name + "," + last_name + "," + dob + "," + nino + "," + jobTitle + "," + jobDepartment + "," + contractType + "," + annualSalary + "," + hourlyPayRate + "," + annualGrossSalary + "," + commissionRate;
    }
}
