# springboot


For testing please refer below steps :
1] HTTP port number set to 9090 into application.properties file.
2] Create Customer table in alertcore .
CREATE TABLE CUSTOMER (
CUSTOMER_ID NUMBER(10) ,
NAME VARCHAR2(100),
EMAIL VARCHAR2(100),
CREATED_DATE VARCHAR2(100),
CONSTRAINT "CUSTOMER_ID_PK" PRIMARY KEY (CUSTOMER_ID));
3] Test end point to create customer :
                End point : http://localhost:9090/customer/new
                JSON Request  :  {"id":70,"name":"DEVENDRA","email":"abc@com","date":"2017-07-19"} 
                REQUEST METHOD : POST 
4] To resolve the JNDI naming require below dependency .
<dependency>
                                                <groupId>commons-dbcp</groupId>
                                                <artifactId>commons-dbcp</artifactId>
                                </dependency>
5] Create package 
CREATE PACKAGE CUSTOMER_PKG AS 
PROCEDURE GET_CUSTOMER_DETAILS_BY_ID (
	    v_id   	IN NUMBER,		
      result          	OUT SYS_REFCURSOR
		);
END CUSTOMER_PKG;


create  PACKAGE BODY CUSTOMER_PKG AS
PROCEDURE GET_CUSTOMER_DETAILS_BY_ID (
	    v_id   	IN NUMBER,		
      result          	OUT SYS_REFCURSOR
)
IS
BEGIN
			OPEN result FOR
				SELECT 	*
				FROM 	CUSTOMER WHERE Customer_Id = v_id;
END GET_CUSTOMER_DETAILS_BY_ID;
END CUSTOMER_PKG;

