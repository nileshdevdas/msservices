package com;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadTest {

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(1000);
		for (int b = 0; b < 100; b++) {

			for (int i = 0; i < 1000; i++) {
				service.execute(new Runnable() {
					@Override
					public void run() {
						try {
							URL url = new URL("http://localhost:7001/stock");
							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
							InputStream is = conn.getInputStream();
							byte data[] = new byte[is.available()];
							is.read(data);
							System.out.println(data);
							is.close();
							conn.disconnect();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}
}
