package com.fan.locationsmartcontract.contract;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class ConnectToContract {

    public static Web3j returnWeb3Connection(){
        try {
            String url = "HTTP://10.0.2.2:7545"; // ganache local
//            String url = "HTTP://192.168.43.74:7545"; // ganache WIFI
            return Web3j.build(new HttpService(url));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
