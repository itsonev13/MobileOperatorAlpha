package Calling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import DataBaseConn.conn;

public class Phone {

	public static void Call(int userid,int planid,int minutes)
	{
		int minutesleft=conn.getAllminususedMinutes(userid, planid);
		if(minutesleft<=0)
		{
			System.out.println("Service is deactivated");
		}
		else 
		{
			System.out.println("Call successful minutes left: "+(minutesleft-minutes));
			conn.UpdateMinutes(minutes,conn.getFullplanid(userid, planid));
		}
		
	}
	public static void Texting(int userid,int planid,int sms)
	{
		int SMSleft=conn.getAllminususedSMS(userid, planid);
		if(SMSleft<=0)
		{
			System.out.println("Service is deactivated");
		}
		else 
		{
			System.out.println("SMS were sent successfully SMS left: "+(SMSleft-sms));
			conn.UpdateSMS(sms,conn.getFullplanid(userid, planid));
		}
		
	}
	public static void Surfing(int userid,int planid,int data)
	{
		int dataleft=conn.getAllminususedData(userid, planid);
		if(dataleft<=0)
		{
			System.out.println("Service is deactivated");
		}
		else 
		{
			System.out.println("Surfing ended Mobile data left: "+(dataleft-data));
			conn.UpdateMobileData(data,conn.getFullplanid(userid, planid));
		}
		
	}
}
