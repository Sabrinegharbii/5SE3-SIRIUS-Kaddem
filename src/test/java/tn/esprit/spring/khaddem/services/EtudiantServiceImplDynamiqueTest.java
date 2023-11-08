package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EtudiantServiceImplDynamiqueTest {

    @Autowired
    private EtudiantServiceImpl etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Order(1)
    @Test
     void testAddEtudiant() {

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Doe");
        etudiant.setPrenomE("John");
        Option option = Option.INFINI;


        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertEquals("Doe", addedEtudiant.getNomE());
        assertEquals("John", addedEtudiant.getPrenomE());
    }

    @Order(2)
    @Test
     void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertTrue(etudiants.size() > 0);
    }

    @Order(3)
    @Test
     void testRetrieveEtudiant() {
        Etudiant etudiant = etudiantService.retrieveEtudiant(3);
        assertEquals(3, etudiant.getIdEtudiant());
    }



    @Order(4)
    @Test
     void testUpdateEtudiant() {

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Doe");
        etudiant.setPrenomE("John");
        Etudiant e = etudiantService.addEtudiant(etudiant);


        e.setNomE("Updated nom");
        e.setPrenomE("Updated prenom");
        Etudiant updatedetudiant = etudiantService.updateEtudiant(e);
        Etudiant retrievedetudiant = etudiantRepository.findById(updatedetudiant.getIdEtudiant()).orElse(null);
        assertNotNull(updatedetudiant);
        assertEquals(updatedetudiant.getNomE(),retrievedetudiant.getNomE());
    }

    @Test
     void testRemoveEtudiant() {
       etudiantRepository.deleteById(4);
       assertFalse(etudiantRepository.existsById(4));

    }





}

