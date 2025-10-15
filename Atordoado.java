
public class Atordoado extends StatusEffect {

    public Atordoado(int duracao) {
        super("Atordoado", duracao);
    }

    @Override
    public boolean onTurn(Personagem alvo) {
        System.out.println(alvo.getNome() + " está atordoado e perde o turno. (" + duracao + " turnos restantes)");
        return super.onTurn(alvo);
    }
}
