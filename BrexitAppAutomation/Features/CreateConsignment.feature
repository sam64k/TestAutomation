Feature: Brexit app regression pack (completed scenarios)

  @Login @regression
  Scenario: User login to Breit APP
    Given intitilizing test environment
    Given user launches Brexit App in "CHROME"
    And user enters username
    And user enters password
    Then user clicks on Sign In
    And user logged in successfully

  @TestCaseID_004 @CreateConsignment @regression
  Scenario: Create a consignment Arrowxl-Wigan
    Given user logged in successfully
    Then user clicks on Create New button
    And user selects "ARROWXL" from Carrier dropdown
    And user selects "STENA" from Shipping Line dropdown
    Then verify nationality is "Cyprus"
    And Verify port is "Dublin"
    And user selects Departure date "2" and time HH "2" MM "00"
    And user enters vessel number "123456"
    And user enters trailer Number "456789"
    Then user clicks on create button
    And capture consignment ID
    And extract UPI from JSON file "src\\test\\resources\\UPI.json"
    And user initiates bulk scanning of parcels
    #And user scans UPI "9011425449601049"
    #And verify "Accepted" message is displayed
    Then user clicks on menu button
    And user navigates to consignment page
    Then user search for consignment ID
    And user Verify consignment status is "OPEN"
    And user clicks on close consignment
    And user seal and print consignment
    
  @TestCaseID_005 @CreateConsignment @regression
  Scenario: Create a consignment XPO Wrexham
    Given user logged in successfully
    Then user clicks on Create New button
    And user selects "XPO Wrexham" from Carrier dropdown
    #And user selects "" from Destination dropdown
    And user selects "SEATRUCK" from Shipping Line dropdown
    And user selects Departure date "2" and time HH "2" MM "00"
    And user enters vessel number "123456"
    And user enters trailer Number "456789"
    Then user clicks on create button
    And capture consignment ID
    And extract UPI from JSON file "src\\test\\resources\\UPI.json"
    And user initiates bulk scanning of parcels
    Then user clicks on menu button
    And user navigates to consignment page
    Then user search for consignment ID
    And user Verify consignment status is "OPEN"
    And user clicks on close consignment
    And user seal and print consignment

  @TestCaseID_007 @CreateConsignment @regression
  Scenario: Create new Consignment - parcel that’s already been scanned
    Given user logged in successfully
    #Then user clicks on menu button
    #And user selects consignment
    Then user clicks on Create New button
    And user selects "ARROWXL" from Carrier dropdown
    #And user selects "" from Destination dropdown
    And user selects "STENA" from Shipping Line dropdown
    Then verify nationality is "Cyprus"
    And Verify port is "Dublin"
    And user selects Departure date "2" and time HH "2" MM "00"
    And user enters vessel number "123456"
    And user enters trailer Number "456789"
    Then user clicks on create button
    And user scans UPI "9011426994301049"
    And verify "Parcel has been already scanned" message is displayed
    
    ######################

  @CreateConsignment
  Scenario: Create new dispatch Consignment
    Given user logged in successfully
    #Then user clicks on menu button
    #And user selects consignment
    Then user clicks on Create New button
    And user selects "ARROWXL" from Carrier dropdown
    #And user selects "" from Destination dropdown
    And user selects "STENA" from Shipping Line dropdown
    Then verify nationality is "Cyprus"
    And Verify port is "Dublin"
    And user selects Departure date "9" and time HH "2" MM "00"
    And user enters vessel number "123456"
    And user enters trailer Number "456789"
    Then user clicks on create button
    And user scans UPI "123456789"
    And verify "Parcel not found" message is displayed

  @test786
  Scenario: user clicks on consignment menu
    Then user clicks on menu button
    And user navigates to consignment page
    Then search for consignment "A0231"
    And user verify consignment "A0231" is created
    And user Verify consignment status is "OPEN"
    And user clicks on edit consignment
    And user enters vessel number "1005"
    And user enters trailer Number "400"
    And user clicks on apply button

  @CloseConsignment
  Scenario: Closing consignment
    Given user navigates to consignment page
    Then user clicks on menu button
    And user navigates to consignment page

  @Logout @test3
  Scenario: User log out of Breit APP
    Given user logged in successfully
    Then user Signs Out
