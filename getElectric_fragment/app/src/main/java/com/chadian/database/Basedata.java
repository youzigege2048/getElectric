package com.chadian.database;

import java.text.SimpleDateFormat;

public class Basedata {
    public static String name_drxiaoqu[] = {"校区", "本部芙蓉区", "本部石井区", "本部南光区",
            "本部凌云区", "本部勤业区", "本部海滨新区", "本部丰庭区", "漳州校区芙蓉园", "海韵学生公寓",
            "曾厝安学生公寓", "漳州校区博学园", "漳州校区囊萤园", "漳州校区笃行园", "漳州校区映雪园", "漳州校区勤业园",
            "漳州校区若谷园", "漳州校区凌云园", "漳州校区丰庭园", "漳州校区南安园", "漳州校区南光园", "漳州校区嘉庚若谷园",
            "翔安校区芙蓉区", "翔安校区南安区", "翔安校区南光区", "翔安国光区", "翔安丰庭区", "翔安笃行区"};

    public static String val_drxiaoqu[] = {"", "01", "02", "03", "04", "05",
            "06", "07", "26", "08", "09", "21", "22", "23", "24", "25", "27",
            "28", "29", "30", "31", "32", "33", "34", "35", "42", "41", "40"};

    public static String drlou_num[][] = {{"", "1"}, {"", "1"},
            {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"},
            {"", "1"}, {"芙蓉", "5"}, {"", "1"}, {"", "1"},
            {"博学", "3"}, {"囊萤", "3"}, {"笃行", "2"}, {"映雪", "3"},
            {"勤业", "7"}, {"若谷", "1"}, {"凌云", "3"}, {"丰庭", "12"},
            {"南安", "7"}, {"南光", "13"}, {"若谷贰", "若谷叁"}, {"", "1"},
            {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"}, {"", "1"}};

    public static String subUrl = "http://youzi2048.sinaapp.com/chadian/get_info.php";

    public static String getTime() {

        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
        String date = "null";
        date = df.format(System.currentTimeMillis()) + "";
        return date;
    }
}