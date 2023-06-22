package Playlist;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static List<Musica> musicasCadastradas = new ArrayList<>();
    private static List<Playlist> playlistsCriadas = new ArrayList<>();

    public static void main(String[] args) {
        Musica musica1 = new Musica("Hora errada", "Veigh", 231);
        Musica musica2 = new Musica("Bem melhor", "Cabelinho", 345);
        Musica musica3 = new Musica("Nosso quadro", "Ana castela", 147);
        Musica musica4 = new Musica("Lose yourself", "Eminem", 541);
        Musica musica5 = new Musica("Popstar", "Drake", 187);
        musica1.adicionarMusicaCadastrada(musicasCadastradas, musica1);
        musica2.adicionarMusicaCadastrada(musicasCadastradas, musica2);
        musica3.adicionarMusicaCadastrada(musicasCadastradas, musica3);
        musica4.adicionarMusicaCadastrada(musicasCadastradas, musica4);
        musica5.adicionarMusicaCadastrada(musicasCadastradas, musica5);
        exibirMenu();
    }

    public static void exibirMenu() {

        while (true) {
            System.out.println("\n--------- MENU ---------");
            System.out.println("1. Cadastrar nova música");
            System.out.println("2. Remover uma música");
            System.out.println("3. Criar playlist");
            System.out.println("4. Juntar playlists");
            System.out.println("5. Remover playlists");
            System.out.println("6. Reprodução de músicas");
            System.out.println("7. Sair");

            try {
                int opcao = in.nextInt();
                in.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarMusica();
                        break;
                    case 2:
                        removerMusica();
                        break;
                    case 3:
                        criarPlaylist();
                        break;
                    case 4:
                        juntarPlaylists();
                        break;
                    case 5:
                        removerPlaylist();
                        break;
                    case 6:
                        reproducao();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nComando inválido!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida! Por favor, insira um número.");
                in.nextLine();
            }
        }
    }

    public static void cadastrarMusica() {
        boolean controler = true;
        while (controler == true) {
            System.out.println("\nCadastro de músicas: ");
            System.out.print("Digite o titulo da música: ");
            String titulo = in.nextLine();
            System.out.print("Digite o artista: ");
            String artista = in.nextLine();
            
            int duracao = 0;
            boolean duracaoValida = false;
            while(!duracaoValida) {
                System.out.print("Digite a duração: ");
                String duracaoInput = in.nextLine();
                try {
                    duracao = Integer.parseInt(duracaoInput);
                    duracaoValida = true;
                } catch(NumberFormatException e) {
                    System.out.println("\nDigite um valor numérico inteiro para a duração.");
                }
            }

            Musica musica = new Musica(titulo, artista, duracao);
            musica.adicionarMusicaCadastrada(musicasCadastradas, musica);
            musica.verMusicasCadastradas(musicasCadastradas);
            controler = false;
        }
    }

    public static void criarPlaylist() {
        // verifica se há ao menos uma musica cadastrada
        if (musicasCadastradas.size() > 0) {
            System.out.println("\nCriar playlist: ");
            System.out.print("Digite uma descrição para a playlist: ");
            String descricaoPlaylist = in.nextLine();
            Playlist playlist = new Playlist(descricaoPlaylist);
            playlist.verMusicasCadastradas(musicasCadastradas);
            
            // para cada indice presente no array de indices
            boolean indiceValido = false;
            while(!indiceValido) {
                System.out.print("\nDigite o indice das musicas a serem adicionadas (separados por virgula): ");
                String indices = in.nextLine();
                // divide a string indices em um array de strings usando a virgula como separador.
                String[] indicesStr = indices.split(",");
                for (String indiceStr : indicesStr) {
                    try {
                        // transorma pra inteiro aquele indice
                        int indice = Integer.parseInt(indiceStr.trim());
                        // verifica se é um indice de uma musica
                        if (indice >= 1 && indice <= musicasCadastradas.size()) {
                            // pega a referencia para aquela musica em especifico e adiciona na playlist
                            Musica musica = musicasCadastradas.get(indice - 1);
                            playlist.adicionarMusicaNaPlaylist(musica);
                            indiceValido = true;
    
                        } else {
                            System.out.println("\nIndice inválido!");
                            indiceValido = false;
                        }
    
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido: " + indiceStr);
                        indiceValido = false;
                    }
                }
            }

            // lista a playlist e adiciona a playlist criada em um array contendo as
            // playlists
            playlist.listarPlaylist();
            playlistsCriadas.add(playlist);

        } else {
            System.out.println("\nNenhuma música cadastrada!");
        }
    }

    private static void juntarPlaylists() {
        if (playlistsCriadas.size() > 0) {
            System.out.println("\nListagem de playlists:");
            int cont = 1;
            for (Playlist playlist : playlistsCriadas) {
                System.out.println("\n" + cont + ". PLAYLIST: " + playlist.getDescricaoPlaylist());
                playlist.listarPlaylist2();
                cont++;
            }
            System.out.print("\nDigite os indices da playlist que deseja juntar: ");
            String indices = in.nextLine();
            String[] indicesStr = indices.split(",");

            List<Musica> musicasJuntas = new ArrayList<>();

            for (String indiceStr : indicesStr) {
                try {
                    int indice = Integer.parseInt(indiceStr.trim());
                    if (indice >= 1 && indice <= playlistsCriadas.size()) {
                        Playlist playlistSelecionada = playlistsCriadas.get(indice - 1);
                        List<Musica> musicasPlaylist = playlistSelecionada.getMusicas();
                        for (Musica musica : musicasPlaylist) {
                            if (!musicasJuntas.contains(musica)) {
                                musicasJuntas.add(musica);
                            }
                        }

                    } else {
                        System.out.println("Indice invalido!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Indice invalido: " + indiceStr);
                }
            }
            System.out.print("Digite uma descrição para a nova playlist: ");
            String descricao = in.nextLine();

            Playlist novaPlaylist = new Playlist(descricao);

            // funcao vai colocar as musicas dentro do array playlist do objeto novaPlaylist
            novaPlaylist.setMusicas(musicasJuntas);

            // nova playlist é adicionada no array contendo as playlists
            playlistsCriadas.add(novaPlaylist);

            System.out.println("\nPlaylists juntadas com sucesso!");

            listarPlaylists();

        } else {
            System.out.println("\nNenhuma playlist criada!");
        }
    }

    // metodo que faz a listagem das playlists
    private static void listarPlaylists() {
        System.out.println("\nListagem de playlists:");
        int cont = 1;
        for (Playlist playlist : playlistsCriadas) {
            System.out.println("\n" + cont + ". PLAYLIST: " + playlist.getDescricaoPlaylist());
            playlist.listarPlaylist2();
            cont++;
        }
    }

    public static void reproducao() {
        if (playlistsCriadas.size() > 0) {
            System.out.println("\nPlaylists disponiveis: ");
            System.out.println();
            int cont = 1;
            for (Playlist playlist : playlistsCriadas) {
                int tempo = playlist.caclularDuracaoTotal();
                System.out.println(cont + ". PLAYLIST: " + playlist.getDescricaoPlaylist() + " Duracao: " + playlist.converterSegundos(tempo));
                cont++;
            }
            System.out.print("Digite o numero da playlist: ");
            int num = in.nextInt();
            in.nextLine();
            if (num >= 1 && num <= playlistsCriadas.size()) {
                System.out.println("\nReprodução");
                System.out.println("1. Normal  2. Aleatoria");
                int escolha = in.nextInt();
                in.nextLine();

                switch (escolha) {
                    case 1:
                        Playlist playlistSelecionada = playlistsCriadas.get(num - 1);
                        playlistSelecionada.listarPlaylist2();
                        break;

                    case 2:
                        Playlist playlistSelecionada2 = playlistsCriadas.get(num - 1);
                        playlistSelecionada2.listarPlaylistAleatoriamente();
                        break;

                    default:
                        System.out.println("\nComando invalido!");
                        break;
                }

            } else {
                System.out.println("\nNumero de playlist invalido!");
            }

        } else {
            System.out.println("\nNenhuma playlist encontrada!");
        }
    }

    public static void removerMusica() {
        if (musicasCadastradas.size() > 0) {
            int cont = 1;
            System.out.println("\nMúsicas cadastradas: ");
            for (Musica musica : musicasCadastradas) {
                System.out.println(cont + ". " + musica);
                cont++;
            }
            boolean var = true;
            while (var == true) {
                System.out.print("\nInforme o número da música que deseja remover: ");
                int num = in.nextInt();
                in.nextLine();
                if (num <= 0 || num > musicasCadastradas.size()) {
                    System.out.println("Número inválido!");
                    var = true;
                } else {
                    musicasCadastradas.remove(num - 1);
                    System.out.println("Música removida com sucesso!");
                    var = false;
                }
            }

        } else {
            System.out.println("\nNenhuma música cadastrada!");
        }
    }

    public static void removerPlaylist() {
        if (playlistsCriadas.size() > 0) {
            System.out.println("\nPlaylists cadastradas: ");
            int cont = 1;

            for (Playlist playlist : playlistsCriadas) {
                System.out.println("\nPlaylist " + cont + " - " + playlist.getDescricaoPlaylist());
                playlist.listarPlaylist2();
                cont++;
            }

            boolean var = true;
            while (var == true) {
                System.out.print("\nInforme o número da playlist que deseja remover: ");
                int num = in.nextInt();
                in.nextLine();
                if (num <= 0 || num > playlistsCriadas.size()) {
                    System.out.println("Número inválido!");
                    var = true;
                } else {
                    playlistsCriadas.remove(num - 1);
                    System.out.println("\nPlaylist removida com sucesso!");
                    var = false;
                }
            }

        } else {
            System.out.println("\nNenhuma playlist cadastrada!");
        }
    }
}
