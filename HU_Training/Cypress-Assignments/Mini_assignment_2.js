/// <reference types="Cypress" />

describe('Assignment 2',function(){

    it('Test Language',()=>{
        cy.visit('http://demo.automationtesting.in/Register.html')
        
        cy.get('div[id="msdd"]').click()
        const language=['Hindi','Japanese']    // list of language to be selected
        
        let count=0                           // counts number of language selected

        cy.get('li[class="ng-scope"]').each(($ele,index)=>{       // select languages
            
            const canText=$ele.text()                             // get text
            
            if(canText.includes(language[count]))                 // if canText matches language at index count
            {  
                cy.get('li[class="ng-scope"]').eq(index-count).click().should('contains.text',language[count])  // click & validate
                count++                                             // increase no. of selected languages

                console.log(canText," "+index)  
            }
            
            console.log(canText," ",index)
        })

    })
    it('Test Skills',()=>{
        cy.visit('http://demo.automationtesting.in/Register.html')

        const skill='Javascript'
        cy.get('#Skills').select(skill).should('have.value','Javascript')  // select skill and validata
            
    })
    it('Test Country',()=>{
        cy.visit('http://demo.automationtesting.in/Register.html')

        const country='India'

        cy.get('#countries').select(country).should('have.value', country)  // select country and validate
            
    })

})