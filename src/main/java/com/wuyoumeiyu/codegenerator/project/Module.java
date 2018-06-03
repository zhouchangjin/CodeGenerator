package com.wuyoumeiyu.codegenerator.project;

import java.util.HashMap;

public class Module {
	
	Project project;
	String moduleSpecFileName;
	
	HashMap<String,String> paramsMap=new HashMap<String,String>();
	
	
	public void set(String paramName,String paramValue) {
		paramsMap.put(paramName, paramValue);
	}
	
	public String get(String paramName) {
		return paramsMap.get(paramName);
	}
	
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


	
}
