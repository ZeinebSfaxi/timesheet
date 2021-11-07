package tn.esprit.spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IEmployeService;

@SpringBootTest
class TimesheetApplicationTests {
	
	@Autowired
	private IEmployeService iEmployeService;
	
	@Autowired
    private DepartementRepository depRep;


	@Autowired
	IEntrepriseService ientrepriseservice;

	@Autowired 
	ITimesheetService itimesheetService;
	
	@Test
	void contextLoads() {
		
		//employee test
		Employe emp = new Employe("Zargouni","Amine","mohamedamine.zargouni@esprit.tn","123456",true,Role.INGENIEUR);
		iEmployeService.addOrUpdateEmploye(emp);
		
		//iEmployeService.authenticate("mohamedamine.zargouni@esprit.tn", "123");
		Employe loggedIn= iEmployeService.authenticate("mohamedamine.zargouni@esprit.tn", "123456");
		
		//iEmployeService.deleteEmployeById(6);
		//iEmployeService.mettreAjourEmailByEmployeId("testemail@email.com", 7);
		
		
		iEmployeService.getEmployePrenomById(loggedIn.getId());
		iEmployeService.getNombreEmployeJPQL();
		iEmployeService.getAllEmployeNamesJPQL();
		
		
		//departement test
        Departement department = new Departement();
        depRep.save(department);
        iEmployeService.affecterEmployeADepartement(loggedIn.getId(),department.getId());
		//iEmployeService.desaffecterEmployeDuDepartement(11,3);
		
		
		//contrat test
		Contrat contrat = new Contrat();
		iEmployeService.ajouterContrat(contrat);
		iEmployeService.affecterContratAEmploye(contrat.getReference(),loggedIn.getId());
		//iEmployeService.deleteContratById(2);
		
		






		Entreprise e = new Entreprise("ENTREPRISENAME", "MITHEL");
		ientrepriseservice.ajouterEntreprise(e);
        Departement department1 = new Departement();
		
		department.setName("IT");
		ientrepriseservice.ajouterDepartement(department1);
		
		Departement department2 = new Departement();
		//d1.setEntreprise(e);
		department1.setName("TELECOM");
		ientrepriseservice.ajouterDepartement(department2);
		
		ientrepriseservice.affecterDepartementAEntreprise(1, 1);
		ientrepriseservice.affecterDepartementAEntreprise(2, 1);
	//-------------------------------TIMESHEET-------------------------------------------
		
		
		//timesheet test
		
		Date firstDate = new Date();
	
		Mission mission = new Mission("mission impossible", "c est une mission tres difficle");
		itimesheetService.ajouterMission(mission);

				itimesheetService.ajouterTimesheet(1, 1, firstDate,firstDate);
				
				itimesheetService.getAllEmployeByMission(1);
				itimesheetService.affecterMissionADepartement(1, 1);
			
				itimesheetService.findAllMissionByEmployeJPQL(1);
				
				
			
				
			
		
		
	}
}