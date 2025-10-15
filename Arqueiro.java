import java.util.Random;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 8, 15, 7, 90, 80);
    }

    @Override
    protected boolean tentaEvitar() {
        
        return rnd.nextDouble() < 0.25;
    }
}
