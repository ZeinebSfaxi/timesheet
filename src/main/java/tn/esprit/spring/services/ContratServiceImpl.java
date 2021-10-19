package tn.esprit.spring.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.ContratDTO;

import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	private ModelMapper mapper;


	public List<Contrat> getAllContrats() {
		return (List<Contrat>) contratRepository.findAll();
	}


	@Override
	public Contrat mapToEntity(ContratDTO contratDTO) {
		Contrat contrat = mapper.map(contratDTO, Contrat.class);
		return contrat;
	}


	@Override
	public ContratDTO mapToDTO(Contrat contrat) {
		ContratDTO contratDTO = mapper.map(contrat, ContratDTO.class);
		return contratDTO;
	}

}
