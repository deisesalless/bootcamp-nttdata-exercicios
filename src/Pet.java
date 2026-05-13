public class Pet {
    private String nome;
    private boolean isLimpo;

    public Pet(String nome) {
        this.nome = nome;
        this.isLimpo = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isLimpo() {
        return isLimpo;
    }

    public void setLimpo(boolean limpo) {
        isLimpo = limpo;
    }
}

