package exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class DirCopyMain {

	String src, dst;

	HashMap<String, String> map;

	void directoryGO(File files) {

		try {
			for (File ff : files.listFiles()) {

				if (ff.isDirectory())
					directoryGO(ff);
				else {

					String path = "etc";
					for (Entry<String, String> en : map.entrySet()) {
						if (Pattern.matches(en.getKey(), ff.getName().toLowerCase())) {
							path = en.getValue();
							break;
						}
					}

					if (path.equals("lyrics")) {

						FileReader fr = new FileReader(ff);
						BufferedReader br = new BufferedReader(fr);
						br.readLine();
						path += "/" + br.readLine();
						br.readLine();

						if (br.readLine().equals("한국"))
							path += "/국내";
						else
							path += "/국외";

						br.close();
						fr.close();
					}
					path = dst + "/" + path;
					// System.out.println(ff.getName()+":"+path);

					copyFile(path, ff);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void copyFile(String path, File ori) {
		try {
			File ddd = new File(path);
			if (!ddd.exists())
				ddd.mkdirs();
			path += "/";

			String ff = ori.getName();

			int pos = ff.lastIndexOf(".");
			String domain = ff.substring(0, pos);
			String ext = ff.substring(pos);

			File newFile = new File(path + ff);
			int i = 0;
			while (newFile.exists()) {
				i++;
				newFile = new File(path + domain + "_" + i + ext);

			}
			System.out.println(newFile.getPath());

			FileInputStream fis = new FileInputStream(ori);
			FileOutputStream fos = new FileOutputStream(newFile);
			byte[] buf = new byte[1024];

			while (fis.available() > 0) {
				int len = fis.read(buf);
				fos.write(buf, 0, len);
			}

			fos.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DirCopyMain(String src, String dst) {
		super();
		map = new HashMap<String, String>();
		map.put(".*[.](bmp|jpg|gif|png)", "image");
		map.put(".*[.](mp3|wma|wav)", "music");
		map.put(".*[.](doc|hwp|ppt|xls|pptx|xlsx|docx)", "doc");
		map.put(".*[.]txt", "lyrics");

		// [.] why ? 이름 중간에 . 이 들어 올 수 있고
		// . 이 몇번 들어 올지 모르기 때문에....

		this.src = src;
		this.dst = dst;

		directoryGO(new File(src));
	}

	public static void main(String[] args) {
		new DirCopyMain("abcd", "zxcv");
	}

}
