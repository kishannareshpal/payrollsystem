package com.kishannareshpal;

import com.kishannareshpal.utils.CommandLineTable;
import com.kishannareshpal.utils.Helper;
import com.kishannareshpal.utils.Validator;
import com.kishannareshpal.utils.VerticalComandLineTable;

import java.util.*;
import java.util.function.Predicate;

public class PRSystem {

    /**
     * Asks the registration details from the user.
     * @return an object of the registered Employee
     * @return an object of the registered Employee
     */
    public static Employee showRegistrationForm() {
        Employee employee = new Employee();
        // data vars
        String typeOfEmployee;
        String title;
        String firstName;
        String lastName;
        String dob;
        String nino;
        String jobTitle;
        String jobDepartment;
        String annualSalary;
        String contractType;
        String hourlyPayRate;
        String annualGrossSalary;
        String commissionRate;

        Scanner scn = new Scanner(System.in); // used to read the value of the user input from the console.

        System.out.println();
        System.out.println("+---------------------------+");
        System.out.println("|  Registering an Employee  |");
        System.out.println("+---------------------------+");
        System.out.println("| 1 | Salaried Employee     |");
        System.out.println("| 2 | Hourly Employee       |");
        System.out.println("| 3 | Commission Employee   |");
        System.out.println("+---------------------------+");

        String input;
        boolean isValid;
        do {
            System.out.print("Please select the type of employee: ");
            input = scn.nextLine();
            isValid = Validator.validate(input, new int[]{1, 3});
        } while (!isValid);

        int selectedEmployeeType = Integer.parseInt(input);
        typeOfEmployee = Employee.determineTypeOfEmployee(String.valueOf(selectedEmployeeType));

        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println(" Great, now please provide the Employee details ");
        System.out.println("------------------------------------------------");

        System.out.println();
        System.out.println("+-------------------+");
        System.out.println("| 1 | Mr.           |");
        System.out.println("| 2 | Ms.           |");
        System.out.println("+-------------------+");
        do {
            System.out.print("Title: ");
            title = scn.nextLine();
            isValid = Validator.validate(title, new int[]{1, 4});
        } while (!isValid);
        title = Employee.determineTitle(title);

        do {
            System.out.print("First name: ");
            firstName = scn.nextLine();
            isValid = Validator.validate(firstName, "First Name", false);
        } while (!isValid);

        do {
            System.out.print("Last name: ");
            lastName = scn.nextLine();
            isValid = Validator.validate(lastName, "Last Name", false);
        } while (!isValid);

        do {
            System.out.print("Date of birth (dd/mm/yyyy): ");
            dob = scn.nextLine();
            dob = Validator.validateDate(dob, "Date of Birth");
            isValid = (dob != null);
        } while (!isValid);

        do {
            System.out.print("National Insurance Number: ");
            nino = scn.nextLine();
            nino = Validator.validateNINO(nino, "NINO");
            isValid = (nino != null);
        } while (!isValid);

        System.out.println();
        System.out.println("+-------------------+");
        System.out.println("| 1 | Office Worker |");
        System.out.println("| 2 | Officer       |");
        System.out.println("| 3 | Supervisor    |");
        System.out.println("| 4 | Team Leader   |");
        System.out.println("| 5 | Manager       |");
        System.out.println("| 6 | CEO           |");
        System.out.println("| 7 | Director      |");
        System.out.println("+-------------------+");
        do {
            System.out.print("Job Title: ");
            jobTitle = scn.nextLine();
            isValid = Validator.validate(jobTitle, new int[]{1, 7});
        } while (!isValid);
        jobTitle = Employee.determineJobTitle(jobTitle);

        System.out.println();
        System.out.println("+-------------------+");
        System.out.println("| 1 | Production    |");
        System.out.println("| 2 | Sales         |");
        System.out.println("| 3 | Marketing     |");
        System.out.println("+-------------------+");
        do {
            System.out.print("Job Department: ");
            jobDepartment = scn.nextLine();
            isValid = Validator.validate(jobDepartment, new int[]{1, 3});
        } while (!isValid);
        jobDepartment = Employee.determineJobDepartment(jobDepartment);

        if (selectedEmployeeType == 1) {
            // salaried
            System.out.println();
            System.out.println("+-------------------+");
            System.out.println("| 1 | Part time     |");
            System.out.println("| 2 | Full time     |");
            System.out.println("+-------------------+");
            do {
                System.out.print("Contract type: ");
                contractType = scn.nextLine();
                isValid = Validator.validate(contractType, new int[]{1, 2});
            } while (!isValid);
            contractType = Employee.determineContractType(contractType);
            employee.setContractType(contractType);


            System.out.println();
            System.out.print("Annual Salary (£): ");
            annualSalary = scn.nextLine();
            employee.setAnnualSalary(annualSalary);

        } else if (selectedEmployeeType == 2) {
            // hourly
            System.out.println();
            System.out.print("Hourly pay rate (£): ");
            hourlyPayRate = scn.nextLine();
            employee.setHourlyPayRate(hourlyPayRate);

        } else if (selectedEmployeeType == 3) {
            // commission
            System.out.println();
            System.out.print("Annual gross salary (£): ");
            annualGrossSalary = scn.nextLine();
            employee.setAnnualGrossSalary(annualGrossSalary);

            System.out.print("Commission Rate (£): ");
            commissionRate = scn.nextLine();
            employee.setCommissionRate(commissionRate);
        }

        // generate a unique id
        String lastFourCharsOfNINO = nino.substring(nino.length() - 4);
        String id = lastFourCharsOfNINO + lastName;

        employee.setId(id);
        employee.setTypeOfEmployee(typeOfEmployee);
        employee.setTitle(title);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDob(dob);
        employee.setNino(nino);
        employee.setJobTitle(jobTitle);
        employee.setJobDepartment(jobDepartment);

        return employee;
    }


