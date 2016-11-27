package classes.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Compilador EGCP
 */
public class ErrorLexico extends Error{
     
    @Override
    public String showErrors(){
        return "Erro " + getCodigo() + ": " + getDescricao() + "  em " + getNomeArquivo() + ":" + getNumLinha();
    }

    public void setErrorLexico (int codigo,String lexema,String nomeArquivo,int nLine)
    {
        Error erroLexico = new ErrorLexico();
        erroLexico.setCodigo(codigo);
        erroLexico.setDescricao("Identificador nao esperado: " + lexema);
        erroLexico.setNomeArquivo(nomeArquivo);
        erroLexico.setNumLinha(nLine);
        Error.addErro(erroLexico);

    }

    public Error getErrorLexico ()
    {
        List<Error> errosLexico = new ArrayList<>();
        for(int i = 0;i < Error.getErros().size();i++) {
            errosLexico.add(Error.getErros().get(i) instanceof ErrorLexico ? ((ErrorLexico) Error.getErros().get(i)) : null);
        }
        return (Error) errosLexico;
    }
}
