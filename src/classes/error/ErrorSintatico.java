package classes.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stholen on 19/11/16.
 */
public class ErrorSintatico extends Error {

    @Override
    public String showErrors() {
        return "Erro " + getCodigo() + ": " + getDescricao() + "  em " + getNomeArquivo() + ":" + getNumLinha();
    }

    public void setErrorSintatico (int codigo,String msg,String lexema,String nomeArquivo,int nLine)
    {
        Error erroSintatico = new ErrorSintatico();
        erroSintatico.setCodigo(codigo);
        erroSintatico.setDescricao(msg +": " + lexema);
        erroSintatico.setNomeArquivo(nomeArquivo);
        erroSintatico.setNumLinha(nLine);
        Error.addErro(erroSintatico);

    }

    public Error getErrorSintatico ()
    {
        List<Error> errosSintatico = new ArrayList<>();
        for(int i = 0;i < Error.getErros().size();i++) {
            errosSintatico.add(Error.getErros().get(i) instanceof ErrorSintatico ? ((ErrorSintatico) Error.getErros().get(i)) : null);
        }
        return (Error) errosSintatico;
    }
}
