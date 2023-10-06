package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p2 ->  System.out.println(p.getNome()));

        List<Produto> nomes = produtoDao.buscarPorNome("Xiaomi Redmi");
        todos.forEach(p2 ->  System.out.println(p.getNome()));

        List<Produto> nomesCategorias = produtoDao.buscarPorNomeCategoria("CELULARES");
        todos.forEach(p2 ->  System.out.println(p.getNome()));

        BigDecimal precoProduto = produtoDao.buscarPorNomeOPrecoProduto("Xiaomi Redmi");
        System.out.println("Preco do Produto: " + precoProduto);

    }

        public static void cadastrarProduto() {
            Categoria celulares = new Categoria("CELULARES");
            Produto celular = new Produto("Xiaomi Redmi ", "Duas cameras, 64GB", new BigDecimal("1000"), celulares);

            EntityManager em = JPAUtil.getEntityManager();
            ProdutoDao produtoDao = new ProdutoDao(em);
            CategoriaDao categoriaDao = new CategoriaDao(em);

            em.getTransaction().begin();

            categoriaDao.cadastrar(celulares);
            produtoDao.cadastrar(celular);

            em.getTransaction().commit();
            em.close();

        }
}
