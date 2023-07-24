package br.com.reshi.orderprocessing.formatos;

import br.com.reshi.orderprocessing.models.ItemPedido;
import br.com.reshi.orderprocessing.models.Pedido;
import com.fasterxml.jackson.xml.XmlMapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class XmlFormatoArquivoReader implements FormatoArquivoReader {

    //Extrai os itens do XML
    @Override
    public List<ItemPedido> extraiItens(String nomeArquivo) {
        List<ItemPedido> itensPedido;

        try {
            Reader reader = new FileReader(nomeArquivo);
            XmlMapper mapper = new XmlMapper();

            Pedido pedido = mapper.readValue(reader, Pedido.class);
            itensPedido = pedido.getItens();
            return itensPedido;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    //Tras a extensao do arquivo
    public String getExtensao() {
        return ".xml";
    }
}
