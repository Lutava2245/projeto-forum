import { Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

export default function PostDetail() {
    const { id } = useParams();
    const [post, setPost] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/posts/${id}`)
            .then(response => {
                setPost(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the post!", error);
                setError("Post not found");
            })
    }, [id]);

    if (error) {
        return (
            <div className="post-detail">
                <p className="error">{error}</p>
                <Link to="/posts">Voltar para a lista de postagens</Link>
            </div>
        );
    }

    return (
        <div className="post-detail">
            <h1>{post.title}</h1>
            <p>{post.content}</p>
            <p><strong>Autor:</strong> {post.author}</p>
            <p><strong>Data:</strong> {new Date(post.createdAt).toLocaleDateString('pt-BR')}</p>
            <Link to="/posts">Voltar para a lista de postagens</Link>
        </div>
    );
}