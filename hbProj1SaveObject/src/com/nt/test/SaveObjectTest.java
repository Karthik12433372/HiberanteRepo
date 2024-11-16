package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		Configuration cfg = null;
		SessionFactory factory = null;
		Session ses = null;
		Product prod = null;
	    Transaction tx = null;
	    boolean flag = false;
		
		//Activate Hibernate Framework
		cfg = new Configuration();
		//Supply hibernate configuration file as input
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		//BuildSession Factory
		factory = cfg.buildSessionFactory();
		//open Session
		ses = factory.openSession();
		
		//Create Entity object to save with Db s/w
		prod = new Product();
		prod.setPid(333);prod.setPname("Oil");prod.setPrice(40000);prod.setQuantity(2);
		
		try{
			tx=ses.beginTransaction();//Intenally calls con.setAutocommit(false) to begin tx
			//Save object
			ses.save(prod);
			flag=true;
		}
		catch(HibernateException he) {
			he.printStackTrace();
			flag=false;
		}
		finally {
			if(flag==true) {
				tx.commit();//internally calls con.commit()
				System.out.println("Object is Saved");
			}
			else {
				tx.rollback();//internally calls con.rollback()
				System.out.println("Object is not saved");
			}
			
			//close Session object
			ses.close();
			//close SessionFactory object
			factory.close();
		}//finally
		

	}//main

}//class
