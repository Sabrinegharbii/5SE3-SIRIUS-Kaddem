//package tn.esprit.spring.khaddem.services;

//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import tn.esprit.spring.khaddem.entities.Universite;
//import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

//import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class UniversiteServiceImplDynamiqTest {

  //  @Autowired
   // private UniversiteServiceImpl universiteservice;

    //@Autowired
    //private UniversiteRepository universitrepo;

    //@Test
    //void addUniversite() {
      //  Universite univ = new Universite();
        //univ.setIdUniversite(1);
        //Universite univ2 = universiteservice.addUniversite(univ);
        //assertNotNull(univ2);
        //assertEquals(1, univ2.getIdUniversite());
        //System.out.println(" Add universite: OK");
    //}

    //@Test
    //void retrieveaUniversite() {
      //  List<Universite> universites = universiteservice.retrieveAllUniversites();
        //assertFalse(universites.isEmpty());
        //System.out.println("Liste des universites :");
        //for (Universite c : universites) {
          //  System.err.println("ID : " + c.getIdUniversite() + ", nom : " + c.getNomUniv());
        //}
    //}

    //@Test
    //void retrieveUniversite() {
      //  Universite universiteretrieve = new Universite();
       // universiteretrieve.setNomUniv("esprit");
        //Universite adduniversite = universiteservice.addUniversite(universiteretrieve);

//        System.err.println("ID : " + adduniversite.getNomUniv());

  //      Universite retrieveuniversite = universiteservice.retrieveUniversite(adduniversite.getIdUniversite());

    //    assertEquals(adduniversite.getIdUniversite(), retrieveuniversite.getIdUniversite());
      //  assertEquals(adduniversite.getNomUniv(), retrieveuniversite.getNomUniv());

        //System.out.println("Retrieve universite: OK");
    //}

    //@Test
    //void updateUniversite() {
      //  Universite universitetrue = new Universite();
        //universitetrue.setIdUniversite(3);
        //universitetrue.setNomUniv("tekup");
        //Universite saveduniv = universitrepo.save(universitetrue);
        //String updatednom = "tekup";
        //saveduniv.setNomUniv(updatednom);
        //Universite updateduniversite = universiteservice.updateUniversite(saveduniv);
        //Universite retrieveduniv = universitrepo.findById(updateduniversite.getIdUniversite()).orElse(null);
        //assertNotNull(retrieveduniv);
        //assertEquals(updateduniversite.getNomUniv(), retrieveduniv.getNomUniv());
        //System.err.println("Update nuni : OK !");
    //}
//}
