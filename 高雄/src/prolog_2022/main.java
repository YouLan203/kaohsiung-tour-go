package prolog_2022;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
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
		
		for (int i = 0; i < resultNum; i++) {
			Scenery item = prolog.lookup.get(result.poll());//�ثe����X�Φ�����queue�A�ȼW�[�F����map���o��ƪ���ƶ��G�j�M��key�Ȭ����I���^��W�١A�i�������Ƭ����I������W�١B�^��W�١BID�Bpx�Bpy
			System.out.println(item.chinese_Name);
		}
	}

}
