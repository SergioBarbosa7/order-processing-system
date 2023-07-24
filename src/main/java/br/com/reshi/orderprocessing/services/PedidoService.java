package br.com.reshi.orderprocessing.services;

import br.com.reshi.orderprocessing.models.ItemPedido;
import br.com.reshi.orderprocessing.models.SubTotalPorClasseFiscal;

import java.math.BigDecimal;
import java.util.List;

public class PedidoService {

    //Calcula o total dos pedidos
    public BigDecimal calculaTotal(List<ItemPedido> itensPedido) {
        BigDecimal totalPedido = BigDecimal.ZERO;
        System.out.println(itensPedido);
        for (ItemPedido itemPedido : itensPedido) {
            BigDecimal subtotal = itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
            totalPedido = totalPedido.add(subtotal);
        }

        return totalPedido;
    }

    //Calcula o subtotal de cada item
    public SubTotalPorClasseFiscal calculaSubtotal(List<ItemPedido> itensPedido){
        SubTotalPorClasseFiscal subTotalPorClasseFiscal = new SubTotalPorClasseFiscal();
        for (ItemPedido itemPedido : itensPedido) {
            BigDecimal novoSubTotal = itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
            String classeFiscal = itemPedido.getClasseFiscal();
            subTotalPorClasseFiscal.merge(classeFiscal, novoSubTotal, BigDecimal::add);
        }
        return subTotalPorClasseFiscal;
    }

    //Imprime os valores totais por subclasse
    public void imprimeResultado(SubTotalPorClasseFiscal subTotalPorClasseFiscal, BigDecimal totalPedido) {
        System.out.println("## Total do pedido: " + totalPedido);
        System.out.println("\n## Subtotal por classe fiscal");
        for (String classeFiscal : subTotalPorClasseFiscal.keySet()) {
            System.out.println("\n\tClasse fiscal: " + classeFiscal);
            BigDecimal subtotal = subTotalPorClasseFiscal.get(classeFiscal);
            System.out.println("\tSubtotal: " + subtotal);
        }
    }
}
