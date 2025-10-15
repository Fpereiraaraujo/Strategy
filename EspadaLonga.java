import java.util.Random;

public class EspadaLonga implements Arma {

    @Override
    public String getNome() {
        return "Espada Longa";
    }

    @Override
    public int getDanoBase() {
        return 15;
    }

    @Override
    public int getCustoMana() {
        return 0;
    }

    @Override
    public boolean podeUsar(Personagem p) {
        return p.forca >= 10;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo, Batalha batalha, Random rnd) {
        int dano = getDanoBase();
        
        double crit = atacante.obtémMultiplicadorCritico();
        if (crit > 1) {
            dano *= crit;
            System.out.println("CRÍTICO com Espada Longa! Dano multiplicado: " + crit);
        }
        alvo.takeDamage(dano);

        
        if (rnd.nextInt(100) < 30) {
            alvo.aplicaEfeito(new Sangramento(3, 5));
        }
    }
}
