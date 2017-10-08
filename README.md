Requirement :

1] Create Table Customer 
CREATE TABLE CUSTOMER 
   (	ID NUMBER(10,0), 
	NAME VARCHAR2(100 BYTE), 
	EMAIL VARCHAR2(100 BYTE), 
	VDATE VARCHAR2(100 BYTE)
   );
   

2] Create Stored Procedure :

create or replace PACKAGE CUSTOMER_PKG  AS 
  PROCEDURE GET_CUSTOMER_DETAILS_BY_ID(v_id1 number,v_id2 number, 
  RESULT OUT SYS_REFCURSOR);
END CUSTOMER_PKG;


create or replace PACKAGE BODY CUSTOMER_PKG AS 
 
   PROCEDURE GET_CUSTOMER_DETAILS_BY_ID (v_id1 number,v_id2 number, 
  RESULT OUT SYS_REFCURSOR) IS 
 
  BEGIN 

  OPEN RESULT FOR SELECT * FROM CUSTOMER where ID between v_id1 and v_id2 order by ID;

  END GET_CUSTOMER_DETAILS_BY_ID; 

 END CUSTOMER_PKG;

3] Rest end URL to insert record into Customer table.

URL : http://localhost:9090/customer/new

HTTP METHOD : POST

REQUEST JSON : {"id":10,"name":"MAHI","email":"mahi@com","date":"2017-07-19"}

RESPONSE : <NAME VALUE> CUSTOMER SAVED

4] Rest end URL to search the customer based on between clause.

URL : localhost:9090/customer?v_id1=70&v_id2=100

METHOD : GET 

5] 
To resolve the JNDI naming require below dependency .
<dependency>
                                                <groupId>commons-dbcp</groupId>
                                                <artifactId>commons-dbcp</artifactId>
                                </dependency>

6] jaxb.properties file to resolve eclipse 								