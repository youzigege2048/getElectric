package com.getelectric_pro.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Basedata {
	public static String name_drxiaoqu[] = { "У��", "����ܽ����", "����ʯ����", "�����Ϲ���",
			"����������", "������ҵ��", "������������", "������ͥ��", "����У��ܽ��԰", "����ѧ����Ԣ",
			"���Ȱ�ѧ����Ԣ", "����У����ѧ԰", "����У����ө԰", "����У������԰", "����У��ӳѩ԰", "����У����ҵ԰",
			"����У������԰", "����У������԰", "����У����ͥ԰", "����У���ϰ�԰", "����У���Ϲ�԰", "����У���θ�����԰",
			"�谲У��ܽ����", "�谲У���ϰ���", "�谲У���Ϲ���", "�谲������", "�谲��ͥ��", "�谲������" };

	public static String val_drxiaoqu[] = { "", "01", "02", "03", "04", "05",
			"06", "07", "26", "08", "09", "21", "22", "23", "24", "25", "27",
			"28", "29", "30", "31", "32", "33", "34", "35", "42", "41", "40" };

	public static String drlou_num[][] = { { "", "1" }, { "", "1" },
			{ "", "1" }, { "", "1" }, { "", "1" }, { "", "1" }, { "", "1" },
			{ "", "1" }, { "ܽ��", "5" }, { "", "1" }, { "", "1" },
			{ "��ѧ", "3" }, { "��ө", "3" }, { "����", "2" }, { "ӳѩ", "3" },
			{ "��ҵ", "7" }, { "����", "1" }, { "����", "3" }, { "��ͥ", "12" },
			{ "�ϰ�", "7" }, { "�Ϲ�", "13" }, { "���ȷ�", "������" }, { "", "1" },
			{ "", "1" }, { "", "1" }, { "", "1" }, { "", "1" }, { "", "1" } };
	
	public static String subUrl = "http://youzi2048.sinaapp.com/chadian/get_info.php"; 
	
	public static String getTime(){

		SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
		String date = "null";
		date = df.format(System.currentTimeMillis())+"";
		return date;
	}
}
