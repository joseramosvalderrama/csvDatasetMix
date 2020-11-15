import java.io.*;
import java.util.*;

/*
 * It mixes all CSV files in its folder and puts the rows in a new 
 * file. The new file contains the "Tipo" attribute as a nominal not 
 * a number. The number of rows of the new file is determined by a parameter
 * contained in args. If there is no arguments, the new file will contain
 * 1000 rows by default. The new file will be located in ~/setExperiment/,
 * ~ is the path of this code file.
 */

public class mixingAllNom {
	
	public static final String separator = ",";
	public static int numberofRowsDefault= 1000;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int numberofRows= getNumberofRows(args);
		String pathNewFile = createPathNewFile(numberofRows);
		
		PrintWriter output= new PrintWriter(new BufferedWriter(new FileWriter(pathNewFile)));
		BufferedReader[] inputs = getBuffereds().toArray(new BufferedReader[0]);
		String header;
		String [] lines;
		int id=1;
		
		header= getHeader(inputs);
		output.println(header);
		while((lines=readLines(inputs)) != null && id<=numberofRows) {
			for(int i=0;i<lines.length;i++) {
				if(id > numberofRows)
					break;
				writeLine(id++,lines[i],output);
			}
		}
		output.close();
		closeInputs(inputs);
	}
	
	private static String createPathNewFile(int numberofRows) {	
		File file = new File("./setExperiment");
		file.mkdir();
		
		return "setExperiment/mixNom"+numberofRows+".csv";
	}
	
	private static int getNumberofRows(String[] args) {
		int resul= numberofRowsDefault;
		if(args.length > 0) {
			try {
				resul = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe){
				System.out.println("El argumento no es un numero.");
			}
		}
		return resul;
	}
	
	private static void closeInputs(BufferedReader[] inputs)throws IOException {
		for(BufferedReader in:inputs) {
			in.close();
		}
	}
	
	private static String[] readLines(BufferedReader[] inputs) throws IOException{
		String[] resul=new String[inputs.length];
		for(int i=0;i<inputs.length;i++) {
			resul[i]= inputs[i].readLine();
			if(resul[i]==null)
				return null;
		}
		return resul;
	}
	
	private static String getHeader(BufferedReader[] readers) throws IOException{
		String header= readers[0].readLine();
		for(int i=1;i<readers.length;i++) {
			readers[i].readLine();
		}
		return header;
	}
	
	private static List<BufferedReader> getBuffereds(){
		File[] filesThisPWD= new File(".").listFiles();
		List<BufferedReader> resul= new ArrayList<BufferedReader>();
		for(int i=0; i<filesThisPWD.length;i++) {
			if(filesThisPWD[i].getName().contains(".csv")) {
				try {
					resul.add(new BufferedReader(new FileReader(filesThisPWD[i].getName())));
				}
				catch(IOException e) {
					System.out.println("Error ");
				}
			}
		}
		
		return resul;
	}
	
	
	private static void writeLine(int id,String line,PrintWriter output) {
		String[] fields= line.split(separator);
		String[] tipo= fields[fields.length-1].split("\"");
		
		output.print("\""+id+"\""+separator);
		for(int i=1;i<(fields.length-1);i++) {
			output.print(fields[i]+separator);
		}
		//output.print(fields[fields.length-1]+"\n");
		output.print("\""+getTipoName(Integer.parseInt(tipo[1]))+"\""+"\n");
	}
	
	private static String getTipoName(int tipo) {
		String resul;
		switch(tipo) {
			case 1: resul="gasoleoA";
			break;
			case 2: resul="gasoleoAAditivos";
			break;
			case 3: resul="gasoleoB";
			break;
			case 4: resul="gasoleoC";
			break;
			case 5: resul="gasolina95";
			break;
			case 6: resul="gasolina98";
			break;
			case 7: resul="dieselA";
			break;
			default: resul="error";
		}
		return resul;
	}


}
