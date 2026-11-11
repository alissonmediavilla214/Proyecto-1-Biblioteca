package com.mediavilla.biblioteca.service;

import com.mediavilla.biblioteca.model.biblioteca;
import com.mediavilla.biblioteca.repository.bibliotecarepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class bibliotecaservice {

    private bibliotecarepository bibliotecaRepository1;

    public bibliotecaservice(bibliotecarepository bibliotecaRepository1){
        this.bibliotecaRepository1 = bibliotecaRepository1;
    }

    //TRAER TODOS
    public List <biblioteca> traerTodos(){
        return bibliotecaRepository1.findAll();
    }

    //TRAER UNO
    public Optional <biblioteca> traerUno(int id){
        return bibliotecaRepository1.findById(id);
    }

    //GUARDAR
    public biblioteca  guardar (biblioteca biblio){
        return bibliotecaRepository1.save(biblio);
    }

    //EDITAR
    public Optional <biblioteca> editar(int id, biblioteca bibliotecaActualziado){
        return bibliotecaRepository1.findById(id)
                .map( bibliotecaExiste ->{
                    bibliotecaExiste.setTitulo (bibliotecaActualziado.getTitulo());
                    bibliotecaExiste.setAutor(bibliotecaActualziado.getAutor());
                    bibliotecaExiste.setAnio( bibliotecaActualziado.getAnio());
                    bibliotecaExiste.setDisponible(bibliotecaActualziado.isDisponible());

                    return bibliotecaRepository1.save(  bibliotecaExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( bibliotecaRepository1.existsById(id)){
            bibliotecaRepository1.deleteById(id);
            return true;
        }else{
            return false;
        }


    }
    //disponible true
    public List<biblioteca> disponibles() {
        return bibliotecaRepository1.findAll()
                .stream()
                .filter(biblioteca -> biblioteca.isDisponible())
                .collect(Collectors.toList());
    }
}
