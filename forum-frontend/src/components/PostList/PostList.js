import axios from 'axios';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function PostList() {
    const [posts, setPosts] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/api/posts')
            .then(response => {
                setPosts(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the posts!", error);
                setError("Could not load posts");
            });
    }, []);

    if (error) {
        return (
            <div className="post-list">
                <p className="error">{error}</p>
            </div>
        );
    }

    return (
        <div className="post-list">
            <h1>Lista de Postagens</h1>
            <ul>
                {posts.map(post => (
                    <li key={post.id}>
                        <Link to={`/posts/${post.id}`}>{post.title}</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}