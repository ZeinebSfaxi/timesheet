package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	private Logger log = LoggerFactory.getLogger(TimesheetServiceImpl.class);
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
        Optional<Mission> mission = missionRepository.findById(missionId);
        Optional<Departement> dep = deptRepoistory.findById(depId);
        try {
            if ((mission.isPresent()) && (dep.isPresent())) {
    
                log.info("Log this: mission {}", mission);

                missionRepository.delete(mission.get());
                log.info("Log this: {}", mission);
                log.info("Log this department: {}", dep);
                deptRepoistory.delete(dep.get());
            	mission.get().setDepartement(dep.get());
            	missionRepository.save(mission.get());
            } else {
                log.warn("N'existe pas");
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
    
 
	
	
		
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
	
		log.info("In valider Timesheet");
		
		
		Optional<Employe> validateur = employeRepository.findById(validateurId);
		Optional<Mission> mission = missionRepository.findById(missionId);
		

        try {
            if ((validateur.isPresent()) && (mission.isPresent())) {
    
                log.info("Log this: mission {}", validateur);

                log.info("Log this: {}", validateur);
                log.info("Log this mission: {}", mission);
               
              //verifier s'il est un chef de departement (interet des enum)
        		if(!validateur.get().getRole().equals(Role.CHEF_DEPARTEMENT)){
        			log.warn("l'employe doit etre chef de departement pour valider une feuille de temps !");
        			return;
        		}
        		//verifier s'il est le chef de departement de la mission en question
        		boolean chefDeLaMission = false;
        		for(Departement dep : validateur.get().getDepartements()){
        			if(dep.getId() == mission.get().getDepartement().getId()){
        				chefDeLaMission = true;
        				break;
        			}
        		}
        		if(!chefDeLaMission){
        		
        			log.error("l'employe doit etre chef de departement de la mission en question!");

        			return;
        		}
            } else {
                log.warn("N'existe pas");
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
    
		
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		//Comment Lire une date de la base de donn??es
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String datedebut = dateFormat.format(timesheet.getTimesheetPK().getDateDebut());
		log.info("dateDebut : {} " ,datedebut);
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
