package com.linxu.provider;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author linxu
 * @date 2019/7/11
 */
public class RandomCreateData {
    private static final String YEAR = "2019-05-";

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\SAMSUNG\\Desktop\\topup.txt");
        FileOutputStream os = new FileOutputStream(file);
        try {
            for (int i = 0; i < 10000000; i++) {
                StringBuilder sb = new StringBuilder(getRandomName());
                sb.append(",");
                sb.append(getRandomDate());
                sb.append(",");
                sb.append(getRandomMonye() + "\r\n");
                String line = sb.toString();
                os.write(line.getBytes());
            }
        } finally {
            os.close();
        }
       /* String originalLine = "25,2019-05-30 00:00:00,77.04676514856746";
        int firstIdx = originalLine.indexOf(",");
        System.err.println(firstIdx);
        String date = originalLine.substring(23);
        System.err.println(date);*/
    }

    //用户的范围为0到999
    private static String getRandomName() {
        while (true) {
            double d = Math.random() * 100;
            if (d >= 10) {
                int name = Integer.valueOf((int) d + "");
                return name + "";
            }
        }
    }

    private static String getRandomDate() {
        while (true) {
            double day = Math.random() * 100;
            if (day > 9 && day <= 31) {
                return YEAR + (int) day + " 00:00:00";
            }
        }
    }

    private static String getRandomMonye() {
        return Math.random() * 1000 + "";
    }
}
