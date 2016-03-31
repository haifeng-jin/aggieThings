package common;

import java.net.HttpURLConnection;
import java.net.URL;

public class PingHttp {
	public static void wait(String url) {
		while (!ping(url)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean ping(String url) {
	    try {
	        HttpURLConnection.setFollowRedirects(false);
	        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
	        con.setConnectTimeout(1000);
	        con.setReadTimeout(1000);
	        con.setRequestMethod("HEAD");
	        System.out.println(con.getResponseCode());
	        return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    } catch (Exception e) {
	    	System.out.printf("ping %s no response.\n", url);
	        return false;
	    }
	}
	
	public static void main(String[] args) {
		wait("http://localhost:8080/config/file");
	}
}
