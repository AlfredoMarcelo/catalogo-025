package cl.aiep.java.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.aiep.java.catalogo.repository.ProductoRepository;


@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	
	@GetMapping("/nuevo")
	public String nuevoRegistro() {
		return "hola";
	}
	
	
}
