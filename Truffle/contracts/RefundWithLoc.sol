// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

contract RefundWithLoc {
    address owner;

    struct Delivery {
        address driverAddress;
        string name;
        uint256 startTime;
        uint256 endtime;
        uint initialPayWei;
        uint incentivePayWei;
        int32 _lat;
        int32 _lon;
        uint32 distance;
        bool ongoing;
        bool compliant;
    }
    mapping (address => Delivery) public deliveries;
    mapping (address => Delivery) public currentDelivery;
    Delivery [] public deliveryHistory;
    constructor () public  {
        owner = msg.sender;
    }
    // event DeliveryHistory(address indexed sender, uint256 startTime,uint256 endtime,int32 _lat,int32 _lon);
    event DeliveryHistory(Delivery [] delivery);
    function deposit() public payable {}

    function sendMoney(address payable  _to,uint amt) public payable  {
        require(msg.sender == owner);
        bool sent = _to.send(amt);
        require(sent, "Failed to send Ether");
    }

     function startDelivery(address _driverAddress,string memory _name,uint32 _distance,
        uint256 _endTime,int32 _lat,int32 _lon, uint _initialPayWei,uint _incentiveWei) public {
        require(msg.sender == owner,"not owner");
        uint256 _startTime = block.timestamp;
        Delivery memory delivery = Delivery(_driverAddress,_name,_startTime,_endTime,_initialPayWei,_incentiveWei,_lat,_lon,_distance,true,false);
        deliveries[_driverAddress] = delivery;
      
    }

    function getDriverDeliveryHistory (address _driverAddress) public view returns (Delivery [] memory deliveryList){
        Delivery [] memory deliveryHist = new Delivery[](deliveryHistory.length);
        for (uint i = 0; i < deliveryHistory.length; i++) {
            Delivery memory del = deliveryHistory[i];
            if(del.driverAddress == _driverAddress) {
                deliveryHist[i] = del;
            }
        }

        return deliveryHist;
    }

    function getAddBalance(address _address) public view returns (uint256) {
        return _address.balance;
    }

    function checkDelivery(int32 _lat,int32 _lon,address payable driverAdd,uint16 _distance) public {
        // address payable senderAdd = msg.sender;
        Delivery memory delivery = deliveries[driverAdd];
        if (delivery.distance > _distance && getBalance() > delivery.incentivePayWei) {
            transfer(driverAdd, delivery.incentivePayWei);
        }
    }

    
    function returnDeliveryObj(address _driverAddress) public view returns (Delivery memory) {
        return deliveries[_driverAddress];
    }
    function transfer(address payable _to, uint _amount) public {
        // Note that "to" is declared as payable
        (bool success, ) = _to.call{value: _amount}("");
        require(success, "Failed to send Ether");
    }

    function checkDeliveryStatus (address _driverAddress) public view returns (bool){
        Delivery memory delivery = deliveries[_driverAddress];
        return delivery.ongoing;
    }
    function getBalance() public view returns(uint){
        return address(this).balance;
    }
}