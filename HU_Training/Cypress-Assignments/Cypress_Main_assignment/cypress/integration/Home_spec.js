/// <reference types="Cypress" />
import {login} from '../pageobjects/PageClass'

describe('Test Home page', function() {
    
    beforeEach(()=>{
        cy.visit(Cypress.env('baseURL'))
        // cy.fixture('testdata').then((testdata)=>{
        //     this.testdata = testdata
        // })

    })
   
    it('test shopping ', function(){
 
        cy.get('#menu-icon').click()
        cy.contains('Shop').click()
        cy.get('#content > nav > a').click()
        
        
        cy.get('.products').should('have.length',3)
        
        cy.get('.products').each(($ele,index)=>{         
            const canText=$ele.text()
            if(canText.includes('Selenium Ruby'))
            {
                cy.get('.products').eq(index).click()
            }
        })
        cy.get('.single_add_to_cart_button').click()
        cy.get('.woocommerce-message').should('contain.text',' “Selenium Ruby” has been added to your basket.')  // validation

        cy.get('#wpmenucartli > a > span.amount').should('contain.text','₹500.00')
        
        cy.get('#menu-icon').click()
         cy.get('#wpmenucartli > a').click()  // click on items
          // validate subtotal < total
         cy.get('td[data-title="Subtotal"] > span').should('contain.text','₹500.00')
         
        cy.get('[class*="checkout-button"]').click()
      
        // cy.get('.showlogin').click()
      //  cy.get('#username').should('be.enabled').and('be.visible').type('saurabh@gmail.com')
       // cy.get('#password').should('be.enabled').and('be.visible').type('Saurabh123@')
     //  cy.get("input[name='login']").click()

        cy.get("#billing_first_name").type("saurabh")
        cy.get("#billing_last_name").type("huhaha")
        cy.get("#billing_email").type("saurabh@gmail.com")
        cy.get("#billing_phone").type("9123456789")
        cy.get("p > #billing_address_1 ").type("awdaw")
        cy.get("p > #billing_city").type("asdas")
        cy.get('#select2-chosen-2').type('Chh')
        cy.get('#select2-results-2 > li > div').each(($ele,index)=>{         
            const canText=$ele.text()
            if(canText.includes('Chhattisgarh'))
            {
                cy.get('#select2-results-2 > li > div').eq(index).click()
            }
        })
        cy.get("p > #billing_postcode").type("234561")

        cy.get("#place_order").click()
       })

})

 
 //it('Login with valid user name and password', function(){
 // cy.visit(Cypress.env('baseURL'))
 // login("pardeep12@gmail.com","pardeep@123@##")
 //cy.get('#page-36 > div > div.woocommerce > ul > li').should('contains.text','is incorrect.')
 // })
 
 
