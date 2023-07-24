package br.com.reshi.orderprocessing.formatos;

import br.com.reshi.orderprocessing.models.ItemPedido;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvFormatoArquivoReader implements FormatoArquivoReader {

    //Faz a leitura de um arquivo CSV com separador ; trazendo os dados com os itens dos pedidos
    @Override
    public List<ItemPedido> extraiItens(String nomeArquivo) {
        List<ItemPedido> itensPedido;

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
    }

    //Tras a extensao do arquivo
    @Override
    public String getExtensao() {
        return ".csv";
    }
}
