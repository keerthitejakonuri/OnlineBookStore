package com.ocrs.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocrs.login.model.Course;
import com.ocrs.login.util.HibernateUtil;
import com.ocrs.login.util.Status;
import com.ocrs.login.util.TestMemcached;

 

/**
 * @author katva02 LoginController is used to login to the application and has
 *         methods like login,validateSession etc. TODO: Expand the description
 *         once all information is available
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	private static final Logger logger = Logger.getLogger(CourseController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public static List<Course> getCourses() {
		
		List<Course> courselist = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
		if (courselist != null) {
			logger.info("<------------ CACHE HIT in /course/list ------------------>");
			return courselist;
		}
		logger.info("<------------ CACHE MISS in /course/list ------------------>");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Course");
		List<Course> courses = (List<Course>) query.list();
		session.getTransaction().commit();
		TestMemcached.getMemcachedclinet().add("courselist", courses);
		logger.info("Cache has been updated ");
		logger.info("<!--!>"+courses.size());
		return courses;
	}
   
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Course> getSearchedCourses(@RequestBody Course course) {
		if(course.getCourseName().trim().equals(""))
		{
			return getCourses();
		}
		
		List<Course> courselist = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
		
		if (courselist != null) {
			ArrayList<Course> objcourselist = new ArrayList<Course>();
			logger.info("<------------ CACHE HIT Returning search results from cached course list : curent one ------------------>");
			for (int i = 0; i < courselist.size(); i++) {
				if (courselist.get(i).getCourseName().toLowerCase().contains(course.getCourseName().toLowerCase())) {
					objcourselist.add(courselist.get(i));
				}
			}
			return objcourselist;
		}
		getCourses();
		//logger.info("<------------CACHE MISS Returning search results from Data Base ------------------>");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Course.class)
				.add(Restrictions.like("courseName", "%" + course.getCourseName() + "%"));
		
		Query query = session.createQuery(
				"select objcourse from Course objcourse where lower(objcourse.courseName) LIKE :searchKeyword");
		query.setString("searchKeyword", "%"+course.getCourseName().toLowerCase()+"%");
		
		List<Course> courses = (List<Course>) query.list();
		session.getTransaction().commit();
		logger.info("courses list returned "+courses);
		return courses;
	}
	

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status addCourse(@RequestBody Course course) {
		Status currentStatus = new Status();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(
					"select count(*) from Course objcourse where objcourse.courseId=:courseId and objcourse.professorName=:professorName and objcourse.startTime=:startTime");
			query.setString("courseId", course.getCourseId());
			query.setString("professorName", course.getProfessorName());
			query.setString("startTime", course.getStartTime());
			Long count = (Long) query.uniqueResult();
			if (count == 0) {
				session.beginTransaction();
				session.save(course);
				session.getTransaction().commit();
				// adding to cache
				String[] strArray = new String[] { "add" };
				addtocache(strArray, course);
				currentStatus.setStatus("SUCCESS");
			} else {
				currentStatus.setStatus("FAILURE");
				currentStatus.setErrorCode(10);// Already the courseid,
												// professor, timing combination
												// exist
				currentStatus.setErrorMsg("Already the courseid, professor, timing combination exist");
			}
		} catch (Exception e) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(11);
			currentStatus.setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		logger.info("Returned Status details [Status : "+currentStatus.getStatus() +"\n ErrorCode : "+ currentStatus.getErrorCode()+" ]");
		return currentStatus;
	}

	
	private void addtocache(String[] strArray, Course course) {
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i].equals("add")) {
				List<Course> courselist = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
				if (courselist != null) {
					courselist.add(course);
					TestMemcached.getMemcachedclinet().replace("courselist", courselist);
				}
			}
		}
	}

	
	@RequestMapping(value = "/removeCourse", method = RequestMethod.POST)
	@ResponseBody
	public Status removeCourse(@RequestParam("id") int id) {
		Status currentStatus = new Status();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Course course = new Course();
			course.setId(id);
			session.delete(course);
			session.getTransaction().commit();
			String[] strArray = new String[] { "add" };
			removefromcache(strArray, course);
			currentStatus.setStatus("SUCCESS");
		} catch (Exception e) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(12);
			currentStatus.setErrorMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		logger.info("Returned Status details [Status : "+currentStatus.getStatus() +"\n ErrorCode : "+ currentStatus.getErrorCode()+" ]");
		return currentStatus;
	}
	

	private void removefromcache(String[] strArray, Course course) {
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i].equals("add")) {
				List<Course> courselist = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
				if (courselist != null) {
					for (int j = 0; j < courselist.size(); j++) {
						Course objcourse = courselist.get(j);
						System.out.println("current course id : " + objcourse.getId());
						if (objcourse.getId().equals(course.getId())) {
							courselist.remove(j);
							TestMemcached.getMemcachedclinet().replace("courselist", courselist);
							logger.info("courselist  key has been updated in cache");
							break;
						}
					}
				}
			}
		}
	}

	
	@RequestMapping(value = "/getCourse", method = RequestMethod.POST)
	@ResponseBody
	public Course getUserProfile(@RequestBody Course crc) {
		int id = crc.getId();
		List<Course> courselist = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
		if (courselist != null) {
			logger.info("<------------ returning course with id : " + id + " from cache ------------------>");
			for (int j = 0; j < courselist.size(); j++) {
				Course objcourse = courselist.get(j);
				if (objcourse.getId().equals(id)) {
					return objcourse;
				}
			}
			return null;
		}
		logger.info("<------------ retriving course with id : " + id + " from DataBase ------------------>");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String queryString = "from Course where id = :id";
		Query query = session.createQuery(queryString);
		query.setInteger("id", id);
		Object queryResult = query.uniqueResult();
		Course course = (Course) queryResult;
		session.getTransaction().commit();
		logger.info("Returned Course"+course);
		return course;
	}

	
	@RequestMapping(value = "/updateCourse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status updateCourse(@RequestBody Course course) {
		Status currentStatus = new Status();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.update(course);
				session.getTransaction().commit();
				String[] strArray = new String[] { "add" };
				removefromcache(strArray, course); // removing the old course from cache by id.
				addtocache(strArray, course); // adding to the cache the new updated object of the course.
				currentStatus.setStatus("SUCCESS");
		} catch (Exception e) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(14); // Update
			currentStatus.setErrorMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		logger.info("Returned Status details [Status : "+currentStatus.getStatus() +"\n ErrorCode : "+ currentStatus.getErrorCode()+" ]");
		return currentStatus;
	}

}
