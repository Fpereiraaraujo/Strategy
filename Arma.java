
import java.util.Random;

public interface Arma {
    String getNome();
    int getDanoBase();
    int getCustoMana();
    boolean podeUsar(Personagem p); 
    /**
     * 
     * @param atacante 
     * @param alvo
     * @param batalha
     * @param rnd 
     */
    void usar(Personagem atacante, Personagem alvo, Batalha batalha, Random rnd);
}
