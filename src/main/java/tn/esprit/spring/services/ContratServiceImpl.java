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


	public List<Contrat> getAllContrats() {
		return (List<Contrat>) contratRepository.findAll();
	}


	@Override
	public Contrat mapToEntity(ContratDTO contratDTO) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(contratDTO, Contrat.class);
	}


	@Override
	public ContratDTO mapToDTO(Contrat contrat) {
		ModelMapper modelMapper = new ModelMapper();

		return modelMapper.map(contrat, ContratDTO.class);
	}

}
