package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.services.IEmployeService;


@Scope(value = "session")
@Controller(value = "employeController")
@ELBeanName(value = "employeController")
@Join(path = "/", to = "/login.jsf")
public class ControllerEmployeImpl  {

	@Autowired
	IEmployeService employeService;

	private String loginC; 
	private String passwordC; 
	private Boolean loggedInC;

	private Employe authenticatedUserC = null; 
	private String prenomC; 
	private String nomC; 
	private String emailC;
	private boolean actifC;
	private Role roleC;  
	public Role[] getRoles() { return Role.values(); }

	private List<Employe> employesC; 

	private Integer employeIdToBeUpdatedC; // getter et setter

	private static final String LOGIN_URL ="/login.xhtml?faces-redirect=true";
	
	public String doLogin() {

		String navigateTo = "null";
		authenticatedUserC=employeService.authenticate(loginC, passwordC);
		if (authenticatedUserC != null && authenticatedUserC.getRole() == Role.ADMINISTRATEUR) {
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			loggedInC = true;
		}		

		else
		{
			
			FacesMessage facesMessage =
					new FacesMessage("Login Failed: Please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;	
	}

	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	
	return LOGIN_URL;
	}


	public String addEmploye() {

		if (authenticatedUserC==null || !loggedInC) return LOGIN_URL;

		employeService.addOrUpdateEmploye(new Employe(nomC, prenomC, emailC, passwordC, actifC, roleC)); 
		return "null"; 
	}  

	public String removeEmploye(int employeId) {
		String navigateTo = "null";
		if (authenticatedUserC==null || !loggedInC) return LOGIN_URL;

		employeService.deleteEmployeById(employeId);
		return navigateTo; 
	} 

	public String displayEmploye(Employe empl) 
	{
		String navigateTo = "null";
		if (authenticatedUserC==null || !loggedInC) return LOGIN_URL;


		this.setPrenom(empl.getPrenom());
		this.setNom(empl.getNom());
		this.setActif(empl.isActif()); 
		this.setEmail(empl.getEmail());
		this.setRole(empl.getRole());
		this.setPassword(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId());

		return navigateTo; 

	} 

	public String updateEmploye() 
	{ 
		String navigateTo = "null";
		
		if (authenticatedUserC==null || !loggedInC) return LOGIN_URL;

		employeService.addOrUpdateEmploye(new Employe(employeIdToBeUpdatedC, nomC, prenomC, emailC, passwordC, actifC, roleC)); 

		return navigateTo; 

	} 


	// getters and setters 

	public IEmployeService getEmployeService() {
		return employeService;
	}

	public void setEmployeService(IEmployeService employeService) {
		this.employeService = employeService;
	}

	public String getLogin() {
		return loginC;
	}

	public void setLogin(String login) {
		this.loginC = login;
	}

	public String getPassword() {
		return passwordC;
	}

	public void setPassword(String password) {
		this.passwordC = password;
	}


	public List<Employe> getAllEmployes() {
		return employeService.getAllEmployes();
	}

	public Boolean getLoggedIn() {
		return loggedInC;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedInC = loggedIn;
	}

	public int ajouterEmploye(Employe employe)
	{
		employeService.addOrUpdateEmploye(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		employeService.mettreAjourEmailByEmployeId(email, employeId);

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		employeService.affecterEmployeADepartement(employeId, depId);

	}



	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		employeService.desaffecterEmployeDuDepartement(employeId, depId);
	}

	public int ajouterContrat(Contrat contrat) {
		employeService.ajouterContrat(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId)
	{
		employeService.affecterContratAEmploye(contratId, employeId);
	}


	public String getEmployePrenomById(int employeId) {
		return employeService.getEmployePrenomById(employeId);
	}

	public void deleteEmployeById(int employeId) {
		employeService.deleteEmployeById(employeId);

	}
	public void deleteContratById(int contratId) {
		employeService.deleteContratById(contratId);
	}

	public int getNombreEmployeJPQL() {

		return employeService.getNombreEmployeJPQL();
	}

	public List<String> getAllEmployeNamesJPQL() {

		return employeService.getAllEmployeNamesJPQL();
	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeService.getAllEmployeByEntreprise(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {	
		employeService.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		employeService.deleteAllContratJPQL();

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeService.getSalaireByEmployeIdJPQL(employeId);
	}


	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeService.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return employeService.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public String getPrenom() {
		return prenomC;
	}

	public void setPrenom(String prenom) {
		this.prenomC = prenom;
	}

	public String getNom() {
		return nomC;
	}

	public void setNom(String nom) {
		this.nomC = nom;
	}

	public String getEmail() {
		return emailC;
	}

	public void setEmail(String email) {
		this.emailC = email;
	}




	public boolean isActif() {
		return actifC;
	}

	public void setActif(boolean actif) {
		this.actifC = actif;
	}

	public Role getRole() {
		return roleC;
	}

	public void setRole(Role role) {
		this.roleC = role;
	}

	public List<Employe> getEmployes() {
		employesC = employeService.getAllEmployes(); 
		return employesC;
	}

	public void setEmployes(List<Employe> employes) {
		this.employesC = employes;
	}

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdatedC;
	}

	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdatedC = employeIdToBeUpdated;
	}

	public Employe getAuthenticatedUser() {
		return authenticatedUserC;
	}

	public void setAuthenticatedUser(Employe authenticatedUser) {
		this.authenticatedUserC = authenticatedUser;
	}

}
