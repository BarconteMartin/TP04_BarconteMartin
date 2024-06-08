package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoMaterias;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {

    @GetMapping("/formularioMateria")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", new Materia());
        return modelView;
    }

    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
        ListadoMaterias.agregarMateria(materiaParaGuardar);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/borrarMateria/{codigo}")
    public ModelAndView deleteMateria(@PathVariable(name="codigo") String codigo) {
        ListadoMaterias.eliminarMateria(codigo);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView editMateria(@PathVariable(name="codigo") String codigo) {
        Materia materiaParaModificar = ListadoMaterias.buscarMateriaPorCodigo(codigo);
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materiaParaModificar);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView updateMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
        ListadoMaterias.modificarMateria(materiaModificada);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }
}
