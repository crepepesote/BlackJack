import java.util.ArrayList;

public class Player {
    private String name;
    private int position;
    private BlackJack BlackJack;
    private ArrayList<Integer> cardsvalue;
    private boolean alive;

    public Player(BlackJack blackjack, String name) {
        this.BlackJack = blackjack;
        cardsvalue = new ArrayList<>();
        alive = true;
        this.name = name;
    }

    public void requestCard() {
        BlackJack.giveCard(this);
    }

    public void addCard(int value) {
        cardsvalue.add(value);
    }

    public int getTotal() {
        int total = 0;
        for (Integer c : cardsvalue) {
            total += c;
        }
        return total;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive() {
        alive = false;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

}
