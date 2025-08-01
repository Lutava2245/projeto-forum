import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

export default function PostDetail() {
    const { id } = useParams();

    const [post, setPost] = useState([]);
    const [error, setError] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/posts/${id}`)
            .then(response => {
                setPost(response.data);
            })
            .catch(error => {
                console.error("Ocorreu um erro ao procurar post!", error);
                setError("Post not found");
            })
    }, [id]);

    return error === "Post not found" ? (
        <div className="post-detail">
            <p className="error">{error}</p>
            <Link to="/posts">Voltar para a lista de postagens</Link>
        </div>
    ) : (
        <div className="post-detail">
            <h1>{post.title}</h1>
            <p>{post.content}</p>
            <p><strong>Autor:</strong> {post.author}</p>
            <p><strong>Data:</strong> {new Date(post.createdAt).toLocaleDateString('pt-BR')}</p>
            <table>
                <tbody><tr>
                    <td><Link to="/posts">Voltar para a lista de postagens</Link></td>
                    <td><Link to={`/posts/delete/${id}`}>Excluir postagem</Link></td>
                </tr></tbody>
            </table>
            
            
        </div>
    );
}