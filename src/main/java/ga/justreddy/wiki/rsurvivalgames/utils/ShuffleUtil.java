package ga.justreddy.wiki.rsurvivalgames.utils;


import ga.justreddy.wiki.rsurvivalgames.model.game.Game;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Wonkglorg <3
 * It sorts the list from high to low
 * then it shuffles all the values with the same integer value
 * and sorts it again
 * I'm pretty sure that's how it works !
 */
public class ShuffleUtil {

    public static void shuffle(List<Game> games) {

        games.sort(Comparator.comparingInt(Game::getPlayerCount).reversed());

        int startIndex = 0;
        int endIndex = 0;
        int currentValue = games.get(0).getPlayerCount();
        Random random = ThreadLocalRandom.current();

        for(int i = 1; i < games.size(); i++){
            Game currentObject = games.get(i);
            int value = currentObject.getPlayerCount();
            if(value == currentValue){
                endIndex = i;
            } else {
                shuffleEqualValues(games, startIndex, endIndex, random);
                startIndex = i;
                endIndex = i;
                currentValue = value;
            }
        }
        shuffleEqualValues(games, startIndex, endIndex, random);
    }

    private static void shuffleEqualValues(List<Game> list, int start, int end, Random random) {
        int size = end - start + 1;
        while(size > 1){
            int i = start + random.nextInt(size);
            int j = start + --size;
            Collections.swap(list, i, j);
        }
    }

}
