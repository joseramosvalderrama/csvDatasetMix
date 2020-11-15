import java.io.*;

/*
 * It mixes all the files of data which are in the same folder
 * given the name by argument. The new file will have a
 * maximum of 1000 rows.
 */

public class Mixing {
	
	public static final String separator = ",";
	public static final int numberofRows= 1000;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		if(args == null) {
			System.out.println("No arguments");
			return;
		}
		
		PrintWriter output= new PrintWriter(new BufferedWriter(new FileWriter(getMixName(args))));
		BufferedReader[] inputs = getBuffereds(args);
		String header;
		String [] lines;
		int id=1;
		
		header= getHeader(inputs);
		output.println(header);
		while((lines=readLines(inputs)) != null && id<numberofRows) {
			for(int i=0;i<lines.length;i++) {
				if(id > numberofRows)
					break;
				writeLine(id++,lines[i],output);
			}
				
		}
		output.close();
		closeInputs(inputs);
	}
	
	private static String getMixName(String[] args) {
		String resul="";
		for(String name: args) {
			String [] separated= name.split("\\.");
			resul+= separated[0];
		}
		resul+=".csv";
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
	
	private static BufferedReader[] getBuffereds(String[] args){
		BufferedReader[] result = new BufferedReader[args.length];
		for(int i=0;i<args.length;i++) {
			try {
				result[i]=new BufferedReader(new FileReader(args[i]));
			}catch(IOException e) {
				System.out.println("No existe el fichero "+args[i]);
			}
		}
		return result;
	}
	
	private static void writeLine(int id,String line,PrintWriter output) {
		String[] fields= line.split(separator);
		
		output.print("\""+id+"\""+separator);
		for(int i=1;i<(fields.length-1);i++) {
			output.print(fields[i]+separator);
		}
		output.print(fields[fields.length-1]+"\n");
	}

}
