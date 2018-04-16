package pdm.fic.estacio.br.appjogodavelha.Modelo;

/**
 * Created by Matheus on 06/04/2018.
 */

public class Tabuleiro {

    public final int TAM_TABULEIRO = 9;

    public Integer [][] acertos = {{null, null, null}, {null, null, null}};

    private final Integer [][] possibilidades = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    private Integer [][] jogadas = {{null, null, null, null, null}, {null, null, null, null, null}};

    private String vencedor = null;


    /*------------------------------------Metodo(s) Com Retorno------------------------------------------------*/

    public String verificaVencedor(){

        // Metodo para verificar o vencedor

        int acertosO = 0;

        int acertosX = 0;

        for(int coluna = 0; coluna < possibilidades.length; coluna++){

            for(int linha = 0; linha < possibilidades[coluna].length; linha++){

                for(int i = 0; i < jogadas[0].length; i ++){

                    if(jogadas[0][i] == possibilidades[coluna][linha]) {

                        acertos[0][acertosO] = possibilidades[coluna][linha];
                        acertosO++;

                    } else if(jogadas[1][i] == possibilidades[coluna][linha]) {

                        acertos[1][acertosX] = possibilidades[coluna][linha];
                        acertosX++;

                    }

                }

            }

            if(acertosO == 3 || acertosX == 3) {

                limpaAcertos(false);
                return vencedor = (acertosO == 3)? "O Jogador O Venceu!" : "O Jogador X Venceu!";

            }

            acertosO = 0;
            acertosX = 0;
            limpaAcertos(true);

        }

        return vencedor;

    }


    /*--------------------------------------------------------------------------------------------------*/
    /*--------------------------------------------------------------------------------------------------*/


    /*------------------------------------Metodos Sem Retorno------------------------------------------------*/

    public void marcaJogadas(String jogador, int posicaoJogada){

        // Todo: Metodo para marcar onde o jogador jogou

        for(int i = 0; i < jogadas[0].length; i++){

            if(jogador.equals("O") && jogadas[0][i] == null){

                jogadas[0][i] = posicaoJogada;
                break;

            }else if (jogadas[1][i] == null){

                jogadas[1][i] = posicaoJogada;
                break;

            }

        }

    }

    private void limpaAcertos(boolean limpaTodos){

        // Metodo responsavel por limpar a quatidade de acertos

        for(int i = 0; i < acertos[0].length; i++){

            if(acertos[0][2] == null && !limpaTodos)
                acertos[0][i] = null;
            else if (acertos[1][2] == null && !limpaTodos)
                acertos[1][i] = null;
            else{

                acertos[0][i] = null;
                acertos[1][i] = null;

            }

        }

    }

    public void reiniciarJogo(){

        // Metodo para reiniciar o jogo

        for(int i = 0; i < jogadas[0].length; i++){

            jogadas[0][i] = null;

            jogadas[1][i] = null;

        }

        limpaAcertos(true);

        vencedor = null;

    }

}
