package pdm.fic.estacio.br.appjogodavelha;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import pdm.fic.estacio.br.appjogodavelha.Modelo.Tabuleiro;

public class JogoDaVelhaActivity extends AppCompatActivity {

    private Tabuleiro tabuleiro;

    private TableLayout tabela;

    private boolean vez;

    private boolean temVencedor;

    private int numJogadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_da_velha);

        tabela = (TableLayout) findViewById(R.id.tabuleiro);

        tabuleiro = new Tabuleiro();

    }

    /*------------------------------------Metodos OnClick------------------------------------------------*/

    public void novoJogo(View view) {

        // Metodo para iniciar uma nova partida

        for(int coluna = 0; coluna < tabela.getChildCount(); coluna ++){

            TableRow tableRow = (TableRow) tabela.getChildAt(coluna);
            for(int linha = 0; linha < tableRow.getChildCount(); linha++){

                Button btn = (Button) tableRow.getChildAt(linha);

                btn.setEnabled(true);
                btn.setBackgroundColor(Color.WHITE);
                btn.setText("");

            }

        }

        tabuleiro.reiniciarJogo();

        temVencedor = false;

        numJogadas = 0;

    }

    public void sair(View view) {

        // Metodo para sair da aplicacao

        finish();

    }

    public void jogar(View view) {

        // Metodo para jogar

        if(!temVencedor) {

            Button btn = (Button) view;

            String jogador = (vez) ? "O" : "X";

            btn.setText(jogador);

            int cor = (vez) ? Color.RED : Color.BLUE;

            btn.setTextColor(cor);

            vez = !vez;

            btn.setEnabled(false);

            numJogadas++;

            tabuleiro.marcaJogadas(btn.getText().toString(), Integer.parseInt(btn.getTag().toString()));

            verificaVencedor();

        }

    }

    /*--------------------------------------------------------------------------------------------------*/
    /*--------------------------------------------------------------------------------------------------*/


    /*------------------------------------Metodos Sem Retorno------------------------------------------------*/

    private void verificaVencedor(){

        // Metodo para verificar o vencedor da partida

        if(numJogadas > 4) {

            String vencedor = tabuleiro.verificaVencedor();

            vencedor = (numJogadas == tabuleiro.TAM_TABULEIRO && vencedor == null) ? "Deu Velha!" : vencedor;

            if (vencedor != null) {

                mostraVencedor(vencedor);
                temVencedor = true;

            }

        }

    }

    private void mostraVencedor(String vencedor){

        // Metodo para mostrar quem venceu e modificar a cor de onde venceu

        for(int coluna = 0; coluna < tabela.getChildCount(); coluna ++){

            TableRow tableRow = (TableRow) tabela.getChildAt(coluna);
            for(int linha = 0; linha < tableRow.getChildCount(); linha++){

                Button btn = (Button) tableRow.getChildAt(linha);

                for(int i = 0; i < tabuleiro.acertos[0].length; i++) {

                    if (tabuleiro.acertos[0][i] != null &&
                            tabuleiro.acertos[0][i] == Integer.parseInt(btn.getTag().toString())) {

                        btn.setBackgroundColor(Color.BLUE);

                    } else if (tabuleiro.acertos[1][i] != null &&
                            tabuleiro.acertos[1][i] == Integer.parseInt(btn.getTag().toString())) {

                        btn.setBackgroundColor(Color.RED);

                    }

                }



            }


        }

        Toast.makeText(this, vencedor, Toast.LENGTH_SHORT).show();

    }

    /*--------------------------------------------------------------------------------------------------*/

}
