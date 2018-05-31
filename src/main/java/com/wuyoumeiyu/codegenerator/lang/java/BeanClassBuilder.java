package com.wuyoumeiyu.codegenerator.lang.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class BeanClassBuilder {
	
	
	String packageName;
	String className;
	String folderName;
	
	
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
	
	public BeanClassBuilder addPropertie(String name,String type) {
		properties.put(name, type);
		return this;
	}
	
	public void build() {
		
		String path=folderName+"/"+className;
		try {
			FileOutputStream fos=new FileOutputStream(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
