package com.wuyoumeiyu.codegenerator.project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.gamewolf.util.datafile.XMLNode;
import com.gamewolf.util.datafile.XMLReader;
import com.gamewolf.util.file.FileUtil;

public class DefaultModuleBuilder implements ModuleBuilder {
	
	HashSet<String> filterSet=new HashSet<String>();
	
	String wrapperAhead;
	String wrapperBehind;
	
	public DefaultModuleBuilder() {

	}
	
	public static DefaultModuleBuilder startBuild() {
		return new DefaultModuleBuilder();
	}
	
	private void addParam(String param) {
		filterSet.add(param);
	}
	
	private void addParams(String params) {
		String paramsarr[]=params.split(",");
		for(String param:paramsarr) {
			addParam(param);
		}
	}

	public void buildModule(Module module) {
		String fileName = module.getModuleSpecFileName();
		// XMLNode moduleDefXml=XMLReader.loadXMLFile(fileName);
		Map<String,StringBuffer> list=parseModuleFile(fileName);
		StringBuffer sb=list.get("module.xml");
		XMLNode node=XMLReader.parseXMLString(sb.toString());
		
		XMLNode settingNode=node.getNode("Setting");
		String params=settingNode.getNode("Params").getValue();
		wrapperAhead=settingNode.getNode("WrapperAhead").getValue();
		wrapperBehind=settingNode.getNode("WrapperBehind").getValue();
		addParams(params);
		
		
		List<XMLNode> folderList=node.getNodes("Folder");
		for(XMLNode folderNode:folderList) {
			 String pathName=folderNode.getAttribute("name").toString();
			 String parent=folderNode.getAttribute("parent").toString();
			 String moduleFolder=processVariables(parent, module)+"/"+processVariables(pathName,module);
			 FileUtil.CreateFolder(moduleFolder);
			 List<XMLNode> fileNodes=folderNode.getNodes("File");
			 for(XMLNode fileNode:fileNodes) {
				 String name=fileNode.getAttribute("name").toString();
				 
				 String templateFile=fileNode.getAttribute("template").toString();
				 String filePath=moduleFolder+"/"+processVariables(name,module);
				 File f=new File(filePath);
				 String s=list.get(templateFile).toString();
				 String code=processVariables(s, module);
				 try {
					BufferedWriter bw=new BufferedWriter(new FileWriter(f));
					bw.append(code);
					bw.flush();
					bw.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
		}
		
	}
	
	public static Map<String,StringBuffer> parseModuleFile(String path) {
		Map<String,StringBuffer> sbList=new HashMap<String,StringBuffer>();
		File file = new File(path);
		try {
			ZipFile zipFile = new ZipFile(file);
			ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				//System.out.println("file :" + entry.getName());
				BufferedInputStream bis = new BufferedInputStream (zipFile.getInputStream(entry));
				BufferedReader br=new BufferedReader(new InputStreamReader(bis));
				String line="";
				StringBuffer sb=new StringBuffer();
				while((line=br.readLine())!=null) {
					sb.append(line);
					sb.append(System.lineSeparator());
				}
				sbList.put(entry.getName(),sb);
			}
			zipFile.close();
			zis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sbList;
	}
	
	
	String processVariables(String input,Module mod) {
		String res=input;
		for(String param:filterSet) {
			
			String replaceBefor=wrapperAhead+param+wrapperBehind;
			String replaceWith=mod.get(param);
			res=res.replace(replaceBefor, replaceWith);
			
		}
		return res;
	}

}
