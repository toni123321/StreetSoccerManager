import React, {useState} from 'react';
import styles from "../../css/AddNewsForm.scss";

function AddNewsForm({handleAddNews}) {

    const newsState = {
        title: "",
        content: ""
    }
    const [news, setNews] = useState(newsState);


    const handleInput = (e) => {
        const {name, value} = e.target;
        setNews({...news, [name]: value}); 
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        setNews(newsState);
        handleAddNews(news);
    }

    return (
        <form className="add-news-form" onSubmit={handleSubmit}>
            <div className="add-news-group">
                <label htmlFor="title" className="title">Title</label>
                <input name="title" value={news.title} id="title" type="text" className="input" onChange={handleInput}/>
            </div>
            <div className="add-news-group">
                <label htmlFor="content" className="content">Content</label>
                <textarea id="content" value={news.content} name="content" rows="4" cols="50" onChange={handleInput}>
                </textarea>
            </div>
            <div className="add-news-group">
                <input type="submit" className="button btn-add-news-form" value="Publish news"/>
            </div>
        </form>
    );
}

export default AddNewsForm;