    /**
     * Removes an employee (a.k.a. fires him)
     */
    public static List<Employee> showDeletionForm(List<Employee> employeesList) {
        Scanner scn = new Scanner(System.in);

        boolean removingSequence;
        do {
            System.out.print("Please enter the ID of the employee to be deleted: ");
            String idToBeRemoved = scn.nextLine();

            boolean wasRemoved = employeesList.removeIf(new Predicate<Employee>() {
                @Override
                public boolean test(Employee employee) {
                    return employee.getId().equals(idToBeRemoved);
                }
            });

            if (wasRemoved) {
                System.out.println("✔ Employee with the ID `" + idToBeRemoved + "` was removed successfully.");
                System.out.println();
                System.out.print("Would you like to remove another employee? (y/N): ");
                String removeAnotherEmployee = scn.nextLine();
                removingSequence = Helper.isYesOrNo(removeAnotherEmployee, false);

            } else {
                System.err.println("Did not find an employee with the provided ID: `" + idToBeRemoved + "`");
                System.out.print("Would you like to try again? (Y/n): ");
                String retry = scn.nextLine();
                removingSequence = Helper.isYesOrNo(retry, true);
            }

        } while (removingSequence);

        return employeesList;
    }


    public static void showPayslipGenerationForm(List<Employee> employeeList) {
        Scanner scn = new Scanner(System.in);
        boolean onGeneratePayslip;

        System.out.println();
        System.out.println("+---------------------------+");
        System.out.println("| Generating Payslip        |");
        System.out.println("+---------------------------+");

        do {
            System.out.print("Please enter the ID of the employee: ");
            String idToGenPaySlip = scn.nextLine();

            Employee emp = employeeList.stream().filter(new Predicate<Employee>() {
                @Override
                public boolean test(Employee employee) {
                    return employee.getId().equals(idToGenPaySlip);
                }
            }).findFirst().orElse(null);

            if (emp != null) {
                // found the employee with that id
                String employeeFullName = emp.getTitle() + " " + emp.getFirstName() + " " + emp.getLastName();
                System.out.println("- You've selected `" + employeeFullName + "`");

                System.out.println();
                int i = 1;
                String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                System.out.println("+-------------------+");
                System.out.println("+ Available Months  +");
                System.out.println("+-------------------+");
                int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // + 1 because it returns a zero based value.
                for (String month: months) {
                    System.out.println("| " + (i < 10 ? " " + i : i) + " | " + month + Helper.generateWhitespaces(13 - month.length()) +  "|");
                    i += 1;

                    if (i == currentMonth) {
                        break;
                    }
                }
                System.out.println("+-------------------+");

                System.out.print("• Please enter the month of the payslip to generate (" + (i - 1) + "): ");
                String month = scn.nextLine();



                String typeOfEmployee = emp.getTypeOfEmployee();

                String totalHours = "0", totalSales = "0"; // todo: store these variables as int/double instead
                switch (typeOfEmployee) {
                    case Employee.EMPLOYEE_TYPE_HOURLY:
                        System.out.print("Total time:");
                        totalHours = scn.nextLine(); // todo: validate this (to only accept number/double)
                        break;

                    case Employee.EMPLOYEE_TYPE_COMMISSION:
                        System.out.println("Total sales:"); // todo: validate this (to only accept number/double)
                        totalSales = scn.nextLine();
                        break;
                }
                System.out.println();
                System.out.println("\n• Here is `" + employeeFullName + "` " + months[Integer.parseInt(month) - 1] + " payslip:");

                // show his payslip.
                CommandLineTable table = new CommandLineTable();
                table.setShowVerticalLines(true); //if false (default) then no vertical lines are shown

                String fullname = emp.getTitle() + " " + emp.getFirstName() + " " + emp.getLastName();
                String id = emp.getId();
                String nino = emp.getNino();
                String jobTitle = emp.getJobTitle();
                String jobDepartment = emp.getJobDepartment();
                String contractType = emp.getContractType();
                int netDue;

//                // TODO: verticalCLITable
//                VerticalComandLineTable vclt = new VerticalComandLineTable();
//                vclt.addRow("Name", "Kishan Nareshpal Jadav");
//                vclt.addRow("Date", "08/08/1999");
//                vclt.addRow("Age", "21");
//                vclt.addSeparator();
//                vclt.addRow("Total", "£123,000");
//                vclt.show();
//                // TODO-END;


                // todo: calculate and show the correct ammount.
                switch (emp.getTypeOfEmployee()) {
                    case Employee.EMPLOYEE_TYPE_SALARIED:
                        table.setHeaders("Payslip Reference (ID)", "Full Name", "Employee Type", "NINO", "Job Title", "Job Dept.", "Contract Type", "Net Due (£)");
                        int annualSalary = Integer.parseInt(emp.getAnnualSalary());
                        netDue = annualSalary / 52; // There are approx. 52 weeks in a year.
                        table.addRow(id, fullname, typeOfEmployee, nino, jobTitle, jobDepartment, contractType, String.valueOf(netDue));
                        break;

                    case Employee.EMPLOYEE_TYPE_HOURLY:
                        table.setHeaders("Payslip Reference (ID)", "Full Name", "Employee Type", "NINO", "Job Title", "Job Dept.", "Contract Type", "Total Hours", "Net Due (£)");
                        netDue = Integer.parseInt(emp.getHourlyPayRate()) * Integer.parseInt(totalHours); // total numbers of hours multiplied by the hourlypayrate
                        table.addRow(id, fullname, typeOfEmployee, nino, jobTitle, jobDepartment, contractType, String.valueOf(netDue));
                        break;

                    case Employee.EMPLOYEE_TYPE_COMMISSION:
                        table.setHeaders("Payslip Reference (ID)", "Full Name", "Employee Type", "NINO", "Job Title", "Job Dept.", "Contract Type", "Net Due (£)");
                        int annualGrossSalary = Integer.parseInt(emp.getAnnualGrossSalary());
                        netDue = annualGrossSalary / 52; // todo: use the proper equation here.
                        table.addRow(id, fullname, typeOfEmployee, nino, jobTitle, jobDepartment, contractType, String.valueOf(netDue));
                        break;
                }
                table.print();

                System.out.println();
                System.out.println();
                System.out.print("Would you like to view another employee's payslip? (y/N): ");
                String retry = scn.nextLine();
                onGeneratePayslip = Helper.isYesOrNo(retry, false);

            } else {
                // oops. no employee found with the provided id.
                System.err.println("No Employee found with the ID: `" + idToGenPaySlip + "`");

                System.out.println();
                System.out.println();
                System.out.print("Would you like to try another ID? (Y/n): ");
                String retry = scn.nextLine();

                onGeneratePayslip = Helper.isYesOrNo(retry, true);

            }

        } while (onGeneratePayslip);
    }
}
