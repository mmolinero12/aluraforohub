package com.alura.forohub.domain.topico;

public enum Curso {
    LSF("Java: trabajando con lambdas, streams y Spring Framework"),
    PDCJPA("Java: persistencia de datos y consultas con Spring Data JPA"),
    CPAPICFE("Java: creando tu primera API y conectándola al Front End"),
    PSBCL("Practicando Spring Boot: Challenge Literalura"),
    SB3DAPIREST("Spring Boot 3: desarrolla una API REST en Java"),
    SB4BPPAPIREST("Spring Boot 4: aplique las mejores prácticas y proteja una API Rest"),
    SB5DPPAPI("Spring Boot 5: documentar, probar y preparar una API para su implementación"),
    PSFCFH("Practicando Spring Framework: Challenge Foro Hub");

    private final String descripcion;

    // Constructor to initialize the string value
    Curso(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter method to retrieve the string value
    public String getDescripcion() {
        return descripcion;
    }
}
