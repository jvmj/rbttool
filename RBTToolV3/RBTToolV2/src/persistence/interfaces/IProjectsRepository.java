package persistence.interfaces;

import java.util.Vector;

import essentials.Project;

public interface IProjectsRepository {
	
	public void addProject(Project project);

	public void removeProject(Project project);

	public void updateProject(Project project);

	public Project searchProject(String name);
	
	public Vector<Object> getProjects();

	public void importProject();

}
