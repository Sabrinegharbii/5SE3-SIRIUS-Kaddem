package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllEquipes() {
        // Mocking the repository behavior
        List<Equipe> mockEquipes = new ArrayList<>();
        when(equipeRepository.findAll()).thenReturn(mockEquipes);

        // Calling the service method
        List<Equipe> result = equipeService.retrieveAllEquipes();

        // Verifying the result
        assertNotNull(result);
        assertEquals(mockEquipes, result);

        // Verifying that the repository method was called
        verify(equipeRepository, times(1)).findAll();
        System.err.println("All students are retrieved successfully");
    }

    @Test
    void addEquipe() {
        // Creating a mock Equipe
        Equipe mockEquipe = new Equipe();

        // Calling the service method
        Equipe result = equipeService.addEquipe(mockEquipe);

        // Verifying the result
        assertNotNull(result);
        assertEquals(mockEquipe, result);

        // Verifying that the repository method was called
        verify(equipeRepository, times(1)).save(mockEquipe);
        System.err.println("Add successfully");
    }

    @Test
    void updateEquipe() {
        // Creating a mock Equipe
        Equipe mockEquipe = new Equipe();

        // Calling the service method
        Equipe result = equipeService.updateEquipe(mockEquipe);

        // Verifying the result
        assertNotNull(result);
        assertEquals(mockEquipe, result);

        // Verifying that the repository method was called
        verify(equipeRepository, times(1)).save(mockEquipe);
        System.err.println("Update successfully");
    }

    @Test
    void retrieveEquipe() {
        // Creating a mock Equipe
        Equipe mockEquipe = new Equipe();
        mockEquipe.setIdEquipe(1);

        // Mocking the repository behavior
        when(equipeRepository.findById(1)).thenReturn(Optional.of(mockEquipe));

        // Calling the service method
        Equipe result = equipeService.retrieveEquipe(1);

        // Verifying the result
        assertNotNull(result);
        assertEquals(mockEquipe, result);

        // Verifying that the repository method was called
        verify(equipeRepository, times(1)).findById(1);
        System.err.println("Retrived successfully");
    }

    // Add more tests for other methods as needed

}
