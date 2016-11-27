package classes;

import classes.error.Error;
import classes.error.ErrorSintatico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stholen on 19/11/16.
 */
public class AnalisadorSintatico
{
    private int i = 0 ;
    private Map<String,String> variaveisDeclaradas = new HashMap<>();

    AnalisadorLexicoReurn lexico;
    ErrorSintatico erro;
    public AnalisadorSintatico(String caminhoArquivo)
    {
        lexico = new AnalisadorLexicoReurn(caminhoArquivo);
        erro = new ErrorSintatico();
    }

    public void analisar ()
    {

        while (true)
        {
            while(i<lexico.getTokens().size())
            {
                if(lexico.getTokens().get(i++).getToken() == "Inicio")
                {
                    while(lexico.getTokens().get(i).getToken() == "IDENTIFICADOR")
                    {
                        i++;
                    }
                    if(lexico.getTokens().get(i).getLexema() == ";")
                    {
                        while (lexico.getTokens().get(i).getToken() == "IDENTIFICADOR")
                        {
                            validaDeclaracao(lexico.getTokens().get(i).getLexema(),lexico.getTokens().get(i+1).getToken(),lexico.getTokens().get(i+2).getToken(),lexico.getTokens().get(i+3).getToken(),lexico.getTokens().get(i).getnLinha(),lexico.getTokens().get(i).getNomeArquivo());
                            i+=4;
                        }

                    }
                }
                else
                {
                    erro.setErrorSintatico(201,"Identificador nao esperado",lexico.getTokens().get(i).toString(),lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
                    Error.addErro(erro);
                }
            }
        }
    }
    public void validaIdentificador (int codigo,String msg,String lexema,String nomeArquivo,int nLine)
    {

        erro.setErrorSintatico(codigo,msg,lexema,nomeArquivo,nLine);
    }
    public void validaDeclaracao (String identificador,String separador,String tipo,String delimitador,int nlinha,String nomeArquivo)
    {
        if(!(identificador+separador+tipo+delimitador).matches("^IDENTIFICADOR:REAL;$"))
        {
            if (!(identificador+separador+tipo+delimitador).matches("^IDENTIFICADOR:INTEIRO;$"))
            {
                if (!(identificador+separador+tipo+delimitador).matches("^IDENTIFICADOR:CHARACTERE;$"))
                {
                    if (!(identificador+separador+tipo+delimitador).matches("^IDENTIFICADOR:LOGICO;$"))
                    {
                        if(!validaAtribuicao(identificador,separador,tipo,delimitador,nlinha,nomeArquivo));
                        erro.setErrorSintatico(201,"Erro na declaracao",identificador+separador+tipo+delimitador,nomeArquivo,nlinha);
                    }
                    else
                    {
                        variaveisDeclaradas.put(identificador,("false | true"));
                    }
                }
                else
                {
                    variaveisDeclaradas.put(identificador,("\\[a-zA-Z]{1}"));
                }
            }
            else
            {
                variaveisDeclaradas.put(identificador,("^\\d+"));
            }
        }
        else
        {
            variaveisDeclaradas.put(identificador,("^\\d+\\.\\d+"));
        }
    }
    public void validaInstrucao_leitura ()
    {

    }
    public void validaInstrucao_escrita ()
    {

    }
    public void validaInstrucao_enquanto ()
    {

    }
    public void validaInstrucao_se ()
    {

    }
    public void validaExpressao (String expresao,int nLinha,String nomeArquivo) {
        if (!expresao.matches("IDENTIFICADORRELOPIDENTIFICADOR"))
        {
            if (!expresao.matches("IDENTIFICADORADDOPIDENTIFICADOR"))
            {
                if (!expresao.matches("IDENTIFICADORMULOPIDENTIFICADOR"))
                {
                    if (!expresao.matches("IDENTIFICADORRELOPREAL"))
                    {
                        if (!expresao.matches("IDENTIFICADORADDOPREAL"))
                        {
                            if (!expresao.matches("IDENTIFICADORMULOPREAL"))
                            {
                                if (!expresao.matches("IDENTIFICADORRELOPINTEIRO"))
                                {
                                    if (!expresao.matches("IDENTIFICADORADDOPINTEIRO"))
                                    {
                                        if (!expresao.matches("IDENTIFICADORMULOPINTEIRO"))
                                        {
                                            if (!expresao.matches("REALRELOPIDENTIFICADOR"))
                                            {
                                                if (!expresao.matches("REALADDOPIDENTIFICADOR"))
                                                {
                                                    if (!expresao.matches("REALMULOPIDENTIFICADOR"))
                                                    {
                                                        if (!expresao.matches("REALRELOPREAL"))
                                                        {
                                                            if (!expresao.matches("REALADDOPREAL"))
                                                            {
                                                                if (!expresao.matches("REALMULOPREAL"))
                                                                {
                                                                    if (!expresao.matches("REALRELOPINTEIRO"))
                                                                    {
                                                                        if (!expresao.matches("REALADDOPINTEIRO"))
                                                                        {
                                                                            if (!expresao.matches("REALMULOPINTEIRO"))
                                                                            {
                                                                                if (!expresao.matches("INTEIRORELOPIDENTIFICADOR"))
                                                                                {
                                                                                    if (!expresao.matches("INTEIROADDOPIDENTIFICADOR"))
                                                                                    {
                                                                                        if (!expresao.matches("INTEIROMULOPIDENTIFICADOR"))
                                                                                        {
                                                                                            if (!expresao.matches("INTEIRORELOPREAL"))
                                                                                            {
                                                                                                if (!expresao.matches("INTEIROADDOPREAL"))
                                                                                                {
                                                                                                    if (!expresao.matches("INTEIROMULOPREAL"))
                                                                                                    {
                                                                                                        if (!expresao.matches("INTEIRORELOPINTEIRO"))
                                                                                                        {
                                                                                                            if (!expresao.matches("INTEIROADDOPINTEIRO"))
                                                                                                            {
                                                                                                                if (!expresao.matches("INTEIROMULOPINTEIRO"))
                                                                                                                {
                                                                                                                    erro.setErrorSintatico(201, "Erro na Expressao ", expresao, nomeArquivo, nLinha);
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean validaAtribuicao (String variavel,String separador,String valor,String delimitador,int nLine,String nomeArquivo)
    {
        if((variavel+separador+valor+delimitador).matches("IDENTIFICADORATRIBUICAO[a-zA-Z0-9]+;")
                && !(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:REAL;$")
                && !(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:INTEIRO;$")
                && !(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:CHARACTER;$")
                && !!(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:LOGICO;$"))
        {
            if(!valor.matches(variaveisDeclaradas.get(variavel)))
            {
                erro.setErrorSintatico(201,"Valor nao esperado",valor,nomeArquivo,nLine);

            }
        }
        else if (!(variavel+separador+valor+delimitador).matches("IDENTIFICADORATRIBUICAO[a-zA-Z0-9]+;")
                && !(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:REAL;$")
                && !(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:INTEIRO;$")
                && !(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:CHARACTER;$")
                && !!(variavel+separador+valor+delimitador).matches("^IDENTIFICADOR:LOGICO;$"))
        {
            erro.setErrorSintatico(201,"Atribuiçao incorreta",(variavel+separador+valor+delimitador),nomeArquivo,nLine);
            return true;
        }
        return false;
    }
}
