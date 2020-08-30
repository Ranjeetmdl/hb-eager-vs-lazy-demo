package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class CreateInstructorDemo {

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
			Instructor tempInstructor = 
					new Instructor("susan", "public", "susan@luv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"https://www.javaSpring.com/youtube", 
							"Video Games");
			
			//Associate the Objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start/begin a transaction
			session.beginTransaction();
			
			//save the instructor into the database
			/*Note :this will also save tempInstructorDetail object b/c
			of CascadeType.ALL*/
			
			System.out.println("\n\nSaving the instructor :"+tempInstructor);
			session.save(tempInstructor);
			
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
