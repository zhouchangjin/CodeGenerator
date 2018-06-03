package com.wuyoumeiyu.codegenerator.project.mybatis;

import com.wuyoumeiyu.codegenerator.project.DefaultModuleBuilder;
import com.wuyoumeiyu.codegenerator.project.Module;
import com.wuyoumeiyu.codegenerator.project.ModuleBuilder;

public class MyBatisModuleBuilder extends DefaultModuleBuilder {

	public static void main(String args[]) {
		MybatisProject project=new MybatisProject();
		project.setProjectName("c:/work/data/test");
		
		Module m=new Module();
		m.setProject(project);
		m.setModuleSpecFileName("c:/work/data/test/lxmy.zip");
		m.set("entity", "rfid");
		m.set("model", "Rfid");
		m.set("module", "rfid");
		m.set("folder", "c:/work/data/test/");
		MyBatisModuleBuilder builder=new MyBatisModuleBuilder();
		builder.buildModule(m);
	}

}
