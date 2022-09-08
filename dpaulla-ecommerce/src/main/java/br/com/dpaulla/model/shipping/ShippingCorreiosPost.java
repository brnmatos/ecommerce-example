package br.com.dpaulla.model.shipping;

import java.io.Serializable;

import lombok.Data;

@Data public class ShippingCorreiosPost implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*		Código Serviço
	04014 SEDEX à vista
	04510 PAC à vista
	04782 SEDEX 12 ( à vista)
	04790 SEDEX 10 (à vista)
	04804 SEDEX Hoje à vista */
	
	private String nCdEmpresa; //empresa no contrato, se nao tiver igual a vazio.
	private String sDsSenha;	//senha no contrato, se nao tiver igual a vazio.
	private String sCepOrigem;// = "&sCepOrigem=12952060"; 	//cep sem hifen ou ponto.
	private String sCepDestino;// = "&sCepDestino=13253320";	//cep sem hifen ou ponto.
	private String nVlPeso;		//em KG 
	private String nCdFormato;		//1 - caixa pacote, 2 - rolo prisma, 3 - carta
	private String nVlComprimento;	//em centimetros
	private String nVlAltura;		//em centimetros
	private String nVlLargura;		//em centimetros
	private String sCdMaoPropria;		//n
	private String nVlValorDeclarado;	//n
	private String sCdAvisoRecebimento;//n
	private String nCdServico;	//tabela de codigo acima
	private String nVlDiametro;		//default, 0 pois nao é prisma.
	private String StrRetorno;		//default do retorno, xml
	private String nIndicaCalculo;		//
	private String sDtCalcul;// = "&sDtCalcul=20200820"; //formato data aaaammdd
	
	
}
