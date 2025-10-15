
public class Queimadura extends StatusEffect {
    private int danoPorTurno;

    public Queimadura(int duracao, int danoPorTurno) {
        super("Queimadura", duracao);
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public boolean onTurn(Personagem alvo) {
        alvo.takeRawDamage(danoPorTurno);
        System.out.println(alvo.getNome() + " sofre " + danoPorTurno + " de queimadura. ("
                + duracao + " turnos restantes)");
        return super.onTurn(alvo);
    }
}
