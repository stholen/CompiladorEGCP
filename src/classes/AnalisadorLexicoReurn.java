package classes;

import classes.error.Error;
import classes.error.ErrorLexico;
import classes.tabelaDeSimbolos.Simbolos;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexicoReurn
{
    private BufferedReader codigoFonte;
    private String linha;
    private String caractere;
    private String inteiro;
    private String real;
    private String logico;
    private String character;
    private String identificador;
    private String literais;
    private String palavra = "";
    private String nomeArquivo;
    private final List delimitadores = new ArrayList();
    private final List RELOP = new ArrayList();
    private final List ADDOP = new ArrayList();
    private final List MULOP = new ArrayList();
    private final List reservadas = new ArrayList();
    private final List operAtribuicao = new ArrayList();
    private final List<Elemento> tokens = new ArrayList();
    private boolean inComment = false;

    public AnalisadorLexicoReurn(String pathFile)
    {
        try
        {
            int i = 10; 
            this.nomeArquivo = pathFile;
            inteiro = ("^\\d+");
            character = ("\\d{1}");
            real = ("^\\d+\\.\\d+");
            identificador = ("^[a-z]+\\w+[_w]*|^_[a-z]+\\w+[_w]*|^[a-z]\\w?$");
            literais = ("^\".*\"$");
            delimitadores.add(" ");
            delimitadores.add(",");
            delimitadores.add(";");
            delimitadores.add(":");
            delimitadores.add("(");
            delimitadores.add(")");
            delimitadores.add("\\n");
            delimitadores.add("\\r");
            delimitadores.add("\\t");
            delimitadores.add("{");
            delimitadores.add("}");
            
            RELOP.add("==");
            RELOP.add("!=");
            RELOP.add(">=");
            RELOP.add("<=");
            RELOP.add(">");
            RELOP.add("<");

            ADDOP.add("or");
            ADDOP.add("+");
            ADDOP.add("-");

            MULOP.add("*");
            MULOP.add("/");
            MULOP.add("div");
            MULOP.add("mod");
            MULOP.add("and");

            operAtribuicao.add("=");
            operAtribuicao.add("+=");
            operAtribuicao.add("-=");
            operAtribuicao.add("*=");
            operAtribuicao.add("/=");
            operAtribuicao.add("%=");
            operAtribuicao.add("++");
            operAtribuicao.add("--");

            reservadas.add("programa");
            reservadas.add("inicio");
            reservadas.add("se");
            reservadas.add("entao");
            reservadas.add("senao");
            reservadas.add("escreva");
            reservadas.add("leia");
            reservadas.add("enquanto");
            reservadas.add("caractere");
            reservadas.add("real");
            reservadas.add("inteiro");

            codigoFonte = new BufferedReader(new FileReader(pathFile));
        }
        catch (FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
        }
    }
    
    public void analisar() throws IOException
    {
        int nLine = 0;
        while (true)
        {
            nLine++;

            linha = codigoFonte.readLine();

            if (linha == null)
            {
                break;
            }

            int size = linha.length();
            linha = linha.split("\r")[0];
            caractere = "";

            for (int i = 0; i < size;i++)
            {
                caractere = linha.substring(i,i+1);
                if (delimitadores.contains(caractere))
                {
                     if ((!inComment) && (palavra.length() >= 2) && (palavra.substring(0,2).equals(
                        "//")))
                     {
                        palavra = "";
                        break;
                     }
                     if ((!inComment) && (palavra.length() >= 2) && (palavra.substring(0,2).equals(
                        "/*")))
                     {
                        palavra = "";
                        inComment = true;
                     }

                     if ((inComment) && (palavra.length() >= 2) && (palavra.substring(0,2).equals(
                        "*/")))
                     {
                        palavra = "";
                        inComment = false;
                     }
                     
                    if (!inComment)
                    {
                        if ((!palavra.equals("")) && (!palavra.contains("/*")))
                        {
                            this.addToken(palavra, nLine);
                        }
                        //alinhar o token correspondente
                    }
                        palavra = "";
                    
                }
                else
                    palavra = palavra + caractere;
            }
            
        }
    }

    private void addToken(String palavra,int nLine)
    {
        if (palavra.matches(inteiro))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("INTEIRO");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        if (palavra.matches(real))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("REAL");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        if (palavra.matches(character))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("CHARACTER");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        
        if (palavra.matches(literais))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("LITERAL");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
                   
        if (RELOP.contains(palavra))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("RELOP");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        if (ADDOP.contains(palavra))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("ADDOP");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        if (MULOP.contains(palavra))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("MULOP");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        
        if (reservadas.contains(palavra))
        {
            Elemento elemento = new Elemento();
            elemento.setToken(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        if (operAtribuicao.contains(palavra))
        {
            Elemento elemento = new Elemento();
            elemento.setToken("ATRIBUICAO");
            elemento.setLexema(palavra);
            elemento.setNomeArquivo(this.nomeArquivo);
            tokens.add(elemento);
            return;
        }
        if(!palavra.equals(reservadas))
        {
            if (palavra.matches(identificador))
            {
                Elemento elemento = new Elemento();
                elemento.setToken("IDENTIFICADOR");
                elemento.setLexema(palavra);
                elemento.setNomeArquivo(this.nomeArquivo);
                tokens.add(elemento);
                Simbolos simbolo = new Simbolos();
                simbolo.setNome(palavra);
                Simbolos.addSimbolo(simbolo);
                return;
            }

            
        }
        

        ErrorLexico erro = new ErrorLexico();
        erro.setErrorLexico(101,palavra,this.nomeArquivo,nLine);
        erro.setCodigo(101);
        Error.addErro(erro);
    }
    
    
    
    public List<Elemento> getTokens(){
        return tokens;
    }
}
