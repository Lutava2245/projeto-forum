import axios from 'axios';
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

export default function PostList() {
    const [posts, setPosts] = useState([]);
    const [error, setError] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/posts`)
            .then(response => {
                setPosts(response.data);
            })
            .catch(error => {
                console.error("Ocoreu um erro ao procurar postagens!", error);
                setError("Could not load posts");
            });
    }, []);

    return error === "Could not load posts" ? (
        <div className="post-list">
            <p className="error">{error}</p>
        </div>
    ) : (
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