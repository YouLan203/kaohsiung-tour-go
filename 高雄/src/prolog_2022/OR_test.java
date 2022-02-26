package prolog_2022;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import org.jpl7.Query;
import org.jpl7.Term;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OR_test { //輸出調整：以Scenery作為集合內容輸出
	public static void main(String[] args) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("fact_file.pl"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//建立類別選項陣列
		String[] conditionDataOfEnglish = new String[] { "shopping", "technology", "nature", "literature_art", "animal", "cultural_history", "battlefield",	"taking_picture"};
		System.out.println("請選擇欲選取類別號碼：（輸入.表示選擇結束）");
		System.out.println("1. shopping\n2. technology\n3. nature\n4. literature_art\n5. animal\n6. cultural_history\n7. battlefield\n8. taking_picture");
		Scanner in = new Scanner(System.in);
		String choice = in.nextLine();
		ArrayList<Integer> choiceList = new ArrayList<Integer>();
		while(!choice.equals(".")) {
			choiceList.add(Integer.parseInt(choice));
			choice = in.nextLine();
		}
		//根據選項選取與否更改fact檔案
		String[] syntaxArr = conditionDataOfEnglish;
		String syntax = "";
		for (int i=0; i<syntaxArr.length; i++) {
			syntax = "select(" + syntaxArr[i]+") :- ";
			if (choiceList.contains(i+1))
				syntax += " true.";
			else
				syntax += " false.";
			System.out.println(syntax);
			writer.println(syntax);
			writer.flush();
		}
		writer.close();
		System.out.println("done.");
		
		String connectCommend = "consult('" + "fact_file.pl" + "')";
		String connectCommend2 = "consult('" + "rule_OR.pl" + "')";
		try {
			Query q1 = new Query(connectCommend);
			Query q2 = new Query(connectCommend2);
			System.out.println("facts connected?" + (q1.hasSolution() ? " success" : " fail"));
			System.out.println("rules connected?" + (q2.hasSolution() ? " success" : " fail"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String command = "spots(S).";
		HashSet<String> set = new HashSet<String>();
		Query q = new Query(command);
		while (q.hasMoreSolutions()) {
			Map<String, Term> map = q.nextSolution();
			Term tmp = map.get("S");
			set.add(tmp+"");
		}
		
		Map<String, Scenery> lookup = new HashMap<String, Scenery>();
		lookup.put("skm_park", new Scenery("SKM Park", "skm_park", "C1_397000000A_000921", 120.32933, 22.58259));
		lookup.put("music_center", new Scenery("高雄流行音樂中心(愛河灣)", "music_center", "C1_397000000A_001175", 120.28732, 22.61781));
		lookup.put("lianchihtan", new Scenery("蓮池潭風景區", "lianchihtan", "C1_397000000A_000624", 120.2969, 22.67873));
		lookup.put("pier2_art_center", new Scenery("駁二藝術特區", "pier2_art_center", "C1_397000000A_000622", 120.2816, 22.62001));
		lookup.put("british_consulate", new Scenery("打狗英國領事館文化園區", "british_consulate", "C1_397000000A_000575", 120.26684, 22.61891));
		lookup.put("main_public_library", new Scenery("高雄市立圖書館總館", "main_public_library", "C1_397000000A_000069", 120.30174, 22.61024));
		lookup.put("eda_theme_park", new Scenery("義大世界", "eda_theme_park", "C1_397000000A_000608", 120.40673, 22.72984));
		lookup.put("cijin_beach", new Scenery("旗津海水浴場", "cijin_beach", "C1_397000000A_000655", 120.2669, 22.61018));
		lookup.put("love_river", new Scenery("愛河", "love_river", "C1_397000000A_000637", 120.28904, 22.62483));
		lookup.put("kw2", new Scenery("棧貳庫", "kw2", "C1_397000000A_001357", 120.2771, 22.61899));
		lookup.put("xing_da_harbor", new Scenery("興達港(情人碼頭)", "xing_da_harbor", "C1_397000000A_000204", 120.19391, 22.86843));
		lookup.put("dream_mall", new Scenery("統一夢時代購物中心", "dream_mall", "C1_397000000A_000571", 120.30692, 22.59515));
		lookup.put("shou_shan_zoo", new Scenery("壽山動物園", "shou_shan_zoo", "C1_397000000A_000565", 120.27519, 22.63443));
		lookup.put("hamasen", new Scenery("哈瑪星鐵道文化園區", "hamasen", "C1_397000000A_000589", 120.27744, 22.62122));
		lookup.put("xiziwan", new Scenery("西子灣觀景台(觀海平台)", "xiziwan", "C1_397000000A_001214", 120.26591, 22.61769));
		lookup.put("guanyin_mountain", new Scenery("觀音山風景區", "guanyin_mountain", "C1_397000000A_000592", 120.37175, 22.72964));
		lookup.put("ciaotou_sugar_refinery", new Scenery("橋頭糖廠(台灣糖業博物館)", "ciaotou_sugar_refinery", "C1_397000000A_000595", 120.31433, 22.75568));
		lookup.put("siaogangshan_skywalk", new Scenery("崗山之眼", "siaogangshan_skywalk", "C1_397000000A_001261", 120.33432, 22.81199));
		lookup.put("museum_of_fine_arts", new Scenery("高雄市立美術館", "museum_of_fine_arts", "C1_397000000A_000158", 120.28655, 22.65669));
		lookup.put("confucian_temple", new Scenery("孔廟", "confucian_temple", "C1_397000000A_000246", 120.29904, 22.68923));
		lookup.put("fongshan_longshan_buddhist_temple", new Scenery("鳳山龍山寺", "fongshan_longshan_buddhist_temple", "C1_397000000A_000219", 120.36201, 22.62078));
		lookup.put("i_ride", new Scenery("i-Ride KAOHSIUNG 5D 飛行劇院", "i_ride", "C1_397000000A_001250", 120.2989, 22.60561));
		lookup.put("astronomical_museum", new Scenery("高雄市天文教育館", "astronomical_museum", "C1_397000000A_000031", 120.33746, 22.568));
		lookup.put("arena", new Scenery("高雄巨蛋", "arena", "C1_397000000A_000044", 120.30142, 22.66915));
		lookup.put("air_force_museum", new Scenery("空軍軍史館", "air_force_museum", "C1_397000000A_000060", 120.27258, 22.78468));
		lookup.put("military_academy", new Scenery("陸軍官校(校史館)", "military_academy", "C1_397000000A_000187", 120.36585, 22.61932));
		lookup.put("chengcing_lake", new Scenery("澄清湖風景區", "chengcing_lake", "C1_397000000A_000206", 120.35377, 22.66039));
		lookup.put("film_archive", new Scenery("高雄市立電影館", "film_archive", "C1_397000000A_000213", 120.28878, 22.62244));
		lookup.put("national_science_and_technology_museum", new Scenery("國立科學工藝博物館", "national_science_and_technology_museum", "C1_397000000A_000652", 120.32255, 22.64149));
		lookup.put("fastener_museum", new Scenery("台灣螺絲博物館", "fastener_museum", "C1_397000000A_000660", 120.28688, 22.80156));
		
		for (String spot : set) {
			Scenery item = lookup.get(spot);
			System.out.println(item.chinese_Name);
		}
	}
}
