package com.RegistroTareasAPI.RegistroTareas.service;

import com.RegistroTareasAPI.RegistroTareas.model.Tarea;
import com.RegistroTareasAPI.RegistroTareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServiceImpl implements TareaService{

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public Tarea guardaTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea getTarea(Integer identificador) {
        return tareaRepository.getById(identificador);
    }

    @Override
    public Boolean existeTarea(Integer identificador) {
        return tareaRepository.existsById(identificador);
    }

    @Override
    public Boolean borraTarea(Integer identificador) {
        if (tareaRepository.existsById(identificador)){
            tareaRepository.deleteById(identificador);
            return true;
        }
        else
            return false;
    }
}
