package couk.Adamki11s.Regios.Scheduler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.util.FileUtil;

import couk.Adamki11s.Regios.Regions.Region;

public class LogRunner {

	public static HashMap<Region, ArrayList<String>> log = new HashMap<Region, ArrayList<String>>();

	public static int timer = 0;

	public static void pollLogMessages() {
		timer++;
		if (timer >= 600) {//10 minutes between logs(600)
			try {
				pushLogMessages();
			} catch (IOException e) {
				e.printStackTrace();
			}
			timer = 0;
		}
	}

	private synchronized static void pushLogMessages() throws IOException {
		System.out.println("[Regios] Writing region logs to log files...");
		for (Entry<Region, ArrayList<String>> entry : log.entrySet()) {
			File logFile = entry.getKey().getLogFile();
			if(!logFile.exists()){
				logFile.createNewFile();
			}
			fileWipeCheck(logFile);
			ArrayList<String> messages = log.get(entry.getKey());
			for (String msg : messages) {
				try {
					FileWriter fstream = new FileWriter(logFile, true);
					BufferedWriter fbw = new BufferedWriter(fstream);
					fbw.write(msg);
					fbw.newLine();
					fbw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		System.out.println("[Regios] Log files saved & closed.");
	}
	
	private synchronized static void fileWipeCheck(File f){
		 long filesizeInKB = f.length() / 1024;
		 if(filesizeInKB > (1024 * 5000)){ //5 MB Cap on log files because they are wiped.
			 f.delete();
			 try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	}

	public static String getPrefix(Region r) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "[" + sdf.format(cal.getTime()) + "]" + "[" + r.getName() + "]";
	}

	public static void addLogMessage(Region r, String message) {
		if (log.containsKey(r)) {
			ArrayList<String> tempLog = log.get(r);
			tempLog.add(message);
			log.put(r, tempLog);
		} else {
			ArrayList<String> tempLog = new ArrayList<String>();
			tempLog.add(message);
			log.put(r, tempLog);
		}

	}

}
