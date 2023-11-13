package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{

    private static final Logger logger = LoggerFactory.getLogger(EtudiantServiceImpl.class);

    EtudiantRepository etudiantRepository;

    DepartementRepository departementRepository;

    ContratRepository contratRepository;

    EquipeRepository equipeRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);

        if (optionalEtudiant.isPresent()) {
            return optionalEtudiant.get();
        } else {
            return null;
        }
    }


    @Override
    public void removeEtudiant(Integer idEtudiant) {
     etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
        Optional<Departement> optionalDepartement = departementRepository.findById(departementId);

        if (optionalEtudiant.isPresent() && optionalDepartement.isPresent()) {
            Etudiant e = optionalEtudiant.get();
            Departement d = optionalDepartement.get();
            e.setDepartement(d);
            etudiantRepository.save(e);
        } else {
            if (!optionalEtudiant.isPresent()) {
                logger.error("Etudiant with ID {} not found.", etudiantId);
            }
            if (!optionalDepartement.isPresent()) {
                logger.error("Departement with ID {} not found.", departementId);
            }
        }
    }


    @Override
    public List<Etudiant> findByDepartementIdDepartement(Integer idDepartement) {
        return etudiantRepository.findByDepartementIdDepartement(idDepartement);
    }

    @Override
    public List<Etudiant> findByEquipesNiveau(Niveau niveau) {
        return etudiantRepository.findByEquipesNiveau(niveau);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(Specialite specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(String specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        Contrat contrat = contratRepository.findById(idContrat).get();
        Equipe equipe=equipeRepository.findById(idEquipe).get();
        Etudiant etudiant= etudiantRepository.save(e);
        log.info("contrat: "+contrat.getSpecialite());
        log.info("equipe: "+equipe.getNomEquipe());
        log.info("etudiant: "+etudiant.getNomE()+" "+etudiant.getPrenomE()+" "+etudiant.getOp());
        List<Equipe> equipesMisesAjour = new ArrayList<>();
        contrat.setEtudiant(etudiant);
        if(etudiant.getEquipes()!=null) {
            equipesMisesAjour=etudiant.getEquipes();
        }
        equipesMisesAjour.add(equipe);
        log.info("taille apres ajout : "+equipesMisesAjour.size());
        etudiant.setEquipes(equipesMisesAjour);


        return e;
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepartement);

        if (optionalDepartement.isPresent()) {
            Departement departement = optionalDepartement.get();
            return departement.getEtudiants();
        } else {
            return Collections.emptyList();
        }
    }


}
