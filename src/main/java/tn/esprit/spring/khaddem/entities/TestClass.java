package tn.esprit.spring.khaddem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTest;
}
