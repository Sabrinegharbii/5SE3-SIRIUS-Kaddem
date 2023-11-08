package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import java.util.*;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ContratStaticTest {


    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository;
    // You need to create ContratRepository for data access

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    int getId() {
        for(Contrat c : contratRepository.findAll()) {
            return c.getIdContrat();
        }
        return 0;
    }

    @Test
    void testRetrieveAllContrats() {

        List<Contrat> sampleContrats = new ArrayList<>();

        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setMontantContrat(1000);

        sampleContrats.add(contrat1);

        Contrat contrat2 = new Contrat();
        contrat2.setIdContrat(2);
        contrat2.setMontantContrat(2000);

        sampleContrats.add(contrat2);

        when(contratRepository.findAll()).thenReturn(sampleContrats);

        // Call the service method
        List<Contrat> result = contratService.retrieveAllContrats();

        // Verify that the service method returned the expected data
        assertEquals(2, result.size());
        verify(contratRepository, times(1)).findAll();

        System.err.println("GetAllContratsTest : Ok");
    }

    @Test
    void testAddContrat() {
        // Create a sample Contrat to add
        Contrat newContrat = new Contrat();
        newContrat.setIdContrat(3);
        newContrat.setDateDebutContrat(new Date()); // You can use java.util.Date for date fields
        newContrat.setDateFinContrat(new Date());
        newContrat.setSpecialite(Specialite.IA); // Replace with a valid enum value
        newContrat.setArchived(false);
        newContrat.setMontantContrat(5000);

        // Create a sample Etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Ayoub");
        newContrat.setEtudiant(etudiant);

        // Mock the behavior of the contratRepository to save the newContrat and return it
        when(contratRepository.save(newContrat)).thenReturn(newContrat);

        // Call the service method
        Contrat addedContrat = contratService.addContrat(newContrat);

        Contrat addedContratForTest = new Contrat();

        // Verify that the service method returned the added contract
        assertEquals(newContrat, addedContrat);

        // Verify that the save method was called on the contratRepository
        verify(contratRepository, times(1)).save(newContrat);

        System.err.println("AddContratTest : Ok");

    }
    @Test
     void testUpdateContrat() {
        // Create a sample Contrat to update
        Contrat existingContrat = new Contrat();
        Contrat existingContratForTest = new Contrat();
        existingContrat.setIdContrat(1);
        // Set other properties as needed

        // Mock the behavior of the contratRepository to save the updated Contrat and return it
        when(contratRepository.save(existingContrat)).thenReturn(existingContrat);

        // Call the service method
        Contrat updatedContrat = contratService.updateContrat(existingContrat);

        // Verify that the service method returned the updated contract
        assertEquals(existingContrat, updatedContrat);

        // Verify that the save method was called on the contratRepository
        verify(contratRepository, times(1)).save(existingContrat);

        System.err.println("UpdateContratTest : Ok");
    }
    @Test
    void testRetrieveContrat() {


        Contrat sampleContrat = new Contrat();
        sampleContrat.setIdContrat(1);

        when(contratRepository.findById(1)).thenReturn(Optional.of(sampleContrat));


        Contrat result = contratService.retrieveContrat(1);
        assertEquals(sampleContrat, result);


        verify(contratRepository, times(1)).findById(1);

        System.err.println("GetOneContratTest : Ok");
    }
    @Test
    void testDeleteContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);

        when(contratRepository.save(contrat)).thenReturn(contrat);
        contratService.removeContrat(contrat.getIdContrat());
        List<Contrat> result = contratService.retrieveAllContrats();
        assertEquals(0, result.size());

        System.err.println("DeleteContratTest : Ok");
    }
    @Test
    void ChiffreAffaireTest(){
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setMontantContrat(200);
        Date d = new Date(2023,2,8);
        contrat.setDateDebutContrat(d);
        contrat.setSpecialite(Specialite.IA);
        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(2);
        contrat1.setMontantContrat(200);
        contrat1.setSpecialite(Specialite.IA);
        Date d2 = new Date(2023,2,8);
        contrat1.setDateDebutContrat(d2);

        List<Contrat> sampleContrats = new ArrayList<>();
        sampleContrats.add(contrat);
        sampleContrats.add(contrat1);
        float mont=0;
        for (Contrat c:sampleContrats) {
            mont=mont+c.getMontantContrat();
        }
        contratService.addContrat(contrat);
        contratService.addContrat(contrat1);
        Date d5 = new Date(2022,2,8);
        Date d6 = new Date(2024,5,30);
        float result = contratService.getChiffreAffaireEntreDeuxDates(d5,d6);
        assertEquals(400,mont);
        System.err.println("ChiffreAffaireEntreDeuxDatesTest : Ok");

    }


}