import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class MonitoredData 
{
	private String startTime;
	private String endTime;
	private String activityLabel;
	public MonitoredData(String s , String e , String a)
	{
		this.startTime=s;
		this.endTime=e;
		this.activityLabel=a;
	}
	public String afisare()
	{
		return "Start :		"+startTime +"   End :		"+endTime+"   Label :		"+activityLabel;
	}
	public String getStartTime() 
	{
		return startTime;
	}
	public String getEndTime() 
	{
		return endTime;
	}
	public String getActivityLabel() 
	{
		return activityLabel;
	}
	
	public int getZi()
	{
		String[] splitSpace = this.startTime.split(" ");
		String[] splitData=splitSpace[0].split("-");
		return Integer.parseInt(splitData[2]);
	}	
	public long getDurata()
	{
		SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		Date end = null;
		try {
			start = formatData.parse(this.startTime);
			end = formatData.parse(this.endTime);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long milisecunde =  end.getTime() - start.getTime();
		return milisecunde;
	}
	
}


