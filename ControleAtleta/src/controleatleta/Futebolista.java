package controleatleta;

import java.util.ArrayList;

public class Futebolista extends Atleta {

    private char categoria; // A=Amador P=Profissional
    private char capitao; // S=É capitão N=Não é capitão
    private ArrayList<Patrocinio> patrocinios;
    private int numeroCamisa;
    private String time;
    private String posicao;
    private int cartoesAmarelos;
    private int cartoesVermelhos;

    public Futebolista(String nome) {
        super(nome);
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public char getCapitao() {
        return capitao;
    }

    public void setCapitao(char capitao) {
        this.capitao = capitao;
    }

    public ArrayList<Patrocinio> getPatrocinios() {
        return patrocinios;
    }

    public void setPatrocinios(ArrayList<Patrocinio> patrocinios) {
        this.patrocinios = patrocinios;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getCartoesAmarelos() {
        return cartoesAmarelos;
    }

    public void setCartoesAmarelos(int cartoesAmarelos) {
        this.cartoesAmarelos = cartoesAmarelos;
    }

    public int getCartoesVermelhos() {
        return cartoesVermelhos;
    }

    public void setCartoesVermelhos(int cartoesVermelhos) {
        this.cartoesVermelhos = cartoesVermelhos;
    }

    
}