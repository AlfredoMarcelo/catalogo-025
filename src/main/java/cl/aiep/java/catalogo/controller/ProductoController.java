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
import cl.aiep.java.catalogo.modelo.Producto;
import cl.aiep.java.catalogo.repository.CategoriaRepository;
import cl.aiep.java.catalogo.repository.ProductoRepository;


@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/nuevo")
	public String nuevoRegistro(Producto producto, Model modelo) {
		List<Categoria> categorias = categoriaRepository.findAll();
		modelo.addAttribute("categorias", categorias);
		return "producto/formulario";
	}
	
	@PostMapping("/registrar")
	public String registrar(Producto producto) {
		productoRepository.saveAndFlush(producto);
		return "redirect:/producto/lista";
	}
	
	
	@GetMapping("/lista")
	public String listar(Model modelo) {
		List<Producto> productos = productoRepository.findAll();
		modelo.addAttribute("productos", productos);
		return "producto/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Producto producto, Model modelo) {
		List<Categoria> categorias = categoriaRepository.findAll();
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("producto", producto);
		return "producto/formulario";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		productoRepository.deleteById(id);
		return "redirect:/producto/lista";
	}
	
	
	
}
