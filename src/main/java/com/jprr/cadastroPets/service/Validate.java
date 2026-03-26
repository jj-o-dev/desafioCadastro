package main.java.com.jprr.cadastroPets.service;

import main.java.com.jprr.cadastroPets.model.entity.PetAddress;
import main.java.com.jprr.cadastroPets.model.enums.PetType;
import main.java.com.jprr.cadastroPets.model.enums.PetSex;
import main.java.com.jprr.cadastroPets.model.exceptions.PetInfoException;

public final class Validate {
    public static void name(String name) {
        name = name.trim();

        if (name.isEmpty()) {
            return;
        }

        String[] aux = name.split(" ");

        if (aux.length < 2) {
            throw new PetInfoException("Precisa ter sobrenome!");
        }
        if (!name.matches("^[a-zA-Z ]*$")) {
            throw new PetInfoException("Digite apenas letras sem acento!");
        }
    }

    public static PetType type(String type) {
        type = type.trim().toUpperCase();

        if (type.equals("CACHORRO") || type.equals("CAO") || type.equals("DOG") || type.equals("C")) {
            return PetType.DOG;
        } else if (type.equals("GATO") || type.equals("CAT") || type.equals("G")) {
            return PetType.CAT;
        } else {
            throw new PetInfoException("Tipo de pet inválido!");
        }
    }

    public static PetSex sex(String sex) {
        sex = sex.trim().toUpperCase();

        if (sex.equals("FEMEA") || sex.equals("FEMALE") || sex.equals("F")) {
            return PetSex.FEMALE;
        } else if (sex.equals("MACHO") || sex.equals("MALE") || sex.equals("M")) {
            return PetSex.MALE;
        } else {
            throw new PetInfoException("Sexo inválido!");
        }
    }

    public static PetAddress address (String num, String city, String street) {
        num = num.trim();
        city = city.trim();
        street = street.trim();

        if (!num.matches("^\\d*$")) {
            throw new PetInfoException("Número de endereço inválido!");
        }

        if (num.isEmpty()) {
            num = "0";
        }
        int n = Integer.parseInt(num);

        if (!city.matches("^[a-zA-Z ]*$")) {
            throw new PetInfoException("Nome de cidade inválida!");
        }
        if (!street.matches("^[a-zA-Z0-9 ]*$")) {
            throw new PetInfoException("Nome de rua inválida!");
        }

        return new PetAddress(n, city, street);
    }

    public static double age(String age, String timeUnit) {
        timeUnit = timeUnit.trim().toUpperCase();
        age = age.trim();

        if (age.isEmpty()) {
            return 0.0d;
        }

        if (!age.matches("^[0-9.]*$") || Integer.parseInt(age) > 20) {
            throw new PetInfoException("Idade inválida!");
        }

        if (timeUnit.equals("MES") || timeUnit.equals("MESES") || timeUnit.equals("MONTH") || timeUnit.equals("M")) {
            age = "0." + age;
        }
        return Double.parseDouble(age);
    }

    public static double weight(String weight) {
        weight = weight.trim();

        if (weight.isEmpty()) {
            return 0.0d;
        }

        double converted = Double.parseDouble(weight);

        if (converted > 60.0d || converted < 0.5d) {
            throw new PetInfoException("Peso inválido!");
        }
        return converted;
    }

    public static void breed(String breed) {
        breed = breed.trim();

        if (breed.isEmpty()) {
            return;
        }

        if (!breed.matches("^[a-zA-Z ]*$")) {
            throw new PetInfoException("Digite apenas letras sem acento!");
        }
    }
}
