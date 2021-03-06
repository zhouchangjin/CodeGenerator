package com.wuyoumeiyu.codegenerator.project.mybatis;

import com.wuyoumeiyu.codegenerator.project.DefaultModuleBuilder;
import com.wuyoumeiyu.codegenerator.project.Module;

public class MyBatisModuleBuilder extends DefaultModuleBuilder {

	public static void main(String args[]) {
		MybatisProject project=new MybatisProject();
		project.setProjectName("booksharing");
		
		Module m=new Module();
		m.setProject(project);
		m.setModuleSpecFileName("c:/work/data/test/lxmy.zip");
		m.set("entity", "recycleRecord");
		m.set("model", "RecycleRecord");
		m.set("module", "recycle");
		m.set("folder", "c:/work/Workspace/JEE/booksharing");
		m.set("path", "recycle_record");
		m.set("table", "recycle_record");
		MyBatisModuleBuilder builder=new MyBatisModuleBuilder();
		builder.buildModule(m);
	}

}
