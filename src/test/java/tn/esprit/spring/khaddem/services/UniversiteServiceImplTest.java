package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Departement;

import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplTest {
    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();

        Universite universite1 = new Universite();
        universite1.setIdUniversite(1);
        universites.add(universite1);

        Universite universite2 = new Universite();
        universite2.setIdUniversite(2);
        universites.add(universite2);

        Universite universite3 = new Universite();
        universite3.setIdUniversite(3);
        universites.add(universite3);

        when(universiteRepository.findAll()).thenReturn(universites);
        List<Universite> result = universiteService.retrieveAllUniversites();//hthy bch tnedi l service
        assertEquals(universites, result);//tverifi si methode eli f service raj3t les etudiants
        System.out.println("all universite sont recuperes");
    }

    @Test
    public void testAddUniversiteWithDepartements() {
        // Créez une instance d'Universite
        Universite universite = new Universite();
        universite.setIdUniversite(4);
        universite.setNomUniv("esprit");

        // Créez une liste de départements liée à l'université
        List<Departement> departements = new ArrayList<>();
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Informatique");
        departements.add(departement1);

        Departement departement2 = new Departement();
        departement2.setIdDepartement(2);
        departement2.setNomDepart("Mathématiques");
        departements.add(departement2);

        universite.setDepartements(departements);

        when(universiteRepository.save(universite)).thenReturn(universite);
System.out.println("ggggll");
        Universite addedUniversite = universiteService.addUniversite(universite);

        // Vérifiez que la méthode save a été appelée avec l'objet attendu
        verify(universiteRepository).save(universite);
        System.out.println("gggg");
        // Ajoutez des assertions pour vérifier que l'objet résultant est correct
        assertEquals(universite, addedUniversite);
        assertEquals(2, addedUniversite.getDepartements().size());
        System.out.println("gggg");// Vérifiez le nombre de départements associés
        // Ajoutez d'autres assertions pour vérifier les attributs de l'objet résultant si nécessaire
    }

    @Test
    void testUpdateEtudiant() {


        Universite existinguniversite = new Universite();
        existinguniversite.setIdUniversite(4);
        existinguniversite.setNomUniv("esprit");
        List<Departement> departements = new ArrayList<>();
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Informatique");
        departements.add(departement1);



        existinguniversite.setDepartements(departements);


        when(universiteRepository.save(existinguniversite)).thenReturn(existinguniversite);
        Universite updateuniversite = universiteService.updateUniversite(existinguniversite);
        assertEquals(existinguniversite, updateuniversite);
        verify(universiteRepository, times(1)).save(existinguniversite);
        System.out.println("universite updated successfully");

    }

    @Test
    public void testRetrieveUniversite() {
        // Créez un ID d'université fictif que vous souhaitez récupérer
        Integer universiteId = 1;

        // Créez une instance d'université fictive que vous vous attendez à obtenir en réponse
        Universite expectedUniversite = new Universite();
        expectedUniversite.setIdUniversite(universiteId);
        expectedUniversite.setNomUniv("esprit");

        // Configurez le mock pour la méthode de recherche d'université par ID
        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(expectedUniversite));

        // Appelez la méthode de récupération d'université
        Universite retrievedUniversite = universiteService.retrieveUniversite(universiteId);

        // Vérifiez que la méthode findById a été appelée avec l'ID attendu
        verify(universiteRepository).findById(universiteId);

        // Ajoutez des assertions pour vérifier que l'objet résultant est correct
        assertEquals(expectedUniversite, retrievedUniversite);
        System.out.println(retrievedUniversite.getIdUniversite());
        // Ajoutez d'autres assertions pour vérifier les attributs de l'objet résultant si nécessaire
    }

    @Test
    @Transactional
    public void testAssignUniversiteToDepartement() {
        // Given
        Integer universiteId = 1;
        Integer departementId = 2;

        Universite universite = new Universite(); // Create a mock or use a real instance
        Departement departement = new Departement(); // Create a mock or use a real instance

        Mockito.when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(universite));
        Mockito.when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        // When
        universiteService.assignUniversiteToDepartement(universiteId, departementId);

        // Then
        Mockito.verify(universiteRepository, Mockito.times(1)).findById(universiteId);
        Mockito.verify(departementRepository, Mockito.times(1)).findById(departementId);
        Mockito.verify(universiteRepository, Mockito.times(1)).save(universite);
    }
}

