package com.ads.loja;
import java.util.ArrayList; //importa uma implementação de lista
import java.util.List; // importa a interface geral de lista

import org.springframework.beans.factory.annotation.Autowired; //permite a injeção de dependência
import org.springframework.web.bind.annotation.CrossOrigin;//Configura a chamada entre origens diferentes
import org.springframework.web.bind.annotation.GetMapping; //Importa a anotação usada para ligar um método a uma requisição GET
import org.springframework.web.bind.annotation.PathVariable;//Importa a anotação que pega um valor da URL e o coloca em um parâmetro do método
import org.springframework.web.bind.annotation.PostMapping;//Importa a anotação usada para ligar um método a uma requisição HTTP POST
import org.springframework.web.bind.annotation.RequestBody;// importa a anotação que indica que o parametro do metodo virá do corpo da requisição HTTP, por exemplo de um JSON. O Spring convverte o JSON em objeto Java automaticamente
import org.springframework.web.bind.annotation.RestController;//Marca a classe como controller REST

@CrossOrigin 
@RestController
public class ProdutoController {

    @Autowired Produto obj; // Pede ao Spring para forneceer um objeto Produto para a variável obj
    @GetMapping ("/produto/{id}")//Mapeia a requisição GET para o endereço /produto/{id}
    public Produto carregar(@PathVariable int id)//Recebe o id do produto a ser carregado
    { //TODO: Recuperar do Banco de dados via ID
        obj.setCodigo(id);//coloca no codigo do produto o valor id recebido na URL
        obj.setDescricao("Furadeira Eletrica");// define a descirção do produto
        obj.setKeywords("Eletrica 220v");//define as palavras-chave do produto
        obj.setNome("Furadeira");// define o nomed do produto
        obj.setDesconto(100);// prenche o respectivo campo do objeto com o valor 100
        obj.setQuantidade(100);//prenche o respectivo campo do objeto com o valor 100
        obj.setPreco(140);//prenche o respectivo campo do objeto com o valor 140
        return obj;//devolve o objeto como resposta da requisição. Como a classe tem o @RestController. O Spring envia o valor retornado no corpo da resposta. Geralmente em JSON.

    }

    @PostMapping ("/produto") //associa o método à requisição POST /produto.
    public Retorno gravar(@RequestBody Produto novo /*pede ao Spring para converter o corpo da requisição — por exemplo, um JSON — em um objeto Produto chamado novo */)//declara um método público chamado gravar. Retorno indica o tipo de objeto que ele devolve.
    // TODO: Gravar obj no Banco de dados
    {
        var r = new Retorno(); //cria um novo objeto Retorno. o var permite que o Java deduza op tipo pela expressão a direita: aqui, Retorno.
        r.setMensagem("Seu produto foi gravado com sucesso");
        return r; // devolve esse objeto ao cliente
    }
    /*O produto recebido em novo ainda não é usado. O método responde com uma mensagem, mas não grava o produto em lugar nenhum. O Spring associa POST /produto a esse método, mas o código dentro dele é que determina o que acontece. */

    @GetMapping ("/produto/vitrine")//associa o endereço GET/produto/vitrine a este método
    public List<Produto> retornaVitrine(){//declara que o metodo devolve uma lista de objetos Produto
        return new ArrayList<Produto>();//cria uma lista de produtos
    }
}
