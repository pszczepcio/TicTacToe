package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterDraw {

    public static List<String> characterDetermination() {
        Random random = new Random();
        List<String> signList = new ArrayList<>();
        int value = random.nextInt(100);
        if (value % 2 == 0) {
            signList.add("X");
            signList.add("O");
        }else {
            signList.add("O");
            signList.add("X");
        }
        return signList;
    }
}
