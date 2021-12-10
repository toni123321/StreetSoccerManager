import React, {useState, useEffect} from 'react';
import ListGroup from 'react-bootstrap/ListGroup'
import styles from "../../css/Newsfeed.scss";

function NewsFeedList({newsList}) {
    const [newsfeed, setNewsfeed] = useState([]);

    useEffect(() => {
        setNewsfeed(newsList);
    }, [])

    return (
            <ListGroup className='newsfeed-list'>
            {newsList.map((news) => (
                <ListGroup.Item>
                    <blockquote class="otro-blockquote">
                        <div className="newsfeed-item-title">{news.title}</div>
                        <div className="newsfeed-item-content">{news.content}</div>
                    </blockquote>
                </ListGroup.Item>
            ))} 
            </ListGroup>
    );
}

export default NewsFeedList;