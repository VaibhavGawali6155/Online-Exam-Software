package com.project.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.internal.QuerySecondPass;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tables.Answer;
import com.project.tables.question;
import com.project.tables.user;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(" http://localhost:4200/")
public class Questioncontroller {
	@Autowired 
	SessionFactory sf;
	
	@RequestMapping("getFirstQuestion/{subject}")
	 public question getFirstQuestion(@PathVariable String subject)
	{
		Session ss=sf.openSession();
		Query qq=ss.createQuery("from question where subject=:subject");
		qq.setParameter("subject",subject);
		List<question> list=qq.list();
		HttpSession httpsession=logincontroller.httpsession;
		httpsession.setAttribute("list",list);
		System.out.println(list.size());
		question firstquestion=list.get(0);
		return firstquestion;
	}
     @RequestMapping("nextquestion")
	public question nextquestion()
	{
		HttpSession httpsession=logincontroller.httpsession;
		List<question> list=(List<question>)httpsession.getAttribute("list");
	    
		if((int)httpsession.getAttribute("questionIndex")<list.size()-1)
		{
			httpsession.setAttribute("questionIndex",(int)httpsession.getAttribute("questionIndex")+1);
		    question qq=list.get((int)httpsession.getAttribute("questionIndex"));
		    return qq;
		
		}
		else
		{
			question qa= list.get(list.size()-1);
			return qa;
			
		}
	
	}
     
     @RequestMapping("previousquestion")
 	public question previousquestion()
 	{
 		HttpSession httpsession=logincontroller.httpsession;
 		List<question> list=(List<question>)httpsession.getAttribute("list");
 	    
 		if((int)httpsession.getAttribute("questionIndex")>0)
 		{
 			httpsession.setAttribute("questionIndex",(int)httpsession.getAttribute("questionIndex")+-1);
 		    question qq=list.get((int)httpsession.getAttribute("questionIndex"));
 		    return qq;
 		
 		}
 		else
 		{
 			
 			return list.get(0);
 			
 		}
 	
 	}  
     
    
    @PostMapping("saveUser")
    public void saveUser(@RequestBody  Answer answer)
    {
    	HttpSession httpsession=logincontroller.httpsession;
    	HashMap<Integer,Answer>hashMap=(HashMap<Integer,Answer>)httpsession.getAttribute("submittedDetails");
        hashMap.put(answer.getQno(), answer);
        System.out.println(hashMap);
       
    }
    
    @RequestMapping("endexam")
    public ResponseEntity<Integer> endexam()
    {
    	HttpSession httpsession=logincontroller.httpsession;
    	
    	HashMap hashmap=(HashMap) httpsession.getAttribute("submittedDetails");
    	
    	Collection<Answer> collection=hashmap.values();
    	for(Answer answer:collection)
    	{
    		if(answer.getSubmittedAnswer().equals(answer.getOriginalAnswer()))
    		{
    			httpsession.setAttribute("score",(int)httpsession.getAttribute("score")+1);
    		}
    	}
    	int score=(int)httpsession.getAttribute("score");
    	ResponseEntity<Integer> responseEntity=new ResponseEntity<>(score,HttpStatus.OK);
    	
    	return responseEntity;
    }
    @RequestMapping("getAllAnswer")
    public ResponseEntity<Collection> getAllAnswer()
    {
    	HttpSession httpsession=logincontroller.httpsession;
    	HashMap<Integer,Answer>hashmap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
    	Collection<Answer> collection=hashmap.values();
    	ResponseEntity<Collection> responseEntity=new ResponseEntity<>(collection,HttpStatus.OK);
        return responseEntity;
    }
    @PostMapping("saveQuestion")
	public void saveQuestion(@RequestBody question q)
	{
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();
		ss.save(q);
		tx.commit();
		System.out.println("Data saved...");
		
	}
    @RequestMapping("getAllSubjects")
    public List<String> getAllSubjects()
    {
    	Session session=sf.openSession();
    	Query query=session.createQuery("select distinct subject from question");
         List<String>list=query.list(); 
         return list;
    }
  
}
