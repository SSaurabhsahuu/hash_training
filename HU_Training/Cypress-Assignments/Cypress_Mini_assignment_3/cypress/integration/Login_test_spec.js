/// <reference types="Cypress" />

describe('Test Login ',function(){
    let data_1;
    let data_2;
    let data_3;
    let data_4;

  before(()=>{
        cy.fixture("login_data_1").then(function(data)
        {
           data_1=data
        })
        cy.fixture("login_data_2").then(function(data)
        {
            data_2=data
        })
        cy.fixture("login_data_3").then(function(data)
        {
           data_3=data
        })
        cy.fixture("login_data_4").then(function(data)
        {
            data_4=data
        })
    })
    
    it('VALID username and valid password',function(){   // => arrow function does not recognise this.data.username
        // custom command login                               // so use function()
        cy.login(data_1.username,data_1.password)
      
        cy.url().should('include','/dashboard')
          
    })
    it('VALID username and invalid password',function(){
        cy.login(data_2.username,data_2.password)

        cy.get('input[value="LOGIN"]').next('span').should('contains.text','Invalid credentials')
          
    })
    it('INVALID username and valid password',function(){
       cy.login(data_3.username,data_3.password)

        cy.get('input[value="LOGIN"]').next('span').should('contains.text','Invalid credentials')
          
    })
    it('INVALID username and invalid password',function(){
        cy.login(data_4.username,data_4.password)

        cy.get('input[value="LOGIN"]').next('span').should('contains.text','Invalid credentials')
          
    })
    it('Empty username and empty password',function(){
        cy.login(' ',' ')    

        cy.get('input[value="LOGIN"]').next('span').should('contains.text','Invalid credentials')
          
    })
})