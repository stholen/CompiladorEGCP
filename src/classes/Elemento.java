package classes;

/**
 * Compilador EGCP
 */
public class Elemento {
    private String token;
    private String lexema = "";
    private int nLinha;
    private String nomeArquivo = "";
   
    public String getToken(){
        return token;
    }
    
    public void setToken(String token){
        this.token = token;
    }

    public int getnLinha() {
        return nLinha;
    }

    public void setnLinha(int nLinha) {
        this.nLinha = nLinha;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getLexema(){
        return lexema;
    }
    
    public void setLexema(String lexema){
        this.lexema = lexema;
        
    }

    public String toString(){
        if (lexema.isEmpty())
            return "< " + token + " >";
        else
            return "< " + token + ", " + lexema + " >";
    }  

}
