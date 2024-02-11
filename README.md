# Refund by Location Smart Contract
The project's goal is to develop and deploy an Ethereum-based decentralised application (dApp) that includes a thoroughly tested smart contract on a testnet, accompanied by a user interface for real-time status monitoring and management by GeoLogix Solutions.

The smart contract in this scenario is going to be used to facilitate a delivery service that an employer will issue a delivery to happen for certain driver to be at a certain location in a given time. 
This will be done by an admin android application where it connects to an already deployed smart contract with the admin's address and when ever it wants to create a delivery it accesses an exposed method from the smart contract 
where it will set the parameters for the delivery like location for the driver to be, the allowed distance from the location and the time stamp where the delivery ends.

To get a full detail on how the project works head over to this link [https://medium.com/@fanuelabebe/web-3-android-dapp-8b71e9b213a7]

## Part of the Project

- Back-End:[Truffle-Folder](https://github.com/fanuelabebe/RefundByLocationSmartContract/tree/main/Truffle)
- Front-End:[Front-End-Folder](https://github.com/fanuelabebe/RefundByLocationSmartContract/tree/main/app)
---

## Folder Structure

### You will find the following directories in this project

1. `./app/` - contains the full android project
2. `./app/src/main/java/com/fan/locationsmartcontract/employer/` - contains the implementation for the admin 
3. `./app/src/main/java/com/fan/locationsmartcontract/driver/` - contains the implementation for the employee/driver 
4. `./Screenshots` - contains screenshots of the frontend UI
5. `./Truffle/` - contains implementation of the smart contract

---

## Tech Stack

We have used the following techstacks for making this Application

- Android
- Web3j
- Truffle
- nodejs
- Solidity
