package by.andrei.firstproject.task2;

import java.util.ArrayList;

public class Summator{
    private String listTotal = "";
    private int sumElem = 0;
    //для вывода листа в строке
    public void sumToString (ArrayList<Integer> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            listTotal += arrayList.get(i) + " ";
        }
    }
    //сумма
    public void sumCount (ArrayList<Integer> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sumElem += arrayList.get(i);
        }
    }

    public String getSumToString() {
        return listTotal;
    }

    public Integer getSumResultat() {
        return sumElem;
    }

}
