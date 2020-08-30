package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class GetInstructorCourseDemo {

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
	
			//start/begin a transaction
			session.beginTransaction();
			
			//get the instructor
			int theId=3;
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			System.out.println("\nInstructor :"+theInstructor);
			
			//retrieve the courses from instructor
			List<Course> courses = theInstructor.getCourses();
			System.out.println("\nCourses :"+courses);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			factory.close();
		}
	}

}
