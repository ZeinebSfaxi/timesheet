package tn.esprit.spring.entities;

import java.util.List;



public class EmployeDTO {

	
	private int id;

	private String prenom;

	private String nom;


	private String email;

	private String password;

	private boolean actif;

	private Role role;


	private List<Departement> departements;


	private Contrat contrat;

	private List<Timesheet> timesheets;




	

}
