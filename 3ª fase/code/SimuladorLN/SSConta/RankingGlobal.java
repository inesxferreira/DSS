package SimuladorLN.SSConta;

public class RankingGlobal implements Comparable<RankingGlobal> {

    private String idConta;
    private int score;

    public RankingGlobal() {
        this.idConta = "";
        this.score = 0;
    }

    public RankingGlobal(String idConta, Integer score) {
        this.idConta = idConta;
        this.score = score;
    }

    public RankingGlobal(RankingGlobal c) {
        this.idConta = c.getIdConta();
        this.score = c.getScore();
    }

    public String getIdConta() {
        return this.idConta;
    }

    public int getScore() {
        return this.score;
    }

    public void setIdConta(String id) {
        this.idConta = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int compareTo(RankingGlobal o) {
        return this.score - o.getScore();
    }

    public RankingGlobal clone() {
        return new RankingGlobal(this);
    }
}