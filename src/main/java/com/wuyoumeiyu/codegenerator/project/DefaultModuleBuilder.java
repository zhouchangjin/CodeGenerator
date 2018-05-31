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
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.gamewolf.util.datafile.XMLNode;
import com.gamewolf.util.datafile.XMLReader;
import com.gamewolf.util.file.FileUtil;

public class DefaultModuleBuilder implements ModuleBuilder {
	
	public static final String MODULE_NAME="{moduleName}"; 
	public static final String PROJECT_NAME="{projectName}";
	public static final String PROJECT_FOLDER="{projectFolder}";

	public void buildModule(Module module) {
		String fileName = module.getModuleSpecFileName();
		// XMLNode moduleDefXml=XMLReader.loadXMLFile(fileName);
		Map<String,StringBuffer> list=parseModuleFile(fileName);
		StringBuffer sb=list.get("module.xml");
		XMLNode node=XMLReader.parseXMLString(sb.toString());
		System.out.println(node.toXML());
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
				 try {
					BufferedWriter bw=new BufferedWriter(new FileWriter(f));
					bw.append(s);
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
		String res=input.replace(MODULE_NAME, mod.getModuleName());
		res=res.replace(PROJECT_FOLDER, mod.getProject().getProjectFolder());
		return res;
	}

}