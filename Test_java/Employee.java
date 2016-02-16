package Test_java;

import java.io.IOException;
import java.sql.SQLException;

public class Employee extends dbProcess{
	//field
	private String name,identity,password,workID;//workID作为工号
	private static String allIdentity[]={"经理","员工"};
	
	
	//constructor accessory
	Employee(){super();name=identity=workID=password=null;}
	Employee(String n,String i){
		super();
		this.name=n;
		this.setIdentity(i);
		this.workID=i;
		this.password=null;
	}
	Employee(String n,String i,String iden){
		super();
		this.name=n;
		this.workID=i;
		this.identity=iden;
		this.password=null;
	}
	Employee(String n,String i,String iden,String p){
		this(n,i,iden);
		this.password=p;
	}
	
	//get accessory
	public String getName(){return this.name;}
	public String getIdentity(){return this.identity;}
	public String getWorkId(){return this.workID;}
	protected String getPassword(){return this.password;}
	
	//set accessory
	public void setIdentity(String n){
		for(String s:allIdentity)
			if(s.equals(n))
				try {
					throw new IOException();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Set Identity of Employee Failed");
					e.printStackTrace();
				}
	}
	public void setIdentity(int i){
		if(i<0||i>=allIdentity.length)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Identity of Employee Failed");
				e.printStackTrace();
			}
	}
	public void setWorkId(String n){this.workID=n;}
	//升职和降职方法
	public static void setPromotion(Employee e){
		if(e.hasIdentity()&&e.isEmployee()){
			e.identity=allIdentity[1];
			e.updateString(e.identity, "employee", "EmployeeState", "employeeId", e.getWorkId());
		}
		else System.out.println("Promotion Error!");
	}
	public static void setDemotion(Employee e){
		if(e.hasIdentity()&&e.isManager()){
			e.identity=allIdentity[0];
			e.updateString(e.identity, "employee", "EmployeeState", "employeeId", e.getWorkId());
		}
		else System.out.println("Demotion Error!");
	}
	
	//boolean accessory
	public boolean hasName(){return this.name!=null;}
	public boolean hasIdentity(){return this.identity!=null;}
	public boolean hasId(){return this.workID!=null;}
	public boolean isTranValid(){return this.hasId()&&this.hasName()&&this.hasIdentity();}
	public boolean isManager(){
		if(this.hasIdentity())
			if(this.getIdentity()==allIdentity[0])return true;
			else return false;
		else {
			System.out.println("Have no Identity!");
			return false;
		}
	}
	public boolean isEmployee(){
		if(this.hasIdentity())
			if(this.getIdentity()==allIdentity[1])return true;
			else return false;
		else {
			System.out.println("Have no Identity!");
			return false;
		}
	}
	
	//integrated accessory
}