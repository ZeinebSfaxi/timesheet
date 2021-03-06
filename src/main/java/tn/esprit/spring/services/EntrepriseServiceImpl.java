package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;


	private Logger log = LoggerFactory.getLogger(EntrepriseServiceImpl.class);
	private static final String STRINGLOG = "Log this: {}"; 

	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		log.info(entreprise.getName());
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		log.info(dep.getName());
		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {

		try {
			Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);

			if (depManagedEntity.isPresent() && entrepriseManagedEntity.isPresent()) {
				Departement departement = depManagedEntity.get();
				Entreprise entreprise = entrepriseManagedEntity.get();
				departement.setEntreprise(entreprise);
				deptRepoistory.save(departement);
			
				log.info(STRINGLOG, entrepriseManagedEntity);
				 log.info(STRINGLOG, depManagedEntity);
			}
		}

		catch (Exception e) {
			log.error(e.toString());
		}

	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {

		try {
			Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);

			if (entrepriseManagedEntity.isPresent()) {

				List<String> depNames = new ArrayList<>();
				Entreprise entreprise = entrepriseManagedEntity.get();
				for (Departement dep : entreprise.getDepartements()) {
					depNames.add(dep.getName());
				}

			    log.info(STRINGLOG, entrepriseManagedEntity);
			
				return depNames;
				
			}

			else
				return Collections.emptyList();
		}

		catch (Exception e) {
			log.error(e.toString());
			return Collections.emptyList();
		}

	}

	@Transactional
	public void deleteEntrepriseById(int id) {
		Optional<Entreprise> entreprise = entrepriseRepoistory.findById(id);
		try {
			if (entreprise.isPresent()) {
				  log.info(STRINGLOG, entreprise);
				entrepriseRepoistory.delete(entreprise.get());
			} else {
				log.warn("N'existe pas");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

	}

	@Transactional
	public void deleteDepartementById(int id) {
		Optional<Departement> departement = deptRepoistory.findById(id);
		try {

			if (departement.isPresent()) {
				log.info(STRINGLOG, departement);
				deptRepoistory.delete(departement.get());
			} else {
				log.warn("N'existe pas");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	public Entreprise getEntrepriseById(int id) {
		Optional<Entreprise> entreprise = entrepriseRepoistory.findById(id);
		try {
			Entreprise entreprisee = null;
			if (entreprise.isPresent()) {

				entreprisee = entreprise.get();
				log.info(STRINGLOG, entreprisee);
				return entreprisee;
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return null;

	}

}
