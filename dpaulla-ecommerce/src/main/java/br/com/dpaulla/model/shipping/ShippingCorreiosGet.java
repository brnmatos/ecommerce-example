package br.com.dpaulla.model.shipping;

import java.io.Serializable;

public class ShippingCorreiosGet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//04510 PAC à vista			=	1
	//04014 SEDEX à vista		=	2
	//04782 SEDEX 12 ( à vista)	=	3
	//04790 SEDEX 10 (à vista)	=	3
	//04804 SEDEX Hoje à vista	=	3
	private String codigo;
	private String valor;
	private String prazoEntrega;
	private String valorSemAdicionais;
	private String valorMaoPropria;
	private String valorAvisoRecebimento;
	private String valorValorDeclarado;
	private String entregaDomiciliar;
	private String entregaSabado;
	private String erro;
	private String msgErro;
	private String obsFim;
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getCodigoDePara() {
		String dePara = codigo;
		switch (dePara) {
		case "04510":
			dePara = "1";
			break;
		case "04014":
			dePara = "2";
			break;

		default:
			dePara = "3";
			break;
		}
		return dePara;
	}

	public String getValor() {
		return valor;
	}

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public String getValorSemAdicionais() {
		return valorSemAdicionais;
	}

	public String getValorMaoPropria() {
		return valorMaoPropria;
	}

	public String getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}

	public String getValorValorDeclarado() {
		return valorValorDeclarado;
	}

	public String getEntregaDomiciliar() {
		return entregaDomiciliar;
	}

	public String getEntregaSabado() {
		return entregaSabado;
	}

	public String getErro() {
		return erro;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public String getObsFim() {
		return obsFim;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public void setValorSemAdicionais(String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}

	public void setValorMaoPropria(String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}

	public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}

	public void setValorValorDeclarado(String valorValorDeclarado) {
		this.valorValorDeclarado = valorValorDeclarado;
	}

	public void setEntregaDomiciliar(String entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}

	public void setEntregaSabado(String entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public void setObsFim(String obsFim) {
		this.obsFim = obsFim;
	}
}
