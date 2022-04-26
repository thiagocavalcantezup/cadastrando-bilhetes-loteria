package br.com.zup.handora.cadastrandobilhetesloteria.bilhete;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BilheteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String telefoneCelular;

    @NotNull
    @Min(1)
    @Max(9999)
    private Integer numeroDaSorte;

    public BilheteRequest() {}

    public BilheteRequest(@NotBlank String nome,
                          @NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$") String telefoneCelular,
                          @NotNull @Min(1) @Max(9999) Integer numeroDaSorte) {
        this.nome = nome;
        this.telefoneCelular = telefoneCelular;
        this.numeroDaSorte = numeroDaSorte;
    }

    public Bilhete toModel() {
        return new Bilhete(nome, telefoneCelular, numeroDaSorte);
    }

    public String getNome() {
        return nome;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public Integer getNumeroDaSorte() {
        return numeroDaSorte;
    }

}
