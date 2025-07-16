package com.alura.forohub.controllers;

import com.alura.forohub.domain.topico.DatosRegistroTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
    Este Controller recibe requests de la aplicación Cliente.
 */

@RestController
@RequestMapping("/topicos")
public class TopicoController {


    // No olvidar @Transactional cuando se integre MySQL Driver
    // No olvidar que este método ya NO será String, será void
    @PostMapping
    public String registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico){
        return "Todo Correcto con el Registro";
    }

    @GetMapping
    public String listarTopicos(){
        return "Todo Correcto con listarTopicos";
    }


}
