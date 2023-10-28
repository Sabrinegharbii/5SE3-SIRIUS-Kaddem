package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    //GetAll
    @Test
     void testRetrieveAllEtudiants() {
        List<Etudiant> Etudiants = new ArrayList<>();

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        Etudiants.add(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(2);
        Etudiants.add(etudiant2);

        Etudiant etudiant3 = new Etudiant();
        etudiant2.setIdEtudiant(3);
        Etudiants.add(etudiant3);

        when(etudiantRepository.findAll()).thenReturn(Etudiants);//hthy bch tekhdem khdmt repo
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();//hthy bch tnedi l service
        assertEquals(Etudiants, result);//tverifi si methode eli f service raj3t les etudiants
        System.err.println("All students are retrieved successfully");

    }

    //Add
    @Test
     void testAddEtudiant() {

        Option option = Option.INFINI;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(4);
        etudiant.setPrenomE("John");
        etudiant.setNomE("Doe");
        etudiant.setOp(option);

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);
        assertEquals(etudiant, addedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);

        System.err.println("Student is added successfully");
    }

    //Update
    @Test
     void testUpdateEtudiant() {

        Option option = Option.GAMIX;
        Etudiant existingEtudiant = new Etudiant();
        existingEtudiant.setIdEtudiant(4);
        existingEtudiant.setPrenomE("Jey");
        existingEtudiant.setNomE("Doe");
        existingEtudiant.setOp(option);

        when(etudiantRepository.save(existingEtudiant)).thenReturn(existingEtudiant);
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(existingEtudiant);
        assertEquals(existingEtudiant, updatedEtudiant);
        verify(etudiantRepository, times(1)).save(existingEtudiant);
        System.err.println("Student updated successfully");

    }

    //Getbyid
    @Test
     void testRetrieveEtudiant() {
        int id = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(id);
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));
        Etudiant result = etudiantService.retrieveEtudiant(id);
        assertEquals(etudiant, result);
        verify(etudiantRepository, times(1)).findById(id);
        System.err.println("Student retrieved successfully");



    }

    //Delete
    @Test
     void testRemoveEtudiant() {
        doNothing().when(etudiantRepository).deleteById(anyInt());
        etudiantService.removeEtudiant(1);
        verify(etudiantRepository, times(1)).deleteById(1);
        System.err.println("Student deleted successfully");

    }

    @Test
     void testRetrieveEtudiantsByContratSpecialite() {
        Specialite specialite = Specialite.IA;
        List<Etudiant> etudiants = new ArrayList<>();

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiants.add(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(2);
        etudiants.add(etudiant2);

        when(etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite)).thenReturn(etudiants);
        List<Etudiant> result = etudiantService.retrieveEtudiantsByContratSpecialite(specialite);
        assertEquals(etudiants, result);
        System.err.println("Student retrieved by contract successfully");

    }








}