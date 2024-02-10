package com.fan.locationsmartcontract.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple11;
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
public class RefundWithLoc_sol_RefundWithLoc extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b505f80546001600160a01b031916331790556116668061002e5f395ff3fe6080604052600436106100d9575f3560e01c806384ac65c51161007c578063ab29fe6311610057578063ab29fe6314610257578063c55b124c14610283578063d0e30db0146101c1578063ee4ae2c9146102a2575f80fd5b806384ac65c5146101e25780638cfb781614610209578063a9059cbb14610238575f80fd5b80636574235f116100b75780636574235f1461015757806376a08ba11461018357806378a0bac2146101a257806379ed1eee146101c3575f80fd5b8063080077a1146100dd5780630d3f9f3e1461011c57806312065fe01461013b575b5f80fd5b3480156100e8575f80fd5b506100fc6100f73660046110e3565b6102b5565b6040516101139b9a9998979695949392919061113d565b60405180910390f35b348015610127575f80fd5b506100fc6101363660046111ce565b6103c3565b348015610146575f80fd5b50475b604051908152602001610113565b348015610162575f80fd5b506101766101713660046111ce565b6103ee565b60405161011391906112a0565b34801561018e575f80fd5b506100fc61019d3660046111ce565b61053c565b3480156101ad575f80fd5b506101c16101bc3660046112ef565b610568565b005b3480156101ce575f80fd5b506101c16101dd3660046113f9565b610863565b3480156101ed575f80fd5b506101496101fc3660046111ce565b6001600160a01b03163190565b348015610214575f80fd5b506102286102233660046111ce565b6109e1565b6040519015158152602001610113565b348015610243575f80fd5b506101c1610252366004611454565b610b2c565b348015610262575f80fd5b506102766102713660046111ce565b610bc7565b604051610113919061147e565b34801561028e575f80fd5b506101c161029d3660046111ce565b610dc7565b6101c16102b0366004611454565b610ff9565b600381815481106102c4575f80fd5b5f918252602090912060079091020180546001820180546001600160a01b039092169350906102f2906114e0565b80601f016020809104026020016040519081016040528092919081815260200182805461031e906114e0565b80156103695780601f1061034057610100808354040283529160200191610369565b820191905f5260205f20905b81548152906001019060200180831161034c57829003601f168201915b50505060028401546003808601546004870154600588015460069098015496979396919550935080820b91600160201b8204900b9063ffffffff600160401b8204169060ff600160601b8204811691600160681b9004168b565b60026020525f9081526040902080546001820180546001600160a01b0390921692916102f2906114e0565b6103f661107a565b6001600160a01b038083165f908152600160208181526040928390208351610160810190945280549094168352908301805492939291840191610438906114e0565b80601f0160208091040260200160405190810160405280929190818152602001828054610464906114e0565b80156104af5780601f10610486576101008083540402835291602001916104af565b820191905f5260205f20905b81548152906001019060200180831161049257829003601f168201915b5050509183525050600282015460208201526003808301546040830152600483015460608301526005830154608083015260069092015480830b60a0830152600160201b810490920b60c082015263ffffffff600160401b83041660e082015260ff600160601b830481161515610100830152600160681b90920490911615156101209091015292915050565b600160208190525f9182526040909120805491810180546001600160a01b03909316926102f2906114e0565b5f546001600160a01b031633146105b25760405162461bcd60e51b81526020600482015260096024820152683737ba1037bbb732b960b91b60448201526064015b60405180910390fd5b60408051610160810182526001600160a01b038a811680835260208084018c815242858701819052606086018c90526080860189905260a0860188905260038b810b60c08801528a900b60e087015263ffffffff8d16610100870152600161012087018190525f61014088018190529485529283905295909220845181546001600160a01b03191694169390931783559051839291820190610654908261155c565b506040820151600282015560608201516003808301919091556080830151600483015560a0830151600583015560c08301516006909201805460e0850151610100860151610120870151610140909701511515600160681b0260ff60681b19971515600160601b0260ff60601b1963ffffffff938416600160401b021664ffffffffff60401b19948416600160201b0267ffffffffffffffff19909616939098169290921793909317919091169490941793909317939093169190911790915580546001810182555f91909152815160079091027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b810180546001600160a01b039093166001600160a01b031990931692909217825560208301518392917fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85c019061079f908261155c565b5060408201516002820155606082015160038201556080820151600482015560a0820151600582015560c08201516006909101805460e0840151610100850151610120860151610140909601511515600160681b0260ff60681b19961515600160601b0260ff60601b1963ffffffff938416600160401b021664ffffffffff60401b19948416600160201b0267ffffffffffffffff199096169390971692909217939093179190911693909317929092179290921617905550505050505050505050565b6001600160a01b038083165f9081526001602081815260408084208151610160810190925280549095168152918401805493949293918401916108a5906114e0565b80601f01602080910402602001604051908101604052809291908181526020018280546108d1906114e0565b801561091c5780601f106108f35761010080835404028352916020019161091c565b820191905f5260205f20905b8154815290600101906020018083116108ff57829003601f168201915b5050509183525050600282015460208201526003808301546040830152600483015460608301526005830154608083015260069092015480830b60a0830152600160201b810490920b60c082015263ffffffff600160401b8304811660e083015260ff600160601b84048116151561010080850191909152600160681b909404161515610120909201919091529082015191925061ffff841691161180156109c7575060a081015147115b156109da576109da838260a00151610ff9565b5050505050565b6001600160a01b038082165f9081526001602081815260408084208151610160810190925280549095168152918401805493948594909284019190610a25906114e0565b80601f0160208091040260200160405190810160405280929190818152602001828054610a51906114e0565b8015610a9c5780601f10610a7357610100808354040283529160200191610a9c565b820191905f5260205f20905b815481529060010190602001808311610a7f57829003601f168201915b5050509183525050600282015460208201526003808301546040830152600483015460608301526005830154608083015260069092015480830b60a0830152600160201b810490920b60c082015263ffffffff600160401b83041660e082015260ff600160601b830481161515610100830152600160681b90920490911615156101209182015201519392505050565b5f826001600160a01b0316826040515f6040518083038185875af1925050503d805f8114610b75576040519150601f19603f3d011682016040523d82523d5f602084013e610b7a565b606091505b5050905080610bc25760405162461bcd60e51b81526020600482015260146024820152732330b4b632b2103a379039b2b7321022ba3432b960611b60448201526064016105a9565b505050565b6003546060905f9067ffffffffffffffff811115610be757610be76112b2565b604051908082528060200260200182016040528015610c2057816020015b610c0d61107a565b815260200190600190039081610c055790505b5090505f5b600354811015610dc0575f60038281548110610c4357610c4361161c565b5f91825260209182902060408051610160810190915260079092020180546001600160a01b031682526001810180549293919291840191610c83906114e0565b80601f0160208091040260200160405190810160405280929190818152602001828054610caf906114e0565b8015610cfa5780601f10610cd157610100808354040283529160200191610cfa565b820191905f5260205f20905b815481529060010190602001808311610cdd57829003601f168201915b5050509183525050600282015460208201526003808301546040830152600483015460608301526005830154608083015260069092015480830b60a0830152600160201b810490920b60c082015263ffffffff600160401b83041660e082015260ff600160601b830481161515610100830152600160681b90920490911615156101209091015280519091506001600160a01b03808716911603610db75780838381518110610dab57610dab61161c565b60200260200101819052505b50600101610c25565b5092915050565b6003545f9067ffffffffffffffff811115610de457610de46112b2565b604051908082528060200260200182016040528015610e1d57816020015b610e0a61107a565b815260200190600190039081610e025790505b5090505f5b600354811015610fbd575f60038281548110610e4057610e4061161c565b5f91825260209182902060408051610160810190915260079092020180546001600160a01b031682526001810180549293919291840191610e80906114e0565b80601f0160208091040260200160405190810160405280929190818152602001828054610eac906114e0565b8015610ef75780601f10610ece57610100808354040283529160200191610ef7565b820191905f5260205f20905b815481529060010190602001808311610eda57829003601f168201915b5050509183525050600282015460208201526003808301546040830152600483015460608301526005830154608083015260069092015480830b60a0830152600160201b810490920b60c082015263ffffffff600160401b83041660e082015260ff600160601b830481161515610100830152600160681b90920490911615156101209091015280519091506001600160a01b03808616911603610fb45780838381518110610fa857610fa861161c565b60200260200101819052505b50600101610e22565b507faa393e002356dcf316561e723769a1ba80faedef54bbb0c5ad673e25ee51c16f81604051610fed919061147e565b60405180910390a15050565b5f546001600160a01b0316331461100e575f80fd5b6040515f906001600160a01b0384169083156108fc0290849084818181858888f19350505050905080610bc25760405162461bcd60e51b81526020600482015260146024820152732330b4b632b2103a379039b2b7321022ba3432b960611b60448201526064016105a9565b6040518061016001604052805f6001600160a01b03168152602001606081526020015f81526020015f81526020015f81526020015f81526020015f60030b81526020015f60030b81526020015f63ffffffff1681526020015f151581526020015f151581525090565b5f602082840312156110f3575f80fd5b5035919050565b5f81518084525f5b8181101561111e57602081850181015186830182015201611102565b505f602082860101526020601f19601f83011685010191505092915050565b6001600160a01b038c168152610160602082018190525f906111618382018e6110fa565b604084019c909c5250506060810198909852608088019690965260a0870194909452600392830b60c0870152910b60e085015263ffffffff16610100840152151561012083015215156101409091015292915050565b6001600160a01b03811681146111cb575f80fd5b50565b5f602082840312156111de575f80fd5b81356111e9816111b7565b9392505050565b80516001600160a01b031682525f6101606020830151816020860152611218828601826110fa565b91505060408301516040850152606083015160608501526080830151608085015260a083015160a085015260c083015161125760c086018260030b9052565b5060e083015161126c60e086018260030b9052565b506101008381015163ffffffff16908501526101208084015115159085015261014092830151151592909301919091525090565b602081525f6111e960208301846111f0565b634e487b7160e01b5f52604160045260245ffd5b803563ffffffff811681146112d9575f80fd5b919050565b8035600381900b81146112d9575f80fd5b5f805f805f805f80610100898b031215611307575f80fd5b8835611312816111b7565b9750602089013567ffffffffffffffff8082111561132e575f80fd5b818b0191508b601f830112611341575f80fd5b813581811115611353576113536112b2565b604051601f8201601f19908116603f0116810190838211818310171561137b5761137b6112b2565b816040528281528e6020848701011115611393575f80fd5b826020860160208301375f60208483010152809b5050505050506113b960408a016112c6565b9550606089013594506113ce60808a016112de565b93506113dc60a08a016112de565b925060c0890135915060e089013590509295985092959890939650565b5f805f806080858703121561140c575f80fd5b611415856112de565b9350611423602086016112de565b92506040850135611433816111b7565b9150606085013561ffff81168114611449575f80fd5b939692955090935050565b5f8060408385031215611465575f80fd5b8235611470816111b7565b946020939093013593505050565b5f60208083016020845280855180835260408601915060408160051b8701019250602087015f5b828110156114d357603f198886030184526114c18583516111f0565b945092850192908501906001016114a5565b5092979650505050505050565b600181811c908216806114f457607f821691505b60208210810361151257634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610bc257805f5260205f20601f840160051c8101602085101561153d5750805b601f840160051c820191505b818110156109da575f8155600101611549565b815167ffffffffffffffff811115611576576115766112b2565b61158a8161158484546114e0565b84611518565b602080601f8311600181146115bd575f84156115a65750858301515b5f19600386901b1c1916600185901b178555611614565b5f85815260208120601f198616915b828110156115eb578886015182559484019460019091019084016115cc565b508582101561160857878501515f19600388901b60f8161c191681555b505060018460011b0185555b505050505050565b634e487b7160e01b5f52603260045260245ffdfea26469706673582212208a38191f8b1ea19af23ca920861670fbebc4e499f7e233295c0ec2f40772d5c064736f6c63430008180033";

    public static final String FUNC_CHECKDELIVERY = "checkDelivery";

    public static final String FUNC_CHECKDELIVERYSTATUS = "checkDeliveryStatus";

    public static final String FUNC_CURRENTDELIVERY = "currentDelivery";

    public static final String FUNC_DELIVERIES = "deliveries";

    public static final String FUNC_DELIVERYHISTORY = "deliveryHistory";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_GETADDBALANCE = "getAddBalance";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETDEVLIVERYHISTORYWITHEVENT = "getDevliveryHistoryWithEvent";

    public static final String FUNC_GETDRIVERDELIVERYHISTORY = "getDriverDeliveryHistory";

    public static final String FUNC_RETURNDELIVERYOBJ = "returnDeliveryObj";

    public static final String FUNC_SENDMONEY = "sendMoney";

    public static final String FUNC_STARTDELIVERY = "startDelivery";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event DELIVERYHISTORY_EVENT = new Event("DeliveryHistory", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Delivery>>() {}));
    ;

    @Deprecated
    protected RefundWithLoc_sol_RefundWithLoc(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RefundWithLoc_sol_RefundWithLoc(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RefundWithLoc_sol_RefundWithLoc(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RefundWithLoc_sol_RefundWithLoc(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DeliveryHistoryEventResponse> getDeliveryHistoryEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DELIVERYHISTORY_EVENT, transactionReceipt);
        ArrayList<DeliveryHistoryEventResponse> responses = new ArrayList<DeliveryHistoryEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeliveryHistoryEventResponse typedResponse = new DeliveryHistoryEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.delivery = (List<Delivery>) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DeliveryHistoryEventResponse getDeliveryHistoryEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DELIVERYHISTORY_EVENT, log);
        DeliveryHistoryEventResponse typedResponse = new DeliveryHistoryEventResponse();
        typedResponse.log = log;
        typedResponse.delivery = (List<Delivery>) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DeliveryHistoryEventResponse> deliveryHistoryEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDeliveryHistoryEventFromLog(log));
    }

    public Flowable<DeliveryHistoryEventResponse> deliveryHistoryEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELIVERYHISTORY_EVENT));
        return deliveryHistoryEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> checkDelivery(BigInteger _lat, BigInteger _lon, String driverAdd, BigInteger _distance) {
        final Function function = new Function(
                FUNC_CHECKDELIVERY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int32(_lat), 
                new org.web3j.abi.datatypes.generated.Int32(_lon), 
                new org.web3j.abi.datatypes.Address(160, driverAdd), 
                new org.web3j.abi.datatypes.generated.Uint16(_distance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> checkDeliveryStatus(String _driverAddress) {
        final Function function = new Function(FUNC_CHECKDELIVERYSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _driverAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>> currentDelivery(String param0) {
        final Function function = new Function(FUNC_CURRENTDELIVERY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int32>() {}, new TypeReference<Int32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>(function,
                new Callable<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue(), 
                                (Boolean) results.get(9).getValue(), 
                                (Boolean) results.get(10).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>> deliveries(String param0) {
        final Function function = new Function(FUNC_DELIVERIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int32>() {}, new TypeReference<Int32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>(function,
                new Callable<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue(), 
                                (Boolean) results.get(9).getValue(), 
                                (Boolean) results.get(10).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>> deliveryHistory(BigInteger param0) {
        final Function function = new Function(FUNC_DELIVERYHISTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Int32>() {}, new TypeReference<Int32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>(function,
                new Callable<Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>>() {
                    @Override
                    public Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple11<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue(), 
                                (Boolean) results.get(9).getValue(), 
                                (Boolean) results.get(10).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> deposit(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getAddBalance(String _address) {
        final Function function = new Function(FUNC_GETADDBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getBalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getDevliveryHistoryWithEvent(String _driverAddress) {
        final Function function = new Function(
                FUNC_GETDEVLIVERYHISTORYWITHEVENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _driverAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getDriverDeliveryHistory(String _driverAddress) {
        final Function function = new Function(FUNC_GETDRIVERDELIVERYHISTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _driverAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Delivery>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Delivery> returnDeliveryObj(String _driverAddress) {
        final Function function = new Function(FUNC_RETURNDELIVERYOBJ, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _driverAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Delivery>() {}));
        return executeRemoteCallSingleValueReturn(function, Delivery.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sendMoney(String _to, BigInteger amt, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SENDMONEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(amt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> startDelivery(String _driverAddress, String _name, BigInteger _distance, BigInteger _endTime, BigInteger _lat, BigInteger _lon, BigInteger _initialPayWei, BigInteger _incentiveWei) {
        final Function function = new Function(
                FUNC_STARTDELIVERY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _driverAddress), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint32(_distance), 
                new org.web3j.abi.datatypes.generated.Uint256(_endTime), 
                new org.web3j.abi.datatypes.generated.Int32(_lat), 
                new org.web3j.abi.datatypes.generated.Int32(_lon), 
                new org.web3j.abi.datatypes.generated.Uint256(_initialPayWei), 
                new org.web3j.abi.datatypes.generated.Uint256(_incentiveWei)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static RefundWithLoc_sol_RefundWithLoc load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RefundWithLoc_sol_RefundWithLoc(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RefundWithLoc_sol_RefundWithLoc load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RefundWithLoc_sol_RefundWithLoc(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RefundWithLoc_sol_RefundWithLoc load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RefundWithLoc_sol_RefundWithLoc(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RefundWithLoc_sol_RefundWithLoc load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RefundWithLoc_sol_RefundWithLoc(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RefundWithLoc_sol_RefundWithLoc> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RefundWithLoc_sol_RefundWithLoc.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<RefundWithLoc_sol_RefundWithLoc> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RefundWithLoc_sol_RefundWithLoc.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RefundWithLoc_sol_RefundWithLoc> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RefundWithLoc_sol_RefundWithLoc.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RefundWithLoc_sol_RefundWithLoc> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RefundWithLoc_sol_RefundWithLoc.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Delivery extends DynamicStruct {
        public String driverAddress;

        public String name;

        public BigInteger startTime;

        public BigInteger endtime;

        public BigInteger initialPayWei;

        public BigInteger incentivePayWei;

        public BigInteger _lat;

        public BigInteger _lon;

        public BigInteger distance;

        public Boolean ongoing;

        public Boolean compliant;

        public Delivery(String driverAddress, String name, BigInteger startTime, BigInteger endtime, BigInteger initialPayWei, BigInteger incentivePayWei, BigInteger _lat, BigInteger _lon, BigInteger distance, Boolean ongoing, Boolean compliant) {
            super(new org.web3j.abi.datatypes.Address(160, driverAddress), 
                    new org.web3j.abi.datatypes.Utf8String(name), 
                    new org.web3j.abi.datatypes.generated.Uint256(startTime), 
                    new org.web3j.abi.datatypes.generated.Uint256(endtime), 
                    new org.web3j.abi.datatypes.generated.Uint256(initialPayWei), 
                    new org.web3j.abi.datatypes.generated.Uint256(incentivePayWei), 
                    new org.web3j.abi.datatypes.generated.Int32(_lat), 
                    new org.web3j.abi.datatypes.generated.Int32(_lon), 
                    new org.web3j.abi.datatypes.generated.Uint32(distance), 
                    new org.web3j.abi.datatypes.Bool(ongoing), 
                    new org.web3j.abi.datatypes.Bool(compliant));
            this.driverAddress = driverAddress;
            this.name = name;
            this.startTime = startTime;
            this.endtime = endtime;
            this.initialPayWei = initialPayWei;
            this.incentivePayWei = incentivePayWei;
            this._lat = _lat;
            this._lon = _lon;
            this.distance = distance;
            this.ongoing = ongoing;
            this.compliant = compliant;
        }

        public Delivery(Address driverAddress, Utf8String name, Uint256 startTime, Uint256 endtime, Uint256 initialPayWei, Uint256 incentivePayWei, Int32 _lat, Int32 _lon, Uint32 distance, Bool ongoing, Bool compliant) {
            super(driverAddress, name, startTime, endtime, initialPayWei, incentivePayWei, _lat, _lon, distance, ongoing, compliant);
            this.driverAddress = driverAddress.getValue();
            this.name = name.getValue();
            this.startTime = startTime.getValue();
            this.endtime = endtime.getValue();
            this.initialPayWei = initialPayWei.getValue();
            this.incentivePayWei = incentivePayWei.getValue();
            this._lat = _lat.getValue();
            this._lon = _lon.getValue();
            this.distance = distance.getValue();
            this.ongoing = ongoing.getValue();
            this.compliant = compliant.getValue();
        }
    }

    public static class DeliveryHistoryEventResponse extends BaseEventResponse {
        public List<Delivery> delivery;
    }
}
