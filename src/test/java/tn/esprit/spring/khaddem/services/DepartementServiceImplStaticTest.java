package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DepartementServiceImplStaticTest {
    @InjectMocks
    private DepartementServiceImpl  departementService;
    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    public DepartementServiceImplStaticTest() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testRetrieveAllDepartements() {
        // Mocking the behavior of the departementRepository
        Mockito.when(departementRepository.findAll()).thenReturn(Arrays.asList(new Departement(), new Departement()));

        // Calling the method to be tested
        List<Departement> result = departementService.retrieveAllDepartements();

        // Verifying that the method was called and the correct result was returned
        Mockito.verify(departementRepository, Mockito.times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testAddDepartement() {
        // Create a sample Departement
        Departement departement = new Departement();

        // Calling the method to be tested
        Departement result = departementService.addDepartement(departement);

        // Verifying that the method was called and the correct result was returned
        Mockito.verify(departementRepository, Mockito.times(1)).save(departement);
        assertEquals(departement, result);
    }
    @Test
    public void testUpdateDepartement() {
        // Create a sample Departement
        Departement departement = new Departement();

        // Calling the method to be tested
        Departement result = departementService.updateDepartement(departement);

        // Verifying that the method was called and the correct result was returned
        Mockito.verify(departementRepository, Mockito.times(1)).save(departement);
        assertEquals(departement, result);
    }


//    @Test
//    public void testRetrieveDepartement() {
//        // Mocking the behavior of the departementRepository
//        Mockito.when(departementRepository.findById(1)) .thenReturn(Optional.of(new Departement()));
//
//        // Calling the method to be tested
//        Departement result = departementService.retrieveDepartement(1);
//
//        // Verifying that the method was called and the correct result was returned
//        Mockito.verify(departementRepository, Mockito.times(1)).findById(1);
//        assertEquals(new Departement(), result);
//    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        // Mocking the behavior of the universiteRepository
        Integer idUniversite = 1; // Replace with the actual id
        Universite universite = new Universite();
        universite.setIdUniversite(idUniversite);

        List<Departement> departements = Arrays.asList(new Departement(), new Departement());

        Mockito.when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        Mockito.when(universite.getDepartements()).thenReturn(departements);

        // Calling the method to be tested
        List<Departement> result = departementService.retrieveDepartementsByUniversite(idUniversite);

        // Verifying that the method was called and the correct result was returned
        Mockito.verify(universiteRepository, Mockito.times(1)).findById(idUniversite);
        assertEquals(departements, result);

        System.err.println("Departements retrieved by university successfully");
    }

}