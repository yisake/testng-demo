package com.nico.utils;

import java.io.File;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * 创建log4j的log对象工具 (•ิ_•ิ)
 * 
 * @author huangfucf
 *
 */
public class LoggerUtil {
	
	public static Logger getLogger(String logPath,String loggerName) throws Exception{
        Logger logger = null;
        if(LogManager.exists(loggerName)!=null) {
        	return LogManager.getLogger(loggerName);
        }
        logger = Logger.getLogger(loggerName);
        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss,SSS} [%p] [%t] %m %n");
        
        //判断文件是否存在，存在则删除
        File file = new File(logPath);
        if (file.exists()) {
        	file.delete();
        }
                
        // 文件输出
        FileAppender appender=new FileAppender(layout, logPath);
        appender.setEncoding("UTF-8");
        appender.activateOptions();

        /*// 参数配置, 因为没有找到仅靠配置文件的办法, 只好放在这里设
        R.setAppend(false);
        R.setImmediateFlush(false);
        R.setThreshold(Level.INFO);*/

        // 绑定到Logger
        logger.setLevel(Level.INFO);
        logger.addAppender(appender);
        return logger;
    }
}
