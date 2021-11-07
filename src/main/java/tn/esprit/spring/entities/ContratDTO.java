package tn.esprit.spring.entities;

import java.util.Date;



public class ContratDTO {

	
	
	private int referenceD;
	
	
	private Date dateDebutD;
	
	private String typeContratD;
	
	
	

	private Employe employeD;

	private float salaireD;

	

	public Date getDateDebut() {
		return dateDebutD;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebutD = dateDebut;
	}

	public int getReference() {
		return referenceD;
	}

	public void setReference(int reference) {
		this.referenceD = reference;
	}

	public String getTypeContrat() {
		return typeContratD;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContratD = typeContrat;
	}

	public float getSalaire() {
		return salaireD;
	}

	public void setSalaire(float salaire) {
		this.salaireD = salaire;
	}

	public Employe getEmploye() {
		return employeD;
	}

	public void setEmploye(Employe employe) {
		this.employeD = employe;
	}
	
	
}

