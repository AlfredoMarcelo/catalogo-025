package cl.aiep.java.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.aiep.java.catalogo.modelo.Producto;
import cl.aiep.java.catalogo.repository.CategoriaRepository;
import cl.aiep.java.catalogo.repository.ProductoRepository;



@Controller
public class IndexController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/")
	public String index(Producto producto, Model modelo) {
		List<Producto> productos = productoRepository.findAll();
		modelo.addAttribute("productos", productos);
		modelo.addAttribute("producto", producto);
		return "index";
		
	}
	
	
	
}
