import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import java.util.HashMap;

//This class uses "aip-java-sdk-4.1.1.jar", "json-20160810.jar", "log4j-1.2.17.jar"
////All three jar files are in folder api
public class SpeechRecognition {
	private static final String APP_ID = "15574015";
	private static final String API_KEY = "qTCKgVwGigXavwwIZ6ATQXIt";
	private static final String SECRET_KEY = "uVtbY7MbShzFQfVF4nwsTY8uBwhlXk4y";

	private static final String MANDARIN_WITH_ENGLISH = "1536";
	private static final String MANDARIN = "1537";
	private static final String ENGLISH = "1737";
	private static final String CANTONESE = "1637";
	private static final String SZECHWAN = "1837";
	private static final String MANDARIN_FAR_FIELD = "1936";

	public static void main(String[] args) {
		AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		//client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
		//client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		HashMap<String, Object> options = new HashMap<>();
		options.put("dev_pid", MANDARIN_WITH_ENGLISH);

		// 调用接口
		JSONObject res = client.asr("./input/speechRecognition.wav", "wav", 16000, options);
		System.out.println(res.toString(2));
	}
}
