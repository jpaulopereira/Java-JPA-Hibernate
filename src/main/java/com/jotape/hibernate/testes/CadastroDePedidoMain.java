package com.jotape.hibernate.testes;

import com.jotape.hibernate.dao.CategoriaDAO;
import com.jotape.hibernate.dao.ClienteDAO;
import com.jotape.hibernate.dao.PedidoDAO;
import com.jotape.hibernate.dao.ProdutoDAO;
import com.jotape.hibernate.modelo.*;
import com.jotape.hibernate.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedidoMain {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Produto produto = produtoDao.buscarPorId(1L);
        Cliente cliente = clienteDAO.buscarPorId(1L);


        em.getTransaction().begin();

        Cliente cliente2 = new Cliente("João", "23213543");
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println(totalVendido);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        Cliente cliente = new Cliente("João", "23213543");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}


