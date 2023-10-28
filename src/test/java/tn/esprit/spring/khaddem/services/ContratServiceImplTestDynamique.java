package tn.esprit.spring.khaddem.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest

class ContratServiceImplTestDynamique {

    @Autowired

    private ContratServiceImpl contratService;

    @Autowired
    private ContratRepository contratRepository;
    @Test
    void addContrat() {
        Contrat cont = new Contrat();
        cont.setIdContrat(1);
        Contrat contrat = contratService.addContrat(cont);
        assertNotNull(contrat);
        assertEquals(1, contrat.getIdContrat());
        System.out.println("Add dynamique : Ok");
    }
    @Test
    void retrieveAllContrat() {
        List<Contrat> contrats = contratService.retrieveAllContrats();
        assertFalse(contrats.isEmpty());
        System.out.println("Liste des Contrats dans la table de Contrat :");
        for (Contrat contrat : contrats) {
            System.err.println("ID : " + contrat.getIdContrat()+ ", Montant : " + contrat.getMontantContrat());
        }
    }
    @Test
    void updateContrat() {
        Contrat contrattrue = new Contrat();
        contrattrue.setIdContrat(34);
        contrattrue.setMontantContrat(40);
        Contrat savedContrat = contratRepository.save(contrattrue);
        Integer updatedMontant = 77;
        savedContrat.setMontantContrat(updatedMontant);
        Contrat updatedDepartement = contratService.updateContrat(savedContrat);
        Contrat retrievedDepartement = contratRepository.findById(updatedDepartement.getIdContrat()).orElse(null);
        assertNotNull(retrievedDepartement);
        assertEquals(updatedMontant, retrievedDepartement.getMontantContrat());
        System.err.println("Update Contrat : OK !");
    }

}