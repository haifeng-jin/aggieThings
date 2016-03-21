package common;

public class FileGetter {
	public static String getContent(String path) {
		String content = "";
		FileLineGetter lineGetter = new FileLineGetter(path);
		while (lineGetter.hasNext()) {
			content += lineGetter.nextLine() + "\n";
		}
		return content;
	}
}