package org.xyl.judge;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Compiler{
	
	public String name;
	//public String args;
	private Target target;
	private OutResult result;
	private Process process;
	private Runtime rt;
	
	public Compiler(String compilerName,Target target,Runtime rt){
		this.name=compilerName;
		//this.args=args;
		this.target=target;
		this.rt=rt;
		result=new OutResult();
	}
	
	public OutResult compile(){
		if(name==null||target==null||"".equals(name))
				return null;
		
		System.out.println("--begine compile--");
		try {
			
			//����ǰ��ɾ��Ŀ���ļ�
			if(isTargetFileExit()){
				clearObjFile();	
			}		
			
			
			System.out.println(target.fileExtension+"----����-------������");
			//���ݺ�׺�������벻ͬ�ĳ���
			String str="";
			
			if(target.fileExtension.endsWith("cpp")){
				//str=name+" "+"\""+target.filePath+target.fileName+".cpp\""+" -ansi -fno-asm -s -w -std=c++98 -O2 -DONLINE_JUDGE -o "+"\""+target.filePath+target.fileName+".exe\"";
				str=name+" "+"\""+target.filePath+target.fileName+".cpp\""+" -g -o "+"\""+target.filePath+target.fileName+".exe\"";
			
			}else if(target.fileExtension.endsWith("c")){
				//str=name+" "+"\""+target.filePath+target.fileName+".c\""+" -ansi -fno-asm -s -w -std=c99 -O2 -DONLINE_JUDGE -o "+"\""+target.filePath+target.fileName+".exe\"";
				str=name+" "+"\""+target.filePath+target.fileName+".c\""+" -g -o "+"\""+target.filePath+target.fileName+".exe\"";
			
			}else{
				str=name+" "+"\""+target.filePath+target.fileName+target.fileExtension+"\"";
			}
			
			//System.out.println("�������---������"+str);
			//process=rt.exec(name+" "+"\""+target.filePath+target.fileName+target.fileExtension+"\"");
			System.out.println("---compiler------------"+str);
			process=rt.exec(str);
			
			redirectCompileInfoToResult();	
			if(isSuccess()){
				result.setState("compileSuccess");
			}else{
				result.setState("compileError");
			}
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("����"+e.getMessage());
		}
		System.out.println("--end compile--");
		return result;
	}
	
	private void redirectCompileInfoToResult() {
		InputStream isStdErro = process.getErrorStream();
		InputStream isStdIn = process.getInputStream();
		byte b[] = new byte[1024];
		try {
			int readBytes=isStdErro.read(b);
			 if(readBytes<0)
					readBytes=0;
			String s=new String(b,0,readBytes);
			result.erroCompile=s;
			isStdErro.close();
			readBytes=isStdIn.read(b);
			readBytes-=2;  //������0d 0a
			if(readBytes<0)
				readBytes=0;
			String s1=new String(b,0,readBytes);
			result.outCompile=s1;
			result.targetId=target.targetId;
			isStdIn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//ɾ��Ŀ���ļ�
	private void clearObjFile(){
		File f=new File(target.filePath+target.fileName+target.objExtension);
		//if(f.lastModified()<f.get)
		System.out.println("ɾ��Ŀ���ļ�---"+target.filePath+"==="+target.fileName+"==="+target.objExtension);
		File f1=new File(target.filePath);
		System.out.println(f1.exists()+"--");
		
		if(f.exists()){
			f.delete();
			System.out.println(" delete file.");
		}
		
	}
	
	//�鿴Ŀ���ļ��Ƿ����
	private boolean isTargetFileExit(){
		
		File f=new File(target.filePath+target.fileName+target.objExtension);	
		System.out.println(f.exists()+"Ŀ���ļ�����---"+f.getAbsolutePath());
		
		if(f.exists()){
			return true;
		}
		
		return false;
	}
	
	//�鿴�Ƿ񾯸����
	private boolean isErro(){
		
		System.out.println("����----"+result.erroCompile+"-----�������");
		
		if(result.erroCompile==null){
			return false;
		}
		if(result.erroCompile.length()>0){
			return true;
		}
		return false;
	}
	
	private boolean isSuccess(){
		if(isTargetFileExit()){		//&&!isErro()
			return true;
		}
		return false;
	}

}


