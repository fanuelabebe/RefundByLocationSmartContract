package com.fan.locationsmartcontract.contract;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class ConnectToContract {

    public static Web3j returnWeb3Connection(){
        try {
            return Web3j.build(new HttpService("HTTP://10.0.2.2:7545"));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
