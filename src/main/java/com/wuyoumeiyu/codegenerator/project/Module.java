package com.wuyoumeiyu.codegenerator.project;


public class Module {
	
	Project project;
	String moduleName;
	String moduleSpecFileName;
	
	public String getModuleSpecFileName() {
		return moduleSpecFileName;
	}
	public void setModuleSpecFileName(String moduleSpecFileName) {
		this.moduleSpecFileName = moduleSpecFileName;
	}
	public Module(){
		
	}
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	
}
