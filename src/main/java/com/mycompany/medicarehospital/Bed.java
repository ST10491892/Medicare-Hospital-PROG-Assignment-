/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author emeris
 */
public class Bed {
    
    private String bedNumber;
    private boolean occupied;
    private Inpatient patient;
    
    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        this.occupied = false;
        this.patient = null;
    }
    
    public String getBedNumber() {
        return bedNumber;
    }
    
    public boolean isOccupied() {
        return occupied;
    }
    
    public Inpatient getPatient() {
        return patient;
    }
    
    public boolean allocate(Inpatient patient) {
        
        if (occupied) {
            return false;
        }
        
        this.patient = patient;
        this.occupied = true;
        
        return true;
    }
    
    public void release() {
        this.patient = null;
        this.occupied = false;
    }
   
    public void displayBed(){
        
        if (occupied) {
            System.out.println(
                bedNumber + " - Occupied - " +
                patient.getPatientID() + " - " +
                patient.getFirstName() + " " +
                patient.getLastName()        
            );
        } else {
            System.out.println(bedNumber + " - Availabe");
        }
    }
}
