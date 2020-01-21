package iwe.zh.poker;

import com.zh._interface.GameUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2020/1/17.
 */
public class PokerGamerUser implements GameUser {
    private List<PokerCard> currentCard = new ArrayList<>(32);
    private List<PokerCard> currentOutCard = new ArrayList<>(32);


    public void setCurrentOutCard(List<PokerCard> lc) {
        currentCard.removeAll(lc);
    }
    public List<PokerCard> getCurrentOutCard() {

        return null;
    }
}
