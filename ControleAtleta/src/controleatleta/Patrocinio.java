package controleatleta;

public class Patrocinio {

    private String nomeDoPatrocinador;

    public Patrocinio(String nomeDoPatrocinador) {
        this.nomeDoPatrocinador = nomeDoPatrocinador;
    }

    public String getnomeDoPatrocinadoro() {
        return nomeDoPatrocinador;
    }

    public void setnomeDoPatrocinador(String nomeDoPatrocinador) {
        this.nomeDoPatrocinador = nomeDoPatrocinador;
    }

        
    @Override
    public String toString() {
        return this.nomeDoPatrocinador;
    }
}
