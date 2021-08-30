package com.web_service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {
	public static List<List<String>> compareDiff(List<String> list1, List<String> list2) {
        Collections.sort(list1);
        Collections.sort(list2);
        int index1 = 0;
        int index2 = 0;
        ArrayList<String> diffTable = new ArrayList<>();
        ArrayList<String> commonTable = new ArrayList<>();
        while (index1 < list1.size() || index2 < list2.size()) {
            if (index1 == list1.size()) {
                while (index2 < list2.size()) {
                    diffTable.add(list2.get(index2));
                    index2++;
                }
                break;
            }
            if (index2 == list2.size()) {
                while (index1 < list1.size()) {
                    diffTable.add(list1.get(index1));
                    index1++;
                }
                break;
            }
            int compareVal = list1.get(index1).compareToIgnoreCase(list2.get(index2));
            if (compareVal < 0) {
                while (compareVal < 0 && index1 < list1.size()) {
                    diffTable.add(list1.get(index1));
                    index1++;
                    compareVal = list1.get(index1).compareToIgnoreCase(list2.get(index2));
                }
            } else if (compareVal > 0) {
                while (compareVal > 0 && index2 < list2.size()) {
                    diffTable.add(list2.get(index2));
                    index2++;
                    compareVal = list1.get(index1).compareToIgnoreCase(list2.get(index2));
                }
            } else if (compareVal == 0) {
                commonTable.add(list1.get(index1));
                index1++;
                index2++;
            }
        }
        return new ArrayList<>(Arrays.asList(commonTable, diffTable));
    }
}
