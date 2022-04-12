package com.RegistroTareasAPI.RegistroTareas.controller;

import com.RegistroTareasAPI.RegistroTareas.model.Tarea;
import com.RegistroTareasAPI.RegistroTareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Tarea>> getAllTareas(){
        List<Tarea> listadoTareas = tareaService.getAllTareas();
        //return tareaService.getAllTareas();
        return ResponseEntity.status(HttpStatus.OK).body(listadoTareas);
    }

    @PostMapping("/add")
    public ResponseEntity<Tarea> add(@RequestParam(value = "descripcion", required = true) String descripcion,
                                     @RequestParam(value = "vigente", required = true) boolean vigente)
    {
        Tarea nuevaTarea = new Tarea(descripcion,vigente);
        tareaService.guardaTarea(nuevaTarea);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestParam(value = "identificador", required = true) Integer identificador,
                                 @RequestParam(value = "descripcion", required = true) String descripcion,
                                 @RequestParam(value = "vigente", required = true) boolean vigente){
        try{
            Tarea tareaBuscada = tareaService.getTarea(identificador);
            tareaBuscada.setDescripcion(descripcion);
            tareaBuscada.setVigente(vigente);
            tareaService.guardaTarea(tareaBuscada);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (EntityNotFoundException error){
            return ResponseEntity.status(HttpStatus.OK).body("Tarea No encontrada");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "identificador", required = true) Integer identificador){
        boolean resultadoBorrado = tareaService.borraTarea(identificador);
        if (resultadoBorrado)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return ResponseEntity.status(HttpStatus.OK).body("Tarea No encontrada");
    }
}
