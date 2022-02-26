package kaohsiung_tour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jpl7.Query;
import org.jpl7.Term;

public class prolog {
	String[] conditionDataOfEnglish;
	ArrayList<Integer> choiceList = new ArrayList<Integer>();
	Set<String> sites;
	HashSet<String> andSet = new HashSet<String>();
	HashSet<String> orSet = new HashSet<String>();
	Map<String, City> lookup;
	
	public prolog() {
		this.conditionDataOfEnglish = new String[] { "shopping", "technology", "nature", "literature_art", "animal", "cultural_history", "battlefield",	"taking_picture"};
		this.sites = new HashSet<String>();
		sites.add("skm_park");
		sites.add("music_center");
		sites.add("lianchihtan");
		sites.add("pier2_art_center");
		sites.add("british_consulate");
		sites.add("main_public_library");
		sites.add("eda_theme_park");
		sites.add("cijin_beach");
		sites.add("love_river");
		sites.add("kw2");
		sites.add("xing_da_harbor");
		sites.add("dream_mall");
		sites.add("shou_shan_zoo");
		sites.add("hamasen");
		sites.add("xiziwan");
		sites.add("guanyin_mountain");
		sites.add("ciaotou_sugar_refinery");
		sites.add("siaogangshan_skywalk");
		sites.add("museum_of_fine_arts");
		sites.add("confucian_temple");
		sites.add("fongshan_longshan_buddhist_temple");
		sites.add("i_ride");
		sites.add("astronomical_museum");
		sites.add("arena");
		sites.add("air_force_museum");
		sites.add("military_academy");
		sites.add("chengcing_lake");
		sites.add("film_archive");
		sites.add("national_science_and_technology_museum");
		sites.add("fastener_museum");
		
		this.lookup = new HashMap<String, City>();
		lookup.put("skm_park", new City("C1_397000000A_000921", "SKM Park", "skm_park", 120.32933, 22.58259));
		lookup.put("music_center", new City("C1_397000000A_001175", "�����y�歵�֤���(�R�e�W)", "music_center", 120.28732, 22.61781));
		lookup.put("lianchihtan", new City("C1_397000000A_000624", "�����歷����", "lianchihtan", 120.2969, 22.67873));
		lookup.put("pier2_art_center", new City("C1_397000000A_000622", "��G���N�S��", "pier2_art_center", 120.2816, 22.62001));
		lookup.put("british_consulate", new City("C1_397000000A_000575", "�����^�����]��ƶ��", "british_consulate", 120.26684, 22.61891));
		lookup.put("main_public_library", new City("C1_397000000A_000069", "�������߹Ϯ��]�`�]", "main_public_library", 120.30174, 22.61024));
		lookup.put("eda_theme_park", new City("C1_397000000A_000608", "�q�j�@��", "eda_theme_park", 120.40673, 22.72984));
		lookup.put("cijin_beach", new City("C1_397000000A_000655", "�X�z�����D��", "cijin_beach", 120.2669, 22.61018));
		lookup.put("love_river", new City("C1_397000000A_000637", "�R�e", "love_river", 120.28904, 22.62483));
		lookup.put("kw2", new City("C1_397000000A_001357", "�̶L�w", "kw2", 120.2771, 22.61899));
		lookup.put("xing_da_harbor", new City("C1_397000000A_000204", "���F��(���H�X�Y)", "xing_da_harbor", 120.19391, 22.86843));
		lookup.put("dream_mall", new City("C1_397000000A_000571", "�Τ@�ڮɥN�ʪ�����", "dream_mall", 120.30692, 22.59515));
		lookup.put("shou_shan_zoo", new City("C1_397000000A_000565", "�ؤs�ʪ���", "shou_shan_zoo", 120.27519, 22.63443));
		lookup.put("hamasen", new City("C1_397000000A_000589", "�����P�K�D��ƶ��", "hamasen", 120.27744, 22.62122));
		lookup.put("xiziwan", new City("C1_397000000A_001214", "��l�W�[���x(�[�����x)", "xiziwan", 120.26591, 22.61769));
		lookup.put("guanyin_mountain", new City("C1_397000000A_000592", "�[���s������", "guanyin_mountain", 120.37175, 22.72964));
		lookup.put("ciaotou_sugar_refinery", new City("C1_397000000A_000595", "���Y�}�t(�x�W�}�~�ժ��])", "ciaotou_sugar_refinery", 120.31433, 22.75568));
		lookup.put("siaogangshan_skywalk", new City("C1_397000000A_001261", "�^�s����", "siaogangshan_skywalk", 120.33432, 22.81199));
		lookup.put("museum_of_fine_arts", new City("C1_397000000A_000158", "�������߬��N�]", "museum_of_fine_arts", 120.28655, 22.65669));
		lookup.put("confucian_temple", new City("C1_397000000A_000246", "�ռq", "confucian_temple", 120.29904, 22.68923));
		lookup.put("fongshan_longshan_buddhist_temple", new City("C1_397000000A_000219", "��s�s�s�x", "fongshan_longshan_buddhist_temple", 120.36201, 22.62078));
		lookup.put("i_ride", new City("C1_397000000A_001250", "i-Ride KAOHSIUNG 5D ����@�|", "i_ride", 120.2989, 22.60561));
		lookup.put("astronomical_museum", new City("C1_397000000A_000031", "�������Ѥ�Ш|�]", "astronomical_museum", 120.33746, 22.568));
		lookup.put("arena", new City("C1_397000000A_000044", "�������J", "arena", 120.30142, 22.66915));
		lookup.put("air_force_museum", new City("C1_397000000A_000060", "�ŭx�x�v�]", "air_force_museum", 120.27258, 22.78468));
		lookup.put("military_academy", new City("C1_397000000A_000187", "���x�x��(�եv�])", "military_academy", 120.36585, 22.61932));
		lookup.put("chengcing_lake", new City("C1_397000000A_000206", "��M�򭷴���", "chengcing_lake", 120.35377, 22.66039));
		lookup.put("film_archive", new City("C1_397000000A_000213", "�������߹q�v�]", "film_archive", 120.28878, 22.62244));
		lookup.put("national_science_and_technology_museum", new City("C1_397000000A_000652", "��߬�Ǥu���ժ��]", "national_science_and_technology_museum", 120.32255, 22.64149));
		lookup.put("fastener_museum", new City("C1_397000000A_000660", "�x�W�����ժ��]", "fastener_museum", 120.28688, 22.80156));
	}
	
