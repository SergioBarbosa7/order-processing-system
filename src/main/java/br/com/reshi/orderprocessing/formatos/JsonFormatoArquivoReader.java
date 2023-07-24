package br.com.reshi.orderprocessing.formatos;

import br.com.reshi.orderprocessing.models.ItemPedido;
import br.com.reshi.orderprocessing.models.Pedido;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class JsonFormatoArquivoReader implements FormatoArquivoReader {

    //Extrai os itens do JSON
    @Override
    public List<ItemPedido> extraiItens(String nomeArquivo) {
        List<ItemPedido> itensPedido;

        // TODO: 23/07/2023 Implementar extração da tag Itens independentemente das outras tags do JSON
        try {
            Reader reader = new FileReader(nomeArquivo);
            JsonMapper mapper = new JsonMapper();

            Pedido pedido = mapper.readValue(reader, Pedido.class);
            itensPedido = pedido.getItens();
            return itensPedido;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    //Tras a extensao do arquivo
    @Override
    public String getExtensao() {
        return ".json";
    }
}
