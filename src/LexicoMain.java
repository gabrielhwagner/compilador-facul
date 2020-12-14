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
		TabelaSimbolos lt = new TabelaSimbolos();
		
		//Cria instancia da classe analisador léxico
		LexicoDois lexico = new LexicoDois();
		
		//Realiza a análise léxica
		lt = lexico.analisa("teste1.lpd");
		
		//Imprime número de tokens
		System.out.println("Número de tokens: " +lt.lt.size());
	
		//Percorre o HashMap de tokens imprimindo-os os valores
		for (Map.Entry<Chave, Token> token : lt.lt.entrySet()) {
		     System.out.println(token.getValue()); 
		}
		
		System.out.println("Tipos de token:");
		
		//Imprime o enum de tipo de tokens
		System.out.println(Arrays.toString(TipoToken.values()));
		
	}

}
