/// <reference types="Cypress" />
import {login} from '../pageobjects/PageClass'

describe('Test Login', function() {
    
    beforeEach(()=>{
        cy.visit(Cypress.env('baseURL'))
        // cy.fixture('testdata').then((testdata)=>{
        //     this.testdata = testdata
        // })

    })
    it('wrong username and password', function(){

        login("saurabh","saurabh123")
        cy.get('p[class*="lost_password"]').should('contains.text','Lost your password?')
    })
    it.only('Valid username and password', function(){

        login("saurabh@gmail.com","Saurabh123@")
      //  cy.get('p[class*="lost_password"]').should('contains.text','Lost your password?')
    })
})