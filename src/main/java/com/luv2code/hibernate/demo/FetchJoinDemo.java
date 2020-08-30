package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;

public class FetchJoinDemo {

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
			
			//option 2: Hibernate Query with HQL
			//get the instructor from db
			int theId=3;
			Query<Instructor> query = session.createQuery(
					 "select i from Instructor i "
					+ "JOIN FETCH i.courses where i.id=:theInstuctorId", 
					Instructor.class);
			
			//set parameter on the query
			query.setParameter("theInstuctorId", theId);
			
			//execute the query and get the instructor
			Instructor theInstructor = query.getSingleResult();

			//commit the transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			System.out.println("\nluv2code : session is now closed \n");
			
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
