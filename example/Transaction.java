package example;

import java.io.IOException;
import java.sql.Timestamp;


public abstract class Transaction {
	Transaction(){}
	//field
	private String serialNum,stockNum,stockType,payMethod, name,idCard,phone,tranState,workID,cusState;//买入或者卖出的状态记录
	
	private static String[] allStockType={"股票","期货","债券"};
	private static String[]allPayMethod={"现金支付","银联支付"};
	private static String[]allTranState={"等待中","已完成"};
	private static String[]allCusState={"买入","卖出"};
	
	private Timestamp time=null;
	private int StockAmount;//股数
	
	private double unitPrice,interestRate,totalAmount;//totalAmount是交易总金额
	
	//get accessory
	public String getSerialNum(){ return this.serialNum;}
	public String getStockNum(){return this.stockNum;}
	public String getStockType(){return this.stockType;}
	public String getPayMethod(){return this.payMethod;}
	public Timestamp getTranTime(){return this.time;}
	
	public double getUnitPrice(){return this.unitPrice;}
	public double getInterestRate(){return this.interestRate;}
	public int getStockAmount(){return this.StockAmount;}
	public String getIdCard(){return this.idCard;}
	public String getPhone(){return this.phone;}
	public String getName(){return this.name;}
	public String getTranState(){return this.tranState;}
	public String getWorkID(){return this.workID;}
	public String getCusState(){return this.cusState;}
	public double getTotalAmount(){return this.totalAmount;}
	
	//set accessory
	public void setTranTime(Timestamp t){
		if(t!=null)this.time=t;
		else System.out.println("Set Time Failed!");
	}
	public void setSerialNum(String n){
		if(this.serialNum==null)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Serial Number Filed");
				e.printStackTrace();
			}
			this.stockNum=n;
		this.serialNum=n;
	}
	public void setStockNum(String n){
		if(this.stockNum==null)
		try {
			throw new IOException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Set Stock Number Filed");
			e.printStackTrace();
		}
		this.stockNum=n;
	}
	public void setStockType(String n){
		for(String s:allStockType){
			if(s.equals(n))this.stockType=n;
			break;
		}
		if(this.stockType==null)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Stock Type Filed");
				e.printStackTrace();
			}
	}
	public void setStockType(int n){
		try{
			if(n<0||n>=allStockType.length)throw new IOException();
		}catch(IOException e){
			System.out.println("Invalid input information");
			e.getStackTrace();
		}
		this.stockType=allStockType[n];
	}
	public void setPayMethod(String n){
		for(String s:allPayMethod){
			if(s.equals(n))this.payMethod=n;
			break;
		}
		if(this.payMethod==null)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Pay Method Failed!");
				e.printStackTrace();
			}
	}
	public void setPayMethod(int i){
		try{
			if(i<0||i>=allPayMethod.length)throw new IOException();
		}catch(IOException e){
			System.out.println("Set Pay Method Failed!");
			e.getStackTrace();
		}
	}
	public void setInterestRate(double n){this.interestRate=n;}
	public void setStockAmount(int n){this.StockAmount=n;}
	public void setName(String n){this.name=n;}
	public void setPhone(String n){this.phone=n;}
	public void setIdCard(String n){this.idCard=n;}
	public void setTranState(String n){
		for(String s:allTranState)
			if(s.equals(n))this.tranState=n;
		if(this.tranState==null)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Transaction State Failed");
				e.printStackTrace();
			}
	}
	public void setTranState(int i){
		if(i<0||i>=allTranState.length)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Transaction State Failed");
				e.printStackTrace();
			}
	}
	public void setWorkID(String n){
		if(n==null)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Get Work ID Failed");
				e.printStackTrace();
			}
	}
	public void setCusState(int i){
		if(i<0||i>=allCusState.length)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Custom Statement Failed");
				e.printStackTrace();
			}
		else this.cusState=allCusState[i];
	}
	public void setCusState(String n){
		for(String s:allCusState)
			if(s.equals(n)){
				this.cusState=n;
				break;
			}
		if(this.cusState==null)
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Set Custom Statement Failed");
				e.printStackTrace();
			}
	}
	public void setTotalAmount(){this.totalAmount=this.getUnitPrice()*this.getStockAmount();}
	public void setUnitPrice(double n){this.unitPrice=n;}

}
