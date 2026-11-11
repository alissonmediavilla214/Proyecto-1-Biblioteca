package com.mediavilla.biblioteca.controller;

import com.mediavilla.biblioteca.model.biblioteca;
import com.mediavilla.biblioteca.service.bibliotecaservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteca")
public class bibliotecacontroller {
    private bibliotecaservice bibliotecaservice;

    public bibliotecacontroller( bibliotecaservice bibliotecaservice) {
        this.bibliotecaservice= bibliotecaservice;
    }

    @GetMapping
    public List<biblioteca> verTodos(){
        return bibliotecaservice.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<biblioteca> traerUno(@PathVariable int id){
        return bibliotecaservice.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<biblioteca> guardar(@RequestBody  biblioteca biblioteca){
        biblioteca nuevobiblioteca = bibliotecaservice.guardar(biblioteca);
        return new ResponseEntity<>(nuevobiblioteca, HttpStatus.CREATED);
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<biblioteca> editar(@PathVariable int id, @RequestBody biblioteca biblioteca){
        return bibliotecaservice.editar(id, biblioteca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <biblioteca> eliminar (@PathVariable int id){
        if (bibliotecaservice.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    // Libros disponibles
    @GetMapping("/disponibles")
    public List<biblioteca> disponibles() {
        return bibliotecaservice.disponibles();
    }





}