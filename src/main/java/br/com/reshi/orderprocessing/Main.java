package br.com.reshi.orderprocessing;

import br.com.reshi.orderprocessing.formatos.FormatoArquivoReader;
import br.com.reshi.orderprocessing.models.ItemPedido;
import br.com.reshi.orderprocessing.models.SubTotalPorClasseFiscal;
import br.com.reshi.orderprocessing.services.PedidoService;
import br.com.reshi.orderprocessing.utils.ArquivoReaderUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Forneça um nome de arquivo.");
    }
    //Instanciando as classes utilitárias
    PedidoService service = new PedidoService();
    ServiceLoader<FormatoArquivoReader> loader = ServiceLoader.load(FormatoArquivoReader.class);


    String arquivo = args[0];

    //Adiciona os formatos aceitos
    List<FormatoArquivoReader> formatosAceitos = new ArrayList<>();
    for (FormatoArquivoReader reader : loader) {
      formatosAceitos.add(reader);
    }

    //Instancia os formatos aceitos para o leitor e faz a leitura do arquivo
    ArquivoReaderUtil reader = new ArquivoReaderUtil(formatosAceitos);
    List<ItemPedido> itensPedido = reader.leArquivo(arquivo);

    //Calcula o total e subtototal e imprime os resultados
    BigDecimal totalPedido = service.calculaTotal(itensPedido);
    SubTotalPorClasseFiscal subtotal = service.calculaSubtotal(itensPedido);
    service.imprimeResultado(subtotal, totalPedido);

  }

}
