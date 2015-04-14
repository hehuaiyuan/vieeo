import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.UUID;


public class GenMD5OriString {

	public static void main(String[] args) throws Exception {
		/*String path = GenMD5OriString.class.getResource("/").getPath();
		File file = new File(path+"config.properties");
		LineNumberReader reader= new LineNumberReader(new FileReader(file));
		String value = reader.readLine();
		StringBuilder sb = new StringBuilder();
		while(value != null && !"".equals(value)){
			String[] val = value.split("=");
			if(val.length >1 && val[1] != null && !"".equals(val[1])){
				sb.append(val[1]);
			}
			value = reader.readLine();
		}
		System.out.println("原文:"+sb.toString());*/
		System.out.println("CS0001".matches("^[cs|CS].*"));
	}

}
