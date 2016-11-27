package classes;

import classes.error.Error;
import classes.error.ErrorSintatico;

/**
 * Created by stholen on 19/11/16.
 */
public class AnalisadorSintatico {

    private String programa;
    private String declaracoes;
    private String declaracao;
    private String identificadores;
    private String identificador;
    private String instrucoes;
    private String instrucao;
    private String atribuicao;

    AnalisadorLexicoReurn lexico;

    public AnalisadorSintatico(String caminhoArquivo)
    {
        lexico = new AnalisadorLexicoReurn(caminhoArquivo);
    }

    public void analisar ()
    {
        while (true)
        {
            for(int i =0;i<lexico.getTokens().size();i++)
            {
                if(lexico.getTokens().get(i).getToken() == "Inicio")
                {

                }
                else
                {
                    ErrorSintatico erro = new ErrorSintatico();
                    erro.setErrorSintatico(201,lexico.getTokens().get(i).toString(),lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
                    Error.addErro(erro);
                }
            }
        }
    }
}
