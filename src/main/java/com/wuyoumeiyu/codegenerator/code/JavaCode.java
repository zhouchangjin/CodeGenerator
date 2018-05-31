package com.wuyoumeiyu.codegenerator.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JavaCode extends Code{
	
	String packageName;
	
	String className;
	
	List<String> imports;
	
	HashMap<String,String> beanProperties;
	
	public JavaCode() {
		super();
		imports=new ArrayList<String>();
		beanProperties=new HashMap<String, String>();
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	
	public void addImport(String className) {
		imports.add(className);
	}
	
	public void addProperty(String propName,String propType) {
		this.beanProperties.put(propName, propType);
	}
	
	
	public void regenerateCode() {
		String packageLine="package "+this.packageName+";";
		
		String classHeader="public class "+this.className+" {";
		this.code.clear();
		addLine(packageLine);
		for(int i=0;i<this.imports.size();i++) {
			String imporStr=imports.get(i);
			addLine(imporStr);
		}
		
		addStartLine(classHeader);
		addEndLine("}");
		for(String prop:beanProperties.keySet()) {
			String type=beanProperties.get(prop);
			String line="	"+type+" "+prop+";";
			insertCodeEnd(line);
		}
		
		for(String prop:beanProperties.keySet()) {
			String type=beanProperties.get(prop);
			String getterline=
					"	public "
					+type+" get"
					+prop.substring(0,1).toUpperCase()
					+prop.substring(1)+"(){";
			
			insertCodeEnd(getterline);
			String getterBotty="		return this."+prop+";";
			insertCodeEnd(getterBotty);
			insertCodeEnd("	}");
			
			
			String setterline=
					"	public "
					+"void"+" set"
					+prop.substring(0,1).toUpperCase()
					+prop.substring(1)
					+"("+type+" "+prop+"){";
			insertCodeEnd(setterline);
			String setterBotty="		this."+prop+"="+prop+";";
			insertCodeEnd(setterBotty);
			insertCodeEnd("	}");
			
		}
		
		
	}
	
	public static void main(String args[]) {
		String packageName="test";
		String className="Test";
		JavaCode code=new JavaCode();
		code.setClassName(className);
		code.setPackageName(packageName);
		code.addImport("a,b,c;");
		code.addProperty("name", "String");
		code.regenerateCode();
		code.systemout();
		
	}

}
