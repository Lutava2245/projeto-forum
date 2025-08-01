import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function PostEditForm() {
    const { id } = useParams();

    const [post, setPost] = useState([]);
    const [error, setError] = useState([]);

    const title = useRef([]);
    const content = useRef([]);
    const author = useRef([]);

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

    async function update(event) {
        event.preventDefault();

        const updatedPost = {
                title: title.current.value,
                content: content.current.value,
                author: author.current.value,
            };

        
        axios.put(`http://localhost:8080/api/posts/update/${id}`, updatedPost)
            .then(response => {
                console.log(response);
                alert('Postagem atualizada com sucesso!')
            })
            .catch(error => {
                console.error("Ocorreu um erro ao atualizar postagem!", error);
                alert('Ocorreu um erro ao atualizar postagem!')
            })
    }

    return error === "Post not found" ? (
        <div className="post-detail">
            <p className="error">{error}</p>
            <Link to="/posts">Voltar para a lista de postagens</Link>
        </div>
    ) : (
        <div className="post-edit-form">
            <h1>Editar Postagem</h1>
            <form onSubmit={update}>
                <table>
                    <thead><tr><th colSpan="2">Informações da Postagem</th></tr></thead>
                    <tbody>
                        <tr>
                            <td>Título</td>
                            <td><input type="text" ref={title} maxLength="100" defaultValue={post.title} required /></td>
                        </tr>
                        <tr>
                            <td>Conteúdo</td>
                            <td><textarea ref={content} maxLength="3000" defaultValue={post.content} required /></td>
                        </tr>
                        <tr>
                            <td>Nome do Autor</td>
                            <td><input type="text" ref={author} maxLength="100" defaultValue={post.author} required /></td>
                        </tr>
                    </tbody>
                    <tfoot><tr><td colSpan="2"><button type="submit">Enviar</button></td></tr></tfoot>
                </table>
            </form>
            <Link to={`/posts/${id}`}>Voltar para postagem</Link>
        </div>
    )
}