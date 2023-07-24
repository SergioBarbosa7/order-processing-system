package br.com.alura.oobj;

import com.fasterxml.jackson.xml.XmlMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ArquivoReader {
    public List<ItemPedido> extraiItens(String nomeArquivo){

        List<ItemPedido> itensPedido;

        if (nomeArquivo.endsWith(".csv")) {
            try {
                Reader reader = new FileReader(nomeArquivo);
                CsvToBean<ItemPedido> csvToBean = new CsvToBeanBuilder<ItemPedido>(reader)
                        .withSeparator(';')
                        .withType(ItemPedido.class)
                        .build();
                itensPedido = csvToBean.parse();
                return itensPedido;
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }

        } else if (nomeArquivo.endsWith(".xml")) {
            try {
                Reader reader = new FileReader(nomeArquivo);
                XmlMapper mapper = new XmlMapper();

                Pedido pedido = mapper.readValue(reader, Pedido.class);
                itensPedido = pedido.getItens();
                return itensPedido;
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }

        } else {
            throw new IllegalArgumentException("Formato de arquivo inválido: " + nomeArquivo);

        }
    }
}
