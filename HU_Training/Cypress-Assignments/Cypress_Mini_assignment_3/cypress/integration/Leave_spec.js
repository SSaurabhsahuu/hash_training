/// <reference types="Cypress" />

describe('Test Leave tab',function(){
    let login_data;
    let date;

    before(()=>{
        cy.fixture("calendar_data").then(function(data)
        {
            date=data    
        })
        cy.fixture("login_data_1").then(function(data)
        {
            login_data=data         
        })
    })

    beforeEach('Test login',()=>{
        cy.login(login_data.username,login_data.password)           // custom  command  login

        cy.url().should('include','/dashboard')
    })

    it('Select Engineering from drop down',()=>{
       
        // enter leave page
        cy.get('a[id="menu_leave_viewLeaveModule"]').click()           // click on leave tab
        
        cy.get('input[id="calFromDate"]').type(date.from)            // enter from
        cy.get('input[id="calToDate"]').type(date.to)                // enter to

        cy.get('select[id="leaveList_cmbSubunit"]').select('Engineering').should('have.value','3')    // select Engineering

        cy.get('#btnSearch').click()                                    // click on search

        cy.get('#resultTable > tbody > tr > td').should('contains.text','No Records Found')      // validate table
    })

    it('Select All from drop down',()=>{

        cy.get('a[id="menu_leave_viewLeaveModule"]').click()            // click on leave tab
        
        cy.get('input[id="calFromDate"]').type('2021-01-01\n')           // enter from
        cy.get('input[id="calToDate"]').type('2022-12-31\n')            // enter to

        cy.get('select[id="leaveList_cmbSubunit"]').select('All').should('have.value','0')      // select All

        cy.get('#btnSearch').click()                                    // click on search

        cy.get('#resultTable > tbody > tr td:nth-child(4)').contains('4.00')        // validate Leave balance
                                                                                    // select Approve
        cy.get('#resultTable > tbody > tr td:nth-child(8) > select').select('Approve').should('have.value','89') 
        
    })
})
