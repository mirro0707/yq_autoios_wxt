package com.yq.example;

import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/**
 *
 * 动态生成各个模块中的每条用例的日志，运行完成用例之后请到results/logs目录下查看
 * 使用方法：在Base的类中注册，保证所有的日志都无遗漏并且能保证每个用例一条log
 * 作用：定制自己的日志文件名称，和一些配置
 * */
public class LogConfiguration {
	
		public static void initLog(String fileName){

			String functionModuleName = getFunctionName(fileName);//获取到模块名字

		    final String logFilePath  = "./results/logs/"+functionModuleName+"/"+fileName+".log";  //声明日志文件存储路径以及文件名、格式
			Properties prop = new Properties();
			/*
			配置log的输出等级，以及如何显示，如何输出，输出的日志保存到哪里等配置
			 */
			//配置日志输出的格式
			prop.setProperty("log4j.rootLogger","info, toConsole, toFile");
			prop.setProperty("log4j.appender.file.encoding","UTF-8" );
			prop.setProperty("log4j.appender.toConsole","org.apache.log4j.ConsoleAppender");
			prop.setProperty("log4j.appender.toConsole.Target","System.out");
			prop.setProperty("log4j.appender.toConsole.layout","org.apache.log4j.PatternLayout ");
			prop.setProperty("log4j.appender.toConsole.layout.ConversionPattern","[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");		
			prop.setProperty("log4j.appender.toFile", "org.apache.log4j.DailyRollingFileAppender");
			prop.setProperty("log4j.appender.toFile.file", logFilePath);
			prop.setProperty("log4j.appender.toFile.append", "false");
			prop.setProperty("log4j.appender.toFile.Threshold", "info");
			prop.setProperty("log4j.appender.toFile.layout", "org.apache.log4j.PatternLayout");
			prop.setProperty("log4j.appender.toFile.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");

			PropertyConfigurator.configure(prop);//使配置生效

		}
		
		
	    /**取得模块名字????需要修改！*/
	    public static String getFunctionName(String fileName){
			String functionName = null; 
			int firstUndelineIndex = fileName.indexOf("_"); 
			functionName = fileName.substring(0, firstUndelineIndex-4);
			return functionName;
	    
	}
	

}
