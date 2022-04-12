package com.RegistroTareasAPI.RegistroTareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RegistroTareasAPI.RegistroTareas.model.Tarea;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Integer> {
}
