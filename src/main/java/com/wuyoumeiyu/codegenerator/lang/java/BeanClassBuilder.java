package com.wuyoumeiyu.codegenerator.lang.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gamewolf.util.file.FileUtil;
import com.wuyoumeiyu.codegenerator.code.JavaCode;

public class BeanClassBuilder {
	
	
	String packageName;
	String className;
	String folderName;
	String packageFolder;
	
	List<String> codeLines=new ArrayList<String>();
	
	HashMap<String,String> properties=new HashMap<String, String>();
	
	public static BeanClassBuilder startObject() {
		return new BeanClassBuilder();
	}
	
	public BeanClassBuilder setName(String className) {
		this.className=className;
		return this;
	}
	
	public BeanClassBuilder setPackage(String packageName) {
		this.packageName=packageName;
		return this;
	}
	
	public BeanClassBuilder setFloder(String folderName) {
		this.folderName=folderName;	
		return this;
	}
	
	public BeanClassBuilder addProperty(String name,String type) {
		properties.put(name, type);
		return this;
	}
	
	public BeanClassBuilder addProperties(String... params) {
		
		if(params.length%2==0) {
			for(int i=0;i<params.length;i+=2) {
				String name=params[i];
				String type=params[i+1];
				addProperty(name, type);
			}
		}
		return this;
	}
	

	public void build() {
		JavaCode code=new JavaCode();
		code.setClassName(this.className);
		code.setPackageName(this.packageName);
		buildPackage();
		String fileName=this.packageFolder+"/"+this.className+".java";
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
			code.regenerateCode();
			List<String> script=code.get();
			for(String line:script) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	private void buildPackage() {
		String path[]=this.packageName.split(".");
		String folder=folderName;
		for(String p:path) {
			folder+="/"+p;
			FileUtil.CreateFolder(folder);
		}
		this.packageFolder=folder;
		
	}

}
