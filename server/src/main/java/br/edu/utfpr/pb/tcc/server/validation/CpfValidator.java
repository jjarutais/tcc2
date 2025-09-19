package br.edu.utfpr.pb.tcc.server.validation;

import br.edu.utfpr.pb.tcc.server.annotation.ValidCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return true; // permitir nulo ou vazio
        }

        cpf = cpf.replace(".", "").replace("-", "").trim();

        // Verifica tamanho e sequências inválidas (todos os números iguais)
        if (cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        try {
            Long.parseLong(cpf); // verifica se todos são números
        } catch (NumberFormatException e) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int d1 = 11 - (sum1 % 11);
        if (d1 >= 10) d1 = 0;

        // Calcula o segundo dígito verificador
        int sum2 = 0;
        for (int i = 0; i < 9; i++) {
            sum2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        sum2 += d1 * 2;
        int d2 = 11 - (sum2 % 11);
        if (d2 >= 10) d2 = 0;

        // Verifica se os dígitos calculados são iguais aos do CPF
        String digitoGerado = "" + d1 + d2;
        String digitoReal = cpf.substring(9, 11);

        return digitoGerado.equals(digitoReal);
    }
}