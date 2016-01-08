package org.hannes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * junk
 * 
 */
public class App {

	public static void main(String[] args) {
		System.out.println(getHomeReplayM3U8("2015020603"));
//		search(1);
	}
	
	public static String getHomeReplayM3U8(String gid) {
		return getText("http://smb.cdnak.neulion.com/fs/nhl/mobile/feed_new/data/streams/"
				+ getSeason("20160107").substring(0, 4) + "/ipad/" + gid.substring(4, 6) + "_" + gid.substring(6) + ".json");
    }
	
    private static String search(int gameType) {

        String jsonText;
        int r;
        if (gameType == 1) {
            jsonText = getText("http://live.nhl.com/GameData/SeasonSchedule-" + getSeason("20160107") + ".json");
            if (jsonText.equals("offline")) {
                return "offline";
            }
            return jsonText;
        } else if (gameType == 0) {
            jsonText = getText("http://live.nhl.com/GameData/Scoreboard.json");
            if (jsonText.equals("offline")) {
                return "offline";
            }
            r = jsonText.indexOf(",\"refreshInterval\":");
            jsonText = jsonText.substring(9, r);
            return jsonText;
        } else {
            return "null";
        }
    }

	
    private static String getSeason(String date) {
        String year, month;
        if (!date.contains("/")) {
            month = date.substring(4, 6);
            year = date.substring(0, 4);
        } else {
            year = date.substring(6, date.length());
            month = date.substring(0, 2);
        }

        int y = Integer.parseInt(year), m = Integer.parseInt(month);

        if (m >= 1 && m <= 8) {
            y--;
        }
        m = y++;
        return "" + m + y;
    }
    
	private static String getText(String url) {
		System.out.println(url);
        Reader r = null;
        try {
            URL website;
            try {
                website = new URL(url);
            } catch (MalformedURLException j) {
                return "down";
            }
            URLConnection con = website.openConnection();
            Pattern p = Pattern.compile("text/json");
            Matcher m = p.matcher(con.getContentType());
            /* If Content-Type doesn't match this pre-conception, choose default and 
             * hope for the best. */
            String charset = m.matches() ? m.group(1) : "ISO-8859-1";
            r = new InputStreamReader(con.getInputStream(), charset);
            StringBuilder buf = new StringBuilder();
            while (true) {
                int ch = r.read();
                if (ch < 0) {
                    break;
                }
                buf.append((char) ch);
            }
            m = null;
            p = null;
            con = null;
            r.close();
            r = null;
            return buf.toString();
        } catch (IOException ex) {
            System.err.println(ex);
            return "";
        } catch (NullPointerException n) {
            System.err.println(n);
            return "offline";
        } finally {
            try {
                if (r != null) {
                    r.close();
                    r = null;
                }
            } catch (IOException ex) {

            }
        }
    }


}