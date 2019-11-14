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
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(String.valueOf(i));
            employee.setDob("08/08/1999");
            employee.setFirstName("Kishan");
            employee.setNino("SX123456" + i);
            employee.setLastName(String.valueOf(i));
            employee.setTypeOfEmployee(Employee.EMPLOYEE_TYPE_SALARIED);
            employee.setJobTitle("CEO");
            employee.setJobDepartment("Production");
            employee.setContractType("Full Time");
            employee.setAnnualSalary("20000");
            employee.setAnnualGrossSalary("N/A");
            employee.setCommissionRate("N/A");

            employeeList.add(employee);
        }
        
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
