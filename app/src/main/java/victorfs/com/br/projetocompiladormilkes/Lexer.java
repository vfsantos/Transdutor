package victorfs.com.br.projetocompiladormilkes;

import java.util.ArrayList;

public class Lexer {

    int cont;
    //int tempN;

    String tempN;
    String registro;
    String variavel;
    String linhaSaida;
    String resultado;

    char fita[];
    char letras[] = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J',
            'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't',
            'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z'};
    char numeros[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char outros[] = {'.', ',', '/', '|', ';', ':', '?', '=', '+', '-', '_', '(', ')', '*', '&', '%', '$', '#', '@', '!',
            '^', '´', '`', '~', 'º', 'ª', ' '};

    public ArrayList<String> tempS = new ArrayList<>();
    public ArrayList<String> tab = new ArrayList<>();



    public Lexer() {
        cont = 0;

        registro = "";
        variavel = "";
        linhaSaida = "";
    }

    public String scan(String sentenca) {
        fita = sentenca.toCharArray();
        e0();
        resultado = getSaida();
        return resultado;
    }

    public void e0() {

        if (cont < fita.length) {
            while (fita[cont] == ' ') {
                cont++;
            }
        }

        if (cont < fita.length) {
                    for (char let : letras) {
                        try{
                            if (fita[cont] == let) {
                                cont++;
                                s1(String.valueOf(let));
                                System.out.println(let);
                                e1();
                            }
                        } catch (Exception e) {

                        }
                    }

        }

        if (cont < fita.length) {
            for (char num : numeros) {
                try {
                    if (fita[cont] == num) {
                                cont++;
                                s4(String.valueOf(num));
                                System.out.println(num);
                                e2();
                            }
                        } catch (Exception e) {

                        }

                    }

        }

        if (cont == fita.length) {
            System.out.println("Tabela de variável");
            for (int i = 0; i < tab.size(); i++) {
                //saida += i + " ... " + tab.get(i) + "\n";
                System.out.println(i + " ... " + tab.get(i) + "\n");
            }
        }
    }

    public void e1() {
        if (cont < fita.length) {
            for (char let : letras) {
                try {
                    if (fita[cont] == let) {
                        cont++;
                        s2(String.valueOf(let));
                        System.out.println(let);
                        e1();
                    }
                } catch (Exception e) {

                }
            }
        }

        if (cont < fita.length) {
            for (char num : numeros) {
                try {
                    if (fita[cont] == num) {
                        cont++;
                        s2(String.valueOf(num));
                        System.out.println(num);
                        e1();
                    }
                    } catch (Exception e) {

                    }
            }
        }

        if (cont < fita.length) {
            for (char out : outros) {
                try {
                    if (fita[cont] == out) {
                        cont++;
                        s3();
                        e0();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public void e2() {
        if (cont < fita.length) {
            for (char num : numeros) {
                try {
                    if (fita[cont] == num) {
                        cont++;
                        s5(String.valueOf(num));
                        System.out.println(num);
                        e2();
                        }
                } catch (Exception e) {

                }
            }
        }

        if (cont < fita.length) {
            for (char out : outros) {
                try {
                    if (fita[cont] == out) {
                        cont++;
                        s6();
                        e0();
                    }
                } catch (Exception e) {

                }
            }

        }

        if (cont < fita.length) {
            for (char let : letras) {
                try {
                    if (fita[cont] == let) {
                        cont++;
                        s2(String.valueOf(let));
                        e2();
                        if (cont == fita.length) {
                            s3();
                        }
                    }
                } catch (Exception e) {

                }
            }
        }


    }

    public void s1(String simbolo) {
        //Adiciona no vetor tempS o símbolo
        tempS.add(simbolo);
        System.out.println(simbolo);
    }

    public void s2(String simbolo) {
        //Anexa no vetor tempS o novo símbolo
        tempS.add(simbolo);
        System.out.println(simbolo);
    }

    public void s3() {
        //Cria a variável que sera anexada posteriormente no vetor tab
        for (int i = 0; i < tempS.size(); i++) {
            String aux = tempS.get(i);
            variavel += aux;
        }
        System.out.println("tempS: " + variavel);

        //Verifica se a variável existe no vetor tab
        boolean achou = false;
        for(String eachTab : tab){
            if(eachTab.equals(variavel)) {
                achou = true;
                int index = tab.indexOf(variavel);
                String s = "V(" + index +")";
                setLinhaSaida(s);
            }
        }

        //Adiciona a variavel no vetor tab se a mesma não existir
        if(!achou) {
            tab.add(variavel);
            int index = tab.indexOf(variavel);
            String s = "V(" + index +")";
            setLinhaSaida(s);
        }

        //Limpa a variável
        variavel = "";

        //Limpa o vetor tempS
        tempS.clear();
    }

    public void s4(String simbolo) {
        //tempN = Integer.parseInt(simbolo) - 48;
        tempN = simbolo;
    }


    public void s5(String simbolo) {
        //tempN = tempN * 10 + (Integer.parseInt(simbolo) - 48);
        tempN += simbolo;
    }

    public void  s6() {
        setLinhaSaida("N(" + tempN + ")");
    }

    //Retorna minha linha de saída
    public String getLinhaSaida(){
        System.out.println(linhaSaida);
        return linhaSaida;
    }

    //Constroi minha linha de saída com base nas strings recebidas na troca de estados
    public void setLinhaSaida(String s) {
        linhaSaida += s;
    }

    //Percorre o meu Arraylist tab e controi minha tabela de variável com id e valor
    public String getTabelaSaida() {
        String saida = "";
        for (int i = 0; i < tab.size(); i++) {
            saida += i + " ... " + tab.get(i) + "\n";
            //System.out.println(i + " ... " + tab.get(i) + "\n");
        }
        return saida;
    }

    //Concatena a linha e tabela e retorno o resultado
    public String getSaida() {
        String resultado = "SAÍDA: \n"
                + getLinhaSaida() +
                "\n" +
                "\nTABELA DE VARIÁVEIS: " +
                "\n" + getTabelaSaida();
        System.out.println(resultado);
        return resultado;
    }

}
