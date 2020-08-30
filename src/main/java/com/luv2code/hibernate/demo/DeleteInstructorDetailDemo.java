package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			int theId=4;
			
			//start/begin a transaction
			session.beginTransaction();
			
			//get the instructorDetail object
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//show the instructorDetail object
			System.out.println("\nInstructor Details :"+theInstructorDetail);
			
			//show the associated instructor object
			Instructor theInstructor = theInstructorDetail.getInstructor();
			System.out.println("\nAssociated Instructor :"+theInstructor);

			//now let's delete the instructor detail object
			//remove the associated object reference
			//break bi-directional link
			
			theInstructorDetail.getInstructor().setInstructorDetail(null);
			System.out.println("\nDeleting the Instructor Detail Object :"+theInstructorDetail);
			session.delete(theInstructorDetail);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			factory.close();
		}
	}

}
