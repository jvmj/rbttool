package managers;

import java.util.Vector;

import persistence.ProjectsRepositoryXML;
import persistence.interfaces.IProjectsRepository;
import essentials.Project;
import exceptions.ProjectDoesntExistException;
import exceptions.RBTToolValidationException;


public class ProjectManager {
	
	private IProjectsRepository projectsRepository;
	
	public ProjectManager() {
		super();
		this.projectsRepository = new ProjectsRepositoryXML();
	}
	
	public void addProject(Project project) {
		try {
			if (exists(project)) {
				throw new RBTToolValidationException(
						"Já existe um projeto cadastrado com o nome informado");
			} else {
				projectsRepository.addProject(project);
			}
		} catch (RBTToolValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeProject(Project project) {
		if(!exists(project)){
			try {
				throw new ProjectDoesntExistException("Doesn't exist any project with these informations");
			} catch (ProjectDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			projectsRepository.removeProject(project);
		}

	}

	public void updateProject(Project project) {
		if(!exists(project)){
			try {
				throw new ProjectDoesntExistException("Doesn't exist any project with these informations");
			} catch (ProjectDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			projectsRepository.updateProject(project);
		}

	}

	public void importRequirement() {

	}

	public Project searchProject(String name) {
		Project projectReturned = projectsRepository.searchProject(name);
		if (projectReturned == null) {
			try {
				throw new ProjectDoesntExistException("Doens't exist any project with this name!");
			} catch (ProjectDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return projectReturned;
		}
		return null;		
	}
	
	public Vector<Object> getProjects(){
		return projectsRepository.getProjects();
	}
	
	/**
	 * Verifica se o projeto ja foi cadastrado no repositorio.
	 * @param project
	 * @return
	 */
	public boolean exists(Project project) {
		Project projetoEncontrado = projectsRepository.searchProject(project.getName());
		if (projetoEncontrado != null && projetoEncontrado.getName().equals(project.getName())) {
			return true;
		} else {
			return false;
		}
	}

}
