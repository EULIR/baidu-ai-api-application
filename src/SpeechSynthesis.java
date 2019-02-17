import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//This class uses "aip-java-sdk-4.1.1.jar", "json-20160810.jar", "log4j-1.2.17.jar"
//All three jar files are in folder api
public class SpeechSynthesis {
	private static final String APP_ID = "15574015";
	private static final String API_KEY = "qTCKgVwGigXavwwIZ6ATQXIt";
	private static final String SECRET_KEY = "uVtbY7MbShzFQfVF4nwsTY8uBwhlXk4y";

	public static void main(String[] args) {
		AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		//client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
		//client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		HashMap<String, Object> options = new HashMap<>();
		//tex	String	合成的文本，使用UTF-8编码，
		//cuid	String	用户唯一标识，用来区分用户
		//spd	String	语速，取值0-9，默认为5中语速
		//pit	String	音调，取值0-9，默认为5中语调
		//vol	String	音量，取值0-15，默认为5中音量
		//per	String	发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
		options.put("spd", "5");
		options.put("pit", "5");
		options.put("per", "4");

		String tex = readFile("./input/speechSynthesis.txt");
		if (tex.length() >= 512)
			throw new IllegalArgumentException();

		// 调用接口
		TtsResponse res = client.synthesis(tex, "zh", 1, options);
		byte[] data = res.getData();
		JSONObject res1 = res.getResult();
		if (data != null) {
			try {
				Util.writeBytesToFileSystem(data, "./output/speechSynthesis.mp3");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (res1 != null) {
			System.out.println(res1.toString(2));
		}

	}

	static String readFile(String fileName) {
		File file = new File(fileName);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}