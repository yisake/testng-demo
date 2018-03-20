package com.nico.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作系统文件操作
 * @author 皇甫春峰
 *
 */
public class FileUtil {

	public static void createPath(String dir) {
		File file=new File(dir);
		if  (!file.exists() && !file.isDirectory())      
		{       
		    System.out.println("创建目录"+dir);
		    file.mkdir();    
		} else   
		{  
		    System.out.println(dir+" 目录存在");  
		}
	}
	/**
	 * 
	 * @param path
	 * @return 
	 */
	public static List<String> getFileList (String strPath) {
		List<String> filelist = new ArrayList<String>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("json")) { // 判断文件名是否以.json结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(strFileName);
                } else {
                    continue;
                }
            }
        }
        return filelist;
    }
}