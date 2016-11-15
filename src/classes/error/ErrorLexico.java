package classes.error;

/**
 * Compilador EGCP
 */
public class ErrorLexico extends Error{
     
    @Override
    public String showErrors(){
        return "Erro " + getCodigo() + ": " + getDescricao() + "  em " + getNomeArquivo() + ":" + getNumLinha();
    }
}
