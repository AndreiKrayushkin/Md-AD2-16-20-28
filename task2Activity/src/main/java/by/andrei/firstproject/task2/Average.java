package by.andrei.firstproject.task2;

import java.util.ArrayList;

public class Average extends Summator{
    Summator sum = new Summator();
    private double average = 0;
    public void averageCount (ArrayList<Integer> arrList) {
        sum.sumCount(arrList);
        average = sum.getSumResultat() / 2;
    }

    public double getAverageResultat() {
        return average;
    }
}
