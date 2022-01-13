import React, {useState, useEffect} from "react";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import AddNewsForm from "./AddNewsForm";
import NewsFeedList from "./NewsFeedList";
import axios from 'axios';


// Set the backend location
const ENDPOINT = "http://localhost:8080/ws";

function Home() {
    const [stompClient, setStompClient] = useState(null);
    const [newsList, setNewsList] = useState([]);


    useEffect(() => {
        // use SockJS as the websocket client
        const socket = SockJS(ENDPOINT);
        // Set stomp to use websockets
        const stompClient = Stomp.over(socket);
        // connect to the backend
        stompClient.connect({}, () => {
        // subscribe to the backend
        stompClient.subscribe('/topic/newsfeed', (data) => {
            console.log(data);
            onMessageReceived(data);
        });
        });
        // maintain the client for sending and receiving
        setStompClient(stompClient);
        retrieveNewsFeed();
    }, []);



    function retrieveNewsFeed() {
        axios.get('http://localhost:8080/newsfeed')
        .then((response) => {
            setNewsList(response.data)
        })
        .catch((e) => console.log(e))
    }

    // send the data using Stomp
    // function sendMessage() {
    //     stompClient.send("/app/newsfeed/addNews", {}, JSON.stringify({'name': msgToSend}));

    // };

    // display the received data
    function onMessageReceived(data)
    {
        const result = JSON.parse(data.body);
        setNewsList(newsList => [...newsList, result]);
        //alert("New news!");
        console.log("Newsfeed", result);
        console.log("NewsList", newsList);
    };

    const stopSubscription = () => {
        if (stompClient !== null) {
        stompClient.disconnect();
    }
    
    console.log("Disconnected");
    }

    function handleAddNews(news) {
        //console.log(news);
        stompClient.send("/app/addNews", {}, JSON.stringify({'title': news.title, 'content': news.content}));
    }

    return (
        <div className="newsfeed">
            <NewsFeedList newsList={newsList}/>
            <AddNewsForm handleAddNews={handleAddNews}/>
            {/* <button onClick={stopSubscription}>Stop subscription</button> */}
        </div>
    );
}
export default Home;