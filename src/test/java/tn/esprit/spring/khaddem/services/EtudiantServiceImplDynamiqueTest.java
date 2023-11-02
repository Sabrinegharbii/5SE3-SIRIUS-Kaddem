/*package tn.esprit.spring.khaddem.services;

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
    public void testAddEtudiant() {

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
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertTrue(etudiants.size() > 0);
    }

    @Order(3)
    @Test
    public void testRetrieveEtudiant() {
        Etudiant etudiant = etudiantService.retrieveEtudiant(3);
        assertEquals(3, etudiant.getIdEtudiant());
    }



    @Order(4)
    @Test
    public void testUpdateEtudiant() {
        Etudiant etudiant = etudiantRepository.findById(3).get();
        etudiant.setNomE("Updated nom");
        etudiant.setPrenomE("Updated prenom");
        etudiantRepository.save(etudiant);
        assertEquals(3, etudiant.getIdEtudiant());
        assertEquals("Updated nom", etudiant.getNomE());
        assertEquals("Updated prenom", etudiant.getPrenomE());
    }

    @Test
    public void testRemoveEtudiant() {
       etudiantRepository.deleteById(4);
       assertFalse(etudiantRepository.existsById(4));

    }




}*/
