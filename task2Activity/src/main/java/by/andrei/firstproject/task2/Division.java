package by.andrei.firstproject.task2;

import java.util.ArrayList;

public class Division {
    private ArrayList<Integer> arrayListFirst = new ArrayList<>();
    private ArrayList<Integer> arrayListSecond = new ArrayList<>();
    private double divisionResultat = 0;

    public void divisionCount (ArrayList<Integer> list) {
        int sizeHalf = list.size() / 2;

        for (int i = 0; i < sizeHalf; i++) {
            arrayListFirst.add(list.get(i));
        }
        int sizeListFirst = arrayListFirst.size();
        int sumCountListFirst = 0;
        for (int i = 0; i < sizeListFirst; i++) {
            sumCountListFirst += arrayListFirst.get(i);
        }
        System.out.println("List one: " + arrayListFirst);
        System.out.println("Summ list one: " + sumCountListFirst);
        for (int i = sizeHalf; i < list.size(); i++ ) {
            arrayListSecond.add(list.get(i));
        }
        int sizeListSecond = arrayListSecond.size();
        int substractionCountListSecond = arrayListSecond.get(0);
        for (int i = 1; i < sizeListSecond; i++) {
            substractionCountListSecond -= arrayListSecond.get(i);
        }
        System.out.println("List two: " + arrayListSecond);
        System.out.println("Subs list one: " + substractionCountListSecond);
        divisionResultat = (double) sumCountListFirst / substractionCountListSecond;
    }

    public Double getDivisionResultat() {
        return divisionResultat;
    }
}