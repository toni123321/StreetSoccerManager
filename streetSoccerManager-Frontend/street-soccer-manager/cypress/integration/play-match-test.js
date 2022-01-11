describe('test play match', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000/loginPage')

        cy.get('#email').type("erick@gmail.com");
        cy.get('#password').type("Erick@12345");
        cy.get('.sign-in-btn').click();

        cy.wait(1000);

        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/account')
        })

        cy.get('.gameNavItem').click();
        cy.wait(500);
        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/game')
        })
    })
  
    it('choose oppponent - not selected opponent', () => {
        cy.get('.playMatchGameNavItem').click();
        cy.wait(500);
        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/chooseOpponent')
        })

        cy.get('.btn-next').click();
        cy.get('.choose-opponent-error-msg').should('have.text', "You haven't chosen an opponent!");

    })

    it('choose oppponent', () => {
        cy.get('.playMatchGameNavItem').click();
        cy.wait(500);
        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/chooseOpponent')
        })

        cy.get('.carousel-control-prev').click();
        cy.get('.opponent-5 > .teamToChoose > .team-name').should('have.text', "Levski");
        cy.get('.opponent-5 .team-rating').should('have.text', "OVR: 63");

        cy.get('.opponent-5').click();
        cy.get('.awayTeam .team-selected').should('have.text', "Selected - Levski");
        cy.get('.btn-next').click();
        cy.wait(200);

        cy.get('.startMatch .team-opponents').first().should('have.text', "Real Madrid-Pro");
        cy.get('.startMatch  .team-opponents').last().should('have.text', "Levski");

        cy.get('.playMatch').click();
        cy.wait(500);
        cy.get('.matchStarted > h2').should('have.text', "Match started");
        cy.get('.matchStarted > .matchResult > .team-opponents').first().should('have.text', "Real Madrid-Pro");
        cy.get('.matchStarted > .matchResult > .team-opponents').last().should('have.text', "Levski");
        cy.get('.matchStarted > .matchResult > .team-vs > span').first().should('have.text', "0");
        cy.get('.matchStarted > .matchResult > .team-vs > span').last().should('have.text', "0");
        

        
        let genArr = Array.from({length:2},(v,k)=>k+1)
        cy.wrap(genArr).each((index) => {
            cy.get('.play-match-attack-btn').click();
            cy.wait(500);
        })

        cy.get('.play-match-def-btn').click();
        cy.wait(500);

        cy.wrap(genArr).each((index) => {
            cy.get('.play-match-attack-btn').click();
            cy.wait(500);
        })

        cy.wrap(genArr).each((index) => {
            cy.get('.play-match-def-btn').click();
            cy.wait(500);
        })

        cy.get('.play-match-def-btn').click();
        cy.wait(500);
        
        cy.get('.matchEnd > h2').should('have.text', "Match ends");
    })
})