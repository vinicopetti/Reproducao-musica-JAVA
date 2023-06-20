package Playlist;
import java.util.List;

public class Musica {
    
    // atributos do objeto da classe musica
    String titulo;
    String artista;
    int duracao;

    //  construtor exigindo os parametros na criacao do objeto 
    public Musica(String titulo, String artista, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    // recebe como parametro o array e o objeto da classe musica a ser armazenado
    public void adicionarMusicaCadastrada(List<Musica> musicasCadastradas, Musica musica) {
        musicasCadastradas.add(musica);
        System.out.println("\nMúsica cadastrada com sucesso!");
    }

    // lista o conteudo do array com as musicas cadastradas
    public void verMusicasCadastradas(List<Musica> musicasCadastradas) {
        System.out.println("\nMúsicas cadastradas: ");
        int cont = 1;
        for(Musica musica : musicasCadastradas) {
            System.out.println(cont+". Musica [titulo=" + musica.getTitulo() + ", artista=" + musica.getArtista() + ", duracao=" + musica.getDuracao() + "]");
            cont++;
        }
    }

    // getters e setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Musica [titulo=" + titulo + ", artista=" + artista + ", duracao=" + duracao + "]";
    }
}

