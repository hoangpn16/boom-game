package score;

public class Gamer implements Comparable<Gamer> {

    private int score;
    private String name;

    public Gamer() {

    }

    public Gamer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Gamer gamer) {
        if (this.score > gamer.getScore()) {
            return -1;
        } else if (this.score < gamer.getScore()) {
            return 1;
        }
        return 0;
    }

}
