package logic;

import db.OpretLaanDB;

public class opretLaan {
    OpretLaanDB oprLaanDB = new OpretLaanDB();
    public void CreateLaan(String tlfGetText, int bilid, String udbetalingGetText, String laanleangdeGetText, double rente, double mdlYdelse, double samletPris) {
    	/*/////////////////////////////////////////////////////////////
    	 * WE CAN DO DIS TO CONVERT STRINGS TO INT ETC
    	 */////////////////////////////////////////////////////////////
    	int tlf = Integer.parseInt(tlfGetText);

    	oprLaanDB.createLaan(tlf, bilid, udbetalingGetText, laanleangdeGetText, rente, mdlYdelse, samletPris);
    }


}