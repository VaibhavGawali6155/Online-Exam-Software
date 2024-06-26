import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private httpclient:HttpClient)
   {

    }

    getfirstQuestion(subject:string)
    {
         return  this.httpclient.get<Question>("http://localhost:8080/getFirstQuestion/"+subject);
    }

    nextQuestion()
    {
      return this.httpclient.get<Question>("http://localhost:8080/nextquestion");
    }

    previousQuestion()
    {
      return this.httpclient.get<Question>("http://localhost:8080/previousquestion");
    }
    saveAnswer(answer:Answer):Observable<void>
    {
       return this.httpclient.post<void>("http://localhost:8080/saveUser",answer);    
    }
endexam()
{
    return  this.httpclient.get<number>("http://localhost:8080/endexam");
}

getAllAnswers()
{
   return this.httpclient.get<Answer[]>("http://localhost:8080/getAllAnswer");
}
getAllSubjects()
{
   return this.httpclient.get<string[]>("http://localhost:8080/getAllSubjects");
}
saveQuestion(question:Question)
{
  return this.httpclient.post<void>("http://localhost:8080/saveQuestion",question);

}

}

export class Question{
     
      qno:number;
      subject:string;
      qtext:string;
      op1:string;
      op2:string;
      op3:string;
      op4:string;
      answer:string;

      constructor(qno:number, subject:string,qtext:string,op1:string, op2:string,op3:string, op4:string,answer:string)
      {
            this.qno=qno;
            this.subject=subject;
            this.qtext=qtext;
            this.op1=op1;
            this.op2=op2;
            this.op3=op3;
            this.op4=op4;
            this.answer=answer;
      }

    
}

export class Answer
{
  qno:number;
	submittedAnswer:string;
  qtext:string;
  originalAnswer:string;
  
  constructor(qno:number,qtext:string,submittedAnswer:string,originalAnswer:string)
  {
      this.qno=qno;
      this.originalAnswer=originalAnswer;
      this.submittedAnswer=submittedAnswer;
      this.qtext=qtext;
  }
}