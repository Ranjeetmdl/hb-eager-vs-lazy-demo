package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class EagerLazyDemo {

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
			
			System.out.println("\nluv2code -Instructor :"+theInstructor);
			
			List<Course> courses = theInstructor.getCourses();
			System.out.println("\nluv2code-Courses :"+courses);
			//commit the transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			System.out.println("\nluv2code : session is now closed \n");
			
			//since courses are lazy loaded...this should fail
			//option 1:  call the getter method while session was open
			//retrieve the courses from instructor
			List<Course> theCourses = theInstructor.getCourses();
			System.out.println("\nluv2code-Courses :"+theCourses);
			System.out.println("luv2code-Done!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			factory.close();
		}
	}

}
