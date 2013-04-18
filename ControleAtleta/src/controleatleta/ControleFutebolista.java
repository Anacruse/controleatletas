package controleatleta;

import java.util.ArrayList;

public class ControleFutebolista {

    private ArrayList<Futebolista> listaFutebolistas;

    public ControleFutebolista() {
        this.listaFutebolistas = new ArrayList<Futebolista>();
    }
    
    public ArrayList<Futebolista> getListaFutebolistas() {
        return listaFutebolistas;
    }
    
    public void adicionar(Futebolista umFutebolista) {
        listaFutebolistas.add(umFutebolista);
    }
    
    public void remover(Futebolista umFutebolista) {
        listaFutebolistas.remove(umFutebolista);
    }
    
    public Futebolista pesquisar(String nome) {
        for (Futebolista b: listaFutebolistas) {
            if (b.getNome().equalsIgnoreCase(nome)) return b;
        }
        return null;
    }
}
