package br.com.reshi.orderprocessing.models;

import java.util.List;

//Classe responsavel por trazer o pedido completo com seus itens
public class Pedido {

  private List<ItemPedido> itens;

  public List<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
  }

  @Override
  public String toString() {
    return "Pedido{" +
        "itens=" + itens +
        '}';
  }
}
