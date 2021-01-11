package br.com.tidicas.main;

import java.util.Date;
import java.util.logging.Logger;
import br.com.tidicas.dao.BlogDao;
import br.com.tidicas.dao.CategoriaDao;
import br.com.tidicas.model.Blog;
import br.com.tidicas.model.Categoria;
import junit.framework.TestCase;

/**
 * Classe para geração das tabelas e teste com operacoes crud  
 *   
 * @author Evaldo Junior
 *
 */
public class TesteCrudJpa extends TestCase {

	private static final Logger LOGGER = Logger.getLogger(TesteCrudJpa.class.getName());

	public static void testCrud() {

		CategoriaDao categoriaDao = new CategoriaDao();
		BlogDao blogDao = new BlogDao();

		// 1 Entidade Categoria
		Categoria categoria1 = new Categoria();
		categoria1.setDescricao("categoria 1");

		Categoria categoria2 = new Categoria();
		categoria2.setDescricao("categoria 2");

		categoriaDao.adiciona(categoria1);

		categoriaDao.adiciona(categoria2);

		categoria1 = categoriaDao.buscaPorDescricao(categoria1.getDescricao()).get(0);
		LOGGER.info("retorno :" + categoria1.getDescricao());

		categoria2 = categoriaDao.buscaPorDescricao(categoria2.getDescricao()).get(0);
		LOGGER.info("retorno :" + categoria2.getDescricao());

		categoria1.setDescricao("categoria1 update");
		categoria1 = categoriaDao.atualiza(categoria1);

		categoria2.setDescricao("categoria2 update");
		categoria2 = categoriaDao.atualiza(categoria2);

		// 2 Entidade Blog
		Blog blog1 = new Blog();
		blog1.setCategoria(categoria2);
		blog1.setConteudo("conteúdo teste");
		blog1.setDtevento(new Date());
		blog1.setPublicar(0);
		blog1.setTitulo("titulo");
		
		blogDao.adiciona(blog1);

		blog1 = blogDao.buscaPorTitulo(blog1.getTitulo()).get(0);
		LOGGER.info("retorno:" + blog1.getTitulo());

		blog1.setConteudo("conteúdo teste update");
		blog1.setDtevento(new Date());
		blog1.setPublicar(0);
		blog1.setTitulo("título update");

		blog1 = blogDao.atualiza(blog1);

		blog1 = blogDao.buscaPorTitulo(blog1.getTitulo()).get(0);
		LOGGER.info("retorno:" + blog1.getTitulo());

		blogDao.remove(blog1);
		categoriaDao.remove(categoria1);
		categoriaDao.remove(categoria2);

		LOGGER.info("delete:" + blog1.getTitulo());

	}
}