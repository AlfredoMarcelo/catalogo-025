package cl.aiep.java.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.catalogo.modelo.Categoria;
import cl.aiep.java.catalogo.repository.CategoriaRepository;



@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	@GetMapping("/nuevo")
	public String nuevaCategoria(Categoria categoria) {
		return "categoria/formulario";
	}
	
	@PostMapping("/registrar")
	public String registrar(Categoria categoria) {
		categoriaRepository.saveAndFlush(categoria);
		return "redirect:/categoria/lista";
	}
	
	@GetMapping("/lista")
	public String listar(Model modelo) {
		List<Categoria> categorias = categoriaRepository.findAll();
		modelo.addAttribute("categorias", categorias);
		return "categoria/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		categoriaRepository.deleteById(id);
		return "redirect:/categoria/lista";
	}
	
	//consultar al profe por la linea 52 @PathVariable
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable (name =  "id") Categoria categoria, Model modelo) {
		modelo.addAttribute("categoria", categoria);
		return "categoria/formulario";
	}
	
	
}
