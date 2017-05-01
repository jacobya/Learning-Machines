package mine;


import java.io.*;


public class FileOut {
  File file;
	 PrintStream fout;
	BufferedReader fin;
	//PrintStream out;
	public FileOut(String filename) {
		try {
			fout = new PrintStream(new FileOutputStream(filename));
//			filename = "C:\\" + filename;
//			 file = new File(filename);
//			file.createNewFile();
			//out = new PrintStream(new FileOutputStream(filename));
		} catch (IOException fo) {
			System.out.println(fo);
		}
	}
public  void delete()
{
	file.delete();
}
	FileOut(String filename, String in) {
		try {
			fin = new BufferedReader(new FileReader(filename));

		} catch (IOException fo) {
			System.out.println(fo);
		}
	}

	public void writer(int a[]) {
		for (int i = 0; i < a.length; i++) {
			fout.print(a[i]);
		}
		fout.println();
	}

	public  void writer(int a[][]) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[i].length; k++) {
				//System.out.print(a[i][k]+" ");
				fout.print(a[i][k]+" ");
			}
			fout.println();
			//System.out.println();
		}
		//fout.println();
	}

	public void writer(String a[][]) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[i].length; k++) {
				fout.print(a[i][k]+" ");
			}
			fout.println();
		}
		fout.println();
	}

	public  void writer(String out) {

		fout.print(out);

	}
	public  void writerln(String out) {

		fout.println(out);

	}
	public  void writer(int out) {

		fout.println(out);

	}

	public void writer(char out) {

		fout.println(out);

	}

	public  void writer(double out) {

		fout.println(out);

	}

	public void writer(float out) {

		fout.println(out);

	}

	public String reader() {
		try {
			return fin.readLine();
		} catch (IOException e) {
			System.out.println("error reading from file " + e);
			return "error\t";
		}
	}

	public  void writerln(String type, String token, int line, int place) {
		fout.println(type + " " + token + " " + line + " " + place);
		
	}
}
