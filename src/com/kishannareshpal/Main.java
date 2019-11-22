package com.kishannareshpal;

import com.kishannareshpal.utils.CommandLineTable;
import com.kishannareshpal.utils.Helper;
import com.kishannareshpal.utils.Validator;

import java.util.*;


public class Main {
    private static List<Employee> employeeList;
    private static int id = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        employeeList = new ArrayList<>();

        // Populate some fake users by default;
        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setDob("08/08/1999");
        employee2.setFirstName("Kishan");
        employee2.setNino("SX123456" + 4);
        employee2.setLastName(String.valueOf(4));
        employee2.setTypeOfEmployee(Employee.EMPLOYEE_TYPE_SALARIED);
        employee2.setJobTitle("CEO");
        employee2.setJobDepartment("Production");
        employee2.setContractType("Full Time");
        employee2.setAnnualSalary("20000");
        employee2.setAnnualGrossSalary("N/A");
        employee2.setCommissionRate("N/A");
        employeeList.add(employee2);


        Employee employee3 = new Employee();
        employee3.setId("3");
        employee3.setDob("08/08/1999");
        employee3.setFirstName("Kishan");
        employee3.setNino("SX123456" + 3);
        employee3.setLastName(String.valueOf(4));
        employee3.setTypeOfEmployee(Employee.EMPLOYEE_TYPE_HOURLY);
        employee3.setJobTitle("CEO");
        employee3.setJobDepartment("Production");
        employee3.setContractType("Full Time");
        employee3.setAnnualSalary("N/A");
        employee3.setHourlyPayRate("8.20");
        employee3.setAnnualGrossSalary("N/A");
        employee3.setCommissionRate("N/A");
        employeeList.add(employee3);


        Employee employee6 = new Employee();
        employee6.setId("1");
        employee6.setDob("08/08/1999");
        employee6.setFirstName("Kishan");
        employee6.setNino("SX123456" + 5);
        employee6.setLastName(String.valueOf(5));
        employee6.setTypeOfEmployee(Employee.EMPLOYEE_TYPE_COMMISSION);
        employee6.setJobTitle("CEO");
        employee6.setJobDepartment("Production");
        employee6.setContractType("Full Time");
        employee6.setAnnualSalary("N/A");
        employee6.setAnnualGrossSalary("24000");
        employee6.setCommissionRate("5.6");
        employeeList.add(employee6);
        
        do {
            System.out.println();
            System.out.println();
            System.out.println("+---------------------------+");
            System.out.println("| Welcome to Payroll System |");
            System.out.println("+---------------------------+");
            System.out.println("| 1 | Register Employee     |");
            System.out.println("| 2 | View Employees        |");
            System.out.println("| 3 | Remove Employee       |");
            System.out.println("| 4 | Generate Payslips     |");
            System.out.println("| 5 | Exit                  |");
            System.out.println("+---------------------------+");
            boolean isValid;
            do {
                System.out.print("Please select an option: ");
                input = scanner.nextLine();
                isValid = Validator.validate(input, new int[]{1, 5});
            } while (!isValid);

            if (input.equals("1")) {
                Employee employee = PRSystem.showRegistrationForm();
                employeeList.add(employee);
                System.out.println("✔ Employee registered!");
                System.out.println();

            } else if (input.equals("2")) {
                CommandLineTable clt = new CommandLineTable();
                clt.setShowVerticalLines(true); //if false (default) then no vertical lines are shown
                clt.setHeaders("ID", "Full Name", "Date of birth", "NINO", "Job Title", "Job Dept.", "Contract Type", "Annual Salary (£)", "Hourly pay rate (£)", "Annual Gross Salary (£)", "Commission Rate (£)"); // optional - if not used then there will be no header and horizontal lines

                for (Employee emp : employeeList) {
                    String id = emp.getId();
                    String fullname = emp.getFirstName() + " " + emp.getLastName();
                    String dob = emp.getDob();
                    String nino = emp.getNino();
                    String jobtitle = emp.getJobTitle();
                    String jobDpt = emp.getJobDepartment();
                    String contractType = Helper.isNullOrEmpty(emp.getContractType()) ? "N/A" : emp.getContractType();
                    String annualSalary = Helper.isNullOrEmpty(emp.getAnnualSalary()) ? "N/A" : emp.getAnnualSalary();
                    String hourlyPayRate = Helper.isNullOrEmpty(emp.getHourlyPayRate()) ? "N/A" : emp.getHourlyPayRate();
                    String annualGrossSalary = Helper.isNullOrEmpty(emp.getAnnualGrossSalary()) ? "N/A" : emp.getAnnualGrossSalary();
                    String commissionRate = Helper.isNullOrEmpty(emp.getCommissionRate()) ? "N/A" : emp.getCommissionRate();
                    clt.addRow(id, fullname, dob, nino, jobtitle, jobDpt, contractType, annualSalary, hourlyPayRate, annualGrossSalary, commissionRate);
                }
                clt.print();
                System.out.println();
                System.out.println();

            } else if (input.equals("3")) {
                // remove employee
                employeeList = PRSystem.showDeletionForm(employeeList);

            } else if (input.equals("4")) {
                // generate a payslip
                PRSystem.showPayslipGenerationForm(employeeList);

            } else {
                // default

            }

        } while (!input.equals("5"));

    }



}
