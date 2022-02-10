/// <reference types="Cypress" />
import {register} from '../pageobjects/PageClass'

describe('Test register', function() {
    
    beforeEach(()=>{
        cy.visit(Cypress.env('baseURL'))
        // cy.fixture('testdata').then((testdata)=>{
        //     this.testdata = testdata
        // })

    })
   
    it('Invalid email and valid password', function(){

        register("saurabh@gmailcom","Saurabh123@")
       
        cy.get(".woocommerce-error > li").should('contains.text',' Please provide a valid email address.')
    })
    it('Empty email and valid password', function(){

        register("","Saurabh123@")
       
        cy.get(".woocommerce-error > li").should('contains.text',' Please provide a valid email address.')
    })
    it.only('Valid email and Empty password', function(){

        register("saurabh@gmail.com","")
       
        cy.get(".woocommerce-error > li").should('contains.text','Error: An account is already registered with your email address. Please login.')
    })
    it.only('Empty email and empty password', function(){

        register("","")
       
        cy.get(".woocommerce-error > li").should('contains.text',' Please provide a valid email address.')
    })
    it('New Valid username and valid password', function(){

        register("saurabh@gmail.com","Saurabh123@")
        //cy.get('p[class*="lost_password"]').should('contains.text','Lost your password?')
    })
})