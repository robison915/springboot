package com.curso.springboot;

import com.curso.springboot.DAO.CategoriaDAO;
import com.curso.springboot.DAO.CidadeDAO;
import com.curso.springboot.DAO.ClienteDAO;
import com.curso.springboot.DAO.EnderecoDAO;
import com.curso.springboot.DAO.EstadoDAO;
import com.curso.springboot.DAO.ItemPedidoDAO;
import com.curso.springboot.DAO.PagamentoDAO;
import com.curso.springboot.DAO.PedidoDAO;
import com.curso.springboot.DAO.ProdutoDAO;
import com.curso.springboot.domain.Categoria;
import com.curso.springboot.domain.Cidade;
import com.curso.springboot.domain.Cliente;
import com.curso.springboot.domain.Endereco;
import com.curso.springboot.domain.Estado;
import com.curso.springboot.domain.ItemPedido;
import com.curso.springboot.domain.Pagamento;
import com.curso.springboot.domain.PagamentoBoleto;
import com.curso.springboot.domain.PagamentoCartao;
import com.curso.springboot.domain.Pedido;
import com.curso.springboot.domain.Produto;
import com.curso.springboot.domain.enums.EstadoPagamento;
import com.curso.springboot.domain.enums.TipoCliente;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootconceitoApplication implements CommandLineRunner{
    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private ProdutoDAO produtoDAO;
    @Autowired
    private EstadoDAO estadoDAO;
    @Autowired
    private CidadeDAO cidadeDAO;
    @Autowired
    private EnderecoDAO enderecoDAO;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private PagamentoDAO pagamentoDAO;
    @Autowired
    private PedidoDAO pedidoDAO;
    @Autowired
    private ItemPedidoDAO itemPedidoDAO;
    
    public static void main(String[] args) {
        SpringApplication.run(SpringbootconceitoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*---------------------------------------------------------------------*/
        // Categoria produto
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");
        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);
        
        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().addAll(Arrays.asList(produto2));
        
        produto1.getCategorias().addAll(Arrays.asList(categoria1));
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        produto3.getCategorias().addAll(Arrays.asList(categoria1));
        
        categoriaDAO.saveAll(Arrays.asList(categoria1, categoria2));
        produtoDAO.saveAll(Arrays.asList(produto1, produto2, produto3));
        /*---------------------------------------------------------------------*/
        // Estado Cidade
        Estado estado1 = new Estado(null, "Rio Grande do Sul");
        Estado estado2 = new Estado(null, "São Paulo");
        Cidade cidade1 = new Cidade(null, "Esteio", estado1);
        Cidade cidade2 = new Cidade(null, "Sapucaia", estado1);
        Cidade cidade3 = new Cidade(null, "São Paulo", estado2);
        
        estado1.getCidades().addAll(Arrays.asList(cidade1,cidade2));
        estado2.getCidades().addAll(Arrays.asList(cidade3));
        
        estadoDAO.saveAll(Arrays.asList(estado1,estado2));
        cidadeDAO.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
        /*---------------------------------------------------------------------*/
        // Cliente Endereco
        //Cliente cliente = new Cliente(null, "Fulano", "fulano@gmail.com", "01234567890", TipoCliente.PESSOA_FISICA);
        Cliente cliente1 = new Cliente(null, "Maria", "maria@gmail.com", "01234567891", TipoCliente.PESSOA_FISICA);
        cliente1.getTelefones().addAll(Arrays.asList("0123456789","0123456780"));
        
        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "01234567", cliente1, cidade1);
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "01234568", cliente1, cidade2);
        
        cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
        
        clienteDAO.saveAll(Arrays.asList(cliente1));
        enderecoDAO.saveAll(Arrays.asList(endereco1,endereco2));
        /*---------------------------------------------------------------------*/
        // Pedido Pagamento
        String pattern = "dd/MM/yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), cliente1, endereco1);
        Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("10/10/2017 19:35"), cliente1, endereco2);
        
        Pagamento pagamento1 = new PagamentoCartao(null, 6, EstadoPagamento.QUITADO, pedido1);
        pedido1.setPagamento(pagamento1);
        
        Pagamento pagamento2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedido2, null, simpleDateFormat.parse("20/10/2017 00:00"));
        pedido2.setPagamento(pagamento2);
        
        cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
        
        pedidoDAO.saveAll(Arrays.asList(pedido1,pedido2));
        pagamentoDAO.saveAll(Arrays.asList(pagamento1,pagamento2));
        /*---------------------------------------------------------------------*/
        // ItemPedido
        
        ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
        ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
        ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);
        
        pedido1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
        pedido2.getItens().addAll(Arrays.asList(itemPedido3));
        
        produto1.getItens().addAll(Arrays.asList(itemPedido1));
        produto2.getItens().addAll(Arrays.asList(itemPedido3));
        produto3.getItens().addAll(Arrays.asList(itemPedido2));
        
        itemPedidoDAO.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3));
    }
}
