package classes;

import classes.error.ErrorSintatico;

/**
 * Created by stholen on 27/11/16.
 */
public class AnalisadorSintatico2 {

        AnalisadorLexicoReurn lexico;
        ErrorSintatico erroSintatico;
        int i = 0;
        String token;
        char str;

        public AnalisadorSintatico2(String caminhoArquivo)
        {
            lexico = new AnalisadorLexicoReurn(caminhoArquivo);
            erroSintatico = new ErrorSintatico();

            if(lexico.getTokens().get(i++).getToken().equals("INICIO"))
            {
                if(lexico.getTokens().get(i++).getToken().equals("IDENTIFICADOR"))
                {
                     if(lexico.getTokens().get(i++).getLexema().equals(";"))
                     {
                        AnalisaBloco();
                        if(lexico.getTokens().get(i).getLexema().equals("FIM"))
                        {
                            System.out.print("Compilado.");
                        }
                        else
                        {
                            erroSintatico.setErrorSintatico(201,"Token 'FIM' esperada",null,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
                        }

                    }
                    else
                     {
                         erroSintatico.setErrorSintatico(201,"Token ';' esperada",null,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
                    }
                }
                else
                {
                    erroSintatico.setErrorSintatico(201,"Identificador esperado",null,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
                }
            }
            else
            {
                erroSintatico.setErrorSintatico(201,"Palavra reservada 'INICIO' esperada",null,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
            }

        }

        private void AnalisaBloco()
        {

            AnalisaETVariaveis();
            AnalisaSubRotinas();
            AnalisaComandos();

        }

        private void AnalisaETVariaveis()
        {
            if(lexico.getTokens().get(i++).getToken().equals("INTEIRO")
                    | lexico.getTokens().get(i++).getToken().equals("REAL")
                    | lexico.getTokens().get(i++).getToken().equals("INTEIRO")
                    | lexico.getTokens().get(i++).getToken().equals("LOGICO")
                    | lexico.getTokens().get(i++).getToken().equals("STRING"))
            {
                if(lexico.getTokens().get(i++).getToken().equals("IDENTIFICADOR"))
                {
                    while(lexico.getTokens().get(i++).getToken().equals("IDENTIFICADOR"))
                    {
                        AnalisaVariaveis();
                        if(!lexico.getTokens().get(i++).getLexema().equals(";"))
                        {
                            erroSintatico.setErrorSintatico(201,"Token ';' esperada",null,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
                        }
                    }

                }
                else
                    erroSintatico.setErrorSintatico(201,"Identificador esperado",null,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
            }

        }

        private void AnalisaVariaveis()
        {
            do
            {
                if (lexico.getTokens().get(i).getToken().equals("IDENTIFICADOR")) {
                    if (lexico.getTokens().get(i).getLexema().equals(",") || lexico.getTokens().get(i).getLexema().equals(":")) {
                        if ((lexico.getTokens().get(i++).getLexema().equals(","))) {
                            if ((lexico.getTokens().get(i++).getLexema().equals(":"))) {
                                erroSintatico.setErrorSintatico(201, "Token nao eserada", lexico.getTokens().get(i).getLexema()
                                        , lexico.getTokens().get(i).getNomeArquivo(), lexico.getTokens().get(i).getnLinha());
                            }

                        }

                    } else {
                        erroSintatico.setErrorSintatico(201, "Token ':' ou ',' esperada", null
                                , lexico.getTokens().get(i).getNomeArquivo(), lexico.getTokens().get(i).getnLinha());
                    }

                } else {
                    erroSintatico.setErrorSintatico(201, "Token nao eserada", lexico.getTokens().get(i).getLexema()
                            , lexico.getTokens().get(i).getNomeArquivo(), lexico.getTokens().get(i).getnLinha());
                }
            }
            while(lexico.getTokens().get(i).getLexema().equals(":"));

            AnalisaTipo();

        }

        private void AnalisaTipo()
        {
            if((!lexico.getTokens().get(i).getToken().equals("T_INTEIRO")) && (!lexico.getTokens().get(i).getToken().equals("T_LOGICO")) && (!lexico.getTokens().get(i).getToken().equals("T_REAL")) && (!lexico.getTokens().get(i).getToken().equals("T_CHARACTER")) && (!lexico.getTokens().get(i).getToken().equals("T_STRING")))
                erroSintatico.setErrorSintatico(201,"Tipo de variavel invalido",lexico.getTokens().get(i).getLexema()
                                                ,lexico.getTokens().get(i).getNomeArquivo(),lexico.getTokens().get(i).getnLinha());
            }

        private void AnalisaSubRotinas()
        {
            int flag;
            flag = 0;

            if(("Sprocedimento".equals(token.getSimbolo())) || ("Sfuncao".equals(token.getSimbolo()))){

            }

            while(("Sprocedimento".equals(token.getSimbolo())) || ("Sfuncao".equals(token.getSimbolo()))){

                if("Sprocedimento".equals(token.getSimbolo()))
                    AnalisaDeclaracaoProcedimento();
                else
                    AnalisaDeclaracaoFuncao();

                if(("Sponto_virgula".equals(token.getSimbolo())))
                    token = lexico.getToken();
                else
                    System.out.println("';' esperado. Linha:" + lexico.getLinha());

            }

        }

        private void AnalisaComandos() throws IOException {

            if("Sinicio".equals(token.getSimbolo())){

                token = lexico.getToken();
                AnalisaComandoSimples();

                while(!"Sfim".equals(token.getSimbolo())){

                    if("Sponto_virgula".equals(token.getSimbolo())){

                        token = lexico.getToken();

                        if(!"Sfim".equals(token.getSimbolo()))
                            AnalisaComandoSimples();

                    }else
                        System.out.println("; esperado. Linha:" + lexico.getLinha());

                }
                token = lexico.getToken();

            }else
                System.out.println("Palavra reservada 'inicio' esperada. Linha:" + lexico.getLinha());


        }

        private void AnalisaComandoSimples() throws IOException {

            if("Sidentificador".equals(token.getSimbolo()))
                AnalisaAtribChProcedimento();

            else{
                if("Sse".equals(token.getSimbolo()))
                    AnalisaSe();

                else{
                    if("Senquanto".equals(token.getSimbolo()))
                        AnalisaEnquanto();

                    else{
                        if("Sleia".equals(token.getSimbolo()))
                            AnalisaLeia();

                        else{
                            if("Sescreva".equals(token.getSimbolo()))
                                AnalisaEscreva();

                            else
                                AnalisaComandos();
                        }
                    }
                }
            }
        }

        private void AnalisaAtribChProcedimento() throws IOException {

            token = lexico.getToken();

            if("Satribuicao".equals(token.getSimbolo()))
                AnalisaAtribuicao();
            else
                ChamadaProcedimento();

        }


        private void AnalisaEscreva() throws IOException {

            token = lexico.getToken();
            if("(".equals(token.getLexema())){

                token = lexico.getToken();

                if("Sidentificador".equals(token.getSimbolo())){

                    //se pesquisadeclatabl(token.lexema)
                    token = lexico.getToken();

                    if(")".equals(token.getLexema()))
                        token = lexico.getToken();
                    else
                        System.out.println("')' esperado. Linha:" + lexico.getLinha());
                    //senao erro
                }else
                    System.out.println("Identificador esperado. Linha:" + lexico.getLinha());

            }else
                System.out.println("'(' esperado. Linha:" + lexico.getLinha());

        }

        private void AnalisaEnquanto() throws IOException {

            token = lexico.getToken();
            AnalisaExpressao();

            if("Sfaca".equals(token.getSimbolo())){

                token = lexico.getToken();
                AnalisaComandoSimples();

            }else
                System.out.println("Palavra reservada 'faca' esperada. Linha:" + lexico.getLinha());

        }

        private void AnalisaLeia() throws IOException {

            token = lexico.getToken();
            if("(".equals(token.getLexema())){

                token = lexico.getToken();

                if("Sidentificador".equals(token.getSimbolo())){

                    //se pesquisadeclatabl(token.lexema)
                    token = lexico.getToken();

                    if(")".equals(token.getLexema()))
                        token = lexico.getToken();
                    else
                        System.out.println("')' esperado. Linha:" + lexico.getLinha());
                    //senao erro
                }else
                    System.out.println("Identificador esperado. Linha:" + lexico.getLinha());

            }else
                System.out.println("'(' esperado. Linha:" + lexico.getLinha());

        }

        private void AnalisaSe() throws IOException {

            token = lexico.getToken();
            AnalisaExpressao();

            if("Sentao".equals(token.getSimbolo())){

                token = lexico.getToken();
                AnalisaComandoSimples();

                if("Ssenao".equals(token.getSimbolo())){
                    token = lexico.getToken();
                    AnalisaComandoSimples();
                }

            }else
                System.out.println("Palavra reservada 'entao' esperada. Linha:" + lexico.getLinha());

        }

        private void AnalisaExpressao() throws IOException {

            AnalisaExpressaoSimples();

            if(("Smaior".equals(token.getSimbolo())) || ("Smaiorig".equals(token.getSimbolo())) || ("Sig".equals(token.getSimbolo())) || ("Smenor".equals(token.getSimbolo())) || ("Smenorig".equals(token.getSimbolo())) || ("Sdif".equals(token.getSimbolo()))){

                token = lexico.getToken();
                AnalisaExpressaoSimples();
            }

        }

        private void AnalisaDeclaracaoProcedimento() throws IOException {

            token = lexico.getToken();

            if("Sidentificador".equals(token.getSimbolo())){

                token = lexico.getToken();

                if("Sponto_virgula".equals(token.getSimbolo()))
                    AnalisaBloco();
                else
                    System.out.println("';' esperado. Linha:" + lexico.getLinha());
            }else
                System.out.println("Identificador esperado. Linha:" + lexico.getLinha());

        }

        private void AnalisaDeclaracaoFuncao() throws IOException {

            token = lexico.getToken();

            if("Sidentificador".equals(token.getSimbolo())){

                token = lexico.getToken();

                if("Sdoispontos".equals(token.getSimbolo())){

                    token = lexico.getToken();

                    if(("Sinteiro".equals(token.getSimbolo())) || ("Sbooleano".equals(token.getSimbolo()))){

                        token = lexico.getToken();

                        if("Sponto_virgula".equals(token.getSimbolo()))
                            AnalisaBloco();
                    }else
                        System.out.println("Tipo invalido. Linha:" + lexico.getLinha());
                }else
                    System.out.println("':' esperado. Linha:" + lexico.getLinha());
            }else
                System.out.println("Identificador esperado. Linha:" + lexico.getLinha());
        }

        private void AnalisaExpressaoSimples() throws IOException {

            if(("Smais".equals(token.getSimbolo())) || ("Smenos".equals(token.getSimbolo())))
                token = lexico.getToken();

            AnalisaTermo();

            while(("Smais".equals(token.getSimbolo())) || ("Smenos".equals(token.getSimbolo())) || ("Sou".equals(token.getSimbolo()))){

                token = lexico.getToken();
                AnalisaTermo();
            }

        }

        private void AnalisaTermo() throws IOException {

            AnalisaFator();

            while(("Smult".equals(token.getSimbolo())) || ("Sdiv".equals(token.getSimbolo())) || ("Se".equals(token.getSimbolo()))){

                token = lexico.getToken();
                AnalisaFator();
            }
        }

        private void AnalisaFator() throws IOException {

            if("Sidentificador".equals(token.getSimbolo()))
                AnalisaChamadaFuncao();
            else{

                if("Snumero".equals(token.getSimbolo()))
                    token = lexico.getToken();
                else{
                    if("Snao".equals(token.getSimbolo())){

                        token = lexico.getToken();
                        AnalisaFator();
                    }else{

                        if("Sabre_parenteses".equals(token.getSimbolo())){

                            token = lexico.getToken();
                            AnalisaExpressao();

                            if("Sfecha_parenteses".equals(token.getSimbolo()))
                                token = lexico.getToken();
                            else
                                System.out.println("')' esperado. Linha:" + lexico.getLinha());
                        }else{

                            if(("verdadeiro".equals(token.getLexema())) || ("falso".equals(token.getLexema())))
                                token = lexico.getToken();
                            else
                                System.out.println("verdadeiro ou falso esperado. Linha:" + lexico.getLinha());
                        }
                    }

                }
            }
        }

        private void AnalisaChamadaFuncao() throws IOException {

            token = lexico.getToken();
        }

        private void ChamadaProcedimento() throws IOException {

        /*if("Sponto_virgula".equals(token.getSimbolo())){

            token = lexico.getToken();
            AnalisaComandoSimples();
        }else
            System.out.println("';' esperado. Linha:" + lexico.getLinha());*/
        }

        private void AnalisaAtribuicao()
        {
            AnalisaExpressao();
        }

    }
}
