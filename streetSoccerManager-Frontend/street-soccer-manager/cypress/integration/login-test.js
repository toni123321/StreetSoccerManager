describe('test login', () => {
    beforeEach(() => {
      cy.visit('http://localhost:3000/loginPage')
    })
  
    it('login with incorrect credentials', () => {
        cy.get('#email').type("unknown@gmail.com");
        cy.get('#password').type("unknown@123");
        cy.get('.sign-in-btn').click();

        cy.wait(1000);
        cy.get('.login-error').should(($msg) => {
            expect($msg).to.contain("Wrong credentials");
        })
    })
    
    it('login with correct credentials', () => {
        cy.get('#email').type("erick@gmail.com");
        cy.get('#password').type("Erick@12345");
        cy.get('.sign-in-btn').click();

        cy.wait(1000);

        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/account')
        })
    })
})