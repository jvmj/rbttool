package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Util {

	public static void writeLineOnFile(String dir, String fileName, String line)
			throws Exception {

		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;

		fileWriter = new FileWriter(dir + "\\" + fileName, true);
		bufferWriter = new BufferedWriter(fileWriter);

		bufferWriter.write(line);
		bufferWriter.newLine();
		bufferWriter.flush();
		bufferWriter.close();
		fileWriter.close();
		fileWriter = null;
	}

	
	//Deixei mais generico este metodo para suportar exportaçao de analises de riscos
	public static String generateXMLFileNameYYYYMMDD(String userName) {

		String finalName = "";
		String stringDateMasked = "";
		Date sysdate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		stringDateMasked = formatador.format(sysdate);
		finalName = userName + stringDateMasked + ".xml";
		finalName = finalName.replace(":", "").replace(" ", "_");

		return finalName;
	}
	
	public static String generateRandomID() {
		String radomValue = ((new Random().nextDouble())*1000) +"";
		radomValue = radomValue.substring(0, radomValue.indexOf("."));
		return radomValue;

	}

}
