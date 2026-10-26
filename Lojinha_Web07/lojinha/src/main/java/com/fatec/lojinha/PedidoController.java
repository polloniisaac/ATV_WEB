package com.fatec.lojinha;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    private PedidoRepository bd;

    @Autowired
    private ClienteRepository clientes;

    @PostMapping("/api/pedido")
    public String gravar(@RequestBody Pedido obj) {
        Cliente cliente = localizarCliente(obj);
        if (cliente == null) {
            return "Informe o código de um cliente existente!";
        }

        obj.setCliente(cliente);
        bd.save(obj);
        return "Pedido gravado com sucesso " + obj.getCodigo();
    }

    @PutMapping("/api/pedido")
    public String alterar(@RequestBody Pedido obj) {
        if (!bd.existsById(obj.getCodigo())) {
            return "Pedido não existe!";
        }

        Cliente cliente = localizarCliente(obj);
        if (cliente == null) {
            return "Informe o código de um cliente existente!";
        }

        obj.setCliente(cliente);
        bd.save(obj);
        return "Pedido alterado com sucesso";
    }

    @GetMapping("/api/pedido")
    public List<Pedido> listar() {
        return bd.findAll();
    }

    @GetMapping("/api/pedido/{codigo}")
    public Pedido carregar(@PathVariable("codigo") int codigo) {
        return bd.findById(codigo).orElse(null);
    }

    @DeleteMapping("/api/pedido/{codigo}")
    public String remover(@PathVariable("codigo") int codigo) {
        if (bd.existsById(codigo)) {
            bd.deleteById(codigo);
            return "Pedido removido com sucesso!";
        }
        return "Pedido não existe!";
    }

    private Cliente localizarCliente(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getCliente().getCodigo() <= 0) {
            return null;
        }

        Optional<Cliente> cliente = clientes.findById(pedido.getCliente().getCodigo());
        return cliente.orElse(null);
    }
}
