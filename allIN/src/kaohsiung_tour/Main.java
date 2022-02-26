package kaohsiung_tour;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		/*City[] cities = new City[30];
		cities[0] = new City("C1_397000000A_000921", "SKM Park", "skm_park", 120.32933, 22.58259);
		cities[1] = new City("C1_397000000A_001175", "高雄流行音樂中心(愛河灣)", "music_center", 120.28732, 22.61781);
		cities[2] = new City("C1_397000000A_000624", "蓮池潭風景區", "lianchihtan", 120.2969, 22.67873);
		cities[3] = new City("C1_397000000A_000622", "駁二藝術特區", "pier2_art_center", 120.2816, 22.62001);
		cities[4] = new City("C1_397000000A_000575", "打狗英國領事館文化園區", "british_consulate", 120.26684, 22.61891);
		cities[5] = new City("C1_397000000A_000069", "高雄市立圖書館總館", "main_public_library", 120.30174, 22.61024);
		cities[6] = new City("C1_397000000A_000608", "義大世界", "eda_theme_park", 120.40673, 22.72984);
		cities[7] = new City("C1_397000000A_000655", "旗津海水浴場", "cijin_beach", 120.2669, 22.61018);
		cities[8] = new City("C1_397000000A_000637", "愛河", "love_river", 120.28904, 22.62483);
		cities[9] = new City("C1_397000000A_001357", "棧貳庫", "kw2", 120.2771, 22.61899);
		cities[10] = new City("C1_397000000A_000204", "興達港(情人碼頭)", "xing_da_harbor", 120.19391, 22.86843);
		cities[11] = new City("C1_397000000A_000571", "統一夢時代購物中心", "dream_mall", 120.30692, 22.59515);
		cities[12] = new City("C1_397000000A_000565", "壽山動物園", "shou_shan_zoo", 120.27519, 22.63443);
		cities[13] = new City("C1_397000000A_000589", "哈瑪星鐵道文化園區", "hamasen", 120.27744, 22.62122);
		cities[14] = new City("C1_397000000A_001214", "西子灣觀景台(觀海平台)", "xiziwan", 120.26591, 22.61769);
		cities[15] = new City("C1_397000000A_000592", "觀音山風景區", "guanyin_mountain", 120.37175, 22.72964);
		cities[16] = new City("C1_397000000A_000595", "橋頭糖廠(台灣糖業博物館)", "ciaotou_sugar_refinery", 120.31433, 22.75568);
		cities[17] = new City("C1_397000000A_001261", "崗山之眼", "siaogangshan_skywalk", 120.33432, 22.81199);
		cities[18] = new City("C1_397000000A_000158", "高雄市立美術館", "museum_of_fine_arts", 120.28655, 22.65669);
		cities[19] = new City("C1_397000000A_000246", "孔廟", "confucian_temple", 120.29904, 22.68923);
		cities[20] = new City("C1_397000000A_000219", "鳳山龍山寺", "fongshan_longshan_buddhist_temple", 120.36201, 22.62078);
		cities[21] = new City("C1_397000000A_001250", "i-Ride KAOHSIUNG 5D 飛行劇院", "i_ride", 120.2989, 22.60561);
		cities[22] = new City("C1_397000000A_000031", "高雄市天文教育館", "astronomical_museum", 120.33746, 22.568);
		cities[23] = new City("C1_397000000A_000044", "高雄巨蛋", "arena", 120.30142, 22.66915);
		cities[24] = new City("C1_397000000A_000060", "空軍軍史館", "air_force_museum", 120.27258, 22.78468);
		cities[25] = new City("C1_397000000A_000187", "陸軍官校(校史館)", "military_academy", 120.36585, 22.61932);
		cities[26] = new City("C1_397000000A_000206", "澄清湖風景區", "chengcing_lake", 120.35377, 22.66039);
		cities[27] = new City("C1_397000000A_000213", "高雄市立電影館", "film_archive", 120.28878, 22.62244);
		cities[28] = new City("C1_397000000A_000652", "國立科學工藝博物館", "national_science_and_technology_museum", 120.32255, 22.64149);
		cities[29] = new City("C1_397000000A_000660", "台灣螺絲博物館", "fastener_museum", 120.28688, 22.80156);*/
		
		System.out.println("請選擇欲選取類別號碼：（輸入.表示選擇結束）");
		System.out.println("1. shopping\n2. technology\n3. nature\n4. literature_art\n5. animal\n6. cultural_history\n7. battlefield\n8. taking_picture");
		Scanner in = new Scanner(System.in);
		String choice = in.nextLine();
		
		prolog prolog = new prolog();
		while(!choice.equals(".")) {
			prolog.choiceList.add(Integer.parseInt(choice));
			choice = in.nextLine();
		}
		prolog.editFact();
		
		Queue<String> result = new LinkedList<String>();
		result = prolog.search();
		int resultNum = result.size();
		
		City[] cities = new City[resultNum];
		for (int i = 0; i < resultNum; i++) {
			City item = prolog.lookup.get(result.poll());//目前的輸出形式仍為queue，僅增加了對應map取得資料的資料集：搜尋的key值為景點之英文名稱，可獲取之資料為景點的中文名稱、英文名稱、ID、px、py
			cities[i] = item;
		}
		
		ACO aco = new ACO(cities);
		aco.execute();
		
		String[] Ids = new String[cities.length];
		for(int i = 0; i < cities.length; i++) {
			int index = aco.bestTourOrder[i];
			Ids[i] = cities[index].chin_name;
		}
		
		writeDataFile(String.join(",", Ids));
	}
	
	private static void writeDataFile(String text) {
		try {
			File file = new File("output.csv");
			FileWriter fw = new FileWriter(file);
			fw.write(text);
			fw.close();
		}catch (IOException e) {
			e.printStackTrace();
    	}
	}
}
