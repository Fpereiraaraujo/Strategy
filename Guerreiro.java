public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 15, 8, 5, 120, 50);
    }

    @Override
    protected double getDamageReduction() {
        return 0.20;
    }
}
