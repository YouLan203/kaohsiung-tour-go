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
		cities[1] = new City("C1_397000000A_001175", "�����y�歵�֤���(�R�e�W)", "music_center", 120.28732, 22.61781);
		cities[2] = new City("C1_397000000A_000624", "�����歷����", "lianchihtan", 120.2969, 22.67873);
		cities[3] = new City("C1_397000000A_000622", "��G���N�S��", "pier2_art_center", 120.2816, 22.62001);
		cities[4] = new City("C1_397000000A_000575", "�����^�����]��ƶ��", "british_consulate", 120.26684, 22.61891);
		cities[5] = new City("C1_397000000A_000069", "�������߹Ϯ��]�`�]", "main_public_library", 120.30174, 22.61024);
		cities[6] = new City("C1_397000000A_000608", "�q�j�@��", "eda_theme_park", 120.40673, 22.72984);
		cities[7] = new City("C1_397000000A_000655", "�X�z�����D��", "cijin_beach", 120.2669, 22.61018);
		cities[8] = new City("C1_397000000A_000637", "�R�e", "love_river", 120.28904, 22.62483);
		cities[9] = new City("C1_397000000A_001357", "�̶L�w", "kw2", 120.2771, 22.61899);
		cities[10] = new City("C1_397000000A_000204", "���F��(���H�X�Y)", "xing_da_harbor", 120.19391, 22.86843);
		cities[11] = new City("C1_397000000A_000571", "�Τ@�ڮɥN�ʪ�����", "dream_mall", 120.30692, 22.59515);
		cities[12] = new City("C1_397000000A_000565", "�ؤs�ʪ���", "shou_shan_zoo", 120.27519, 22.63443);
		cities[13] = new City("C1_397000000A_000589", "�����P�K�D��ƶ��", "hamasen", 120.27744, 22.62122);
		cities[14] = new City("C1_397000000A_001214", "��l�W�[���x(�[�����x)", "xiziwan", 120.26591, 22.61769);
		cities[15] = new City("C1_397000000A_000592", "�[���s������", "guanyin_mountain", 120.37175, 22.72964);
		cities[16] = new City("C1_397000000A_000595", "���Y�}�t(�x�W�}�~�ժ��])", "ciaotou_sugar_refinery", 120.31433, 22.75568);
		cities[17] = new City("C1_397000000A_001261", "�^�s����", "siaogangshan_skywalk", 120.33432, 22.81199);
		cities[18] = new City("C1_397000000A_000158", "�������߬��N�]", "museum_of_fine_arts", 120.28655, 22.65669);
		cities[19] = new City("C1_397000000A_000246", "�ռq", "confucian_temple", 120.29904, 22.68923);
		cities[20] = new City("C1_397000000A_000219", "��s�s�s�x", "fongshan_longshan_buddhist_temple", 120.36201, 22.62078);
		cities[21] = new City("C1_397000000A_001250", "i-Ride KAOHSIUNG 5D ����@�|", "i_ride", 120.2989, 22.60561);
		cities[22] = new City("C1_397000000A_000031", "�������Ѥ�Ш|�]", "astronomical_museum", 120.33746, 22.568);
		cities[23] = new City("C1_397000000A_000044", "�������J", "arena", 120.30142, 22.66915);
		cities[24] = new City("C1_397000000A_000060", "�ŭx�x�v�]", "air_force_museum", 120.27258, 22.78468);
		cities[25] = new City("C1_397000000A_000187", "���x�x��(�եv�])", "military_academy", 120.36585, 22.61932);
		cities[26] = new City("C1_397000000A_000206", "��M�򭷴���", "chengcing_lake", 120.35377, 22.66039);
		cities[27] = new City("C1_397000000A_000213", "�������߹q�v�]", "film_archive", 120.28878, 22.62244);
		cities[28] = new City("C1_397000000A_000652", "��߬�Ǥu���ժ��]", "national_science_and_technology_museum", 120.32255, 22.64149);
		cities[29] = new City("C1_397000000A_000660", "�x�W�����ժ��]", "fastener_museum", 120.28688, 22.80156);*/
		
		System.out.println("�п�ܱ�������O���X�G�]��J.��ܿ�ܵ����^");
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
			City item = prolog.lookup.get(result.poll());//�ثe����X�Φ�����queue�A�ȼW�[�F����map���o��ƪ���ƶ��G�j�M��key�Ȭ����I���^��W�١A�i�������Ƭ����I������W�١B�^��W�١BID�Bpx�Bpy
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
