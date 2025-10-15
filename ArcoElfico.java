import java.util.Random;
import java.util.List;

public class ArcoElfico implements Arma {

    @Override
    public String getNome() {
        return "Arco Élfico";
    }

    @Override
    public int getDanoBase() {
        return 12;
    }

    @Override
    public int getCustoMana() {
        return 15;
    }

    @Override
    public boolean podeUsar(Personagem p) {
        return p.destreza >= 8;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo, Batalha batalha, Random rnd) {
        List<Personagem> inimigos = batalha.getInimigosDo(atacante);

        System.out.println(atacante.getNome() + " usa Chuva de Flechas!");
        for (Personagem inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                int dano = getDanoBase();
                double crit = atacante.obtémMultiplicadorCritico();
                if (crit > 1) {
                    dano *= crit;
                    System.out.println("CRÍTICO contra " + inimigo.getNome() + "!");
                }
                inimigo.takeDamage(dano);
            }
        }
    }
}
