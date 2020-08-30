package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Instructor.class)
				               .addAnnotatedClass(InstructorDetail.class)
				               .addAnnotatedClass(Course.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
			int instructorId=2;
			
			//start/begin a transaction
			session.beginTransaction();
			
			//get the instructor based on the id/primary key
			Instructor theInstructor = session.get(Instructor.class, instructorId);
			System.out.println("\nInstructor retreived :"+theInstructor);
			
			//delete the instructor
			//Note : this will also delete the associcated InstructorDetail objec
			//because of CascadeType.ALL
			
			if(theInstructor!=null){
				System.out.println("\ndeleting the instructor :"+theInstructor);
				session.delete(theInstructor);
			}else{
				System.out.println("\nInstructor doesn't available!!");
			}

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
