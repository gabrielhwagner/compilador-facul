
public class PPRCodigo extends Parser{

	public PPRCodigo(String arquivo) {
		super();
		lexico.analisa(arquivo);
	}
	
	@Override
	public void parse() {
		analisaPrograma();
		
		salvaCodigo();
	}
	
	public boolean analisaPrograma() {
		buscaToken();
		if(token.tipo == TipoToken.SPROGRAMA) {
			System.out.println(token.tipo + " ");
			buscaToken();
			
			if(token.tipo == TipoToken.SIDENTIFICADOR) {
				System.out.println(token.tipo + ": " + token.lexema + " ");
				ts.addToken(token);
				buscaToken();
				
				if(token.tipo == TipoToken.SPONTO_E_VIRGULA) {
					System.out.println(token.tipo + " ");
					if(analisaBloco()) {
						buscaToken();
						if(token.tipo == TipoToken.SPONTO) {
							return true;
						}
					} else {
						return erro("Bloco principal nao encontrado");
					}
				} else {
					return erro("Ponto e virgula esperado");
				}
			} else {
				return erro("Identificador esperado");
			}
		}
		return erro("Programa não declarado");
	}

	private boolean analisaBloco() {
		buscaToken();
		Boolean variavel = analisaEtVariaveis();
		Boolean comando = analisaComandos();
		return variavel && comando;
	}
	
	private boolean analisaComandos() {
		if(token.tipo == TipoToken.SINICIO) {
			buscaToken();
			analisaComandoSimples();
			while(token.tipo != TipoToken.SFIM) {
				if(token.tipo == TipoToken.SPONTO_E_VIRGULA) {
					buscaToken();
					if(token.tipo != TipoToken.SFIM) {
						analisaComandoSimples();
					} else {						
						return true;
					}
				} else {
					return erro("Ponto e virgula esperado");
				}
			}
			buscaToken();
			return true;
		} else {
			return erro("Programa não iniciado");
		}
	}
	
	private boolean analisaComandoSimples() {
		if(token.tipo == TipoToken.SIDENTIFICADOR) {
			return analisaAtribuicao();
		} else if(token.tipo == TipoToken.SESCREVA) {
			return analisaEscreva();
		} else {
			return analisaComandos();
		}
	}
	
	private boolean analisaAtribuicao() {
		buscaToken();
		if(token.tipo == TipoToken.SATRIBUICAO) {
			// ANALISAR ATRIBUIÇÃO ATÉ O FIM
			
			// Codigo para atribuir o valor na variavel
			//store i32 3, i32* %ptr
			
			// Codigo para somar
			//%a = add i32 1336, %ptr
			
			while(token.tipo != TipoToken.SPONTO_E_VIRGULA) {
				buscaToken();
			}
			return true;
			//return analisaAtribuicao();
		} else {
			return erro("Atribuição esperada");
		}
	}
	
	private boolean analisaEscreva() {
		buscaToken();
		if(token.tipo == TipoToken.SABRE_PARENTESIS) {
			buscaToken();
			if(token.tipo == TipoToken.SIDENTIFICADOR) {
				// Verifica se o identificador foi declarado
				//ts.getToken(token) != null
				if(true) {
					// Declara o printf
					geraCod("call i32 (i8*, ...) @printf( i8* " + token.codigo +" ) nounwind");
					buscaToken();
					if(token.tipo == TipoToken.SFECHA_PARENTESIS) {
						buscaToken();
						return true;
					} else {
						return erro("Fecha parentesis esperado");
					}
				} else {
					return erro("Identificador não encontrado");
				}
			} else {
				return erro("Identificador esperado");
			}
		} else {
			return erro("Abre parentesis esperado");
		}
	}

	// ANALISAR
	private boolean analisaEtVariaveis() {
        if(token.tipo == TipoToken.SVAR) {
            buscaToken();
            if(token.tipo == TipoToken.SIDENTIFICADOR) {
                while(token.tipo == TipoToken.SIDENTIFICADOR) {
                    analisaVariaveis();
                    if(token.tipo == TipoToken.SPONTO_E_VIRGULA) {
                        buscaToken();
                        return true;
                    } else {
                        return erro("Ponto e virgula esperado");
                    }
                }
            } else {
            	return erro("Identificador esperado");
            }
        }
        return true;
    }

	private void analisaVariaveis() {
		do {
			if(token.tipo == TipoToken.SIDENTIFICADOR) {
				// Verifica se ja nao tem o identificador com o mesmo nome
                Boolean duplicado = ts.getToken(token) != null;
                if(!duplicado) { 
                	// Se nao tiver, gera uma variavel e salva na tabela de simbolos
                	// criar codigo de alocacao de variavel
                	token.codigo = geraTemp();
                	ts.addToken(token);
                	
                    buscaToken();
                    if((token.tipo == TipoToken.SVIRGULA) || (token.tipo == TipoToken.STIPO)){
                        if(token.tipo == TipoToken.SVIRGULA) {
                            buscaToken();
                            if(token.tipo == TipoToken.STIPO) {
                                erro("Não se usa dois pontos depois de uma vírgula.");
                            }
                        } 
                    } else {
                        erro("O símbolo não é uma vírgula.");
                    }
                } else {
                    erro("Existe duplicidade de variaveis.");
                }
            } else {
                erro("Não é um identificador.");
            }
		} while(token.tipo != TipoToken.STIPO);
            
        buscaToken();
        analisaTipo();
    }

	
	private boolean analisaTipo() {
		if(token.tipo != TipoToken.SINTEIRO && token.tipo != TipoToken.SBOOLEANO) {
			return erro("Tipo de variavel não existe");
		} else {
			//colocaTipoTabela();
			buscaToken();
			return true;
		}
	}
	
	public String geraTemp() {
		Integer i = temp++;
		String nome = '%' + i.toString();
		geraCod(nome + " = alloca i32, align 4");
		return nome;
	}
	
	
	public void geraCod(String comando) {
		codigo += comando + "\n";
	}
	
	public void salvaCodigo() {
		CodIntermediario cod = new CodIntermediario(temp, codigo);
		cod.geraLLVMIR();
	}
}
