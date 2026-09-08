

import com.mycompany.medicarehospital.Bed;
import com.mycompany.medicarehospital.Bed;
import com.mycompany.medicarehospital.Bed;
import com.mycompany.medicarehospital.Bed;
import com.mycompany.medicarehospital.HospitalSystem;
import com.mycompany.medicarehospital.HospitalSystem;
import com.mycompany.medicarehospital.HospitalSystem;
import com.mycompany.medicarehospital.HospitalSystem;
import com.mycompany.medicarehospital.Inpatient;
import com.mycompany.medicarehospital.Inpatient;
import com.mycompany.medicarehospital.Inpatient;
import com.mycompany.medicarehospital.Inpatient;
import com.mycompany.medicarehospital.Patient;
import com.mycompany.medicarehospital.Patient;
import com.mycompany.medicarehospital.Patient;
import com.mycompany.medicarehospital.Patient;
import com.mycompany.medicarehospital.PatientCategory;
import com.mycompany.medicarehospital.PatientCategory;
import com.mycompany.medicarehospital.PatientCategory;
import com.mycompany.medicarehospital.PatientCategory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {

    // ==========================================
    // 1. REGISTER PATIENT
    // ==========================================

    @Test
    public void testRegisterPatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P001",
                "John",
                "Dlamini",
                21,
                "Male",
                "Asthma",
                PatientCategory.Outpatient
        );

        boolean result = hospital.registerPatient(patient);

        assertTrue(result);
        assertNotNull(hospital.findPatient("P001"));
    }


    // ==========================================
    // 2. SEARCH FOR PATIENT
    // ==========================================

    @Test
    public void testSearchPatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P002",
                "Thandi",
                "Mokoena",
                25,
                "Female",
                "Flu",
                PatientCategory.Outpatient
        );

        hospital.registerPatient(patient);

        Patient found = hospital.findPatient("P002");

        assertNotNull(found);
        assertEquals("P002", found.getPatientID());
        assertEquals("Thandi", found.getFirstName());
    }


    // ==========================================
    // 3. UPDATE PATIENT
    // ==========================================

    @Test
    public void testUpdatePatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P003",
                "John",
                "Smith",
                20,
                "Male",
                "Flu",
                PatientCategory.Outpatient
        );

        hospital.registerPatient(patient);

        boolean result = hospital.updatePatient(
                "P003",
                "Jonathan",
                "Smith",
                21,
                "Male",
                "Asthma"
        );

        assertTrue(result);

        Patient updated = hospital.findPatient("P003");

        assertEquals("Jonathan", updated.getFirstName());
        assertEquals(21, updated.getAge());
        assertEquals("Asthma", updated.getMedicalCondition());
    }


    // ==========================================
    // 4. DELETE PATIENT
    // ==========================================

    @Test
    public void testDeletePatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P004",
                "David",
                "Ndlovu",
                30,
                "Male",
                "Fever",
                PatientCategory.Outpatient
        );

        hospital.registerPatient(patient);

        boolean result = hospital.deletePatient("P004");

        assertTrue(result);
        assertNull(hospital.findPatient("P004"));
    }


    // ==========================================
    // 5. ALLOCATE A BED
    // ==========================================

    @Test
    public void testAllocateBed() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient patient = new Inpatient(
                "P005",
                "Sipho",
                "Zulu",
                30,
                "Male",
                "Fracture",
                PatientCategory.Inpatient,
                null,
                null
        );

        boolean result = hospital.admitInpatient(patient);

        assertTrue(result);

        assertEquals("W01", patient.getWardNumber());
        assertEquals("B01", patient.getBedNumber());
    }


    // ==========================================
    // 6. RELEASE A BED
    // ==========================================

    @Test
    public void testReleaseBed() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient patient = new Inpatient(
                "P006",
                "Sarah",
                "Naidoo",
                28,
                "Female",
                "Injury",
                PatientCategory.Inpatient,
                null,
                null
        );

        hospital.admitInpatient(patient);

        boolean result =
                hospital.releaseInpatient("P006");

        assertTrue(result);

        assertNull(patient.getBedNumber());
        assertNull(patient.getWardNumber());
    }


    // ==========================================
    // 7. PREVENT DUPLICATE PATIENT IDs
    // ==========================================

    @Test
    public void testDuplicatePatientId() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient1 = new Patient(
                "P007",
                "John",
                "Dlamini",
                21,
                "Male",
                "Asthma",
                PatientCategory.Outpatient
        );

        Patient patient2 = new Patient(
                "P007",
                "Peter",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.emergency
        );

        boolean first =
                hospital.registerPatient(patient1);

        boolean second =
                hospital.registerPatient(patient2);

        assertTrue(first);
        assertFalse(second);
    }


    // ==========================================
    // 8. PREVENT OCCUPIED BED ALLOCATION
    // ==========================================

    @Test
    public void testOccupiedBedAllocation() {

        Bed bed = new Bed("B01");

        Inpatient patient1 = new Inpatient(
                "P008",
                "John",
                "Dlamini",
                25,
                "Male",
                "Asthma",
                PatientCategory.Inpatient,
                null,
                null
        );

        Inpatient patient2 = new Inpatient(
                "P009",
                "Peter",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.Inpatient,
                null,
                null
        );

        boolean firstAllocation =
                bed.allocate(patient1);

        boolean secondAllocation =
                bed.allocate(patient2);

        assertTrue(firstAllocation);
        assertFalse(secondAllocation);

        assertEquals(
                "P008",
                bed.getPatient().getPatientID()
        );
    }


    // ==========================================
    // 9. PREVENT ALLOCATION WHEN ALL BEDS
    //    ARE OCCUPIED
    // ==========================================

    @Test
    public void testWardFull() {

        HospitalSystem hospital = new HospitalSystem();

        // Fill all 20 beds
        for (int i = 1; i <= 20; i++) {

            String patientId =
                    String.format("P%03d", i);

            Inpatient patient = new Inpatient(
                    patientId,
                    "Patient",
                    "Test" + i,
                    20,
                    "Male",
                    "Condition",
                    PatientCategory.Inpatient,
                    null,
                    null
            );

            assertTrue(
                    hospital.admitInpatient(patient)
            );
        }

        // Try to admit patient number 21
        Inpatient patient21 = new Inpatient(
                "P021",
                "Patient",
                "TwentyOne",
                21,
                "Male",
                "Condition",
                PatientCategory.Inpatient,
                null,
                null
        );

        boolean result =
                hospital.admitInpatient(patient21);

        assertFalse(result);
    }


    // ==========================================
    // 10. SORT PATIENTS
    // ==========================================

    @Test
    public void testSortPatients() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient1 = new Patient(
                "P003",
                "John",
                "Zulu",
                21,
                "Male",
                "Asthma",
                PatientCategory.Outpatient
        );

        Patient patient2 = new Patient(
                "P001",
                "Peter",
                "Dlamini",
                30,
                "Male",
                "Flu",
                PatientCategory.Outpatient
        );

        Patient patient3 = new Patient(
                "P002",
                "Sarah",
                "Mokoena",
                25,
                "Female",
                "Injury",
                PatientCategory.emergency
        );

        hospital.registerPatient(patient1);
        hospital.registerPatient(patient2);
        hospital.registerPatient(patient3);

        // Sort by surname
        hospital.sortPatientsBySurname();

        assertEquals(
                "Dlamini",
                hospital.getPatients()
                        .get(0)
                        .getLastName()
        );

        assertEquals(
                "Mokoena",
                hospital.getPatients()
                        .get(1)
                        .getLastName()
        );

        assertEquals(
                "Zulu",
                hospital.getPatients()
                        .get(2)
                        .getLastName()
        );

        // Sort by Patient ID
        hospital.sortPatientsById();

        assertEquals(
                "P001",
                hospital.getPatients()
                        .get(0)
                        .getPatientId()
        );

        assertEquals(
                "P002",
                hospital.getPatients()
                        .get(1)
                        .getPatientId()
        );

        assertEquals(
                "P003",
                hospital.getPatients()
                        .get(2)
                        .getPatientId()
        );
    }
}