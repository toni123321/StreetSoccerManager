import React,{useState, useEffect } from 'react';

function TeamRating({rating}) {
    const [filledStars, setFilledStars] = useState(null);
    
    const [emptyStars, setEmptyStars] = useState(null);

    useEffect(() => {
        let nrOfFilledStars = calcStarsRating();
        setFilledStars(nrOfFilledStars);
        setEmptyStars(5 - nrOfFilledStars);
    }, []);


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