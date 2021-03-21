package by.andrei.firstproject.task2;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Observer {
    private ArrayList<Integer> list = new ArrayList<>();
    private Set<Integer> setList = new LinkedHashSet<>();
    private Random random = new Random();

    public void generateSetListAndAddToArrayList(Integer sizeArrayList) {
        for (int i = 0; i < sizeArrayList; i++) {
            int valueNumber = random.nextInt(100);
            setList.add(valueNumber);
        }
        list.addAll(setList);
    }

    public ArrayList<Integer> getListNumbers() {
        return list;
    }

}
