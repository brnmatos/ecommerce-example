package br.com.dpaulla.component;

import java.text.ParseException;
import java.time.LocalDate;
import br.com.dpaulla.model.User;

public class Util {
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean isValid(String cpfCnpj) {
        //return (isValidCPF(cpfCnpj) || isValidCNPJ(cpfCnpj));
    	return (isValidCPF(cpfCnpj));
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    private static boolean isValidCPF(String cpf) {
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    @SuppressWarnings("unused")
	private boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.trim().replace(".", "").replace("-", "");
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
    
	public String dateTimeNow() throws ParseException {
		LocalDate date =  LocalDate.now();
		String currentDate = date.toString().replace("-", "");
		return currentDate;
	}
	
	public String returnDDD(User userTelefone) {
		if (userTelefone.getTelefone() != null) {
			if (!userTelefone.getTelefone().equals("")) {
				String ddd = userTelefone.getTelefone().replace("(", "");
				ddd = ddd.replace(")", "");
				ddd = ddd.replace("-", "");
				ddd = ddd.substring(0, 2);
				return ddd;		
			}
		}else if(userTelefone.getCelular() != null){
			if (!userTelefone.getCelular().equals("")) {
				String ddd = userTelefone.getCelular().replace("(", "");
				ddd = ddd.replace(")", "");
				ddd = ddd.replace("-", "");
				ddd = ddd.substring(0, 2);
				return ddd;		
			}
		}
		return "";
	}
	
	public String returnDDD(String userTelefone) {
		if (userTelefone != null) {
			if (!userTelefone.equals("")) {
				String ddd = userTelefone.replace("(", "");
				ddd = ddd.replace(")", "");
				ddd = ddd.replace("-", "");
				ddd = ddd.substring(0, 2);
				
				return ddd;		
			}
		}
		return "";
	}
	
	public String returnTelefone(User userTelefone) {
		if (userTelefone.getTelefone() != null) {
			if (!userTelefone.getTelefone().equals("")) {
				String telefoneOnly = userTelefone.getTelefone().replace("(", "");
				telefoneOnly = telefoneOnly.replace(")", "");
				telefoneOnly = telefoneOnly.replace("-", "");
				telefoneOnly = telefoneOnly.substring(2, 10);
				return telefoneOnly;		
			}
		}else if(userTelefone.getCelular() != null){
			if (!userTelefone.getCelular().equals("")) {
				String telefoneOnly = userTelefone.getCelular().replace("(", "");
				telefoneOnly = telefoneOnly.replace(")", "");
				telefoneOnly = telefoneOnly.replace("-", "");
				telefoneOnly = telefoneOnly.substring(2, 11);
				return telefoneOnly;		
			}
		}
		return "";
	}
    
	public String returnTelefone(String userTelefone) {
		if (userTelefone != null) {
			if (!userTelefone.equals("")) {
				String telefoneOnly = userTelefone.replace("(", "");
				telefoneOnly = telefoneOnly.replace(")", "");
				telefoneOnly = telefoneOnly.replace("-", "");
				telefoneOnly = telefoneOnly.substring(2, 11);
				return telefoneOnly;		
			}
		}
		return "";
	}
	
	public String tratarCPF(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		return cpf;
	}
	
	public String tratarCEP(String cep) {
		cep = cep.replace(".", "");
		cep = cep.replace("-", "");
		
		return cep;
	}
    
	public String convertValue(String valor) {
		double value = Double.valueOf(valor);
	    return String.format("%.2f", (double)value).replace(",", ".");
	}
	
	
}
