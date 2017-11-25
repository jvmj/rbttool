package persistence;

import java.util.Iterator;
import java.util.Vector;

import persistence.interfaces.IProjectsRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.Project;
import essentials.Requirement;

public class ProjectsRepositoryXML implements IProjectsRepository {
	
	@Override
	public void addProject(Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		if (objetos == null) {
			objetos = new Vector<Object>();
		}
		project.setRequirements(new Vector<Requirement>());
		objetos.addElement(project);
		rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);		
		
	}

	@Override
	public void importProject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProject(Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		objetos.removeElement(project);
		rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);	
		
	}

	@Override
	public Project searchProject(String name) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		if (objetos != null && objetos.size() != 0) {
			for (Iterator iterator = objetos.iterator(); iterator.hasNext();) {
				Project project = (Project) iterator.next();
				if (project.getName().equals(name)) {
					return project;
				}
			}
		} 
		return null;
	}


	@Override
	public void updateProject(Project project) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		if (objetos != null && objetos.size() != 0) {
			for (int i = 0; i < objetos.size(); i++) {
				Project projectIt = (Project)objetos.elementAt(i);
				if (projectIt.getName().equals(project.getName())) {
					objetos.setElementAt(project, i);
					rbtToolXStream.writeXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME, objetos);		
				}
			}
		} 
		
	}

	@Override
	public Vector<Object> getProjects() {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.PROJECTSPATH, FilesUtil.PROJECTSFILENAME);
		return objetos;
	}

}
