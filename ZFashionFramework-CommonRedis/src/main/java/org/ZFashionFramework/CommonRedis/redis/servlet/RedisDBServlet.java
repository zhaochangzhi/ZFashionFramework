package org.ZFashionFramework.CommonRedis.redis.servlet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
 






import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ZFashionFramework.CommonRedis.redis.readxml.ReadRedis;
import org.ZFashionFramework.CommonRedis.redis.utils.DBMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 

public class RedisDBServlet implements ServletContextListener {
	private static final Log LOG = LogFactory.getLog(RedisDBServlet.class);
	private static final String XML_FILE_PROPERTY = "redisXmlFile";
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LOG.debug(DBMessage.DBMESSAGE_011);  
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletConfig) {
		LOG.debug(DBMessage.DBMESSAGE_010);  
		 ServletContext context = servletConfig.getServletContext(); 
		 String appDir = servletConfig.getServletContext().getRealPath("/");
		  Enumeration<?> names = context.getInitParameterNames();
		   while (names.hasMoreElements()) {
			  
			 
	            String name = (String) names.nextElement();
	            String value = context.getInitParameter(name);
	            List<String> list=new ArrayList<String>();
	            if (name.equals(XML_FILE_PROPERTY)) {
	            	String[] values  =value.split(",");
	            
	            	for(String temp :values){
	            		int length=temp.indexOf("file:");
	            		if(length==-1){
	            			list.add(appDir.concat(temp));
	            		}else{
	            			list.add(temp.split("file:")[1]);
	            		}
	            		
	            	
	            	}
	             if(list.size()>0){
	            	 ReadRedis.getInstance((String[])list.toArray(new String[0]));
	             }
			   }
//	            MongoModel aa=ReadMongo.model;
//	           Map aaa= ReadMongo.mapModel;
		   }
		
	}

}
