describe('test registration', () => {
    beforeEach(() => {
      cy.visit('http://localhost:3000/signUp')
    })
  
    it('register with already used email', () => {
        cy.get('#email').type("erick@gmail.com");
        cy.get('#nickname').type("PeterKO");
        cy.get('#firstName').type("Peter");
        cy.get('#lastName').type("Petrov");
        cy.get('#password').type("Peter@123");
        
        cy.get('.sign-up-btn').click();

        cy.wait(1000);
        cy.get('.registration-error').should(($msg) => {
            expect($msg).to.contain("Wrong registration details!");
        })
    })
    
    it('registration with correct details', () => {
        cy.get('#email').type("peter@gmail.com");
        cy.get('#nickname').type("PeterKO");
        cy.get('#firstName').type("Peter");
        cy.get('#lastName').type("Petrov");
        cy.get('#password').type("Peter@123");
        
        cy.get('.sign-up-btn').click();

        cy.wait(1000);

        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/loginPage')
        })
    })
})