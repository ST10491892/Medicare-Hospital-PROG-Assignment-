/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

import java.util.ArrayList;
import java.util.Comparator;

public class HospitalSystem {
    
    private ArrayList<Patient> patients;
    private HospitalWard ward;
    
    public HospitalSystem() {
        patients = new ArrayList<>();
        ward = new HospitalWard();
    }
    
    /*
    Register Patient
    */
    public boolean registerPatient(Patient patient) {
       
        if (findPatient(patient.getPatientID()) != null) {
            return false;
        }
        
        patients.add(patient);
        
        return true;
    }
    
    /*
    Display All Patients
    */
    public void displayAllPatients() {
        
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }
        
        System.out.println("\n===== All Patients =====");
        
        for (Patient patient : patients) {
            
            patient.displayDetails();
            
            System.out.println("--------------");
        }    
    }
    
    /*
    Search Patients
    */
    public void searchPatient(String patientID) {
        
        Patient patient = findPatient(patientID);
        
        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            
            System.out.println("\n===== Patient Found =====");
            patient.displayDetails();
        }
    }
    
    public Patient findPatient(String patientID){
        
        for (Patient patient : patients) {
            
            if (patient.getPatientID().equalsIgnoreCase(patientID)) {
                return patient;
            }
        }
        
        return null;
    }
    
    /*
    Deleted Patient
    */
    public boolean deletedPatient(String patientID) {
        
        Patient patient = findPatient(patientID);
        
        if (patient == null){
            return false;
        }
        
        if (patient instanceof Inpatient){
            Inpatient inpatient = (Inpatient) patient;
            
            if (inpatient.getBedNumber() != null) {
                ward.releaseBed(inpatient.getBedNumber()
                );
            }
        }
        
        patients.remove(patient);
        return true;
    }
    
    
    /*
    Ipdate Patient
    */
    public boolean updatePatient(
     String patientID,
     String firstName,
     String lastName,
     int age,
     String gender,
     String medicalCondition) {
    
        Patient patient = findPatient(patientID);
    
        if (patient == null) {
            return false;
        }
        
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);
        
        return true;     
    }
    
    /*
    Admin Inpatient
    */
    public boolean admitInpatient(Inpatient patient) {
        
        if (findPatient(patient.getPatientID()) != null) {
            return false;
        }
        
        boolean allocated = ward.allocateBed(patient);
        
        if (! allocated) {
            return false;
        }
        
        patients.add(patient);
        
        return true;
    }
    
    /*
    Display Ward
    */
    public void displayWard() {
        ward.displayWard();
    }
    
    /*
    Available beds
    */
    public void displayAvailableBeds() {
        ward.displayAvailableBeds();
    }
    
    /*
    Occupied Beds
    */
    public void displayOccupiedBeds(){
        ward.displayOccupiedBeds();
    }

    boolean deletePatient(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean releaseInpatient(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void sortPatientsBySurname() {

        patients.sort(
            Comparator.comparing(
                Patient::getLastName,
                String.CASE_INSENSITIVE_ORDER
            )
        );
    }
    
    public void sortPatientsById() {

    patients.sort(
            Comparator.comparing(
                Patient::getPatientID,
                String.CASE_INSENSITIVE_ORDER
            )
        );
    }

    public Object getPatients() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
