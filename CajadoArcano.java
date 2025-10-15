    import java.util.Random;

public class CajadoArcano implements Arma {

    @Override
    public String getNome() {
        return "Cajado Arcano";
    }

    @Override
    public int getDanoBase() {
        return 8;
    }

    @Override
    public int getCustoMana() {
        return 25;
    }

    @Override
    public boolean podeUsar(Personagem p) {
        return p.inteligencia >= 12;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo, Batalha batalha, Random rnd) {
        int dano = getDanoBase();
        double crit = atacante.obtémMultiplicadorCritico();
        if (crit > 1) {
            dano *= crit;
        }

        alvo.takeDamage(dano);
        alvo.aplicaEfeito(new Queimadura(2, 10));
    }
}
