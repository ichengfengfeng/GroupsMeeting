<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" 
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
  <display-name></display-name>
  <servlet>
    <description>This is the description of my J2EE component</description>
    <display-name>This is the display name of my J2EE component</display-name>
    <servlet-name>LoginSvl</servlet-name>
    <servlet-class>com.icss.meeting.control.LoginSvl</servlet-class>
  </servlet>
  <servlet>
    <description>This is the description of my J2EE component</description>
    <display-name>This is the display name of my J2EE component</display-name>
    <servlet-name>MainSvl</servlet-name>
    <servlet-class>com.icss.meeting.control.MainSvl</servlet-class>
  </servlet>


  <servlet-mapping>
    <servlet-name>LoginSvl</servlet-name>
    <url-pattern>/LoginSvl</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>MainSvl</servlet-name>
    <url-pattern>/MainSvl</url-pattern>
  </servlet-mapping>	
  
  
  <resource-ref>
		<res-ref-name>jdbc/OracleMeeting</res-ref-name>
		<res-type>javax.sql.DataSource</res-type>
		<res-auth>Container</res-auth>
  </resource-ref>
 
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
</web-app>
