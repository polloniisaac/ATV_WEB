package com.ads.loja; 

import java.util.ArrayList; //Permite criar uma lista de clientes para devolver na resposta.
import java.util.HashMap; //guarda clientes associados a um ID.
import java.util.List; // declara uma lista de clientes.
import java.util.Map; //declara uma estrutura que associa IDs a clientes.

import org.springframework.http.HttpStatus; //Fornece códigos de resposta HTTP, como CREATED.
import org.springframework.http.ResponseEntity; //Permite controlar o status HTTP e o conteúdo da resposta.
import org.springframework.web.bind.annotation.CrossOrigin; // Configura chamadas vindas de outras origens.
import org.springframework.web.bind.annotation.DeleteMapping; // Associa um método a uma requisição HTTP DELETE.
import org.springframework.web.bind.annotation.GetMapping; //Associa um método a uma requisição HTTP GET.
import org.springframework.web.bind.annotation.PathVariable;// Lê um valor que aparece no caminho da URL.
import org.springframework.web.bind.annotation.PostMapping; //Associa um método a uma requisição HTTP POST.
import org.springframework.web.bind.annotation.PutMapping;// Associa um método a uma requisição HTTP PUT.
import org.springframework.web.bind.annotation.RequestBody; // Converte o JSON do corpo da requisição em um objeto Java.
import org.springframework.web.bind.annotation.RestController;// Marca esta classe como um controller de API REST.

@RestController // Informa ao Spring que esta classe recebe requisições web e devolve respostas.
@CrossOrigin // Permite chamadas de origens diferentes, como um front-end em outra porta.
public class ClienteController { // Declaraa classe pública que controla as operações de Cliente.

    private Map<Integer, Cliente> clientes = new HashMap<>(); // Guarda cada cliente associado ao seu ID, temporariamente na memória.

    private int proximoId = 1;//Define o ID que será usado no próximo cadastro.

    @GetMapping("/cliente") // Associa GET/cliente ao método abaixo.
    public List<Cliente> listarClientes() {//Declara um método que devolve uma lista de clientes.
        return new ArrayList<>(clientes.values()); // Devolve uma lista com os clientes guardados no mapa.
    } 

    @GetMapping("/cliente/{id}") //Associa GET/cliente/id ao método abaixo.
    public ResponseEntity<Cliente> inforCliente(@PathVariable int id) { // Recebe o ID da URL e prepara uma resposta com um Cliente.
        Cliente cli = clientes.get(id); //Procura no mapa o cliente que corresponde ao ID recebido.

        if (cli == null) { //Verifica se não encontrou um cliente com esse ID.
            return ResponseEntity.notFound().build(); // Devolve o status HTTP 404, que significa "não encontrado".
        }

        return ResponseEntity.ok(cli);//Devolve ocliente encontrado com o status HTTP 200, que significa "sucesso".
    } // Encerra o método inforCliente.

    @PostMapping("/cliente") // Associa POST /cliente ao método abaixo.
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente novo) { // Recebe o JSON e o converte em um objeto Cliente.
        novo.setId(proximoId); // Coloca no cliente o ID que será usado para cadastrá-lo.
        clientes.put(proximoId, novo);//ghuarda o cliente no mapa usando o ID como chave.
        proximoId++; // Aumenta o contador para que o próximo cliente receba outro ID.

        return ResponseEntity.status(HttpStatus.CREATED).body(novo); // Devolve o cliente cadastrado com status HTTP 201,"criado".
    } // Encerra o método cadastrarCliente.

    @PutMapping("/cliente/{id}")//Associa PUT /cliente/algumNumero ao método abaixo.
    public ResponseEntity<Cliente> atualizarCliente(//Declara o método que atualiza um cliente existente.
            @PathVariable int id, //Lê da URL oID do cliente que será atualizado.
            @RequestBody Cliente atualizado) {//Converte o JSON recebido em um objeto Cliente.

        if (!clientes.containsKey(id)) { //Verifica sse o mapa não contém um cliente com esse ID.
            return ResponseEntity.notFound().build(); // Devolve o status HTTP 404 se o cliente não rxistir.
        } // Encerra o bloco do if.

        atualizado.setId(id); //Garanpe que o objeto mantenha o ID informado na URL.
        clientes.put(id, atualizado); //Substitui no mapa o cliente antigo pelos dados atualizados.

        return ResponseEntity.ok(atualizado); // Devolve o cliente atualizado com status HTTP 200.
    }

    @DeleteMapping("/cliente/{id}") // Associa DELETE /cliente/algumNumero ao método abaixo.
    public ResponseEntity<Void> excluirCliente(@PathVariable int id) { // Recebe da URL o ID do cliente a excluir.
        Cliente removido = clientes.remove(id); // Remove do mapa o cliente e guarda o removido nesta variável.

        if (removido == null) { // Verifica se não havia cliente com o ID informado.
            return ResponseEntity.notFound().build(); // Devolve o status HTTP 404 se o cliente não existir.
        } 

        return ResponseEntity.noContent().build(); // Devolve o status HTTP 204, indicando exclusão sem conteúdo de resposta.
    } // Encerra o método excluirCliente.
} 