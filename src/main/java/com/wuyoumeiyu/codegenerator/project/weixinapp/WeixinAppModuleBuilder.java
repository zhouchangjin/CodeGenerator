package com.wuyoumeiyu.codegenerator.project.weixinapp;

import com.wuyoumeiyu.codegenerator.project.DefaultModuleBuilder;
import com.wuyoumeiyu.codegenerator.project.Module;
import com.wuyoumeiyu.codegenerator.project.Project;

public class WeixinAppModuleBuilder extends DefaultModuleBuilder {



	public static void main(String args[]) {
		Module module=new Module();
		Project p=new WeixinAppProject();
		p.setProjectFolder("C:/work/Workspace/wxWork/myW");
		p.setProjectName("myW");
		
		module.setProject(p);
		
		String path=WeixinAppModuleBuilder.class.getResource("/wxappmodule.zip").getPath();
		module.setModuleSpecFileName(path);
		WeixinAppModuleBuilder wpBuilder=new WeixinAppModuleBuilder();
		wpBuilder.buildModule(module);

	}

}
