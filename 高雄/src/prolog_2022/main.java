package prolog_2022;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
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
		
		for (int i = 0; i < resultNum; i++) {
			Scenery item = prolog.lookup.get(result.poll());//目前的輸出形式仍為queue，僅增加了對應map取得資料的資料集：搜尋的key值為景點之英文名稱，可獲取之資料為景點的中文名稱、英文名稱、ID、px、py
			System.out.println(item.chinese_Name);
		}
	}

}
