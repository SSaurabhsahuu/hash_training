const LOCATOR ={
    userLocator : "#username",
    passLocator : "#password",
    loginLocator : "input[name='login']",
    reg_userLocator : "#reg_email",
    reg_passLocator : "#reg_password",
    reg_loginLocator : "input[name='register']"
}

function login(email,password)
{   
    cy.log(email,password)
    cy.signin(LOCATOR.userLocator,LOCATOR.passLocator,LOCATOR.loginLocator,email,password) // custom 
}

function register(email,password)
{  cy.get("#menu-icon").click()
   cy.contains('My Account').click()

   if(email.length != 0)
        cy.get(LOCATOR.reg_userLocator).type(email)
    
   if(password.length != 0)
       cy.get(LOCATOR.reg_passLocator).type(password)

   cy.get(LOCATOR.reg_loginLocator).then(($btn)=>{
    if($btn.hasClass('disabled')){
        cy.get($btn).should('be.disabled')
    }
    else
    {
        cy.wrap($btn).click()
    }
})
    



}
module.exports = {
    login,
    register
}