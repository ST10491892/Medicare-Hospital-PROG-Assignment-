/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.medicarehospital;
import java.util.Scanner;

public class MedicareHospital {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HospitalSystem hospital = new HospitalSystem();

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("       MEDICARE HOSPITAL");
            System.out.println("================================");
            System.out.println("1. Register Outpatient");
            System.out.println("2. Register Emergency Patient");
            System.out.println("3. Admit Inpatient");
            System.out.println("4. Search Patient");
            System.out.println("5. Update Patient");
            System.out.println("6. Delete Patient");
            System.out.println("7. Release Inpatient");
            System.out.println("8. Display All Patients");
            System.out.println("9. Display Available Beds");
            System.out.println("10. Display Occupied Beds");
            System.out.println("11. Display Ward");
            System.out.println("0. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerOutpatient(scanner, hospital);
                    break;

                case 2:
                    registerEmergency(scanner, hospital);
                    break;

                case 3:
                    admitInpatient(scanner, hospital);
                    break;

                case 4:
                    searchPatient(scanner, hospital);
                    break;

                case 5:
                    updatePatient(scanner, hospital);
                    break;

                case 6:
                    deletePatient(scanner, hospital);
                    break;

                case 7:
                    releaseInpatient(scanner, hospital);
                    break;

                case 8:
                    hospital.displayAllPatients();
                    break;

                case 9:
                    hospital.displayAvailableBeds();
                    break;

                case 10:
                    hospital.displayOccupiedBeds();
                    break;

                case 11:
                    hospital.displayWard();
                    break;

                case 0:
                    running = false;
                    System.out.println("Thank you for using Medicare Hospital.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    /*
    OutPatient
    */
    private static void registerOutpatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.println("\n====== REGISTER OUTPATIENT ======");

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition,
                PatientCategory.Outpatient
        );

        if (hospital.registerPatient(patient)) {

            System.out.println(
                    "Outpatient registered successfully."
            );

        } else {

            System.out.println(
                    "Registration failed. Patient ID already exists."
            );
        }
    }

    /*
    Emergency
    */
    private static void registerEmergency(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.println("\n====== EMERGENCY PATIENT ======");

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition,
                PatientCategory.emergency
        );

        if (hospital.registerPatient(patient)) {

            System.out.println(
                    "Emergency patient registered successfully."
            );

        } else {

            System.out.println(
                    "Registration failed. Patient ID already exists."
            );
        }
    }
   
    /*
    Inpatient
    */
    private static void admitInpatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.println("\n====== ADMIT INPATIENT ======");

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        Inpatient patient = new Inpatient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition,
                PatientCategory.Inpatient,
                null,
                null
        );

        if (hospital.admitInpatient(patient)) {

            System.out.println(
                    "Inpatient admitted successfully."
            );

            System.out.println(
                    "Ward: " + patient.getWardNumber()
            );

            System.out.println(
                    "Bed: " + patient.getBedNumber()
            );

        } else {

            System.out.println(
                    "Admission failed. Patient may already exist "
                    + "or no beds are available."
            );
        }
    }

    /*
    Search
    */
    private static void searchPatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("\nEnter Patient ID: ");

        String id = scanner.nextLine();

        hospital.searchPatient(id);
    }

    /*
    Update
    */
    private static void updatePatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.println("\n====== UPDATE PATIENT ======");

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        Patient existing = hospital.findPatient(id);

        if (existing == null) {

            System.out.println("Patient not found.");
            return;
        }

        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Gender: ");
        String gender = scanner.nextLine();

        System.out.print("New Medical Condition: ");
        String condition = scanner.nextLine();

        if (hospital.updatePatient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition)) {

            System.out.println(
                    "Patient updated successfully."
            );

        } else {

            System.out.println(
                    "Patient update failed."
            );
        }
    }

    /*
    Delete
    */
    private static void deletePatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.println("\n====== DELETE PATIENT ======");

        System.out.print("Patient ID: ");

        String id = scanner.nextLine();

        if (hospital.deletePatient(id)) {

            System.out.println(
                    "Patient deleted successfully."
            );

        } else {

            System.out.println(
                    "Patient not found."
            );
        }
    }

    /*
    Release
    */
    private static void releaseInpatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.println("\n====== RELEASE INPATIENT ======");

        System.out.print("Patient ID: ");

        String id = scanner.nextLine();

        if (hospital.releaseInpatient(id)) {

            System.out.println(
                    "Inpatient released successfully."
            );

        } else {

            System.out.println(
                    "Patient could not be released."
            );
        }
    
    }
        
    
}
