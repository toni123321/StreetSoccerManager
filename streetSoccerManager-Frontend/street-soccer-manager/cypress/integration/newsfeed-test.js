describe('test newsfeed on home page', () => {
    beforeEach(() => {
      // Cypress starts out with a blank slate for each test
      // so we must tell it to visit our website with the `cy.visit()` command.
      // Since we want to visit the same URL at the start of all our tests,
      // we include it in our beforeEach function so that it runs before each test
      cy.visit('http://localhost:3000')
    })
  
    it('add news to newsfeed', () => {
        // check if there is one element in the newsfeed
        cy.get('.list-group-item').should('have.length', 1)

        cy.scrollTo('bottom')
        cy.get('#title').type("Win for Sevilla")
        cy.get('#content').type("Sevilla wins the first place the League Europe group stage")
        cy.get('.btn-add-news-form').click()
       
        // check for alert
        // cy.on("window:alert", (str) => {
        //       expect(str).to.equal("New news!");
        //     cy.get('[name="alert"]').click()        
        // }, false)
        
        cy.get('.list-group-item').should('have.length', 2)
        // check if new item is in the list of items
        
        // cy.get('.newsfeed-list')
        //     .contains(".list-group-item")

        //cy.get('.todo-list li').first().should('have.text', 'Pay electric bill')
        //cy.get('.todo-list li').last().should('have.text', 'Walk the dog')

        // check title equality

        // check content equality
    })
  
})
  
