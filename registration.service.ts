import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export class User{
    username:string;
    password:string;
    mobileno:number;
    emailid:string;

    constructor(username:string,password:string,mobileno:number,emailid:string)
    {
        this.username=username;
        this.password=password;
        this.emailid=emailid;
        this.mobileno=mobileno;
    }

}


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpclient:HttpClient) 
  { }

  saveUser(user:User)
  {
       return this.httpclient.post<void>("http://localhost:8080/saveUs",user);
  }
}
