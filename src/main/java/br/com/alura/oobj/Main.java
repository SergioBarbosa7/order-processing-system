package br.com.alura.oobj;

import java.math.BigDecimal;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    if (args.length <= 0) {
      throw new IllegalArgumentException("Forneça um nome de arquivo.");
    }
    ArquivoReader reader = new ArquivoReader();
    CalculaPedido calc = new CalculaPedido();

    String arquivo = args[0];

    List<ItemPedido> itensPedido = reader.extraiItens(arquivo);

    BigDecimal totalPedido = calc.calculaTotal(itensPedido);
    SubTotalPorClasseFiscal subtotal = calc.calculaSubtotal(itensPedido);

    ImprimePedido printer = new ImprimePedido();
    printer.imprimeResultado(subtotal, totalPedido);

  }

}
