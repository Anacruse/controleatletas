/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validacoes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gabriel
 */
public class Validacoes {
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean validarNome(String nome) {
        if (nome.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean validaCartoesVermelhos(String cartoesVermelhos) {
        try {
            Integer.parseInt(cartoesVermelhos);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }

    public static boolean validaNumero(String numero) {
        if (!numero.equals("")) {
            try {
                Integer.parseInt(numero);
            } catch (Exception ex) {
                return true;
            }
        }
        return false;
    }

    public static boolean validaDataNascimento(String data) {
        if (data.length() != 0) {
            try {
                dateFormat.parse(data);
            } catch (ParseException ex) {
                return true;
            }
        }
        return false;
    }

    public static boolean validaAltura(String altura) {
        try {
            Double.parseDouble(altura);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }

    public static boolean validaPeso(String peso) {
        try {
            Double.parseDouble(peso);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }

    public static boolean validaCartoesAmarelos(String cartoesAmarelos) {
        try {
            Integer.parseInt(cartoesAmarelos);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }
    
}
