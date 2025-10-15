
public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 5, 7, 18, 70, 150);
    }

    @Override
    protected void onTurnRegen() {
        int regen = 10;
        mana += regen;
        if (mana > manaMax) mana = manaMax;
        System.out.println(nome + " regenera " + regen + " de mana (passiva). Mana atual: " + mana);
    }
}
