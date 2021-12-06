import React,{useState, useEffect } from 'react';
import RatingService from '../services/RatingService';
import Cookies from 'universal-cookie';

function TeamRating({id}) {
    const [rating, setRating] = useState(null);

    const [filledStars, setFilledStars] = useState(null);
    
    const [emptyStars, setEmptyStars] = useState(null);

    const cookies = new Cookies();
    const token = cookies.get('login-token');

    useEffect(() => {
        console.log(id);
        retrieveTeamRating(id);
        let nrOfFilledStars = calcStarsRating();
        setFilledStars(nrOfFilledStars);
        setEmptyStars(5 - nrOfFilledStars);
    }, [id]);

    function retrieveTeamRating(teamId) {
        RatingService.getTeamRating(teamId, token)
        .then((response) => {
            console.log(response);
            setRating(response.data);
        }, (error) => {
            console.log(error);
        });
    }

    function calcStarsRating () {
        if(rating <= 60) {
            return 1;
        }
        else if(rating > 60 && rating <= 70){
            return 2;
        }
        else if(rating > 70 && rating <= 80) {
            return 3;
        }
        else if(rating > 80 && rating <= 90) {
            return 4;
        }
        else{
            return 5;
        }
        
    }

    return (
        <div>
            <div className="team-stars-rating">                
            {[...Array(filledStars)].map((i) =>
                <i class="fas fa-star filledStar"></i>
            )}
            {[...Array(emptyStars)].map((i) =>
                <i class="fas fa-star"></i>
            )}
            </div>
            
            <div className="team-rating">OVR: {rating}</div>
        </div>
    );
}

export default TeamRating;