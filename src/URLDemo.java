/**
 * Description:
 * URL class and convert InputStream into OutputStream
 *
 * @author apktool
 * @version 0.1
 * @since 2017-08-25
 */
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class URLDemo{
	public static void main(String[] args) throws MalformedURLException, IOException{
		URL url = new URL("http://baidu.com/index.html");
		InputStream input = url.openStream();
		System.out.println(input);

		byte[] buff = new byte[1024];
		int len;
		while((len = input.read(buff)) != -1){
			System.out.write(buff, 0, len);
		}
	}
}
