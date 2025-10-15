
public class Sangramento extends StatusEffect {
    private int danoPorTurno;

    public Sangramento(int duracao, int danoPorTurno) {
        super("Sangramento", duracao);
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public boolean onTurn(Personagem alvo) {
        alvo.takeRawDamage(danoPorTurno); 
        System.out.println(alvo.getNome() + " sofre " + danoPorTurno + " de sangramento. ("
                + duracao + " turnos restantes)");
        return super.onTurn(alvo);
    }
}
