package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.ContratDTO;



public interface IContratService {
	
	
	public List<Contrat> getAllContrats();


	public Contrat mapToEntity(ContratDTO contratDTO);
	public ContratDTO mapToDTO(Contrat contrat);
	
	

	
}
