package com.fan.locationsmartcontract.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.0.
 */
@SuppressWarnings("rawtypes")
public class TruffleContract_sol_TruffleContract extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5060df8061001c5f395ff3fe6080604052348015600e575f80fd5b50600436106026575f3560e01c806313bdfacd14602a575b5f80fd5b604080518082018252600c81526b48656c6c6f20576f726c642160a01b6020820152905160569190605f565b60405180910390f35b5f602080835283518060208501525f5b81811015608957858101830151858201604001528201606f565b505f604082860101526040601f19601f830116850101925050509291505056fea26469706673582212204e9fb24ba91697891457d8d0f6ba69a406b4e42c1a643b7db3b1047b13662a3a64736f6c63430008180033";

    public static final String FUNC_PRINT = "print";

    @Deprecated
    protected TruffleContract_sol_TruffleContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TruffleContract_sol_TruffleContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TruffleContract_sol_TruffleContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TruffleContract_sol_TruffleContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> print() {
        final Function function = new Function(FUNC_PRINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static TruffleContract_sol_TruffleContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TruffleContract_sol_TruffleContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TruffleContract_sol_TruffleContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TruffleContract_sol_TruffleContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TruffleContract_sol_TruffleContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TruffleContract_sol_TruffleContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TruffleContract_sol_TruffleContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TruffleContract_sol_TruffleContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TruffleContract_sol_TruffleContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TruffleContract_sol_TruffleContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<TruffleContract_sol_TruffleContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TruffleContract_sol_TruffleContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TruffleContract_sol_TruffleContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TruffleContract_sol_TruffleContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TruffleContract_sol_TruffleContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TruffleContract_sol_TruffleContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
