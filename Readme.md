Nomes: Gabriel Wagner e Kathleen Alves

<h3>Compilador LPD</h3>

O projeto foi feito pelos dois participantes, trabalhando calls.
<br>
Linguagem: Java
<br>
IDE: Eclipse
<br>
Compilado para codigo intermediario <b>LLMV</b>

Arquivos:

<ul>
	<li><b>Chave: </b> Classe utilizada para criaçao de uma chave passada no HashMap</li>
	<li><b>CodIntermediario: </b> Classe responsavel por gerar o arquivo de codigo (codigo.ll)</li>
	<li><b>LexicoDois: </b> Classe utilizada para abertura do arquivo e identificacao dos tokens</li>
	<li><b>LexicoMain: </b> Classe principal onde é instanciada a PPRCodigo</li>
	<li><b>Parser: </b> Classe abstrata para qualquer parser</li>
	<li><b>PPR: </b> Classe onde fica o analisador lexico e semantico</li>
	<li><b>PPRCodigo: </b> Copia da classe PPR, porem com geracao de codigo junto</li>
	<li><b>TabelaSimbolos: </b> Classe para criacao do HashMap</li>
	<li><b>TipoToken: </b> Enum com os tipos dos tokens existentes</li>
	<li><b>Token: </b> Classe para criacao de um token</li>
	<li><b>teste1: </b> Codigo em lpd</li>
	<li><b>codigo.ll: </b> Codigo gerado em llvm</li>
</ul>