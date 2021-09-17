Feature: Brexit app regression pack

  @Login
  Scenario: User login to Breit APP
    Given intitilizing test environment
    Given user launches Brexit App in "CHROME"
    And user enters username
    And user enters password
    Then user clicks on Sign In
    And user logged in successfully

  @EditConsignment @TestCaseID_004
  Scenario: user clicks on consignment menu
    Then user clicks on menu button
    And user navigates to consignment page
    Then search for consignment "X0222"
    And user Verify consignment status is "OPEN"
    And user clicks on close consignment

  #And user seal and print consignment
  @TestCaseID_005 @CreateConsignment @INPROGRESS
  Scenario: Create new dispatch Consignment
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
    #And user scans UPI "9011425449601049"
    #And verify "Accepted" message is displayed
    Then user clicks on menu button
    And user navigates to consignment page
    Then user search for consignment ID
    And user Verify consignment status is "OPEN"
    And user clicks on close consignment
    And user seal and print consignment

  @TestCaseID_007 @CreateConsignment
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
    And user selects Departure date "2" and time HH "2" MM "00"
    And user enters vessel number "123456"
    And user enters trailer Number "456789"
    Then user clicks on create button
    And user scans UPI "9011426994301049"
    And verify "Parcel has been already scanned" message is displayed

  @EditConsignment @TestCaseID_009 @Login
  Scenario: user clicks on consignment menu
    Then user clicks on menu button
    And user navigates to consignment page
    And user reset search criteria
    Then user sorts with cosignment status as "CLOSED"
    #Then user selects record from search result

  @EditConsignment @TestCaseID_009
  Scenario: user clicks on consignment menu
    Then user selects record from search result
    And verify that closed consignment cannot be edited
    Then search for consignment "X0222"
    #And user verify consignment "X0222" is created
    And user Verify consignment status is "CREATED"
    And user clicks on edit consignment
    And user enters trailer Number "42"
    And user enters vessel number "18"
    And user clicks on apply button

  @Logout @test3
  Scenario: User log out of Breit APP
    Given user logged in successfully
    Then user Signs Out

  @test123
  Scenario: test sample code
    And test sample code ""
    #And extract UPI from JSON file "C:\\Users\\123440866\\Downloads\\1609164045910.json"
    #And user initiates bulk scanning of parcels
