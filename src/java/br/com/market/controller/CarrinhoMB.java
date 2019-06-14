package br.com.market.controller;

import br.com.market.domain.Produto;
import br.com.market.util.UtilMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "carrinhoMB")
@SessionScoped
public class CarrinhoMB implements Serializable{

    private List<Produto> carrinho;
    private float total;
    
    public CarrinhoMB() {
        this.carrinho = new ArrayList<Produto>();
        this.listar();
    }
    
    public ArrayList<Produto> listar(){
        return (ArrayList<Produto>) carrinho;
    }
       
    public String inserir(Produto produto){
        
        boolean controle = false;
        
        for (Produto c: carrinho){
            if (produto.equals(c)){
                c.setQtde(c.getQtde()+1);
                this.listar();
                controle = true;
                return "list.xhtml?faces-redirect=true";
            }
        }

        if (!controle){
            produto.setQtde(1);
            carrinho.add(produto);
            UtilMessages.messageInfo("Produto inserido com sucesso!");
            this.listar();
            return "list.xhtml?faces-redirect=true";
        }else{
            UtilMessages.messageError("Ocorreu um erro ao inserir o produto!");
            return "list.xhtml?faces-redirect=true";
        }
    }
    
    public String excluir(Produto produto){        
        if (carrinho.remove(produto)){
            UtilMessages.messageInfo("Produto excluído com sucesso!");
            this.listar();
            return "list.xhtml?faces-redirect=true";
        }else{
            UtilMessages.messageError("Ocorreu um erro ao excluir o produto!");
            return null;
        }
    }
    
    public void finalizar(Produto produto){
        
        for(Produto p : carrinho){
            setTotal((float) (getTotal() + p.getPrecoVenda()));
        }
        UtilMessages.messageInfo("Compra Finalizada com Sucesso!");
        
    }

    public String cancelar(){
        return "list.xhtml?faces-redirect=true";
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    
}
