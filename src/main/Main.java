package main;

import classes.AnalisadorLexico;
import classes.AnalisadorSintatico;
import classes.error.Error;
import classes.tabelaDeSimbolos.Simbolos;

import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * Compilador EGCP
 */
public class Main {

    public static void main(String[] args) throws IOException {

        JFileChooser scanner = new JFileChooser("./src/TestesLexico");
        int retorno = scanner.showOpenDialog(null);
        String caminhoArquivo = null;

        if(retorno == JFileChooser.APPROVE_OPTION)
        {
            caminhoArquivo = scanner.getSelectedFile().getPath();

        }

//        AnalisadorSintatico sintatico = new AnalisadorSintatico(caminhoArquivo);
//        sintatico.analisar();

AnalisadorLexico analisador = new AnalisadorLexico(caminhoArquivo);
        try{analisador.analisar();
            System.out.println("Pares <token,lexema> \n"+ analisador.getTokens());
            System.out.println("\n\nLista de erros léxicos");
            int i;
            for (i = 0; i < Error.getErros().size();i++){
                Error erro = Error.getErros().get(i);
                System.out.println(erro.showErrors());
            }
            System.out.println("\n\nTabela de símbolos\n" + Simbolos.getTabelaDeSimbolos());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao ler o arquivo.");
        }
    }
}
