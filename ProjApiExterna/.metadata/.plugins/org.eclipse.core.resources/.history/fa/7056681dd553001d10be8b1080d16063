package br.org.serratec.trabalho.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.org.serratec.trabalho.model.Usuario;
import br.org.serratec.trabalho.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService servico;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obterTodos(){
		
		List<Usuario> lista = servico.obterTodos();
		return ResponseEntity.ok(lista); // 200
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obterPorId(@PathVariable Long id){
		
		Optional<Usuario> optUsuario = servico.obterPorId(id);
		return ResponseEntity.ok(optUsuario.get()); // 200
	}
	
	@PostMapping 
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) throws Exception{
		URL url=new URL ("https://viacep.com.br/ws/"+usuario.getCep()+"/json/");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		String cep = "";
		StringBuilder jsonCep=new StringBuilder();
		while((cep=br.readLine())!=null) {
			jsonCep.append(cep);	
		}
		
		Usuario userAux = new Gson().fromJson(jsonCep.toString(), Usuario.class);
		usuario.setCep(userAux.getCep());
		usuario.setLogradouro(userAux.getLogradouro());
		usuario.setComplemento(userAux.getComplemento());
		usuario.setBairro(userAux.getBairro());
		usuario.setLocalidade(userAux.getLocalidade());
		usuario.setUf(userAux.getUf());
		
		usuario = servico.cadastrar(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		return ResponseEntity.ok(servico.atualizar(id, usuario)); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
}
