package tn.esprit.spring;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IEmployeService;

@SpringBootTest
class TimesheetApplicationTests {
	
	@Autowired
	private IEmployeService iEmployeService;
	
	@Autowired
    private DepartementRepository depRep;

	
	@Test
	void contextLoads() {
		
		//employee test
		Employe emp = new Employe("Zargouni","Amine","mohamedamine.zargouni@esprit.tn","123456",true,Role.INGENIEUR);
		iEmployeService.addOrUpdateEmploye(emp);
		
		//iEmployeService.authenticate("mohamedamine.zargouni@esprit.tn", "123");
		Employe loggedIn= iEmployeService.authenticate("mohamedamine.zargouni@esprit.tn", "123456");
		
		//iEmployeService.deleteEmployeById(6);
		//iEmployeService.mettreAjourEmailByEmployeId("testemail@email.com", 7);
		
		
		iEmployeService.getEmployePrenomById(emp.getId());
		iEmployeService.getNombreEmployeJPQL();
		iEmployeService.getAllEmployeNamesJPQL();
		
		
		//departement test
        Departement department = new Departement();
        depRep.save(department);
        iEmployeService.affecterEmployeADepartement(emp.getId(),department.getId());
		//iEmployeService.desaffecterEmployeDuDepartement(loggedIn.getId(),2);
		
		
		//contrat test
		Contrat contrat = new Contrat();
		iEmployeService.ajouterContrat(contrat);
		iEmployeService.affecterContratAEmploye(contrat.getReference(),emp.getId());
		//iEmployeService.deleteContratById(2);
		
		
	
		
		
	}

}
