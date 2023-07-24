package br.com.reshi.orderprocessing.formatos;

import br.com.reshi.orderprocessing.models.ItemPedido;

import java.util.List;

/**
 * Interface referente aos formatos de arquivo e suas leituras
*/
public interface FormatoArquivoReader {
    List<ItemPedido> extraiItens(String nomeArquivo);

    String getExtensao();
}
