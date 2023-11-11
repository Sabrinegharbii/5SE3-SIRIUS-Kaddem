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
        // Arrange
        List<Departement> mockedDepartements = Arrays.asList(new Departement(), new Departement());
        Mockito.when(departementRepository.findAll()).thenReturn(mockedDepartements);

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        Mockito.verify(departementRepository, Mockito.times(1)).findAll();
        assertEquals(mockedDepartements.size(), result.size());
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


@Test
void testRetrieveDepartementsByUniversite() {
    // Arrange
    Integer idUniversite = 1; // Replace with the actual id
    Universite universite = new Universite();
    universite.setIdUniversite(idUniversite);

    List<Departement> departements = Arrays.asList(new Departement(), new Departement());

    // Mock the behavior of findById
    Mockito.when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

    // Create a spy on the real object to avoid issues with final or private methods
    Universite universiteSpy = Mockito.spy(universite);
    Mockito.doReturn(departements).when(universiteSpy).getDepartements();

    // Replace the real object with the spy
    Mockito.when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universiteSpy));

    // Act
    List<Departement> result = departementService.retrieveDepartementsByUniversite(idUniversite);

    // Assert
    Mockito.verify(universiteRepository, Mockito.times(1)).findById(idUniversite);
    assertEquals(departements, result);

    // Additional check for the number of departements
    assertEquals(departements.size(), result.size(), "Number of retrieved departements does not match");

    System.err.println("Departements retrieved by university successfully");
}

}