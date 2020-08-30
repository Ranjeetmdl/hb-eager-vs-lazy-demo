package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class CreateInstructorDemoTest {

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
					new Instructor("Chad", "darby", "darby@luv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"https://www.luv2code.com/youtube", 
							"Luv 2 code");
			
			Course course1 = new Course("Spring & Hibernate");
			Course course2 = new Course("Java Sserver Face");
			Course course3 = new Course("Servelts & JDBC");
			
			//Associate the Objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			tempInstructor.add(course1);
			tempInstructor.add(course2);
			tempInstructor.add(course3);
			
			//start/begin a transaction
			session.beginTransaction();
			
			//save the instructor into the database
			/*Note :this will also save tempInstructorDetail object & all the associated
			 * courses b/c of CascadeType.ALL*/
			
			System.out.println("\n\nSaving the instructor :"+tempInstructor);
			session.persist(tempInstructor);
			
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
