package tn.esprit.spring;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;




@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetApplicationTests {


	@Autowired
	IEntrepriseService ientrepriseservice;
	
	@Test
	public void contextLoads() {
		
		Entreprise e = new Entreprise("ENTREPRISENAME", "MITHEL");
		ientrepriseservice.ajouterEntreprise(e);
        Departement department = new Departement();
		
		department.setName("IT");
		ientrepriseservice.ajouterDepartement(department);
		
		Departement department1 = new Departement();
		//d1.setEntreprise(e);
		department1.setName("TELECOM");
		ientrepriseservice.ajouterDepartement(department1);
		
		ientrepriseservice.affecterDepartementAEntreprise(1, 1);
		ientrepriseservice.affecterDepartementAEntreprise(2, 1);
			
		
	}
}