package iwe.zh.poker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zh on 2020/1/17.
 */
public final class PokerCard {
    private static Map<Integer, PokerCard> map = new HashMap<>(64);
    private int cardId;
    private String cardValue;
    private String cardType;

    private PokerCard(int cardId, String cardValue, String cardType) {
        this.cardId = cardId;
        this.cardValue = cardValue;
        this.cardType = cardType;
    }

    private static final String[] CARD_VALUE = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", ""};


    static {
    }

    public static PokerCard getCard(int cardId) {
        return map.get(cardId);
    }

    public int getCardId() {
        return cardId;
    }
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    public String getCardValue() {
        return cardValue;
    }
    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
