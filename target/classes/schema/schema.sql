Drop database ship;

Create database ship;

use ship;

create table Admin(
                      Admin_Name VARCHAR(100) NOT NULL,
                      Admin_Possision VARCHAR(100) NOT NULL,
                      Admin_Nic VARCHAR(100) UNIQUE,
                      EMail VARCHAR(100) UNIQUE,
                      Password VARCHAR(50),
                      Confirm_Password VARCHAR(50),
                      Contact Varchar(20),
                      Admin_Code Varchar(10),
                      CONSTRAINT PRIMARY KEY (Admin_Nic,EMail)
);

create table Employees(
                          Employee_Id VARCHAR(30) UNIQUE,
                          Employee_Name VARCHAR(100),
                          Employee_Possision VARCHAR(100),
                          Employee_Nic VARCHAR(20) UNIQUE,
                          Employee_AddDate VARCHAR(30),
                          Employee_AddTime VARCHAR(20),
                          CONSTRAINT PRIMARY KEY (Employee_Id,Employee_Nic)
);

create table Suppliers(
                          Supplier_Nic VARCHAR(30) PRIMARY KEY,
                          Supplier_Name VARCHAR(100),
                          Item_Id VARCHAR(20),
                          Item_Name VARCHAR(100),
                          Supply_Date VARCHAR(30),
                          Supply_Time VARCHAR(30)
);


create table Stocks(
                       Stock_Id VARCHAR(50),
                       Employee_Nic VARCHAR(30) NOT NULL,
                       Supplier_Nic VARCHAR(30),
                       Time VARCHAR(30),
                       Date VARCHAR(30),
                       Quantity VARCHAR(40),
                       CONSTRAINT PRIMARY KEY(Stock_Id,Employee_Nic,Supplier_Nic),
                       CONSTRAINT FOREIGN KEY(Employee_Nic) REFERENCES employees(Employee_Nic)
                           ON UPDATE CASCADE ON DELETE CASCADE,
                       CONSTRAINT FOREIGN KEY(Supplier_Nic) REFERENCES suppliers(Supplier_Nic)
                           ON UPDATE CASCADE ON DELETE CASCADE
);


create table Orders(
                       Order_Id VARCHAR(20) UNIQUE,
                       Order_Company VARCHAR(100) NOT NULL,
                       Order_Country VARCHAR(100) NOT NULL,
                       Stock_Id VARCHAR(50),
                       Admin_Nic VARCHAR(100),
                       Order_Date VARCHAR(100),
                       Order_Time VARCHAR(20),
                       CONSTRAINT PRIMARY KEY (Order_Id,Stock_Id,Admin_Nic,Order_Date,Order_Time),
                       CONSTRAINT FOREIGN KEY(Admin_Nic) REFERENCES admin(Admin_Nic)
                           ON UPDATE CASCADE ON DELETE CASCADE,
                       CONSTRAINT FOREIGN KEY(Stock_Id) REFERENCES Stocks(Stock_Id)
                           ON UPDATE CASCADE ON DELETE CASCADE
);



create table AdminReports(
                             Report_Id VARCHAR(100) UNIQUE,
                             Admin_Nic VARCHAR(100),
                             Employee_Nic VARCHAR(30) NOT NULL,
                             Time VARCHAR(100) ,
                             Date VARCHAR(100) ,
                             CONSTRAINT PRIMARY KEY (Report_Id,Admin_Nic,Employee_Nic),
                             CONSTRAINT FOREIGN KEY(Admin_Nic) REFERENCES admin(Admin_Nic)
                                 ON UPDATE CASCADE ON DELETE CASCADE,
                             CONSTRAINT FOREIGN KEY(Employee_Nic) REFERENCES employees(Employee_Nic)
                                 ON UPDATE CASCADE ON DELETE CASCADE
);

