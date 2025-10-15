
public abstract class StatusEffect {
    protected int duracao; 
    protected String nome;

    public StatusEffect(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public String getNome() { return nome; }
    public int getDuracao() { return duracao; }

   
    public void onApply(Personagem alvo) {}

  
    public boolean onTurn(Personagem alvo) {
        duracao--;
        return duracao > 0;
    }
}
