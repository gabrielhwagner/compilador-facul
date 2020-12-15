import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Nomes: Gabriel Wagner e Kathleen Alves
 * Criação: O código do compilador foi feito através de ligação pelo discord,
 * onde os dois participantes tiveram ação direta nele.
 */
public class LexicoMain {

	public static void main(String[] args) {
		//Cria HashMap de tokens
		//List lt = new Arraylist<Token>();
		
		//Cria instancia da classe analisador léxico
		PPRCodigo ppr = new PPRCodigo("teste1.lpd");
		ppr.parse();
		//Realiza a análise léxica
		//lt = lexico.analisa("teste1.lpd");

		
	}



}
