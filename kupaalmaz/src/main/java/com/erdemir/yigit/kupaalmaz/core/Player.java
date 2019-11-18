package com.erdemir.yigit.kupaalmaz.core;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String username;
    private List<Card> cards;
    private List<Card> penaltyCards = new ArrayList<Card>();

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public List<Card> getCards() {
	return cards;
    }

    public void setCards(List<Card> cards) {
	this.cards = cards;
    }

    public List<Card> getPenaltyCards() {
	return penaltyCards;
    }

}
