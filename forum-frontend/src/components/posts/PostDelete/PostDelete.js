import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import NotFound from "../../NotFound/NotFound";

export default function PostDelete() {
    const { id } = useParams();

    const [post, setPost] = useState([]);
    const [status, setStatus] = useState([]);
    const [error, setError] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/posts/${id}`)
            .then(response => {
                setPost(response.data);
            })
            .catch(error => {
                console.error("Ocorreu um erro ao procurar post!", error);
                setError("Post not found.");
            })
    }, [id]);

    async function deletePost(event) {
        event.preventDefault();

        axios.delete(`http://localhost:8080/api/posts/delete/${id}`)
        .then(response => {
            setStatus(response.status);
            console.log(response);
            alert('Postagem foi excluida.');
        })
        .catch(error => {
            console.error('Ocoreu um erro ao excluir postagem!', error);
            setError("Could not delete post");
            alert('Ocoreu um erro ao excluir postagem!');
        })
    }
    if (error === "Post not found.") {
        return <NotFound/>;
    }

    return error === "Could not delete post" ? (
        <div className="post-delete">
            <p className="error">{error}</p>
            <Link to="/posts">Voltar para a lista de postagens</Link>
        </div>
    ) : ( status === 204 ? (
        <div className="post-delete">
            <h2>Postagem "{post.title}" foi excluída.</h2>
            <Link to="/posts">Voltar para a lista de postagens</Link>
        </div>
    ) : (
        <div className="post-delete">
            <h1>Excluir Postagem</h1>
            <div className="post-detail">
                <h2>{post.title}</h2>
                <p>{post.content}</p>
                <p><strong>Autor:</strong> {post.author}</p>
                <p><strong>Data:</strong> {new Date(post.createdAt).toLocaleDateString('pt-BR')}</p>
            </div>
            <table>
                <thead><tr><td><p>Deseja excluir a postagem?</p></td></tr></thead>
                <tbody><tr><td><button onClick={deletePost}>Excluir</button></td></tr></tbody>
                <tfoot><tr><td><Link to={`/posts/${id}`}>Voltar para postagem</Link></td></tr></tfoot>
            </table>
        </div>
    )
    )
}