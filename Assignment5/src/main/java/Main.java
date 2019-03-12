import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//GHITUN PATRICIA ROXANA - GRUPA 30227
public class Main {

	public static void task1(ArrayList<MonitoredData> data)
	{
		int zile=0;
		zile=(int) data.stream().map(d->d.getZi()).distinct().count();
		System.out.println("Numar de zile distincte : "+zile);
	}
	
	public static HashMap<String,Integer> task2(ArrayList<MonitoredData> data)
	{
		HashMap<String,Integer> activitati = new HashMap<String,Integer>();		
		data.stream().forEach(
				d->{
						activitati.computeIfPresent(d.getActivityLabel(),(k,v)->v=v+1);
						activitati.putIfAbsent(d.getActivityLabel(),1);}
				);			
		return activitati;
	}
	
	public static void scriereTask2(HashMap<String,Integer> activitati) throws IOException
	{
		System.out.println("Scriere in fisier - activitati distincte - task2");
		BufferedWriter w = new BufferedWriter(new FileWriter("task2.txt"));
		w.write("Aparitiile fiecarei activitati din fisier - task2 : ");
		w.newLine();
		activitati.entrySet().stream().forEach(
				e->{
					try {
						w.write(e.getKey()+" "+e.getValue());
						w.newLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				);
		w.close();
	}
	public static Map<Integer, HashMap<String, Integer>> task3(ArrayList<MonitoredData> data)
	{
		Map<Integer, HashMap<String, Integer>> map=new HashMap<Integer,HashMap<String,Integer>>();
		data.stream().forEach(
				d->map.put(d.getZi(), new HashMap<String,Integer>())
				);		
		data.stream().forEach(
				d->{
					map.get(d.getZi()).computeIfPresent(d.getActivityLabel(),(k,v)->v=v+1);
					map.get(d.getZi()).putIfAbsent(d.getActivityLabel(),1);}
				);
		return map;		
	}
	public static void scriereTask3(Map<Integer,HashMap<String,Integer>> activitati) throws IOException
	{
		System.out.println("Scriere in fisier - activitati distincte pe zile - task3");
		BufferedWriter w = new BufferedWriter(new FileWriter("task3.txt"));		
		w.write("Aparitiile fiecarei activitati pe zile - task3 : ");
		w.newLine();
		activitati.entrySet().stream().forEach(
				e->
					{	try {
						w.write("Ziua : 	"+ e.getKey()+"	");
						e.getValue().entrySet().stream().forEach(
								e2 -> {
										try {
											w.write(e2.getKey()+"  "+e2.getValue()+" ");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								);
						w.newLine();
						} catch (IOException e1) {
							e1.printStackTrace();
						}				
				}
				);
		w.close();
	}
	public static Map<String,Long> task4(ArrayList<MonitoredData> data)
	{
		Map<String, Long> map=new HashMap<String,Long>();
		map=data.stream().collect(Collectors.groupingBy(MonitoredData::getActivityLabel,Collectors.summingLong(MonitoredData::getDurata)));
		//map=data.stream().collect(Collectors.groupingBy(
		//		e->((MonitoredData)e).getActivityLabel() , 
		//		Collectors.summingLong(
		//		e->((MonitoredData)e).getDurata())
		//		));
		return map;
	}
	public static void scriereTask4(Map<String,Long> map) throws IOException
	{
		System.out.println("Scriere in fisier - task 4");
		BufferedWriter writer = new BufferedWriter(new FileWriter("task4.txt"));		
		writer.write("Task 4 : ");
		writer.newLine();
		map.entrySet().stream().filter(e->e.getValue()/3600000>10).forEach(
				e->{
						try {
							writer.write(e.getKey()+" "+e.getValue()/3600000+" ore");
							writer.newLine();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					});
		map.entrySet().stream().forEach(e-> System.out.println(e.getKey()+" "+e.getValue()));
		writer.close();
	}
	public static List<String> task5(ArrayList<MonitoredData> data)
	{
		List<String> lista = new ArrayList<String>();
		Map<String,Integer> aparitii5 = new HashMap<String,Integer>();
		Map<String,Integer> aparitiiTotal = task2(data); 		
		data.stream()
				.filter(d-> d.getDurata()<300000)
					.forEach(d->
						{
							aparitii5.computeIfPresent(d.getActivityLabel(), (k,v)->v=v+1);
							aparitii5.putIfAbsent(d.getActivityLabel(),1);
						});				
		System.out.println("Aparitii activitati < 5 minute");
		aparitii5.entrySet().stream().forEach(entry->System.out.println(entry.getKey()+" " +entry.getValue()));
		aparitii5.entrySet()
			.stream().filter(e->e.getValue()>=aparitiiTotal.get(e.getKey())*90/100).forEach(e->lista.add(e.getKey()));		
		return lista;		
	}
	public static void scriereTask5(List<String> lista) throws IOException
	{
		System.out.println("Scriere in fisier - task 5");
		BufferedWriter w = new BufferedWriter(new FileWriter("task5.txt"));		
		w.write("Task 5 : ");
		w.newLine();
		lista.stream().forEach
			(
					e->{
						try {
							w.write("Activitatea : "+e);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			);
		w.close();
	}
	public static void main(String []args)
	{
		Data d = new Data();
		ArrayList<MonitoredData> activitati= d.getMonitoredData();	
		//TASK 1
		task1(activitati);
	 	//TASK 2		 
		HashMap<String,Integer> activitatiTask2 = task2(activitati);
		try {
			scriereTask2(activitatiTask2);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		//TASK 3
		Map<Integer, HashMap<String, Integer>> activitatiTask3 = task3(activitati);
		try {
			scriereTask3(activitatiTask3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TASK 4
		Map<String,Long> activitatiTask4 = task4(activitati);
		try {
			scriereTask4(activitatiTask4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TASK 5
		List<String> activitatiTask5 = task5(activitati);
		try {
			scriereTask5(activitatiTask5);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
