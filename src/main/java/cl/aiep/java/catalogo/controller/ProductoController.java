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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


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
	public String registrar(Producto producto, @RequestParam("archivo")MultipartFile archivo) {
		try {
			String tipoArchivo = archivo.getContentType();
			byte[] contenidoArchivo = archivo.getBytes();
			Producto product = Producto.builder()
								.datos(contenidoArchivo)
								.tipo(tipoArchivo)
								.nombre(producto.getNombre())
								.descripcion(producto.getDescripcion())
								.precio(producto.getPrecio())
								.categoria(producto.getCategoria())//consultar
								.build()
								;
			productoRepository.saveAndFlush(product);//guardar producto
		}catch(Exception e) {
			e.printStackTrace();
			return "producto/formulario";
		}
		return "redirect:/producto/lista";
		
	}
	
	//metodo que genera url para consumir una imagen de la BD
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> mostrarImagen(@PathVariable ("id")Producto producto){
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
				.contentType(MediaType.valueOf(producto.getTipo()))
				.body(producto.getDatos())
				;
	}
	
	
	@GetMapping("/lista")
	public String listar(Producto producto,Model modelo) {
		List<Producto> productos = productoRepository.findAll();
		modelo.addAttribute("productos", productos);
		modelo.addAttribute("producto", producto);
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
	
	@GetMapping("/ver/{id}")
	public String verArticulo(@PathVariable(name = "id")Producto producto, Model modelo) {
		List<Categoria> categorias = categoriaRepository.findAll();
		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("producto", producto);
		return "producto/articulo";
	}
	
	
}
