/// <reference types="Cypress" />
describe('Test Swag labs', function() {

    it('VERIFY url and login', function(){

        cy.visit('https://www.saucedemo.com/')

        cy.url().should('eq','https://www.saucedemo.com/')
       cy.get('input[id="login-button"]').click()
        cy.get('div[class*="error"] > h3').should('contains.text','Epic sadface: Username is required')

    })
    it('VERIFY login without password ', function(){

        cy.visit('https://www.saucedemo.com/')
        cy.get('#user-name').type('standard_user')

        cy.get('input[id="login-button"]').click()
        cy.get('div[class*="error"] > h3').should('contains.text','Epic sadface: Password is required')

    })

    it('VERIFY Homepage ', function(){

        cy.visit('https://www.saucedemo.com/')

        cy.get('#user-name').type('standard_user')
        cy.get('#password').type('secret_sauce')

        cy.get('input[id="login-button"]').click()

        // validate total 6 items in inventory 
        cy.url().should('eq','https://www.saucedemo.com/inventory.html')
        cy.get('.inventory_item').should('have.length', 6)

        // validate filter button
        cy.get('.select_container').click()

        cy.get('select.product_sort_container > option:nth-of-type(1)').should('contains.text','Name (A to Z)')
        cy.get('select.product_sort_container > option:nth-of-type(2)').should('contains.text','Name (Z to A)')
        cy.get('select.product_sort_container > option:nth-of-type(3)').should('contains.text','Price (low to high)')
        cy.get('select.product_sort_container > option:nth-of-type(4)').should('contains.text','Price (high to low)')

    })
})