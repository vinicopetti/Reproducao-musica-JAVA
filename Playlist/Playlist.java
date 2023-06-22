package Playlist;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

    String descricaoPlaylist;
    private List<Musica> playlist; 

    // construtor
    public Playlist(String descricaoPlaylist) {
        this.descricaoPlaylist = descricaoPlaylist;
        playlist = new ArrayList<>();
    }

    // lista as musicas cadastradas
    public void verMusicasCadastradas(List<Musica> musicasCadastradas) {
        System.out.println("\nMúsicas disponíveis na biblioteca: ");
        int cont = 1;
        for(Musica musica : musicasCadastradas) {
            System.out.println(cont+". Musica [titulo=" + musica.getTitulo() + ", artista=" + musica.getArtista() + ", duracao=" + musica.getDuracao() + "]");
            cont++;
        }
    }

    // rece como parametro uma musica e adiciona ela na playlist
    public void adicionarMusicaNaPlaylist(Musica musica) {
        playlist.add(musica);
    }

    // lista a playlist com todas as musicas
    public void listarPlaylist() {
        System.out.println("\nPlaylist criada: ");
        int cont = 1;
        for(Musica musica : playlist) {
            System.out.println(cont + ". Musica [titulo=" + musica.getTitulo() + ", artista=" + musica.getArtista() + ", duracao=" + musica.getDuracao() + "]");
            cont++;
        }
    }

    // lista a playlist sem o contador
    public void listarPlaylist2() {
        System.out.println();
        for(Musica musica : playlist) {
            System.out.println("Titulo = " + musica.getTitulo() + ", artista = " + musica.getArtista() + ", duracao = " + musica.getDuracao());
        }
    }

    // getters e setters
    public String getDescricaoPlaylist() {
        return descricaoPlaylist;
    }

    public List<Musica> getMusicas() {
        return playlist;
    }

    public void setMusicas(List<Musica> musicas) {
        playlist = new ArrayList<>(musicas);
    }

    // funcao que calcula a duracao total da playlist
    public Integer caclularDuracaoTotal() {
        int duracaoTotal = 0;
        for(Musica musica : playlist) {
            duracaoTotal += musica.getDuracao();
        }
        return duracaoTotal;
    }

    // recebe a duracao total em segundos e transforma pra minutos + segundos
    public String converterSegundos(int segundos) {
        int min = segundos / 60;
        int segRestantes = segundos % 60;
        return (min + " minuto(s) e " + segRestantes +  " segundos");
    }


    public void listarPlaylistAleatoriamente() {
        // cria uma copia da playlist original
        List<Musica> playlistOriginal = new ArrayList<>(playlist);
        System.out.println();
        // embaralha a playlistOriginal
        Collections.shuffle(playlist);
        for(Musica musica : playlist) {
            System.out.println("Titulo = " + musica.getTitulo() + ", artista = " + musica.getArtista() + ", duracao = " + musica.getDuracao());
        }
        // faz a playlistOriginal receber a copia nao embaralhada
        playlist = playlistOriginal;
    }
}