create table Materials(
                          Material_Id VARCHAR(30) PRIMARY KEY,
                          Material_Name VARCHAR(100) NOT NULL,
                          Supplier_Country VARCHAR(100),
                          Quality VARCHAR(50),
                          Supply_Date VARCHAR(50),
                          Supply_Time VARCHAR(30)
);


create table Machines(
                         Machine_Id VARCHAR(20) PRIMARY KEY,
                         Machine_Name VARCHAR(50),
                         Quality VARCHAR(50),
                         Repair_Count VARCHAR(255) NOT NULL,
                         Repair_Date VARCHAR(100),
                         Repair_Time VARCHAR(100)
);

create table UseMachinesConformation(
                                        Machine_Id VARCHAR(20),
                                        Material_Id VARCHAR(30),
                                        Employee_Nic VARCHAR(20),
                                        Time VARCHAR(20),
                                        Date VARCHAR(20),
                                        CONSTRAINT PRIMARY KEY(Machine_Id,Material_Id,Employee_Nic),
                                        CONSTRAINT FOREIGN KEY(Machine_Id) REFERENCES machines(Machine_Id)
                                            ON UPDATE CASCADE ON DELETE CASCADE,
                                        CONSTRAINT FOREIGN KEY(Material_Id) REFERENCES materials(Material_Id)
                                            ON UPDATE CASCADE ON DELETE CASCADE,
                                        CONSTRAINT FOREIGN KEY(Employee_Nic) REFERENCES employees(Employee_Nic)
                                            ON UPDATE CASCADE ON DELETE CASCADE
);

create table Daily_Reports(
                              Report_Id VARCHAR(200) UNIQUE,
                              Employee_Nic VARCHAR(30) NOT NULL,
                              Temperature_Level VARCHAR(200),
                              Machine_Running_Pressure VARCHAR(200),
                              CONSTRAINT PRIMARY KEY(Employee_Nic,Report_Id),
                              CONSTRAINT FOREIGN KEY(Employee_Nic) REFERENCES employees(Employee_Nic)
                                  ON UPDATE CASCADE ON DELETE CASCADE
);






create table OrderDetails(
                             Order_Id VARCHAR(20),
                             Stock_Id VARCHAR(50),
                             Qty VARCHAR(100),
                             CONSTRAINT PRIMARY KEY(Order_Id,Stock_Id),
                             CONSTRAINT FOREIGN KEY(Order_Id) REFERENCES Orders(Order_Id)
                                 ON UPDATE CASCADE ON DELETE CASCADE,
                             CONSTRAINT FOREIGN KEY(Stock_Id) REFERENCES Stocks(Stock_Id)
                                 ON UPDATE CASCADE ON DELETE CASCADE
);


create table Buyers(
                       Buyer_Nic VARCHAR(100) UNIQUE,
                       Admin_Nic VARCHAR(100),
                       Buyer_Country VARCHAR(50),
                       Buy_Item VARCHAR(100) NOT NULL,
                       Buy_Date VARCHAR(30),
                       Buy_Time VARCHAR(30),
                       CONSTRAINT PRIMARY KEY(Buyer_Nic,Admin_Nic,Buy_Time),
                       CONSTRAINT FOREIGN KEY(Admin_Nic) REFERENCES admin(Admin_Nic)
                           ON UPDATE CASCADE ON DELETE CASCADE
);

create table Admin_Suppliers(
                                Supplier_Nic VARCHAR(100),
                                Admin_Nic VARCHAR(100),
                                CONSTRAINT PRIMARY KEY(Supplier_Nic,Admin_Nic),
                                CONSTRAINT FOREIGN KEY(Admin_Nic) REFERENCES admin(Admin_Nic)
                                    ON UPDATE CASCADE ON DELETE CASCADE,
                                CONSTRAINT FOREIGN KEY(Supplier_Nic) REFERENCES suppliers(Supplier_Nic)
                                    ON UPDATE CASCADE ON DELETE CASCADE
);