	public void editFact() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("fact_file.pl"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String[] syntaxArr = conditionDataOfEnglish;
		String syntax = "";
		for (int i=0; i<syntaxArr.length; i++) {
			syntax = "select(" + syntaxArr[i]+") :- ";
			if (choiceList.contains(i+1))
				syntax += " true.";
			else
				syntax += " false.";
			writer.println(syntax);
			writer.flush();
		}
		writer.close();
	}
	
	public HashSet<String> andSearch() {
		String connectCommend = "consult('" + "fact_file.pl" + "')";
		String connectCommend2 = "consult('" + "rule_AND.pl" + "')";
		try {
			Query q1 = new Query(connectCommend);
			Query q2 = new Query(connectCommend2);
			System.out.println("facts connected?" + (q1.hasSolution() ? " success" : " fail"));
			System.out.println("rules connected?" + (q2.hasSolution() ? " success" : " fail"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for (String site : sites)
			andSet.add(site);
		for (int i = 0; i < conditionDataOfEnglish.length; i++) {
			String command = "spots_" + (i + 1) + "(S).";
			HashSet<String> set = new HashSet<String>();
			Query q = new Query(command);
			while (q.hasMoreSolutions()) {
				Map<String, Term> map = q.nextSolution();
				Term tmp = map.get("S");
				set.add(tmp + "");
			}
			if (!set.isEmpty()) {
				andSet.retainAll(set); //��j�M���G�H�~���F�F���X
			}
		}
		
		return andSet;
	}
	
	public HashSet<String> orSearch() {
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
		orSet = new HashSet<String>();
		String result = "";
		Query q = new Query(command);
		while (q.hasMoreSolutions()) {
			Map<String, Term> map = q.nextSolution();
			Term tmp = map.get("S");
			orSet.add(tmp+""); 
			result += tmp + ",";
		}
		return orSet;
	}
	
	public Queue<String> search() {
		andSet = this.andSearch();
		orSet = this.orSearch();
		
		Queue<String> result = new LinkedList<String>();
		for (String spot : andSet) {
			result.offer(spot);
		}
		orSet.removeAll(andSet);
		for (String spot : orSet) {
			result.offer(spot);
		}
		
		return result;
	}
}