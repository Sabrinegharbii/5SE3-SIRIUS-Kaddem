package tn.esprit.spring.khaddem.services;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest

class ContratServiceImplDynamiqueTest {

   @Autowired

   private ContratServiceImpl contratService;

   @Autowired
   private ContratRepository contratRepository;
   @Order(1)
   @Test
   void addContrat() {
       Contrat cont = new Contrat();
       cont.setMontantContrat(700);
       Contrat contrat = contratService.addContrat(cont);
       assertNotNull(contrat);
       assertEquals(700, contrat.getMontantContrat());
       System.out.println("Add dynamique : Ok");
   }

   @Test
   void retrieveAllContrat() {
       List<Contrat> contrats = contratService.retrieveAllContrats();
       assertFalse(contrats.isEmpty());
       System.out.println("RetriveAll dynamique : Ok");
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
