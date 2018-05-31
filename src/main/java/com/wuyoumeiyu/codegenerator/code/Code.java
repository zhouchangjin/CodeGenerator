package com.wuyoumeiyu.codegenerator.code;


import java.util.ArrayList;
import java.util.List;

public class Code {
	
	List<String> code;
	
	int startLine=0;
	int endLine=9999;
	public Code(){
		code=new ArrayList<String>();
	}
	
	public void addLine(String line) {
		code.add(line);
	}
	
	public void addStartLine(String line) {
		code.add(line);
		startLine=code.size();
	}
	
	public void addEndLine(String line) {
		endLine=code.size();
		code.add(line);
	}
	
	public void insertCodeEnd(String line) {
		code.add(endLine, line);
		endLine++;
	}
	
	public void insertCodeStart(String line) {
		code.add(startLine,line);
		endLine++;
	}
	
	public void insertBeforeCode(String line) {
		code.add(line);
		startLine++;
		endLine++;
	}
	
	public void systemout() {
		for(String line:code) {
			System.out.println(line);
		}
	}
	
	public List<String> get(){
		return code;
	}
}
