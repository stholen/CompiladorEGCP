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
            System.out.println(lexico.getTokens());
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
//        int i = 1;
//        while (i <= lexico.getTokens().size())
//        {
//            if(lexico.getTokens().get(i++).getToken().equals("INICIO"))
//            {
//                if(lexico.getTokens().get(i++).getToken().equals("IDENTIFICADOR"))
//                {
//                    if (lexico.getTokens().get(i++).getLexema().equals(";"))
//                    {
//                        if(lexico.getTokens().get(i).getLexema().equals("FIM"))
//                        {
//                            System.out.println("Compilado!");
//                        }
//                        else
//                        {
//                            System.out.println("Identificador 'FIM' esperado");
//                        }
//                    }
//                    else
//                    {
//                        System.out.println("';' esperado. Linha "+lexico.getTokens().get(i).getnLinha() );
//                    }
//                }
//                else
//                {
//                    System.out.println("Identificador esperado. Linha "+lexico.getTokens().get(i).getnLinha());
//                }
//            }
//            else
//            {
//                System.out.println("Palavra reservada 'INICIO' esperada. Linha"+lexico.getTokens().get(i).getnLinha());
//            }
//        }

    }
}
