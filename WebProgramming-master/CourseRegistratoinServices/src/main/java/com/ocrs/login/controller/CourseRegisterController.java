package com.ocrs.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocrs.login.model.Course;
import com.ocrs.login.model.Registration;
import com.ocrs.login.model.User;
import com.ocrs.login.util.HibernateUtil;
import com.ocrs.login.util.Status;
import com.ocrs.login.util.TestMemcached;

/**
 * @author katva02 LoginController is used to login to the application and has
 *         methods like login,validateSession etc. TODO: Expand the description
 *         once all information is available
 */
@RestController
@RequestMapping("/registerCourse")
public class CourseRegisterController {
	
	 @Autowired
		private LoginSessionBean loginSessionBean;
	private static final Logger logger = Logger.getLogger(CourseRegisterController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status register(@RequestBody Registration registration, @RequestParam String sessionKey) {
		Status currentStatus = new Status();
		
		User userObj = (User)loginSessionBean.getSessionMap().get(sessionKey);
		if(userObj == null)
		{
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(23);
			return currentStatus;
		}
		registration.setUserId(userObj.getId());
		if (iscourseUserexists(registration.getCourseId(), registration.getUserId())) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(20);// Existing user course id
											// combination
			currentStatus.setErrorMsg(
					"User " + registration.getUserId() + " has already registered for " + registration.getCourseId());
			logger.info("Returned Status details [Status : " + currentStatus.getStatus() + "\n ErrorCode : "
					+ currentStatus.getErrorCode() + " ]");
			return currentStatus;
		}

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(registration);
			
			Query query2 = session.createQuery(
					"select availableSlots from Course where id=:courseId");
			query2.setInteger("courseId", registration.getCourseId());
			Integer slots= (Integer)query2.uniqueResult();
			 slots++ ;
			Query query3 = session.createQuery(
					"update Course  set availableSlots= :slots where id=:courseId ");
			query3.setInteger("courseId", registration.getCourseId());
			query3.setInteger("slots", slots);
			query3.executeUpdate();
			session.getTransaction().commit();
			currentStatus.setStatus("SUCCESS");
			List<Integer> courseIdList = (List<Integer>) TestMemcached.getMemcachedclinet()
					.get(registration.getUserId() + "-courseIdlist");
			if(courseIdList!=null)
			courseIdList.add(registration.getCourseId());
			TestMemcached.getMemcachedclinet().replace(registration.getUserId() + "-courseIdlist", courseIdList);
			logger.info("Added course" + registration.getCourseId() + " to " + registration.getUserId());
			logger.info(registration.getUserId() + "courseIdlist  key has been updated in cache");

		} catch (Exception e) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(21);
			currentStatus.setErrorMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		logger.info("Returned Status details [Status : " + currentStatus.getStatus() + "\n ErrorCode : "
				+ currentStatus.getErrorCode() + " ]");
		return currentStatus;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Status removeCourse(@RequestBody Registration registration, @RequestParam String sessionKey) {
		
		Status currentStatus = new Status();
		
		User userObj = (User)loginSessionBean.getSessionMap().get(sessionKey);
		if(userObj == null)
		{ 
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(25);
			return currentStatus;
		}
		registration.setUserId(userObj.getId());
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"delete from Registration reg where reg.userId=:userId and reg.courseId=:courseId");
			query.setInteger("userId", registration.getUserId());
			query.setInteger("courseId", registration.getCourseId());
			query.executeUpdate();
			
			Query query2 = session.createQuery(
					"select availableSlots from Course where id=:courseId");
			query2.setInteger("courseId", registration.getCourseId());
			Integer slots= (Integer)query2.uniqueResult();
			 slots-- ;
			Query query3 = session.createQuery(
					"update Course  set availableSlots= :slots where id=:courseId ");
			query3.setInteger("courseId", registration.getCourseId());
			query3.setInteger("slots", slots);
			query3.executeUpdate();
			//session.delete(registration);
			session.getTransaction().commit();
			currentStatus.setStatus("SUCCESS");
			logger.info(" USER (" + registration.getUserId() + ") Course " + registration.getCourseId()
					+ " has been removed  ");
			List<Integer> courseIdList = (List<Integer>) TestMemcached.getMemcachedclinet()
					.get(registration.getUserId() + "-courseIdlist");
			courseIdList.remove(registration.getCourseId());
			TestMemcached.getMemcachedclinet().replace(registration.getUserId() + "-courseIdlist", courseIdList);
			logger.info(registration.getUserId() + "courseIdlist  key has been updated in cache");

		} catch (Exception e) {
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(22);
			currentStatus.setErrorMsg(e.getMessage());
			logger.error(e.getMessage());
		}
		logger.info("Returned Status details [Status : " + currentStatus.getStatus() + "\n ErrorCode : "
				+ currentStatus.getErrorCode() + " ]");

		return currentStatus;
	}

	@RequestMapping(value = "/getRegCourses", method = RequestMethod.GET)
	@ResponseBody
	public List<Course> getRegCourses( @RequestParam String sessionKey) {
		Status currentStatus = new Status();
		System.out.println("In get reg courses");
		User userObj = (User)loginSessionBean.getSessionMap().get(sessionKey);
		if(userObj == null)
		{
			currentStatus.setStatus("FAILURE");
			currentStatus.setErrorCode(24);
			return null;
		}
		
		Integer userId = userObj.getId();
		List<Course> result = new ArrayList<Course>();
		List<Integer> courseIdList = (List<Integer>) TestMemcached.getMemcachedclinet().get(userId + "-courseIdlist");
		if (courseIdList == null) {
			logger.info("Cache MISS for getRegCourses");
			try {
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				Query query = session.createQuery(
						"select courseId from Registration objRegistration where objRegistration.userId=:userId");
				query.setInteger("userId", userId);
				courseIdList = (ArrayList<Integer>) query.list();
				TestMemcached.getMemcachedclinet().add(userId + "-courseIdlist", courseIdList);
				logger.info(userId + "courseIdlist  key has been updated in cache");
			} catch (Exception e) {
				currentStatus.setStatus("FAILURE");
				currentStatus.setErrorCode(23);
				currentStatus.setErrorMsg(e.getMessage());
				logger.info("Returned Status details [Status : " + currentStatus.getStatus() + "\n ErrorCode : "
						+ currentStatus.getErrorCode() + " ]");
			}
		} else {
			logger.info("Cache HIT for getRegCourses");
		}

		if (courseIdList != null) {
			List<Course> currentList = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
			if (currentList == null) {
				CourseController.getCourses();
				currentList = (List<Course>) TestMemcached.getMemcachedclinet().get("courselist");
			}
			if (currentList != null)
				for (Course crc : currentList) {
					if (courseIdList.contains(crc.getId()))
						result.add(crc);
				}
		}
		logger.info("Courses returned" + result);
		return result;
	}

	private boolean iscourseUserexists(Integer courseId, Integer userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(
				" from Registration objRegistration where objRegistration.userId=:userId and objRegistration.courseId=:courseId");
		query.setInteger("userId", userId);
		query.setInteger("courseId", courseId);
		int count = query.list().size();
		return count != 0;
	}

}
