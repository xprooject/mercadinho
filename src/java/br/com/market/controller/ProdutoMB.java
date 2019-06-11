package br.com.market.controller;

import br.com.market.domain.Produto;
import br.com.market.util.UtilMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoMB implements Serializable{

    private Produto produto;
    private List<Produto> produtos;
    
    public ProdutoMB() {
        this.produtos = new ArrayList<Produto>();
        this.listar();
    }
    
    public ArrayList<Produto> listar(){
        return (ArrayList<Produto>) produtos;
    }
    
    public String novo(){
        produto = new Produto();
        return "new.xhtml?faces-redirect=true";
    }
    
    public String inserir(){
        if (produtos.add(produto)){
            UtilMessages.messageInfo("Produto cadastrado com sucesso!");
            this.listar();
            return "list.xhtml?faces-redirect=true";
        }else{
            UtilMessages.messageError("Ocorreu um erro ao cadastrar o produto!");
            return null;
        }
    }
    
    public String alterar(){
        produtos.add(produto.getId(), produto);
        
        if (produtos.get(produto.getId()).equals(produto)){
            UtilMessages.messageInfo("Produto alterado com sucesso!");
            this.listar();
            return "list.xhtml?faces-redirect=true";
        }else{
            
            UtilMessages.messageError("Ocorreu um erro ao alterar a produto!");
            return null;
        }
    }
    
    public String carregarDados(Produto produto){
        this.produto = produto;
        return "alter.xhtml?faces-redirect=true";
    }
    
    public String excluir(Produto produto){        
        if (produtos.remove(produto)){
            UtilMessages.messageInfo("Produto excluído com sucesso!");
            this.listar();
            return "list.xhtml?faces-redirect=true";
        }else{
            UtilMessages.messageError("Ocorreu um erro ao excluir o produto!");
            return null;
        }
    }
    
    public String cancelar(){
        return "list.xhtml?faces-redirect=true";
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
