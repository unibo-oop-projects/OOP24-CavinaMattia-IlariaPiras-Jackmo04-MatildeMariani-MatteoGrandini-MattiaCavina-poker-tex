| \<\<Interface\>\> <br>Player                                                                                                                                                                                                                                                                 |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| + getBet() : Optional\<Integer\> <br>+ getAction(State currentState) : Action<br>+ getCards() : Set\<Card\><br>+ getCombination() : Combination<br>+ giveCards(Set\<Card\> cards) : void<br>+ setRole(Role role) : void<br>+ getRole() : Role<br>+ isAlive() : boolean<br>+ isAI() : boolean |
```java
enum Action {
	FOLD, BET, PASS;
}

enum Role {
	SMALL_BLIND(0.5), BIG_BLIND(1);
	private double multiplier;
}
```

| State                                                                                                                            |
| :------------------------------------------------------------------------------------------------------------------------------- |
| - int handNumber<br>- HandFase handFase<br>- int plate<br>- int remainingPlayers<br>- Set\<Card\> tableCards<br>- int betToEqual |

| \<\<Abstract\>\> <br>AbstractPlayer                                                                                                                                                     |
| :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| - Set\<Card\> cards<br>- Combination combination                                                                                                                                        |
| + getBet() : Optional\<Integer\> <br>+ getAction(State currentState) : Action<br>+ getCards() : Set\<Card\><br>+ getCombination() : Combination<br>+ setCards(Set\<Card\> cards) : void |

| \<\<Interface\>\><br>DeckFactory                    |
| :-------------------------------------------------- |
| + standardDeck() : Deck<br>+ romagnoloDeck() : Deck |

| \<\<Interface\>\><br>Deck                                       |
| :-------------------------------------------------------------- |
| + getCards(int numOfCards) : Set\<Card\><br>+ shuffled() : Deck |

```java
class DeckImpl implements Deck {
	
	Deque<Card> deck = new LinkedList<>();

	@Override
	Set<Card> getCards(int numOfCards) {
		...
	}

	@Override
	Deck shuffled() {
		return new DeckImpl();
	}
}

var df = new DeckFactory();
var deck = df.standardDeck();
...
deck = deck.shuffled();

```

```java
enum CombinationType {...}
record Combination(Set<Card> cards, CombinationType type, int tieBreaker);

class CombinationHandler {

	...
	@Override
	Combination getCombinationFromCards(Set<Card> cards) {
		...
		return new Combination(subsetCards, type, tieBreaker);
	}
}
```


| \<\<Interface\>\><br>CombinationHandler                    |
| :--------------------------------------------------------- |
| + getCombinationFromCards(Set\<Card\> cards) : Combination |

| Combination                                                                            |
| :------------------------------------------------------------------------------------- |
| - Set\<Card\> cards<br>- CombinationType type<br>- int tieBreaker                      |
| + getCards() : Set\<Card\><br>+ getType() : CombinationType<br>+ getTieBreaker() : int |
