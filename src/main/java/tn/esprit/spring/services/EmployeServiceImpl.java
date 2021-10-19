package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	private static final Logger log = LogManager.getLogger(EmployeServiceImpl.class);

	@Override
	public Employe authenticate(String login, String password) {
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {

		try {
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

			if (employeManagedEntity.isPresent()) {
				Employe employe = employeManagedEntity.get();
				employe.setEmail(email);
				employeRepository.save(employe);
			} else {
				log.warn("Cet employe n'existe pas");
			}

		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	@Transactional
	public void affecterEmployeADepartement(int employeId, int depId) {

		try {

			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

			if (depManagedEntity.isPresent() && employeManagedEntity.isPresent()) {

				Departement department = depManagedEntity.get();
				Employe employe = employeManagedEntity.get();

				if (department.getEmployes() == null) {

					List<Employe> employes = new ArrayList<>();
					employes.add(employe);
					department.setEmployes(employes);
				} else {

					department.getEmployes().add(employe);
				}

				deptRepoistory.save(department);

				log.info(depManagedEntity);
				log.info(employeManagedEntity);

			}

			else {
				log.warn("Cet employe ou ce departement n'existe pas");
			}

		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId) {

		try {
			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

			if (depManagedEntity.isPresent() && employeManagedEntity.isPresent()) {

				Departement dep = depManagedEntity.get();
				Employe emp = employeManagedEntity.get();

				int employeNb = dep.getEmployes().size();
				int index = 0;
				int i = 0;

				for (index = 0; index < employeNb; index++) {
					if (dep.getEmployes().get(index).getId() == employeId) {
						dep.getEmployes().remove(index);

						break;
					}
				}

				if (index >= employeNb) {

					log.info("Cet employe n'existe pas dans ce departement");

				} else {
					int depNb = emp.getDepartements().size();

					for (i = 0; i < depNb; i++) {
						if (emp.getDepartements().get(i).getId() == depId) {
							emp.getDepartements().remove(i);
							break;
						}
					}

					deptRepoistory.save(dep);
					employeRepository.save(emp);
				}
			}

			else {
				log.warn("Ce departement ou cet employé n'existe pas");
			}

		}

		catch (Exception e) {
			log.error(e.toString());
		}

	}


	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {

		try {
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
			Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);

			if (contratManagedEntity.isPresent() && employeManagedEntity.isPresent()) {

				Contrat contrat = contratManagedEntity.get();
				Employe employe = employeManagedEntity.get();

				contrat.setEmploye(employe);
				contratRepoistory.save(contrat);
			} else {
				log.warn("Ce contract ou cet employé n'existe pas");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

	}

	public String getEmployePrenomById(int employeId) {
		try {
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
			if (employeManagedEntity.isPresent()) {
				Employe employe = employeManagedEntity.get();
				return employe.getPrenom();
			} else {
				log.warn("Cet employé n'existe pas");

			}

		} catch (Exception e) {
			log.error(e.toString());

		}
		return null;

	}

	public void deleteEmployeById(int employeId) {

		try {
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
			if (employeManagedEntity.isPresent()) {

				Employe employe = employeManagedEntity.get();

				for (Departement dep : employe.getDepartements()) {
					dep.getEmployes().remove(employe);
					deptRepoistory.save(dep);
				}

				employeRepository.delete(employe);
			}

			else {
				log.warn("Cet employé n'existe pas");

			}
		} catch (Exception e) {
			log.error(e.toString());

		}

	}

	public void deleteContratById(int contratId) {

		try {
			Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);

			if (contratManagedEntity.isPresent()) {
				Contrat contrat = contratManagedEntity.get();
				contratRepoistory.delete(contrat);
			}
			
			else {
				log.warn("Ce contrat n'existe pas");

			}
		} catch (Exception e) {
			log.error(e.toString());

		}

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
