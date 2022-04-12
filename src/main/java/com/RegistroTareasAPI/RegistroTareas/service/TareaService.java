package com.RegistroTareasAPI.RegistroTareas.service;

import com.RegistroTareasAPI.RegistroTareas.model.Tarea;
import java.util.List;

public interface TareaService {
    public Tarea guardaTarea(Tarea tarea);
    public List<Tarea> getAllTareas();
    public Tarea getTarea(Integer identificador);
    public Boolean existeTarea(Integer identificador);
    public Boolean borraTarea(Integer identificador);

}
