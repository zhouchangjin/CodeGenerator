package com.wuyoumeiyu.codegenerator.project;

import com.gamewolf.util.file.FileUtil;

public class DefaultProjectBuilder implements ProjectBuilder {

	public void buildProject(Project pj) {
		// TODO Auto-generated method stub
		String folder=pj.getProjectFolder();
		FileUtil.CreateFolder(folder);
	}

}
