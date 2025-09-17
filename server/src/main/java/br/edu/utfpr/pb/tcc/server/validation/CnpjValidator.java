package br.edu.utfpr.pb.tcc.server.validation;

import br.edu.utfpr.pb.tcc.server.annotation.ValidCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<ValidCnpj, String> {

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return true;
        }

        cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");

        if (cnpj.length() != 14 || cnpj.matches(cnpj.charAt(0) + "{14}")) {
            return false;
        }

        try {
            long number = Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }

        int[] pesoCNPJ = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesoCNPJ2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Valida o primeiro dígito verificador
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        // Valida o segundo dígito verificador
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ2);

        return cnpj.substring(12).equals(digito1.toString() + digito2.toString());
    }

    private int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * peso[i];
        }
        soma = 11 - (soma % 11);
        return soma > 9 ? 0 : soma;
    }
}