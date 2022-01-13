describe('test team squad', () => {
    beforeEach(() => {
      cy.visit('http://localhost:3000/loginPage')
    })
  
    it('rotate players in team squad', () => {
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

        // click on team squad nav item from Game menu
        cy.get('.teamSquadGameNavItem').click();

        cy.wait(500);
        cy.location().should((loc) => { 
            expect(loc.pathname).to.eq('/teamSquad')
        })

        cy.get('.teamName').should('have.text', "Team: Real Madrid-Pro");
        cy.get('.teamManager').should('have.text', "Manager: Erick12");
        cy.get('.teamFormation').should('have.text', "Formation: 1-2-1");

        cy.get('.starting-players-row-1 > .squad-col-10 > .player').should('contain', "F.Sotoca");
        cy.get('.starting-players-row-1 > .squad-col-10 > .player').should('contain', "ST");

        cy.get('.starting-players-row-2 > .squad-col-21 > .player').should('contain', "X.Simons");
        cy.get('.starting-players-row-2 > .squad-col-21 > .player').should('contain', "RM");

        cy.get('.rotate-player-btn-5').click();

        cy.wait(500);
        cy.get('.playerForRotation').should('have.text', "F.Sotoca");
        
        cy.get('.player-4').click();
        cy.get('.playerToRotateWith > .player-4').should('contain', "X.Simons");

        cy.get('.rotate-players-btn').click();
        cy.wait(500);

        cy.get('.starting-players-row-1 > .squad-col-10 > .player').should('contain', "X.Simons");
        cy.get('.starting-players-row-1 > .squad-col-10 > .player').should('contain', "ST");

        cy.get('.starting-players-row-2 > .squad-col-21 > .player').should('contain', "F.Sotoca");
        cy.get('.starting-players-row-2 > .squad-col-21 > .player').should('contain', "RM");

    })
})