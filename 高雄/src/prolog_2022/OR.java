package prolog_2022;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import org.jpl7.Query;
import org.jpl7.Term;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OR {
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
		String displayRes = "";
		for (String spot : set) {
			displayRes += spot + "\n";
		}
		System.out.println(displayRes);
	}
}
