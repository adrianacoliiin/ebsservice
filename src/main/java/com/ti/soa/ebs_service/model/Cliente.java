package com.ti.soa.ebs_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Cliente {
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
    private String fecha_nacimiento;
}
