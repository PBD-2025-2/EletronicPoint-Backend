package pbd.ponto_eletronico.enums;

public enum GenderType {
    Male("male"),
    Female("female");

    private final String gender;

    GenderType(String gender){this.gender = gender;}

    public String getGender(){return gender;}
}
