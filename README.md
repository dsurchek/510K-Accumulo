# 510K-Accumulo

This runs against the Open FDA 510K data set https://download.open.fda.gov/device/510k/device-510k-0001-of-0001.json.zip.

GSON is used to marshal JSON to Java, however it also uses streaming and threads so that all objects are in in memory.

This is a maven project and can also be run using maven using the standard Accumulo command line arguments.

 s  mvn exec:java -Dexec.mainClass="fda.Controller" -Dexec.args="-u root -p <password> -i <instance name> -z localhost:2181"
 
This will create a table in Accumulo named 510k.  If the table is present it will be dropped and re-created.  The table has the following schema
   <K Number> "record":<field name> <current time> <field value>
    
To see any record using the Accumulo shell 
   root@fda 510k> table 510k
   root@fda 510k> scan -b DEN000010 -e DEN000010


There are a number of improvements than can be acheived.  
1. Testing.  There is only a minimal effort for ensuring mapping from Java to Accumulo.
2. Properties should be used over hard-coding, e.g. 510K URL.
3. The code uses GSON binding meaning that any change to the 510K schema requires a compile time dependency in Java.  It would be arguably better to use the streaming interface that Jackson provides.  Specifications could allow mapping JSON name/value to Accumulo rows.
3. Or NiFi...
4. Ingestion can also be accomplished by pushing records into HDFS and then using a mapper to create Accumulo rows. This may be faster in a larger cluster.




A record is shown below.

DEN000010 record:address_1 []    11030 ROSELLE ST.
DEN000010 record:address_2 []
DEN000010 record:advisory_committee []    CH
DEN000010 record:advisory_committee_description []    Clinical Chemistry
DEN000010 record:applicant []    BIOSITE INCORPORATED
DEN000010 record:city []    SAN DIEGO
DEN000010 record:clearance_type []    Post-NSE
DEN000010 record:contact []    JEFF  DAHLEN
DEN000010 record:country_code []    US
DEN000010 record:date_received []    2000-11-15
DEN000010 record:decision_code []    DENG
DEN000010 record:decision_date []    2000-11-20
DEN000010 record:decision_description []    Unknown
DEN000010 record:device_name []    TRIAGE B-TYPE NATRIURETIC PEPTIDE (BNP) TEST
DEN000010 record:expedited_review_flag []
DEN000010 record:openfda/device_class []    2
DEN000010 record:openfda/device_name []    Test,Natriuretic Peptide
DEN000010 record:openfda/fei_number/0 []    3004932970
DEN000010 record:openfda/fei_number/1 []    2967
DEN000010 record:openfda/fei_number/10 []    2245578
DEN000010 record:openfda/fei_number/11 []    3002755490
DEN000010 record:openfda/fei_number/12 []    1000125596
DEN000010 record:openfda/fei_number/13 []    2517506
DEN000010 record:openfda/fei_number/14 []    1219913
DEN000010 record:openfda/fei_number/15 []    3008990414
DEN000010 record:openfda/fei_number/16 []    3005670738
DEN000010 record:openfda/fei_number/17 []    3005643513
DEN000010 record:openfda/fei_number/18 []    3011183348
DEN000010 record:openfda/fei_number/19 []    2246703
DEN000010 record:openfda/fei_number/2 []    2122870
DEN000010 record:openfda/fei_number/20 []    3007111389
DEN000010 record:openfda/fei_number/21 []    3008661376
DEN000010 record:openfda/fei_number/22 []    3008117742
DEN000010 record:openfda/fei_number/3 []    3005174594
DEN000010 record:openfda/fei_number/4 []    2432235
DEN000010 record:openfda/fei_number/5 []    3004205692
DEN000010 record:openfda/fei_number/6 []    3004192065
DEN000010 record:openfda/fei_number/7 []    2521625
DEN000010 record:openfda/fei_number/8 []    3002806559
DEN000010 record:openfda/fei_number/9 []    3002807710
DEN000010 record:openfda/medical_specialty_description []    Clinical Chemistry
DEN000010 record:openfda/registration_number/0 []    3004932970
DEN000010 record:openfda/registration_number/1 []    2122870
DEN000010 record:openfda/registration_number/10 []    9610126
DEN000010 record:openfda/registration_number/11 []    9610987
DEN000010 record:openfda/registration_number/12 []    3011183348
DEN000010 record:openfda/registration_number/13 []    2517506
DEN000010 record:openfda/registration_number/14 []    8031673
DEN000010 record:openfda/registration_number/15 []    1219913
DEN000010 record:openfda/registration_number/16 []    3008990414
DEN000010 record:openfda/registration_number/17 []    3005643513
DEN000010 record:openfda/registration_number/18 []    1226181
DEN000010 record:openfda/registration_number/19 []    2246703
DEN000010 record:openfda/registration_number/2 []    3005174594
DEN000010 record:openfda/registration_number/20 []    3007111389
DEN000010 record:openfda/registration_number/21 []    3008661376
DEN000010 record:openfda/registration_number/22 []    3008117742
DEN000010 record:openfda/registration_number/3 []    2432235
DEN000010 record:openfda/registration_number/4 []    2027969
DEN000010 record:openfda/registration_number/5 []    3004205692
DEN000010 record:openfda/registration_number/6 []    3004192065
DEN000010 record:openfda/registration_number/7 []    2521625
DEN000010 record:openfda/registration_number/8 []    3005360469
DEN000010 record:openfda/registration_number/9 []    2245578
DEN000010 record:openfda/regulation_number []    862.1117
DEN000010 record:postal_code []    92121
DEN000010 record:product_code []    NBC
DEN000010 record:review_advisory_committee []    CH
DEN000010 record:state []    CA
DEN000010 record:statement_or_summary []
DEN000010 record:third_party_flag []    N
DEN000010 record:zip_code []    92121




