package br.com.reshi.orderprocessing.utils;

import br.com.reshi.orderprocessing.models.ItemPedido;
import br.com.reshi.orderprocessing.formatos.FormatoArquivoReader;

import java.util.List;

public class ArquivoReaderUtil {
    private List<FormatoArquivoReader> formatosAceitos;

    //Cria o ArquivoReader junto dos formator de arquivos aceitos
    public ArquivoReaderUtil(List<FormatoArquivoReader> formatosAceitos){
        this.formatosAceitos = formatosAceitos;
    }

    //Identifica formato com base na extensao final do arquivo
    public FormatoArquivoReader identificarFormato(String nomeArquivo){
        for(FormatoArquivoReader formato : formatosAceitos){
            if(nomeArquivo.endsWith(formato.getExtensao())){
                return formato;
            }
        }
        throw new IllegalArgumentException("Formato de arquivo inválido: " + nomeArquivo);
    }

    //Le o arquivo repassado utilizando um conversor com base na sua extensao
    public List<ItemPedido> leArquivo(String nomeArquivo){
        return identificarFormato(nomeArquivo).extraiItens(nomeArquivo);
    }
}
