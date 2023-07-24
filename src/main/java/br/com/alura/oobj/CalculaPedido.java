package br.com.alura.oobj;

import java.math.BigDecimal;
import java.util.List;

public class CalculaPedido {
    public BigDecimal calculaTotal(List<ItemPedido> itensPedido) {
        BigDecimal totalPedido = BigDecimal.ZERO;
        System.out.println(itensPedido);
        for (ItemPedido itemPedido : itensPedido) {
            BigDecimal subtotal = itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
            totalPedido = totalPedido.add(subtotal);
        }

        return totalPedido;
    }
    public SubTotalPorClasseFiscal calculaSubtotal(List<ItemPedido> itensPedido){
        SubTotalPorClasseFiscal subTotalPorClasseFiscal = new SubTotalPorClasseFiscal();
        for (ItemPedido itemPedido : itensPedido) {
            BigDecimal novoSubTotal = itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
            String classeFiscal = itemPedido.getClasseFiscal();
            subTotalPorClasseFiscal.merge(classeFiscal, novoSubTotal, BigDecimal::add);
        }
        return subTotalPorClasseFiscal;
    }
}
