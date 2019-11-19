package br.edu.riobrancofac.pdeapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;

public class FormataDados {

    private FormataDados() {
    }

    public static String mascaraCpf(String cpfSemFormatacao) {
        String cpfFormatado = new CPFFormatter().format(cpfSemFormatacao);

        StringBuilder cpfMascarado = new StringBuilder(cpfFormatado);
        cpfMascarado.setCharAt(4, '*');
        cpfMascarado.setCharAt(5, '*');
        cpfMascarado.setCharAt(8, '*');
        cpfMascarado.setCharAt(9, '*');
        cpfMascarado.setCharAt(12, '*');
        cpfMascarado.setCharAt(13, '*');

        return cpfMascarado.toString();
    }

    public static String formatarCpfCnpj(String cpfCnpjSemFormatacao) {
        if (cpfCnpjSemFormatacao.length() == 14) {
            return formatarCnpj(cpfCnpjSemFormatacao);
        } else {
            return new CPFFormatter().format(cpfCnpjSemFormatacao);
        }
    }

    public static String formatarCnpj(String cnpjSemFormatacao) {
        return new CNPJFormatter().format(cnpjSemFormatacao);
    }

    public static String formatarTelefone(String dddSemFormatacao, String telefoneSemFormatacao) {
        String dddSemFormatacaoEspaco = dddSemFormatacao.replaceAll(" ", "");
        String telefoneSemFormatacaoEspaco = telefoneSemFormatacao.replaceAll(" ", "");
        StringBuilder numeroFormatado = new StringBuilder();

        numeroFormatado.append(dddSemFormatacaoEspaco).append(telefoneSemFormatacaoEspaco);

        if (!numeroFormatado.toString().isEmpty() && numeroFormatado.length() == 11) {
            numeroFormatado.insert(0, '(');
            numeroFormatado.insert(3, ')');
            numeroFormatado.insert(4, ' ');
            numeroFormatado.insert(10, '-');

            return numeroFormatado.toString();
        }

        if (!numeroFormatado.toString().isEmpty() && numeroFormatado.length() == 10) {
            numeroFormatado.insert(0, '(');
            numeroFormatado.insert(3, ')');
            numeroFormatado.insert(4, ' ');
            numeroFormatado.insert(9, '-');

            return numeroFormatado.toString();
        }
        return numeroFormatado.toString();
    }

    public static String removeFormatacao(String dado) {
        if(dado == null) return null;
        String dadoSemFormatacao = dado;
        dadoSemFormatacao = dadoSemFormatacao.replace(".", "");
        dadoSemFormatacao = dadoSemFormatacao.replace("-", "");
        dadoSemFormatacao = dadoSemFormatacao.replace("/", "");
        dadoSemFormatacao = dadoSemFormatacao.replace("(", "");
        dadoSemFormatacao = dadoSemFormatacao.replace(")", "");
        dadoSemFormatacao = dadoSemFormatacao.replace("%", "");
        dadoSemFormatacao = dadoSemFormatacao.replace("R$", "");
        return dadoSemFormatacao;
    }

    public static Double stringToDouble(String valorEmTexto) {
        String valorEmTextoSemFormatacao = removeFormatacao(valorEmTexto).replace(",", ".").trim().replaceAll("[a-zA-Z]", "");
        return Double.valueOf(valorEmTextoSemFormatacao);
    }

    public static String formatarRG(String rg) {
        String rgFormatado = null;
        String grupo1 = rg.substring(0, 2);
        String grupo2 = rg.substring(2, 5);
        String grupo3 = rg.substring(5, 8);
        String grupo4 = rg.substring(8);

        rgFormatado = grupo1 + "." + grupo2 + "." + grupo3 + "-" + grupo4;

        return rgFormatado;
    }

    public static String formatarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(data, formatter);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter2);
    }

    public static String convDataBanco(String dataSistema) throws ParseException {
        Date dataFormatada;
        String dataBanco = "";
        dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dataSistema);
        dataBanco = new SimpleDateFormat("yyyy-MM-dd").format(dataFormatada);
        return dataBanco;
    }

    public static String formataTamanhoCampo(String campo, int tamanho) {
        if (!(campo.isEmpty()) && campo.length() > tamanho) {
            return campo.substring(0, tamanho - 1);
        }

        return campo;
    }
}
