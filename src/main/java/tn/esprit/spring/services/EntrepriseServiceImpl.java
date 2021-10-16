package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		
		try {
		Optional <Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		Optional <Departement> depManagedEntity = deptRepoistory.findById(depId);
		
		
		if(depManagedEntity.isPresent() && entrepriseManagedEntity.isPresent()) {
			Departement departement = depManagedEntity.get();
			Entreprise entreprise = entrepriseManagedEntity.get();
			departement.setEntreprise(entreprise);
			deptRepoistory.save(departement);
		}	
	}
	
	  catch(Exception e){
		  System.out.println("Exception");
	    } 
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
		
/* try { Optional <Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		
		if(entrepriseManagedEntity.isPresent()) {
		
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames; 
		}
		}
		catch(Exception e) {
			System.out.println("Exception");
		}*/
	}
	}

	@Transactional
	public void deleteEntrepriseById(int id) {
		Optional <Entreprise> entreprise = entrepriseRepoistory.findById(id);
		if (entreprise.isPresent()) {
			
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(id).get());	
		}
		else {
			System.out.println("N'existe pas");
		}

	
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();	
	}

}
