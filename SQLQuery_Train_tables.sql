/****** Script for creating train_delay_details table  ******/


  CREATE TABLE CBC_Firmstep_Test.dbo.train_delay_details  (
    journer_id int PRIMARY KEY,
      train_id varchar(50),
     station varchar(50),
	 scheduleDep varchar(50),
	 actualDep varchar(50),
	 delay int 
);