package com.project.controller;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tables.Answer;
import com.project.tables.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@RestController
@CrossOrigin("http://localhost:4200")
public class logincontroller {
	
	
	@Autowired
	SessionFactory sf;
	
	static HttpSession httpsession;
	
	@PostMapping("validate")
	public boolean validate(@RequestBody user userfromang,HttpServletRequest request)
	{
		 String userang=userfromang.username;
		 
		 Session ss=sf.openSession();
		 user userdb=ss.get(user.class,userfromang.username);
		 if(userdb==null)
		 {
			 return false;
		 }
		 else
		 {
			 if(userdb.password.equals(userfromang.password))
			 {
				 httpsession=request.getSession();
				 httpsession.setAttribute("score",0);
				 httpsession.setAttribute("questionIndex",0);
				 HashMap<Integer,Answer>hashMap=new HashMap<>();
				 httpsession.setAttribute("submittedDetails",hashMap);
				 return true;
			 }
			 else
			 {
				 return false;
			 }
		 }
	}
	@PostMapping("saveUs")
	public user saveUser(@RequestBody user us)
	{
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();
		ss.save(us);
		tx.commit();
		System.out.println("Data saved...");
		return us;
		
	}

}
