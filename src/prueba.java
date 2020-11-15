import java.io.*;
public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("./setExperiment");
		if(file.mkdir()) {
			System.out.println("Dir created");
		}
	}

}
