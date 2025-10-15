import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Personagem {
    protected String nome;
    protected int forca;
    protected int destreza;
    protected int inteligencia;
    protected int vidaMax;
    protected int vida;
    protected int manaMax;
    protected int mana;
    protected Arma armaEquipada;
    protected List<StatusEffect> efeitos = new ArrayList<>();

    protected Random rnd = new Random();

    public Personagem(String nome, int forca, int destreza, int inteligencia, int vida, int mana) {
        this.nome = nome;
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.vidaMax = vida;
        this.vida = vida;
        this.manaMax = mana;
        this.mana = mana;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getMana() { return mana; }
    public boolean estaVivo() { return vida > 0; }

    public void equipar(Arma a) {
        this.armaEquipada = a;
        System.out.println(nome + " equipou " + (a != null ? a.getNome() : "Nenhuma arma"));
    }

    public void takeRawDamage(int dmg) { 
        vida -= dmg;
        if (vida < 0) vida = 0;
    }

    public void takeDamage(int dmg) { 
        double reduz = getDamageReduction(); 
        int finalDmg = (int) Math.round(dmg * (1 - reduz));
        vida -= finalDmg;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebe " + finalDmg + " de dano (redução " + (int)(reduz*100) + "%). Vida: " + vida + "/" + vidaMax);
    }

    protected double getDamageReduction() { return 0.0; }

    
    protected boolean tentaEvitar() { return false; }

   
    protected void onTurnRegen() {}

   
    public void aplicaEfeito(StatusEffect e) {
        System.out.println(nome + " recebeu efeito: " + e.getNome());
        e.onApply(this);
        efeitos.add(e);
    }

   
    public boolean processaEfeitosTurno() {
       
        boolean perdeTurno = false;
        Iterator<StatusEffect> it = efeitos.iterator();
        List<StatusEffect> paraManter = new ArrayList<>();
        while (it.hasNext()) {
            StatusEffect e = it.next();
            if (e instanceof Atordoado) {
                System.out.println(nome + " está atordoado e perde o turno.");
                perdeTurno = true;
                boolean continua = e.onTurn(this);
                if (continua) paraManter.add(e);
            } else {
                boolean continua = e.onTurn(this);
                if (continua) paraManter.add(e);
            }
        }
        efeitos = paraManter;
        return perdeTurno;
    }

  
    public void atacar(Personagem alvo, Batalha batalha) {
        if (!estaVivo()) return;

        
        if (alvo.tentaEvitar()) {
            System.out.println(alvo.getNome() + " evitou o ataque!");
            return;
        }

        if (armaEquipada == null) {
            System.out.println(nome + " ataca desarmado contra " + alvo.getNome());
            int dano = 3 + forca / 2;
            double critMul = obtémMultiplicadorCritico();
            if (critMul > 1.0) {
                dano = (int)Math.round(dano * critMul);
                System.out.println("CRÍTICO! " + nome + " causa " + dano + " no desarmado.");
            }
            alvo.takeDamage(dano);
            return;
        }

        
        if (mana < armaEquipada.getCustoMana()) {
            System.out.println(nome + " não tem mana suficiente para usar " + armaEquipada.getNome() + ". Usando ataque básico.");
            int dano = 3 + forca / 2;
            double critMul = obtémMultiplicadorCritico();
            if (critMul > 1.0) {
                dano = (int)Math.round(dano * critMul);
                System.out.println("CRÍTICO! " + nome + " causa " + dano + " no básico.");
            }
            alvo.takeDamage(dano);
            return;
        }

        if (!armaEquipada.podeUsar(this)) {
            System.out.println(nome + " não atende os requisitos para usar " + armaEquipada.getNome() + ". Usando ataque básico.");
            int dano = 3 + forca / 2;
            double critMul = obtémMultiplicadorCritico();
            if (critMul > 1.0) {
                dano = (int)Math.round(dano * critMul);
                System.out.println("CRÍTICO! " + nome + " causa " + dano + " no básico.");
            }
            alvo.takeDamage(dano);
            return;
        }

       
        mana -= armaEquipada.getCustoMana();
        System.out.println(nome + " usa " + armaEquipada.getNome() + " contra " + (alvo != null ? alvo.getNome() : "vários alvos") + ". (Mana restante: " + mana + ")");
        armaEquipada.usar(this, alvo, batalha, rnd);
    }

   
    protected double obtémMultiplicadorCritico() {
        double prob = 0.15;
        if (rnd.nextDouble() < prob) return 2.0;
        return 1.0;
    }

   
    public void começarTurno() {
        onTurnRegen(); 
    }

    
    @Override
    public String toString() {
        return nome + " [HP:" + vida + "/" + vidaMax + " | MP:" + mana + "/" + manaMax + "]";
    }
}
