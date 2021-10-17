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
		

		Optional <Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		
		if(entrepriseManagedEntity.isPresent()) {
		
		List<String> depNames = new ArrayList<>();
		Entreprise entreprise = entrepriseManagedEntity.get();
		for(Departement dep : entreprise.getDepartements()){
			depNames.add(dep.getName());
		}
		 return depNames; 
			
		}
		else return Collections.emptyList();
	
	}
	

	@Transactional
	public void deleteEntrepriseById(int id) {
		Optional <Entreprise> entreprise = entrepriseRepoistory.findById(id);
		try {
		if (entreprise.isPresent()) {
			
			entrepriseRepoistory.delete(entreprise.get());	
		}
		else {
			System.out.println("N'existe pas");
		}
		}
		 catch(Exception e){
			  System.out.println("Exception");
		    } 

	
	}

	@Transactional
	public void deleteDepartementById(int id) {
		Optional <Departement> departement = deptRepoistory.findById(id);
		try {
		
         if (departement.isPresent()) {
         deptRepoistory.delete(departement.get());	
		}
		else {
			System.out.println("N'existe pas");
		}
		}
		 catch(Exception e){
			  System.out.println("Exception");
		    } 
	}


	public Entreprise getEntrepriseById(int id) {
		Optional <Entreprise> entreprise = entrepriseRepoistory.findById(id);
		try {
			Entreprise entreprisee = null;
		 if (entreprise.isPresent()) {
			
			 entreprisee = entreprise.get();	
				return entreprisee;	
		 }
		}
		 catch(Exception e){
			  System.out.println("Exception");
		    } 

		return null;

	
	}

}
