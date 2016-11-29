package classes;

import classes.error.Error;
import classes.error.ErrorLexico;
import classes.error.ErrorSintatico;

import javax.swing.*;
import java.io.IOException;
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

    AnalisadorLexico lexico;

    public AnalisadorSintatico(String caminhoArquivo)
    {
        lexico = new AnalisadorLexico(caminhoArquivo);

    }

    public void analisar ()
    {
        try {
            lexico.analisar();
            if(Error.getErros().size()>=1)
            {

                for(int i = 0 ; i < Error.getErros().size(); i++)
                {
                    Error erro = Error.getErros().get(i);
                    System.out.println(erro.showErrors());
                }

                System.exit(0);
            }
        

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha ao ler o arquivo.");
        }

        System.out.println("qqcoisa");
    }
}
