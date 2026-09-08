/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medicarehospital;

/**
 *
 * @author emeris
 */
public class HospitalWard {
    
    private Bed[][] beds;
    
    public HospitalWard() {
        beds = new Bed [4][5];
        
        int bedNumber = 1;
        
        for (int row = 0; row < beds.length; row++) {
            
            for (int column = 0; column < beds[row].length; column++) {
                String bedID = String.format("B%02d", bedNumber);
                beds[row][column] = new Bed(bedID);
                bedNumber++;
            }
        }
    }
    
    public void displayWard() {
        for (int row = 0; row < beds.length; row++) {
            
            for (int column = 0; column < beds[row].length; column++){
                Bed bed = beds[row][column];
                
                System.out.print(bed.getBedNumber());
                
                if (bed.isOccupied()) {
                    System.out.print(
                            bed.getBedNumber() + "[OCCUPIED] "
                    );
                } else {
                    System.out.print(
                            bed.getBedNumber() + "[AVAILABLE] "
                    );
                }
            }
            
            System.out.println();
        }
    }
    
    
    
    public Bed findAvailableBed() {
        
       for (int row = 0; row < beds.length; row++) {
           
            for (int column = 0; column < beds[row].length; column++) {
                
                if (!beds[row][column].isOccupied()) {
                    return beds[row][column];
                }
            }
        }
       return null;
    }
    
    public boolean allocateBed(Inpatient patient) {
        Bed availableBed = findAvailableBed();
        
        if (availableBed == null) {
            return false;
        }
        
        if (availableBed.allocate(patient)) {
        
            patient.setBedNumber(availableBed.getBedNumber());
            patient.setWardNumber("W01");
        
            return true;
        }
        
        return false;
    }
    
    
    
    public boolean releaseBed(String bedNumber) {
       
        for (int row= 0; row <beds.length; row++) {
         
            for (int column = 0; column < beds[row].length; column++) {
                Bed bed = beds[row][column];
                
                if (bed.getBedNumber().equalsIgnoreCase(bedNumber)) {
                    if (!bed.isOccupied()) {
                        return false;
                    }
                    
                    Inpatient patient = bed.getPatient();

                    patient.setWardNumber(null);
                    patient.setBedNumber(null);
                    
                    bed.release();
                    
                    return true;
                }
            }
        }
        
        return false;
    }
     public void displayAvailableBeds() {
       
        System.out.println("\n====== AVAILABLE BEDS ======");
        
        boolean found = false;
        
        for (int row = 0; row < beds.length; row++) {
            for (int column = 0; column < beds[row].length; column++) {
                 
                Bed bed = beds[row][column];
                 
                if (!bed.isOccupied()) {
                    System.out.println(bed.getBedNumber());
                    found = true;
                }
           
            }
        }
    }
     
     public void displayOccupiedBeds() {
         
        System.out.println("\n====== OCCUPIED BEDS ======"); 
        
        boolean found = false;
         
         for (int row = 0; row < beds.length; row++) {
             for (int column = 0; column < beds[row].length; column++) {
                 
                 Bed bed = beds[row][column];
                 
                 if (bed.isOccupied()) {

                    System.out.println(
                            bed.getBedNumber() +
                            " - " +
                            bed.getPatient().getPatientID() +
                            " - " +
                            bed.getPatient().getFirstName() +
                            " " +
                            bed.getPatient().getLastName()
                    );

                    found = true;
                }
            }
        }
         
         if (!found) {
            System.out.println("No beds available.");
         }    
    }
}

