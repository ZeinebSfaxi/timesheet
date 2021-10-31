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
		//Employe emp = new Employe("Zargouni","Amine","mohamedamine.zargouni@esprit.tn","123456",true,Role.INGENIEUR);
		//iEmployeService.addOrUpdateEmploye(emp);
		
		//iEmployeService.authenticate("mohamedamine.zargouni@esprit.tn", "123");
		//iEmployeService.authenticate("mohamedamine.zargouni@esprit.tn", "123456");
		
		//iEmployeService.deleteEmployeById(6);
		//iEmployeService.mettreAjourEmailByEmployeId("testemail@email.com", 7);
		
		
		//iEmployeService.getEmployePrenomById(7);
		//iEmployeService.getNombreEmployeJPQL();
		//iEmployeService.getAllEmployeNamesJPQL();
		
		
		//departement test
        //Departement department = new Departement();
        //depRep.save(department);
        //iEmployeService.affecterEmployeADepartement(7,1);
		//iEmployeService.desaffecterEmployeDuDepartement(7,1);
		
		
		//contrat test
		//Contrat contrat = new Contrat();
		//iEmployeService.ajouterContrat(contrat);
		//iEmployeService.affecterContratAEmploye(1,7);
		//iEmployeService.deleteContratById(2);
		
		
		/*public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId);
		public void deleteAllContratJPQL();
		public float getSalaireByEmployeIdJPQL(int employeId);
		public Double getSalaireMoyenByDepartementId(int departementId);
		public List<Employe> getAllEmployes();
		public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, 
		Date dateDebut, Date dateFin);*/

		
		
	}

}
