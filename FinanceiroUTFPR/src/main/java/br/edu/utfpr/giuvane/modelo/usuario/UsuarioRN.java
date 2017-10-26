package br.edu.utfpr.giuvane.modelo.usuario;

import br.edu.utfpr.giuvane.modelo.categoria.CategoriaRN;
import java.util.List;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = new UsuarioDAOHibernate();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}

	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getCodigo();
		if (codigo == null || codigo == 0) {
			usuario.getPermissao().add("ROLE_USUARIO"); 
			this.usuarioDAO.salvar(usuario);
                        // ALTERADO APÓS CRIAÇÃO DE CATEGORIA
                        CategoriaRN categoriaRN = new CategoriaRN();
                        categoriaRN.salvaEstruturaPadrao(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public void excluir(Usuario usuario) {
            // ALTERADO APÓS CRIAÇÃO DE CATEGORIA
            CategoriaRN categoriaRN = new CategoriaRN();
            categoriaRN.excluir(usuario);
            
            this.usuarioDAO.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}
}
