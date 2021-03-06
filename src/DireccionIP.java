import java.util.Scanner;

public class DireccionIP {

    int ip[] = new int[4];
    int ideDeRed[] = new int[4];
    int mascaraDeRed[] = new int[4];

    String ipp[] = new String[4];

    char claseIp;

    boolean esidDeRed;
    boolean esprivada;
    boolean sonDeLaMismaRed;

    public DireccionIP(String stringip) {
        ipp = stringip.split(",");
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(ipp[i]);
        }

        inicializarIp();
    }

    public DireccionIP(int intip1, int intip2, int intip3, int intip4) {
        ip[0] = intip1;
        ip[1] = intip2;
        ip[2] = intip3;
        ip[3] = intip4;

        inicializarIp();
    }

    public DireccionIP(int intip[]) {
        ip[0] = intip[0];
        ip[1] = intip[1];
        ip[2] = intip[2];
        ip[3] = intip[3];

        inicializarIp();
    }

    private void inicializarIp() {

        //clasede ip

        if (ip[0] < 128) {
            claseIp = 'A';
        }
        else if (ip[0] > 127 && ip[0] < 192) {
            claseIp = 'B';
        }
        else if (ip[0] > 191 && ip[0] < 224) {
            claseIp = 'C';
        }

        //Si es una id de red

        if (ip[3] == 0) {
            esidDeRed = true;
        }
        else {
            esidDeRed = false;
        }

        //la id de esa red

        ideDeRed[0] = ip[0];
        ideDeRed[1] = ip[1];
        ideDeRed[2] = ip[2];
        ideDeRed[3] = 0;

        //mascara de red

        if (claseIp == 'A') {
            mascaraDeRed[0] = 255;
            mascaraDeRed[1] = 0;
            mascaraDeRed[2] = 0;
            mascaraDeRed[3] = 0;
        }
        else if (claseIp == 'B') {
            mascaraDeRed[0] = 255;
            mascaraDeRed[1] = 255;
            mascaraDeRed[2] = 0;
            mascaraDeRed[3] = 0;
        }
        else if (claseIp == 'C') {
            mascaraDeRed[0] = 255;
            mascaraDeRed[1] = 255;
            mascaraDeRed[2] = 255;
            mascaraDeRed[3] = 0;
        }

        //si es privada o publica

        if (claseIp == 'A' && ip[0] == 10) {
            esprivada = true;
        }
        else if (claseIp == 'B' && ip[0] == 172 && ip[1] > 15 && ip[1] < 32) {
            esprivada = true;
        }
        else if (claseIp == 'C' && ip[0] == 192 && ip[1] == 168) {
            esprivada = true;
        }
        else {
            esprivada = false;
        }
    }

    public String infoIP() {
        String informacionDeIP;
        informacionDeIP =
                "La IP introducida es:" + ip[0] + "." + ip[1] + "." + ip[2] + "." + ip[3] + "\n"
                        + "la red es de clase: " + claseIp + "\n";

        if (esidDeRed == true) {
            informacionDeIP += "La IP introducida es una id de red \n";
        }
        else if (esidDeRed == false) {
            informacionDeIP += "La IP introducida no es una id de red \n";
        }
        informacionDeIP +=
                "La id de la red es:" + ideDeRed[0] + "." + ideDeRed[1] + "." + ideDeRed[2] + "." + ideDeRed[3] + "\n"
                        +"La mascara de red es:" + mascaraDeRed[0] + "." + mascaraDeRed[1] + "." + mascaraDeRed[2] + "." + mascaraDeRed[3] + "\n";

        if (esprivada == true) {
            informacionDeIP += "La IP introducida es una red privada";
        }
        else if (esprivada == false) {
            informacionDeIP += "La IP introducida es una red publica";
        }
        return informacionDeIP;
    }

    //Comprobar 2 direcciones ip

    public boolean compararIp(String primeraIp, String segundaIp){

        String primeraIpArray [] = new String[4];
        String segundaIpArray [] = new String[4];

        int ipUno [] = new int[4];
        int ipDos [] = new int[4];

        char claseIpUno = 'A';
        char claseIpDos = 'A';

        primeraIpArray = primeraIp.split(",");
        for (int i = 0; i < 4; i++) {
            ipUno[i] = Integer.parseInt(primeraIpArray[i]);
        }
        segundaIpArray = segundaIp.split(",");
        for (int i = 0; i < 4; i++) {
            ipDos[i] = Integer.parseInt(segundaIpArray[i]);
        }

        //Definir la clase de la ip 1

        if (ipUno[0] < 128) {
            claseIpUno = 'A';
        }
        else if (ipUno[0] > 127 && ip[0] < 192) {
            claseIpUno = 'B';
        }
        else if (ipUno[0] > 191 && ip[0] < 224) {
            claseIpUno = 'C';
        }

        //Definir la clase de la ip 2

        if (ipDos[0] < 128) {
            claseIpDos = 'A';
        }
        else if (ipDos[0] > 127 && ip[0] < 192) {
            claseIpDos = 'B';
        }
        else if (ipDos[0] > 191 && ip[0] < 224) {
            claseIpDos = 'C';
        }

        //comparar si son de la misma red

        if (claseIpUno == 'A' && claseIpUno == claseIpDos && ipUno[0] == ipDos[0] ) {
            return true;
        }
        else if (claseIpUno == 'B' && claseIpUno == claseIpDos && ipUno[0] == ipDos[0] && ipUno[1] == ipDos[1]) {
            return true;
        }
        else if (claseIpUno == 'C' && claseIpUno == claseIpDos && ipUno[0] == ipDos[0] && ipUno[1] == ipDos[1] && ipUno[2] == ipDos[2] ) {
            return true;
        }
        else {
            return false;
        }
    }

}


