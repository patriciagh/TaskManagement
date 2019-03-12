import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class Data {
	private  ArrayList<MonitoredData> data = new ArrayList<MonitoredData>();
	private  final String fisier = "Activities.txt";
	public Object[] citireFisier()
	{
		Object[] text = null;
		try(Stream<String> stream = Files.lines(Paths.get(fisier)))
		{
			text=stream.toArray();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return text;
	}	
	public void parsare(Object[] text)
	{
		for(Object o : text){
			String line = o.toString();
			String[] splitString = line.split("		"); 
			String start = splitString[0];
			String end = splitString[1];
			String label = splitString[2];
			MonitoredData d = new MonitoredData(start,end,label);			
			data.add(d);
		}
	}
	public ArrayList<MonitoredData> getMonitoredData()
	{
		Object[] text=citireFisier();
		parsare(text);
		return this.data;
	}
	public void afisare()
	{
		for(MonitoredData md : this.data)
			System.out.println(md.afisare());
	}
	
}
