package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Instructor.class)
				               .addAnnotatedClass(InstructorDetail.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
			int theId=789;
			
			//start/begin a transaction
			session.beginTransaction();
			
			//get the instructorDetail object
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//show the instructorDetail object
			System.out.println("\nInstructor Details :"+theInstructorDetail);
			
			//show the associated instructor object
			Instructor theInstructor = theInstructorDetail.getInstructor();
			System.out.println("\nAssociated Instructor :"+theInstructor);

			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
		}finally{
			factory.close();
		}
	}

}
